package com.temychp.fitccalc.util.convertors;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public abstract class ConvertorDto<T, V> {

    private final Class<T> modelClass;

    private final Class<V> dtoClass;

    private final ModelMapper modelMapper;

    public T DtoToModel(V dto) {
        return modelMapper.map(dto, modelClass);
    }

    public V ModelToDto(T model) {
        return modelMapper.map(model, dtoClass);
    }

}