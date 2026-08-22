package com.tais.inventario.mappers;

import com.tais.inventario.dtos.SupplierDTO;
import com.tais.inventario.entities.Supplier;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
  SupplierDTO toDTO(Supplier supplier);

  Supplier toEntity(SupplierDTO supplierDTO);

  List<SupplierDTO> toDTOList(List<Supplier> suppliers);

  List<Supplier> toEntityList(List<SupplierDTO> supplierDTOs);
}
