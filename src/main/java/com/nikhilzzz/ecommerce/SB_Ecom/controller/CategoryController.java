package com.nikhilzzz.ecommerce.SB_Ecom.controller;


import com.nikhilzzz.ecommerce.SB_Ecom.model.Category;
import com.nikhilzzz.ecommerce.SB_Ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("api/admin/createcategories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        try {
            String status =categoryService.createCategory(category);
            return ResponseEntity.ok(status);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
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
    public ResponseEntity<String> updateCategory(@PathVariable("categoryId") long categoryId,@RequestBody Category updatedCategory) {
        String status = categoryService.updateCategory(categoryId, updatedCategory);
        return ResponseEntity.ok(status);
    }
}
