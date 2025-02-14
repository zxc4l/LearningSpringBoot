package com.example.demodto.service;

import com.example.demodto.dto.ProductDTO;
import com.example.demodto.dto.RoleDTO;
import com.example.demodto.model.Product;
import com.example.demodto.model.Role;
import com.example.demodto.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    // Lấy tất cả roles  và chuyển đổi sang DTO
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Tạo mới role
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        role = roleRepository.save(role);
        return convertToDTO(role);
    }

    // Xóa role theo ID
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }

    // Chuyển đổi Role sang RoleDTO
    private RoleDTO convertToDTO(Role role) {
        return new RoleDTO(role.getName());
    }

}
