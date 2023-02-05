package vdm.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vdm.user.controller.exception.PersistenceException;
import vdm.user.domen.User;
import vdm.user.domen.dto.UserDto;
import vdm.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public User createUser(UserDto dto) {
    User user = new User();
    user.setUsername(dto.getUsername());
    user.setAmountOfPosts("0");

    return userRepository.save(user);
  }

  public User findUser(Long id) {

    return userRepository.findById(id)
        .orElseThrow(() -> new PersistenceException("User doesn’t exist with given id"));
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public User updateUser(Long id, String newName, String amountOfPosts) {

    User user = userRepository.findById(id)
        .map(it -> {
          if (newName != null) {
            it.setUsername(newName);
          }
          if (amountOfPosts != null) {
            it.setAmountOfPosts(amountOfPosts);
          }
          return it;
        })
        .orElseThrow(() -> new PersistenceException("User doesn’t exist with given id"));

    return userRepository.save(user);
  }

}
