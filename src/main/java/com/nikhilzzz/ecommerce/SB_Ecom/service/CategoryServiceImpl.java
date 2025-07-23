package com.nikhilzzz.ecommerce.SB_Ecom.service;

import com.nikhilzzz.ecommerce.SB_Ecom.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {


    private List<Category> categories = new ArrayList<>();
    private long nextId = 1L;
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public String createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
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
        categories.removeIf(category -> category.getCategoryId() == categoryId);

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
        return categories.stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst()
                .map(categoryToUpdate ->{ categoryToUpdate.setCategoryName(updatedCategory.getCategoryName());
                                                    return "Category Updated Successfully!!!";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Id!!!"));
    }
}
