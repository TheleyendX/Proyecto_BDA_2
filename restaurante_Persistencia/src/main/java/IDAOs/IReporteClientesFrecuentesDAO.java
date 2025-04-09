/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import Entidades.ClienteFrecuente;
import Excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author katia
 */
public interface IReporteClientesFrecuentesDAO {
    public List<ClienteFrecuente> obtenerClientesFrecuentesPorFiltro(String nombre, Integer visitasMinimas) throws PersistenciaException;
    
    public LocalDate obtenerUltimaFechaComanda(ClienteFrecuente cliente);
}
