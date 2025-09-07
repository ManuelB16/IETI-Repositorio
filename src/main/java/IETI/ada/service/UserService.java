package IETI.ada.service;

import IETI.ada.model.User;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    List<User> getAll();
    Optional<User> getById(String id);
    User create(User user);
    Optional<User> update(String id, User user);
    boolean delete(String id);
    UserDetails loadUserByUsername(String username);
    User save(User user);
}