package com.leo.bootcampglobant.finalshoppingcart.repositories;

import com.leo.bootcampglobant.finalshoppingcart.models.Category;
import com.leo.bootcampglobant.finalshoppingcart.models.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findByCategory(Category category);
  List<Product> findByNameContaining(String name);
  Optional<Product> findByName(String name);

}
