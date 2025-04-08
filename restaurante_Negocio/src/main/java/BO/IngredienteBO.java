/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAOs.IngredienteDAO;
import DTOs.IngredienteDTO;
import Entidades.Ingrediente;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import IDAOs.IIngredienteDAO;
import Mapper.IngredienteMapper;
import java.util.List;

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

    public IngredienteDTO registrarIngrediente(IngredienteDTO dto) throws NegocioException {
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

    public List<IngredienteDTO> obtenerIngredientes() throws NegocioException {
        try {
            List<Ingrediente> ingredientes = ingredienteDAO.obtenerIngredientes();
            return ingredientes.stream().map(mapperIngrediente::toDTO).toList();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener los ingredientes.", e);
        }
    }

    public IngredienteDTO buscarPorNombre(String nombre) throws NegocioException {
        try {
            Ingrediente ingrediente = ingredienteDAO.buscarNombre(nombre);
            if (ingrediente == null) {
                throw new NegocioException("No se encontró el ingrediente con nombre: " + nombre);
            }
            return mapperIngrediente.toDTO(ingrediente);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar ingrediente por nombre.", e);
        }
    }

    public IngredienteDTO buscarPorUnidad(Enum unidadMedida) throws NegocioException {
        try {
            Ingrediente ingrediente = ingredienteDAO.buscarPorUnidad(unidadMedida);
            if (ingrediente == null) {
                throw new NegocioException("No se encontró el ingrediente con unidad de medida: " + unidadMedida);
            }
            return mapperIngrediente.toDTO(ingrediente);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar ingrediente por unidad de medida.", e);
        }
    }

    public IngredienteDTO eliminarIngrediente(Long id) throws NegocioException {
        try {
            Ingrediente ingrediente = ingredienteDAO.obtenerIngredientesPorID().stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NegocioException("Ingrediente no encontrado con ID: " + id));

            ingredienteDAO.eliminarIngrediente(ingrediente);
            return mapperIngrediente.toDTO(ingrediente);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar ingrediente.", e);
        }
    }

    public IngredienteDTO editarIngrediente(IngredienteDTO dto) throws NegocioException {
        try {
            Ingrediente ingrediente = mapperIngrediente.toEntity(dto);
            Ingrediente ingredienteActualizado = ingredienteDAO.editarIngrediente(ingrediente);
            return mapperIngrediente.toDTO(ingredienteActualizado);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al editar ingrediente.", e);
        }
    }

    private void validarIngrediente(IngredienteDTO dto) throws NegocioException {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new NegocioException("El nombre del ingrediente es obligatorio.");
        }
        if (dto.getUndadMedida()== null || dto.getUndadMedida().isBlank()) {
            throw new NegocioException("La unidad de medida es obligatoria.");
        }
    }

    private void validarDuplicados(IngredienteDTO ingredienteDTO) throws NegocioException {
        try {
            List<IngredienteDTO> existentes = obtenerIngredientes();
            for (IngredienteDTO existente : existentes) {
                if (existente.getNombre().equals(ingredienteDTO.getNombre())) {
                    throw new NegocioException("Ya existe un ingrediente con el mismo nombre.");
                }
            }
        } catch (NegocioException e) {
            throw e;
        }
    }

}
