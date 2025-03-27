package com.tais.inventario.mappers;

import com.tais.inventario.dtos.ProductDTO;
import com.tais.inventario.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);

    Product toEntity(ProductDTO productDTO);

    List<ProductDTO> toDTOList(List<Product> list);

    List<Product> toEntityList(List<ProductDTO> productDTOS);
}
