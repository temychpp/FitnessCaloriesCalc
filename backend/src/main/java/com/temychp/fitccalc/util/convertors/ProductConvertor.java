package com.temychp.fitccalc.util.convertors;

import com.temychp.fitccalc.dto.ProductDto;
import com.temychp.fitccalc.models.product.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class ProductConvertor {

    private final ModelMapper modelMapper;

    public Product DtoToModel(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    public ProductDto ModelToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}