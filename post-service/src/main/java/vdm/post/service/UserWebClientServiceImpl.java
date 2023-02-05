package vdm.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import vdm.post.domen.dto.UserDto;

@Component
public class UserWebClientServiceImpl implements UserWebClientService {

  @Autowired
  @Qualifier("userWebClient")
  private WebClient usersWebClient;

  public UserDto updateUser(UserDto userDto) {
    return usersWebClient
        .put()
        .uri("/users/{id}", userDto.userId())
        .body(BodyInserters.fromValue(userDto))
        .retrieve()
        .bodyToMono(UserDto.class)
        .block();
  }
}
