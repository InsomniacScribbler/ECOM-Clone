package com.nikhilzzz.ecommerce.SB_Ecom.Repository;

import com.nikhilzzz.ecommerce.SB_Ecom.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    String getByCategoryName(@NotBlank(message = "Category needs to have a Name") @Size(min = 5, message = "Category Name to be greater than 5 chars") String categoryName);
}
