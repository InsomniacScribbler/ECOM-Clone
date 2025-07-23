package com.nikhilzzz.ecommerce.SB_Ecom.service;

import com.nikhilzzz.ecommerce.SB_Ecom.Repository.CategoryRepo;
import com.nikhilzzz.ecommerce.SB_Ecom.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    private List<Category> categories = categoryRepo.findAll();
    //private long nextId = 1L;



    @Override
    public List<Category> getAllCategories() {

        return categories;
    }

    @Override
    public String createCategory(Category category) {
        //category.setCategoryId(nextId++);
        categoryRepo.save(category);
        return "Category created";

        /*
        *
        * return categories.removeIf(c -> c.getCategoryId() == id)
            ? "Deleted"
            : throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        * */
    }

    @Override
    public String deleteCategory(long categoryId) {
        //categoryRepo.deleteById(categoryId); RISKY as no output if ID not present

        if(!categoryRepo.existsById(categoryId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category ID not found");
        }
        categoryRepo.deleteById(categoryId);


        /*
        * can also be written using streams
        *
        * Optional<Category> categoryToDelete = categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst();
        * categoryToDelete.ifPresent(Categories::remove)
        *                       ----OR----
        * Category categoryToDelete  = categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst()
         * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
         * categories.remove(categoryToDelete)
        *
        *
        * */

        return categoryId+" Deleted successfully!!";
    }

    @Override
    public String updateCategory(long categoryId,Category updatedCategory) {
        return categoryRepo
                .findById(categoryId)
                .map(categoryToUpdate ->{
                                                    categoryToUpdate.setCategoryName(
                                                            updatedCategory.getCategoryName()
                                                    );

                                                    categoryRepo.save(categoryToUpdate);
                                                    return "Category Updated Successfully!!!";
                })

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Id!!!"));
    }
}
