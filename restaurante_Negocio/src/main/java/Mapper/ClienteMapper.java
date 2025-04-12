/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTOs.ClienteDTO;
import Entidades.Cliente;

/**
 * Clase encargada de mapear entre la entidad Cliente y su
 * correspondiente DTO.
 * @author katia
 */
public class ClienteMapper {
    
    /**
     * Convierte un ClienteDTO en una entidad Cliente.
     * @param clienteDTO El DTO que contiene los datos del cliente.
     * @return La entidad Cliente con los datos del DTO proporcionado.
     */
    public Cliente toEntity(ClienteDTO clienteDTO) {
        if (clienteDTO == null) {
            return null;
        }
        Cliente cliente = new Cliente(
                clienteDTO.getId(), 
                clienteDTO.getNombre(), 
                clienteDTO.getApellidoP(),
                clienteDTO.getApellidoM(), 
                clienteDTO.getCorreo(),
                clienteDTO.getTelefono(),
                clienteDTO.getFechaRegistro(),
                clienteDTO.getComandas()
        );
        return cliente;
    }
    
    /**
     * Convierte una entidad Cliente a su correspondiente DTO ClienteDTO.
     * @param cliente La entidad que contiene los datos del cliente.
     * @return Un DTO con los datos de la entidad proporcionada.
     */
    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteDTO(
                cliente.getId(), 
                cliente.getNombre(), 
                cliente.getApellidoP(),
                cliente.getApellidoM(),
                cliente.getCorreo(),
                cliente.getTelefono(),
                cliente.getFechaRegistro(),
                cliente.getComandas()
        );
    }
    
}
