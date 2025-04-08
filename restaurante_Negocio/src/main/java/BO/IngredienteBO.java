/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOs.IngredienteDTO;
import Entidades.Ingrediente;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import IDAOs.IIngredienteDAO;
import Mapper.IngredienteMapper;

/**
 *
 * @author jorge
 */
public class IngredienteBO {
       private final IIngredienteDAO ingredienteDAO;
    private final IngredienteMapper mapperIngrediente;

    public IngredienteBO() {
        this.ingredienteDAO = new IngredienteDAO();
        this.mapperIngrediente = new IngredienteMapper();
    }

    public IngredienteDTO registrarIngrediente(IngredienteDTO dto) throws NegocioException, PersistenciaException {
        dto.setNombre(dto.getNombre().trim());
        dto.setUnidadMedida(dto.getUndadMedida().trim);

        validarIngrediente(dto);
        validarDuplicados(dto);

        Ingrediente ingredienteEntidad = mapperIngrediente.toEntity(dto);

        try {
            Ingrediente registrado = ingredienteDAO.registrarIngrediente(ingredienteEntidad);
            return mapperIngrediente.toDTO(registrado);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar ingrediente.", e);
        }
    }

    public List<IngredienteDTO> obtenerTodos() throws NegocioException {
        try {
            List<Ingrediente> ingredientes = ingredienteDAO.obtenerTodos();
            return ingredientes.stream().map(mapperIngrediente::toDTO).toList();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener ingredientes.", e);
        }
    }

    public IngredienteDTO buscarPorId(Long id) throws NegocioException {
        try {
            Ingrediente ingrediente = ingredienteDAO.buscarPorId(id);
            if (ingrediente == null) {
                throw new NegocioException("No se encontró el ingrediente con ID: " + id);
            }
            return mapperIngrediente.toDTO(ingrediente);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar ingrediente por ID.", e);
        }
    }

    private void validarIngrediente(IngredienteDTO dto) throws NegocioException {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new NegocioException("El nombre del ingrediente es obligatorio.");
        }
        if (dto.getUnidadMedida() == null || dto.getUnidadMedida().isBlank()) {
            throw new NegocioException("La unidad de medida es obligatoria.");
        }
        if (dto.getPrecio() < 0) {
            throw new NegocioException("El precio no puede ser negativo.");
        }
    }

    private void validarDuplicados(IngredienteDTO dto) throws NegocioException {
        try {
            List<IngredienteDTO> existentes = obtenerTodos();
            for (IngredienteDTO existente : existentes) {
                if (existente.getNombre().equalsIgnoreCase(dto.getNombre().trim())) {
                    throw new NegocioException("Ya existe un ingrediente con ese nombre.");
                }
            }
        } catch (NegocioException e) {
            throw e;
        }
    } 
}
