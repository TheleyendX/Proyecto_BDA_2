/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTOs.ClienteDTO;
import Entidades.Cliente;

/**
 *
 * @author katia
 */
public class ClienteMapper {
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
