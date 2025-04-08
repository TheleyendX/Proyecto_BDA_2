/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTOs.ProductoIngredienteDTO;
import Entidades.ProductoIngrediente;

public class ProductoIngredienteMapper {

    public ProductoIngrediente toEntity(ProductoIngredienteDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductoIngrediente entidad = new ProductoIngrediente();
        entidad.setProducto(dto.getProducto());
        entidad.setIngrediente(dto.getIngrediente());
        entidad.setCantidadRequerida(dto.getCantidadRequerida());

        return entidad;
    }

    public ProductoIngredienteDTO toDTO(ProductoIngrediente entidad) {
        if (entidad == null) {
            return null;
        }

        ProductoIngredienteDTO dto = new ProductoIngredienteDTO();
        dto.setProducto(entidad.getProducto());
        dto.setIngrediente(entidad.getIngrediente());
        dto.setCantidadRequerida(entidad.getCantidadRequerida());

        return dto;
    }
}

