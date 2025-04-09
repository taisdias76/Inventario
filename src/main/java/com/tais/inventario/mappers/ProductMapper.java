package com.tais.inventario.mappers;

import com.tais.inventario.dtos.ProductDTO;
import com.tais.inventario.dtos.ProductRequest;
import com.tais.inventario.entities.Category;
import com.tais.inventario.entities.Product;
import com.tais.inventario.entities.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  ProductDTO toDTO(Product product);

  Product toEntity(ProductDTO productDTO);

  List<ProductDTO> toDTOList(List<Product> list);

  List<Product> toEntityList(List<ProductDTO> productDTOS);

  @Named("fromRequest")
  default Product fromRequest(ProductRequest request, Category category, Supplier supplier) {
    Product product = new Product();
    product.setName(request.name());
    product.setDescription(request.description());
    product.setPrice(request.price());
    product.setQuantity(request.quantity());
    product.setCategory(category);
    product.setSupplier(supplier);
    return product;
  }
}
