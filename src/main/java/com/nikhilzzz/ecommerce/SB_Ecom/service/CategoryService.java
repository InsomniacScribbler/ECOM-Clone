package com.nikhilzzz.ecommerce.SB_Ecom.service;

import com.nikhilzzz.ecommerce.SB_Ecom.Repository.CategoryRepo;
import com.nikhilzzz.ecommerce.SB_Ecom.model.Category;

import java.util.List;


public interface CategoryService {


    List<Category> getAllCategories();
    String createCategory(Category category);
    String deleteCategory(long categoryId);
    String updateCategory(long categoryId,Category updatedCategory);
}
