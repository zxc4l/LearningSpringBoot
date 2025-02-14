package com.example.demodto.service;

import com.example.demodto.dto.UserDTO;
import com.example.demodto.model.Role;
import com.example.demodto.model.User;
import com.example.demodto.repository.RoleRepository;
import com.example.demodto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // Lấy danh sách tất cả người dùng
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Tạo mới người dùng
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        setRolesForUser(user, userDTO.getRoles());
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    // Cập nhật người dùng
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        setRolesForUser(user, userDTO.getRoles());
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    // Xóa người dùng
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    // Phương thức xử lý roles
    private void setRolesForUser(User user, Set<String> roleNames) {
        Set<Role> roles = roleNames.stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());
        user.setRoles(roles);
    }

    // Chuyển đổi User sang UserDTO
    private UserDTO convertToDTO(User user) {
        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        return new UserDTO(user.getUsername(), user.getEmail(), roleNames);
    }
}