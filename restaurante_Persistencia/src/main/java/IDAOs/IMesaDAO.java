/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IDAOs;

import Entidades.Mesa;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author mmax2
 */
public interface IMesaDAO {
    public List<Mesa> registraMesas ()throws PersistenciaException;
    
    public Mesa actualizaEstado (Long id,int estado) throws PersistenciaException;
}
