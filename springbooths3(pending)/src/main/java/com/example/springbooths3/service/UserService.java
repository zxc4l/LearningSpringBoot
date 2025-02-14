package com.example.springbooths3.service;

import com.example.springbooths3.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Integer id);
    User updateUser(Integer id, User user);
    void deleteUser(Integer id);
    Page<User> searchUsers(String keyword, Pageable pageable);

}