/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTOs.IngredienteDTO;
import Entidades.Ingrediente;
import java.util.Collections;

/**
 *
 * @author jorge
 */
public class IngredienteMapper {

     public Ingrediente toEntity(IngredienteDTO ingredienteDTO) {
        if (ingredienteDTO == null) {
            return null;
        }

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setStock(ingredienteDTO.getStock());
        ingrediente.setNombre(ingredienteDTO.getNombre());
        ingrediente.setUnidadMedida(ingredienteDTO.getUndadMedida());

       

        return ingrediente;
    }

    public IngredienteDTO toDTO(Ingrediente ingrediente) {
        if (ingrediente == null) {
            return null;
        }

        return new IngredienteDTO(
                ingrediente.getStock(),
                ingrediente.getNombre(),
                ingrediente.getUnidadMedida()
        );
    }
}
    
    
    
    

