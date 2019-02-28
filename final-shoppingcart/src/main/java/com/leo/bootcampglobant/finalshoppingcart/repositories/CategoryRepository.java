package com.leo.bootcampglobant.finalshoppingcart.repositories;

import com.leo.bootcampglobant.finalshoppingcart.models.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findByName(String name);

}
