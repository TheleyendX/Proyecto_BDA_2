/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTOs.ClienteFrecuenteDTO;
import Entidades.Cliente;
import Entidades.ClienteFrecuente;

/**
 *
 * @author katia
 */
public class ClienteFrecuenteMapper extends ClienteMapper {
    
    public ClienteFrecuente toEntity(ClienteFrecuenteDTO clienteFrecuenteDTO) {
        if (clienteFrecuenteDTO == null) return null;

        ClienteFrecuente entity = new ClienteFrecuente();
        entity.setId(clienteFrecuenteDTO.getId());
        entity.setNombre(clienteFrecuenteDTO.getNombre());
        entity.setApellidoP(clienteFrecuenteDTO.getApellidoP());
        entity.setApellidoM(clienteFrecuenteDTO.getApellidoM());
        entity.setCorreo(clienteFrecuenteDTO.getCorreo());
        entity.setTelefono(clienteFrecuenteDTO.getTelefono());
        entity.setFechaRegistro(clienteFrecuenteDTO.getFechaRegistro());
        entity.setComandas(clienteFrecuenteDTO.getComandas());

        // Atributos derivados (se permiten porque son @Transient)
        entity.setPuntos(clienteFrecuenteDTO.getPuntos());
        entity.setGastoTotalAcumulado(clienteFrecuenteDTO.getGastoTotalAcumulado());
        entity.setConteoVisitas(clienteFrecuenteDTO.getConteoVisitas());

        return entity;
    }
    
    public ClienteFrecuenteDTO toDTO(ClienteFrecuente clienteFrecuente) {
        if (clienteFrecuente == null) return null;

        return new ClienteFrecuenteDTO(
            clienteFrecuente.getPuntos(),
            clienteFrecuente.getGastoTotalAcumulado(),
            clienteFrecuente.getConteoVisitas(),
            clienteFrecuente.getId(),
            clienteFrecuente.getNombre(),
            clienteFrecuente.getApellidoP(),
            clienteFrecuente.getApellidoM(),
            clienteFrecuente.getCorreo(),
            clienteFrecuente.getTelefono(),
            clienteFrecuente.getFechaRegistro(),
            clienteFrecuente.getComandas()
        );
    }
}
