package com.firstboot.apirest.repository;

import com.firstboot.apirest.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {
  Products findById(long id);
}
