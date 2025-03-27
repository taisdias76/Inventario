package com.tais.inventario.mappers;

import com.tais.inventario.dtos.SupplierDTO;
import com.tais.inventario.entities.Supplier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDTO toDTO(Supplier supplier);

    Supplier toEntity(SupplierDTO supplierDTO);

    List<SupplierDTO> toDTOList(List<Supplier> suppliers);

    List<Supplier> toEntityList(List<SupplierDTO> supplierDTOs);
}
