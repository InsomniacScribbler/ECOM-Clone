package com.nikhilzzz.ecommerce.SB_Ecom.Payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String categoryName;
    private Long categoryId;
}
