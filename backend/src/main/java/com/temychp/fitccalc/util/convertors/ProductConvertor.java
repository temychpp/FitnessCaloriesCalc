package com.temychp.fitccalc.util.convertors;

import com.temychp.fitccalc.dto.ProductDto;
import com.temychp.fitccalc.models.product.Product;
import org.modelmapper.ModelMapper;

public class ProductConvertor extends ConvertorDto<Product,ProductDto> {

    public ProductConvertor(ModelMapper modelMapper) {
        super(Product.class, ProductDto.class, modelMapper);
    }

}