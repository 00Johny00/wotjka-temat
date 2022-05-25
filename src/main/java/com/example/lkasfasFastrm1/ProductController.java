package com.example.lkasfasFastrm1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private static  final List<Product> PRODUCTS = Arrays.asList(
            new Product(1l,"Tv Samsung","Tv dla wymagających",false),
			new Product(2l,"Tv QLED","Tv dla NIE wymagających",false),
			new Product(3l,"Tv SHARP","Tv dla wymagających",false)
    );

    @GetMapping("/fill")
    public void updateDb(){
        PRODUCTS.forEach(product -> productRepository.save(product));
    }


    @GetMapping("/test/{productId}")
    public String getProduct(@PathVariable("productId") Long productId){
        /*return PRODUCTS.stream()
                .filter(product -> productId.equals(product.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No such product with such id: " + productId));*/
    return productRepository.findById(productId).toString();
    }
    @GetMapping("/products")
    public String getAllProducts(){
        return productRepository.findAll().toString();
    }

    @GetMapping("/products/page/{id}")
    public String getAllProducts(@PathVariable("id") int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 100);
        Page page = productRepository.findAll(pageable);
        System.out.println(page.getContent());
        return String.valueOf(productRepository.findAll(pageable).getContent());
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable("id") Long id){
        productRepository.deleteById(id);
    }

    @GetMapping("/products/{id}")
    public Optional<Product> findProductById(@PathVariable("id") Long id){
        return productRepository.findById(id);
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product){
        System.out.println("Created produt : " + product);
        productRepository.save(product);
    }
}
