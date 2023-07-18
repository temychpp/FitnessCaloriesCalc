package com.temychp.fitccalc.util.validators;

import com.temychp.fitccalc.models.product.Product;
import com.temychp.fitccalc.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class ProductNameValidator implements Validator {

    private final ProductService productService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product product = (Product) o;
        if (productService.findByName(product.getName()).isPresent())
            errors.rejectValue("name", "", "This product name is already taken!");
    }
}