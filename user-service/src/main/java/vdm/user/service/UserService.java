package vdm.user.service;

import vdm.user.domen.User;
import vdm.user.domen.dto.UserDto;

public interface UserService {

  User createUser(UserDto dto);

  User findUser(Long id);

  void deleteUser(Long id);

  User updateUser(Long id, String newName, String amountOfPosts);
}
