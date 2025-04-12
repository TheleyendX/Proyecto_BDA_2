/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import Entidades.Comanda;
import Excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author katia
 */
public interface IReporteComandasDAO {
    public List<Comanda> obtenerComandasFiltros(LocalDateTime inicio, LocalDateTime fin) throws PersistenciaException;
    public Double obtenerTotalAcumuladoVentas(LocalDateTime inicio, LocalDateTime fin) throws PersistenciaException;
}
