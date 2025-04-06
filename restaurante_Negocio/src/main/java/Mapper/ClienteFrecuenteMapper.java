/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTOs.ClienteFrecuenteDTO;
import Entidades.ClienteFrecuente;

/**
 *
 * @author katia
 */
public class ClienteFrecuenteMapper {
    public static ClienteFrecuente toEntity(ClienteFrecuenteDTO cliente){
        return new ClienteFrecuente(cliente.getPuntos(), cliente.getGastoTotalAcumulado(), cliente.getConteoVisitas());
    }
    
    public ClienteFrecuenteDTO toDTO(ClienteFrecuente cliente){
        return new ClienteFrecuenteDTO(cliente.getPuntos(), cliente.getGastoTotalAcumulado(), cliente.getConteoVisitas());
    }
}
