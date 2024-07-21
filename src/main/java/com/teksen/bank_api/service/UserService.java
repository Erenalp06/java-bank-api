package com.teksen.bank_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teksen.bank_api.entity.User;
import com.teksen.bank_api.exception.custom.UserNotFoundException;
import com.teksen.bank_api.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
