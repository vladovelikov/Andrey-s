package com.example.eventsdemo.custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final AddProductPublisher productPublisher;
    private int productCounter = 0;

    @Autowired
    public ProductController(AddProductPublisher productPublisher) {
        this.productPublisher = productPublisher;
    }

    @GetMapping("/add-product")
    public ResponseEntity<String> addProduct() {
        productPublisher.publishEvent("Product" + (++productCounter));
        return ResponseEntity.ok().build();
    }
}
