package com.nikhilzzz.ecommerce.SB_Ecom.controller;


import com.nikhilzzz.ecommerce.SB_Ecom.Payload.CategoryDto;
import com.nikhilzzz.ecommerce.SB_Ecom.Payload.CategoryResponse;
import com.nikhilzzz.ecommerce.SB_Ecom.model.Category;
import com.nikhilzzz.ecommerce.SB_Ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;



import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //@GetMapping("api/public/getcategories")
    @RequestMapping(value = "api/public/getcategories", method = RequestMethod.GET)
    public ResponseEntity<CategoryResponse> getCategories() {

        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping("api/admin/createcategories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDto category) {

            String status =categoryService.createCategory(category);
            return ResponseEntity.ok(status);

        }

    @DeleteMapping("api/admin/deletecategories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") long categoryId) {

        String status =categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(status);
//        try {
//            String status = categoryService.deleteCategory(categoryId);
//            return ResponseEntity.ok(status);
//        } catch (ResponseStatusException e) {
//            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
//        } ----REMOVING TRY CATCH CUZ WE HAVE OUR OWN HANDLER HANDLING IT----
    }

    @PutMapping("api/admin/updatecategories/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable("categoryId") long categoryId,@RequestBody CategoryDto updatedCategory) {
        String status = categoryService.updateCategory(categoryId, updatedCategory);
        return ResponseEntity.ok(status);
    }
}
