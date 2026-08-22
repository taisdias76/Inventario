package com.tais.inventario.mappers;

import com.tais.inventario.dtos.CategoryDTO;
import com.tais.inventario.entities.Category;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  CategoryDTO toDTO(Category category);

  Category toEntity(CategoryDTO categoryDTO);

  List<CategoryDTO> toDTOList(List<Category> categorys);

  List<Category> toEntityList(List<CategoryDTO> categoryDTOs);
}
