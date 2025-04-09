/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import DAOs.ComandaDAO;
import DTOs.ComandaDTO;
import ENUM.EstadoComanda;
import IDAOs.IComandaDAO;

/**
 *
 * @author mmax2
 */
public interface IComandaBO {
    
    public ComandaDTO registraComnada ();
    
    public ComandaDTO actualizaEstado(Long id, EstadoComanda estado);
}
