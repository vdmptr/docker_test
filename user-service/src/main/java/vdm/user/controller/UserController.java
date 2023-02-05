package vdm.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vdm.user.controller.api.UserApi;
import vdm.user.domen.User;
import vdm.user.domen.dto.UserDto;
import vdm.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

  private final UserService userService;

  @PostMapping("/users")
  public User createUser(UserDto userDto) {
    return userService.createUser(userDto);
  }

  @GetMapping("/users/{id}")
  public User findUser(@PathVariable Long id) {
    return userService.findUser(id);
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }

  @PutMapping("/users/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
    return userService.updateUser(id, userDto.getUsername(), userDto.getAmountOfPosts());
  }
}
