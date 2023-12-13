package com.example.demo.services;

import com.example.demo.DTO.User.UserUpdateDTO;
import com.example.demo.controllers.exceptions.UserNotFoundException;
import com.example.demo.domain.UserObject;
import com.example.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void createUser(UserObject userObject) {
        userRepository.save(userObject);
    }

    @Override
    public UserObject getUserById(Long id) throws UserNotFoundException {
        var usr = userRepository.findByUserId(id);
        if (usr == null) {
            throw new UserNotFoundException(id);
        }
        return usr;
    }

    @Override
    public UserObject updateUser(Long id, UserUpdateDTO updatedUser) throws UserNotFoundException {
        UserObject existingUser = userRepository.findByUserId(id);

        if (existingUser == null) {
            throw new UserNotFoundException(id);
        }

        if (notNullOrEmpty(updatedUser.getUserName())){
            existingUser.setUserName(updatedUser.getUserName());
        }
        if (notNullOrEmpty(updatedUser.getEmail())){
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (notNullOrEmpty(updatedUser.getPassword())){
            existingUser.setPassword(updatedUser.getPassword());
        }
        return null;
    }

    private boolean notNullOrEmpty(String str) {
        return !(str == null || str.isEmpty());
    }

    @Override
    @Transactional
    public void deleteUser(Long id) throws UserNotFoundException {
        var usr = userRepository.findByUserId(id);
        if (usr == null) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserObject> getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email);
    }
}
