package vdm.user.controller.api;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import vdm.user.domen.User;
import vdm.user.domen.dto.UserDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserApi {

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400",
          description = "Validation error or request body is an invalid"),
      @ApiResponse(responseCode = "500", description = "Internal server error occurred")})
  User createUser(@RequestBody UserDto dto);

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "404", description = "User doesn’t exist with given id"),
      @ApiResponse(responseCode = "500", description = "Internal server error occurred")})
  User findUser(Long id);

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "404", description = "Not Found"),
      @ApiResponse(responseCode = "500", description = "Internal server error occurred")})
  void deleteUser(Long id);

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok"),
      @ApiResponse(responseCode = "400",
          description = "Validation error or request body is an invalid"),
      @ApiResponse(responseCode = "404", description = "User doesn’t exist with given id"),
      @ApiResponse(responseCode = "500", description = "Internal server error occurred")})
  User updateUser(Long id, UserDto userDto) throws Exception;
}
