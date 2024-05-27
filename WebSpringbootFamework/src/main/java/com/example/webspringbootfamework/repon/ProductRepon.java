package com.example.webspringbootfamework.repon;

import com.example.webspringbootfamework.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepon extends JpaRepository<Product, Long> {

}
