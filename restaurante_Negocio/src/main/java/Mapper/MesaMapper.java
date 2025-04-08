/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTOs.MesaDTO;
import Entidades.Mesa;

/**
 *
 * @author mmax2
 */
public class MesaMapper {

    public Mesa toEntity(MesaDTO m) {
        if (m == null) {
            return null;
        }

        return new Mesa(
                m.getId(),
                m.getNumero(),
                m.isEstadoMesa()
        );
    }

    public MesaDTO toDTO(Mesa m) {
        if (m == null) {
            return null;
        }

        return new MesaDTO(
                m.getId(),
                m.getNumero(),
                m.isEstadoMesa()
        );
    }
}

