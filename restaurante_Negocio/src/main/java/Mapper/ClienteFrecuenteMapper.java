/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTOs.ClienteFrecuenteDTO;
import Entidades.Cliente;
import Entidades.ClienteFrecuente;

/**
 * Convierte objetos de entidad a DTO y de DTO a Entidad.
 * Sirve para separar la lógica de la presentación
 * @author katia
 */
public class ClienteFrecuenteMapper extends ClienteMapper {
    
    /**
     * Convierte un DTO en una entidad.
     * @param clienteFrecuenteDTO DTO que contiene los datos del cliente frecuente.
     * @return La entidad con los datos del DTO proporcionado.
     */
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

        entity.setPuntos(clienteFrecuenteDTO.getPuntos());
        entity.setGastoTotalAcumulado(clienteFrecuenteDTO.getGastoTotalAcumulado());
        entity.setConteoVisitas(clienteFrecuenteDTO.getConteoVisitas());
        entity.setUltimaFechaComanda(clienteFrecuenteDTO.getUltimaFechaComanda());

        return entity;
    }
    
    /**
     * Convierte una entidad a su correspondiente DTO.
     * @param clienteFrecuente La entidad que contiene los datos del cliente frecuente.
     * @return Un DTO con los datos de la entidad proporcionada.
     */
    public ClienteFrecuenteDTO toDTO(ClienteFrecuente clienteFrecuente) {
        if (clienteFrecuente == null) return null;

        return new ClienteFrecuenteDTO(
            clienteFrecuente.getPuntos(),
            clienteFrecuente.getGastoTotalAcumulado(),
            clienteFrecuente.getConteoVisitas(),
            clienteFrecuente.getUltimaFechaComanda(),
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
