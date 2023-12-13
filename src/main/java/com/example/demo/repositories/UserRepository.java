package com.example.demo.repositories;

import com.example.demo.domain.UserObject;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserObject, Long> {

    UserObject findByUserId(long userId);

    Optional<UserObject> findByUserName(String username);

    Optional<UserObject> findByEmail(String email);
}
