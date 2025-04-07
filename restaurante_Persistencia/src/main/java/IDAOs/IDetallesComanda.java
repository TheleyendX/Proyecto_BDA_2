/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import Entidades.DetallesComanda;
import Excepciones.PersistenciaException;

/**
 *
 * @author mmax2
 */
public interface IDetallesComanda {
    public DetallesComanda registraDetalles (DetallesComanda dtc) throws PersistenciaException;
    
    
}
