package com.example.springmysql2.controller;

import com.example.springmysql2.model.User;
import com.example.springmysql2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    private  final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // api lay danh sach tat ca nguoi dung
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    //api lay nguoi dung theo ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    //Tao nguoi dung moi
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    //Cap nhat theo ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user){
        return  ResponseEntity.ok(userService.updateUser(id, user));
    }

    //Xoa nguoi dung theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //Lay danh sach nguoi dung theo gioi tinh
    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<User>> getUserByGender(@PathVariable String gender){
        return ResponseEntity.ok(userService.getUsersByGender(User.Gender.valueOf(gender.toUpperCase())));
    }

    //Tim kiem nguoi dung theo tu khoa
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsersByName(@RequestParam String keyword){
        System.out.println("Tìm kiếm với từ khóa: " + keyword);
        return ResponseEntity.ok(userService.searchUsersByName(keyword));
    }
}
