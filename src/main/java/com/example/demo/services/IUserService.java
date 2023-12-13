package com.example.demo.services;

import com.example.demo.DTO.User.UserUpdateDTO;
import com.example.demo.controllers.exceptions.UserNotFoundException;
import com.example.demo.domain.UserObject;

import java.util.Optional;

public interface IUserService {

    /**
     * creates a user
     * @param userObject
     */
    void createUser(UserObject userObject);

    /**
     * Gets a user by its ID
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    UserObject getUserById(Long id) throws UserNotFoundException;

    /**
     * Updates a user by its ID
     * @param id
     * @param updatedUser
     * @return
     * @throws UserNotFoundException
     */
    UserObject updateUser(Long id, UserUpdateDTO updatedUser) throws UserNotFoundException;

    /**
     * deletes a user by its ID
     * @param id
     * @throws UserNotFoundException
     */
    void deleteUser(Long id) throws UserNotFoundException;

    /**
     * Gets a user by its email
     * @param email
     * @return
     * @throws UserNotFoundException
     */
    Optional<UserObject> getUserByEmail(String email) throws UserNotFoundException;

}
