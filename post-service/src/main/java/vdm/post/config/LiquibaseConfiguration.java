package vdm.post.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Liquibase configuration.
 */
@Slf4j
@Configuration
public class LiquibaseConfiguration {

  @Value("${spring.datasource.name}")
  private String databaseName;

  @Value("${spring.datasource.host}")
  private String databaseHost;

  @Bean
  public LiquibaseProperties liquibaseProperties() {
    return new LiquibaseProperties();
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.schemas")
  public List<String> schemas() {
    return new ArrayList<>();
  }

  @Bean(name = "initDataSource")
  public DataSource initDataSource(DataSourceProperties dataSourceProperties) {
    var dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName(dataSourceProperties.getDriverClassName());
    dataSourceBuilder.url("jdbc:postgresql://" + databaseHost + ":5432/postgres");
    dataSourceBuilder.username(dataSourceProperties.getUsername());
    dataSourceBuilder.password(dataSourceProperties.getPassword());
    return dataSourceBuilder.build();
  }

  @Bean
  @Primary
  public DataSource dataSource(DataSourceProperties dataSourceProperties) {
    var dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName(dataSourceProperties.getDriverClassName());
    dataSourceBuilder.url(dataSourceProperties.getUrl());
    dataSourceBuilder.username(dataSourceProperties.getUsername());
    dataSourceBuilder.password(dataSourceProperties.getPassword());
    return dataSourceBuilder.build();
  }

  /**
   * Liquibase bean.
   */
  @Bean
  public SpringLiquibase liquibase(LiquibaseProperties liquibaseProperties,
                                   DataSource dataSource,
                                   @Qualifier("initDataSource") DataSource initDataSource) {
    var liquibase = new SpringLiquibase();
    BeanUtils.copyProperties(liquibaseProperties, liquibase);
    liquibase.setChangeLogParameters(liquibaseProperties.getParameters());
    liquibase.setDataSource(dataSource);

    try (var baseConnection = initDataSource.getConnection();
         var baseStatement = baseConnection.createStatement()) {
      baseStatement.execute(String.format("CREATE DATABASE %s", databaseName));
      log.info("{} database was created or was exists", databaseName);
    } catch (SQLException e) {
      log.info(e.getMessage());
    }

    try (var connection = dataSource.getConnection();
         var statement = connection.createStatement()) {
      for (var schemaName : schemas()) {
        statement.execute(String.format("CREATE SCHEMA IF NOT EXISTS %s", schemaName));
        log.info("{} schema was created or was exists", schemaName);
      }
    } catch (SQLException e) {
      log.error("Create database error", e);
    }
    return liquibase;
  }
}
