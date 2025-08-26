package IETI.ada.service;

import IETI.ada.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InMemoryUserService implements UserService {

    private final Map<String, User> data = new HashMap<>();

    @Override
    public List<User> getAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<User> getById(String id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public User create(User user) {
        String id = (user.getId() == null || user.getId().isBlank())
                ? UUID.randomUUID().toString()
                : user.getId();
        user.setId(id);
        data.put(id, user);
        return user;
    }

    @Override
    public Optional<User> update(String id, User user) {
        if (!data.containsKey(id)) return Optional.empty();
        User existing = data.get(id);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        data.put(id, existing);
        return Optional.of(existing);
    }

    @Override
    public boolean delete(String id) {
        return data.remove(id) != null;
    }
}
