package com.nikhilzzz.ecommerce.SB_Ecom.service;

import com.nikhilzzz.ecommerce.SB_Ecom.Repository.CategoryRepo;
import com.nikhilzzz.ecommerce.SB_Ecom.exception.APIException;
import com.nikhilzzz.ecommerce.SB_Ecom.exception.MyGlobalExceptionHandler;
import com.nikhilzzz.ecommerce.SB_Ecom.exception.ResourceNotFoundException;
import com.nikhilzzz.ecommerce.SB_Ecom.model.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private MyGlobalExceptionHandler myGlobalExceptionHandler;

    private List<Category> categories;//This will cause NPE as the Repo is being called before instantiation = categoryRepo.findAll();
    //private long nextId = 1L;

    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
    }


    @Override
    public List<Category> getAllCategories() {
        if(categories.isEmpty()) {
            throw new APIException("Category List is Empty");
        }
        return categories;
    }

    @Override
    public String deleteCategory(long categoryId) {
        //categoryRepo.deleteById(categoryId); RISKY as no output if ID not present

        if(!categoryRepo.existsById(categoryId)){
            throw new ResourceNotFoundException("Category","CategoryId",categoryId);
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
    public String createCategory(Category category) {
        //category.setCategoryId(nextId++);
        String savedCategoryName= categoryRepo.getByCategoryName(category.getCategoryName());
        if (savedCategoryName != null){
            throw new APIException("Category with name "+category.getCategoryName()+" already exists");
        }
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

                .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));
    }
}
