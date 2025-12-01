package com.ecommerce.Backend.service;

import com.ecommerce.Backend.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Product addProduct(Product product, MultipartFile imageFile) throws IOException;
    List<Product> getAllProducts();
    Product updateProduct(Product products,Long id);
    void deleteProduct(Long id);
}
