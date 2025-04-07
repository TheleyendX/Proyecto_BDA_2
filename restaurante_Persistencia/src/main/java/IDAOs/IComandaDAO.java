/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IDAOs;

import Entidades.Comanda;
import Excepciones.PersistenciaException;

/**
 *
 * @author mmax2
 */
public interface IComandaDAO {
    public Comanda registraComanda (Comanda comanda) throws PersistenciaException;
    
    
}
