/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAOs.ReporteClientesFrecuentesDAO;
import DTOs.ClienteFrecuenteDTO;
import Entidades.ClienteFrecuente;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import IDAOs.IReporteClientesFrecuentesDAO;
import Mapper.ClienteFrecuenteMapper;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase usada para obtener reportes de clientes frecuentes
 * Permite realizar validaciones para verificar que los filtros sean válidos.
 * @author katia
 */
public class ReporteClientesFrecuentesBO {
    private final IReporteClientesFrecuentesDAO reporteClientesDAO;
    private final ClienteFrecuenteMapper clienteFMapper;
    
    /**
     * Constructor de la clase.
     * Inicializa el acceso a los datos de clientes frecuentes y el mapeador.
     */
    public ReporteClientesFrecuentesBO(){
        this.reporteClientesDAO = new ReporteClientesFrecuentesDAO();
        this.clienteFMapper = new ClienteFrecuenteMapper();
    }
    
    /**
     * Obtiene los clientes frecuentes filtrados por nombre y número mínimo de visitas.
     * Si el nombre es proporcionado, se valida que solo contenga letras y espacios.
     * Si el número de visitas es proporcionado, se valida que sea un número entero no negativo.
     * @param nombre El nombre del cliente (puede ser parcial).
     * @param visitasMin El número mínimo de visitas del cliente.
     * @return Lista con los clientes frecuentes que cumplan con los filtros.
     * @throws NegocioException  En caso de que las validaciones fallen o si ocurre algún error.
     */
    public List<ClienteFrecuenteDTO> obtenerClientesFrecuentesPorFiltro(String nombre, Integer visitasMin) throws NegocioException {
        if (visitasMin != null){
            if (visitasMin < 0){
                throw new NegocioException("El número de visitas no puede ser negativo.");
            }
            if (visitasMin % 1 !=0){
                throw new NegocioException("El número de visitas debe ser un número entero.");
            }
        }
        if (nombre != null && !nombre.trim().isEmpty()) {
            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+")) {
                throw new NegocioException("El nombre solo puede contener letras y espacios.");
            }
        }
        try{
            List<ClienteFrecuente> clientes = reporteClientesDAO.obtenerClientesFrecuentesPorFiltro(nombre, visitasMin);
            return convertirAClienteFrecuenteDTO(clientes);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener los clientes frecuentes filtrados: " + e.getMessage());
        }
    }
    
    /**
     * Convierte una lista de entidades ClienteFrecuente en una lista de DTOs ClienteFrecuenteDTO.
     * @param clientes La lista de entidades ClienteFrecuente a convertir.
     * @return Una lista de DTOs ClienteFrecuenteDTO.
     */
    private List<ClienteFrecuenteDTO> convertirAClienteFrecuenteDTO(List<ClienteFrecuente> clientes) {
        return clientes.stream().map(clienteFMapper::toDTO).collect(Collectors.toList());
    }
    

}
