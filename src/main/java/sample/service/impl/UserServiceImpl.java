package sample.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import sample.model.User;
import sample.repository.UserRepository;
import sample.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new RuntimeException(String.format("User %s not found", username));
        }
        return user.get();
    }

}
