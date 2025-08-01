package com.nikhilzzz.ecommerce.SB_Ecom.service;

import com.nikhilzzz.ecommerce.SB_Ecom.Payload.CategoryDto;
import com.nikhilzzz.ecommerce.SB_Ecom.Payload.CategoryResponse;
import com.nikhilzzz.ecommerce.SB_Ecom.Repository.CategoryRepo;
import com.nikhilzzz.ecommerce.SB_Ecom.exception.APIException;
import com.nikhilzzz.ecommerce.SB_Ecom.model.Category;

import java.util.List;


public interface CategoryService {


    CategoryResponse getAllCategories();
    String createCategory(CategoryDto category);
    String deleteCategory(long categoryId);



    String updateCategory(long categoryId, CategoryDto updatedCategory) throws APIException;
}
