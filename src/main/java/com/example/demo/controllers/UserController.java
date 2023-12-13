package com.example.demo.controllers;

import com.example.demo.DTO.User.UserDTO;
import com.example.demo.DTO.User.UserUpdateDTO;
import com.example.demo.controllers.exceptions.UserNotFoundException;
import com.example.demo.domain.UserObject;
import com.example.demo.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin
@Validated
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new user")
    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserObject userObject) {
        userService.createUser(userObject);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDTO.convertToDTO(userObject));
    }

    @Operation(summary = "Get a user by its id")
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@Parameter(description = "The user id") @PathVariable @Min(1) long id) throws UserNotFoundException {
        UserObject userObject = userService.getUserById(id);
        return ResponseEntity.ok(UserDTO.convertToDTO(userObject));
    }

    @Operation(summary = "Get a user by email")
    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam(name = "email") @Email String email) throws UserNotFoundException{
        System.out.println("Searching for user with email {}" + email);
        Optional<UserObject> optionalUser = userService.getUserByEmail(email);
        UserObject userObject = optionalUser.orElseThrow(() -> new UserNotFoundException(email));
        return ResponseEntity.ok(UserDTO.convertToDTO(userObject));
    }

    @Operation(summary = "Update a user by its id")
    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@Parameter(description = "The user id") @PathVariable("id") @Min(1) long id, @Valid @RequestBody UserUpdateDTO updatedUser) throws UserNotFoundException {
        var result = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(UserDTO.convertToDTO(result));
    }

    @Operation(summary = "Delete a user by its id")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDTO> deleteUser(@Parameter(description = "The user id") @PathVariable("id") @Min(1) long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
