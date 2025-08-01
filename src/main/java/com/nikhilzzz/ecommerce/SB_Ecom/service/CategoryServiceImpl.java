package com.nikhilzzz.ecommerce.SB_Ecom.service;

import com.nikhilzzz.ecommerce.SB_Ecom.Payload.CategoryDto;
import com.nikhilzzz.ecommerce.SB_Ecom.Payload.CategoryResponse;
import com.nikhilzzz.ecommerce.SB_Ecom.Repository.CategoryRepo;
import com.nikhilzzz.ecommerce.SB_Ecom.exception.APIException;
import com.nikhilzzz.ecommerce.SB_Ecom.exception.MyGlobalExceptionHandler;
import com.nikhilzzz.ecommerce.SB_Ecom.exception.ResourceNotFoundException;
import com.nikhilzzz.ecommerce.SB_Ecom.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private MyGlobalExceptionHandler myGlobalExceptionHandler;

    @Autowired
    private ModelMapper modelMapper;

    private CategoryResponse categories;//This will cause NPE as the Repo is being called before instantiation = categoryRepo.findAll();
    //private long nextId = 1L;




    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        if(categories.isEmpty()) {
            throw new APIException("Category List is Empty");
        }
        List<CategoryDto> categoryDtos = categories.stream().map(c -> modelMapper.map(c, CategoryDto.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDtos);
        return categoryResponse;
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
    public String createCategory(CategoryDto category) {
        //category.setCategoryId(nextId++);
        Category existingCategory = categoryRepo.getByCategoryName(category.getCategoryName()); //getting the name of the category and checking if it exists already
        if(existingCategory!=null){ throw new APIException("Category Already Exists");}

        Category newCategory = modelMapper.map(category,Category.class); // mapping the Dto object to category object so as to write in the category entiry table

        categoryRepo.save(newCategory); //saving
        return "Category Created successfully!!"; //returning

        /*
        *
        * return categories.removeIf(c -> c.getCategoryId() == id)
            ? "Deleted"
            : throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        * */
    }

    @Override
    public String updateCategory(long categoryId,CategoryDto updatedCategory) throws APIException {
        String existingCategoryName =categoryRepo.findById(categoryId).orElseThrow(() -> new APIException("Id Invalid!!!")).getCategoryName();
//        if(existingCategoryName.equals(updatedCategory.getCategoryName()) && !categoryRepo.existsById(categoryId)){
//            throw new APIException("Category with the name Already Exists");
//        }
        // Check if another category with the same name exists
        Category duplicateCategory = categoryRepo.getByCategoryName(updatedCategory.getCategoryName());
        if (duplicateCategory != null && duplicateCategory.getCategoryId() != categoryId) {
            throw new APIException("Category with the name '" + updatedCategory.getCategoryName() + "' already exists");
        }
        Category categorytoUpdate = categoryRepo.getById(categoryId);
        categorytoUpdate.setCategoryName(updatedCategory.getCategoryName());
        categoryRepo.save(categorytoUpdate);
        return "Category Updated successfully!!";
    }
}

