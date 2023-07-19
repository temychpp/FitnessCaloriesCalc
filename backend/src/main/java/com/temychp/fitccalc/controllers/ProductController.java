package com.temychp.fitccalc.controllers;

import com.temychp.fitccalc.dto.ProductDto;
import com.temychp.fitccalc.models.product.Product;
import com.temychp.fitccalc.services.ProductService;
import com.temychp.fitccalc.util.convertors.ProductConvertor;
import com.temychp.fitccalc.util.exceptions.PersonDuplicateException;
import com.temychp.fitccalc.util.exceptions.PersonNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final ProductConvertor productConvertor;

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductDto productDto) {
        ResponseEntity<ProductDto> result;
        try {
            productService.save(productConvertor.DtoToModel(productDto));
            result = ResponseEntity.status(HttpStatus.OK).body(productDto);
            log.info("Продукт сохранен в базу: {}", productDto);

        } catch (PersonDuplicateException e) {
            result = ResponseEntity.status(HttpStatus.CONFLICT).body(productDto);
            log.info("Продукт уже есть в базе {}", e.getMessage());

        } catch (PersonNotFoundException e) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            log.info("Ошибка в запросе!{}", e.getMessage());

        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            log.info("Ошибка сервера! {}", e.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") Long id) {
        return productConvertor.ModelToDto(productService.findOne(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid ProductDto productDto,
                                             @PathVariable("id") Long id) {
        productService.update(id, productConvertor.DtoToModel(productDto));
        log.info("Обновляем продукт с id={}", id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ProductDto getProductByStartingLetter(@PathVariable("name") String name) {

        Product product = productService.findByName(name).orElse(null);

        return productConvertor.ModelToDto(product);
    }


}