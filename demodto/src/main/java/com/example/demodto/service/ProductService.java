package com.example.demodto.service;

import com.example.demodto.dto.ProductDTO;
import com.example.demodto.model.Product;
import com.example.demodto.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // Lấy tất cả danh sách products chuyển đổi sang DTO
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Tạo mới sản phẩm
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product = productRepository.save(product);
        return new ProductDTO(product.getName(), product.getPrice());
    }

    // Cập nhật thông tin product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product = productRepository.save(product);
        return convertToDTO(product);
    }

    // Xóa product theo ID
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    // Chuyển đổi Product sang ProductDTO
    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product.getName(), product.getPrice());
    }
}
