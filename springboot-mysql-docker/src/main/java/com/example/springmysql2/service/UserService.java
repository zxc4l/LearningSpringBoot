package com.example.springmysql2.service;

import com.example.springmysql2.model.User;
import com.example.springmysql2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Lay danh sach tat ca nguoi dung
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    //Lay nguoi dung theo Id
    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    //Tao nguoi dung moi
    public  User createUser(User user){
        if( userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email da ton tai");
        }
        user.setCreatedAt(Instant.now());
        return userRepository.save(user);
    }


    // Cap nhat thong tin nguoi dung
    public User updateUser(int id, User newUser){
        return userRepository.findById(id).map(user-> {
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setAddress(newUser.getAddress());
            user.setDateOfBirth(newUser.getDateOfBirth());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setGender(newUser.getGender());
            user.setPhoneNumbers(newUser.getPhoneNumbers());
            user.setUpdatedAt(Instant.now());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Khong tim thay User voi ID:" + id) );
    }

    //Xoa nguoi dung theo id
    public void deleteUser(int id){
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Khong tim thay User de xoa");
        }
        userRepository.deleteById(id);
    }

    // Lay danh sach User theo gioi tinh
    public List<User> getUsersByGender(User.Gender gender){
        return userRepository.findByGender(gender);
    }
    //Tim kiem user theo tu khoa trong ten
    public List<User> searchUsersByName(String keyword){
        return userRepository.findByNameContainingIgnoreCase(keyword);
    }
}
