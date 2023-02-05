package vdm.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vdm.user.domen.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
