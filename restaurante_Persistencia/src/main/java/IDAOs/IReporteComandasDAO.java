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
 * Interfaz que define los métodos para acceder a los datos de las comandas en a
 * base de datos.
 * @author katia
 */
public interface IReporteComandasDAO {
    
    /**
     * Obtiene una lista de comandas que coincidan con los filtros de búsqueda
     * proporcionados por el usuario. (Filtros opcionales).
     * @param inicio Fecha y hora de inicio del rango de búsqueda.
     * @param fin Fecha y hora de fin del rango de búsqueda.
     * @return Lista de comandas que coincidan con el rango de fechas
     * proporcionados.
     * @throws PersistenciaException Por si sucede algún error.
     */
    public List<Comanda> obtenerComandasFiltros(LocalDateTime inicio, LocalDateTime fin) throws PersistenciaException;
    
    /**
     * Obtiene el total acumulado de ventas dentro del rango de fechas que se
     * proporcione.
     * @param inicio Fecha y hora de inicio del rango de búsqueda.
     * @param fin Fecha y hora de fin del rango de búsqueda.
     * @return El total acumulado de ventas dentro del rango de fechas.
     * @throws PersistenciaException En caso de error.
     */
    public Double obtenerTotalAcumuladoVentas(LocalDateTime inicio, LocalDateTime fin) throws PersistenciaException;
}
