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
 *
 * @author katia
 */
public class ReporteClientesFrecuentesBO {
    private final IReporteClientesFrecuentesDAO reporteClientesDAO;
    private final ClienteFrecuenteMapper clienteFMapper;
    
    public ReporteClientesFrecuentesBO(){
        this.reporteClientesDAO = new ReporteClientesFrecuentesDAO();
        this.clienteFMapper = new ClienteFrecuenteMapper();
    }
    
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
    
    private List<ClienteFrecuenteDTO> convertirAClienteFrecuenteDTO(List<ClienteFrecuente> clientes) {
        return clientes.stream().map(clienteFMapper::toDTO).collect(Collectors.toList());
    }
    
    public List<ClienteFrecuenteDTO> obtenerTodosLosClientesFrecuentes() throws NegocioException {
        try {
            List<ClienteFrecuente> clientes = reporteClientesDAO.obtenerClientesFrecuentesPorFiltro(null, null); // Sin filtro
            return convertirAClienteFrecuenteDTO(clientes);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener todos los clientes frecuentes: " + e.getMessage());
        }
    }
}
