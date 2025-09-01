package IETI.ada.service;

import IETI.ada.model.User;
import IETI.ada.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  // Para que Spring la detecte como un bean
public class MongoUserService implements UserService {

    private final UserRepository userRepository;

    // Spring injectará la instancia del repositorio
    public MongoUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> update(String id, User user) {
        return userRepository.findById(id).map(existing -> {
            existing.setName(user.getName());
            existing.setEmail(user.getEmail());
            return userRepository.save(existing);
        });
    }

    @Override
    public boolean delete(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}