package com.example.springmysql2.repository;


import com.example.springmysql2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
        // Tim kiem nguoi dung theo email
        Optional<User> findByEmail(String email);
        // Tim danh sach nguoi dung theo gioi tinh
        List<User> findByGender(User.Gender gender);
        // Kiem tra email da ton tai chua
        boolean existsByEmail(String email);
        // Tim nguoi dung co ten chua mot tu khoa bat ky
        List<User> findByNameContainingIgnoreCase(String keyword);

}
