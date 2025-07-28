package com.nikhilzzz.ecommerce.SB_Ecom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    @NotBlank(message = "Category needs to have a Name")
    @Size(min = 5, message = "Category Name to be greater than 5 chars")
    private String categoryName;

}
