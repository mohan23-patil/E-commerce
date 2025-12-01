package com.ecommerce.Backend.service;

import com.ecommerce.Backend.entity.Product;
import com.ecommerce.Backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final String UPLOAD_DIR = "uploads/";

    @Override
    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        // Debug logs
        System.out.println("Adding product: " + product.getName());

        // Handle image
        if (imageFile != null && !imageFile.isEmpty()) {
            File uploadFolder = new File(UPLOAD_DIR);
            if (!uploadFolder.exists()) {
                boolean created = uploadFolder.mkdirs();
                System.out.println("Uploads folder created: " + created);
            }

            String filename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            File dest = new File(uploadFolder, filename);

            try {
                imageFile.transferTo(dest);
                product.setImage("/uploads/" + filename);
                System.out.println("Image saved: " + filename);
            } catch (IOException e) {
                System.err.println("Failed to save image: " + e.getMessage());
                throw e;
            }
        }

        try {
            Product saved = productRepository.save(product);
            System.out.println("Product saved with ID: " + saved.getId());
            return saved;
        } catch (Exception e) {
            System.err.println("Error saving product: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Product exitProduct = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not Found Here!"));

        exitProduct.setName(product.getName());
        exitProduct.setDescription(product.getDescription());
        exitProduct.setPrice(product.getPrice());
        exitProduct.setStock(product.getStock());
        exitProduct.setCategory(product.getCategory());
        exitProduct.setImage(product.getImage());

        if (product.getImage() != null && !product.getImage().isEmpty()) {
            exitProduct.setImage(product.getImage());
        }
        return productRepository.save(exitProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
