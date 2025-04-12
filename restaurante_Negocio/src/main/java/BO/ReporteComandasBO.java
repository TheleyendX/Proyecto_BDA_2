/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAOs.ReporteComandasDAO;
import Entidades.Comanda;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import IDAOs.IReporteComandasDAO;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase encargada de la obtención de reportes de comandas.
 * Proporciona métodos para obtener comandas filtradas por un rango de fechas
 * y para calcular el total acumulado de ventas dentro de un rango de fechas.
 * Realiza validaciones sobre las fechas proporcionadas.
 * @author katia
 */
public class ReporteComandasBO {
    private final IReporteComandasDAO reporteComandasDAO;
    
    /**
     * Constructor de la clase ReporteComandasBO.
     * Inicializa el acceso a los datos de las comandas.
     */
    public ReporteComandasBO(){
        this.reporteComandasDAO = new ReporteComandasDAO();
    }
    
    /**
     * Obtiene las comandas filtradas por el rango de fechas proporcionado.
     * Valida que las fechas proporcionadas sean correctas y tengan lógica.
     * @param inicio  fecha de inicio del rango.
     * @param fin fecha de fin del rango.
     * @return Una lista de comandas dentro del rango de fechas especificado.
     * @throws NegocioException Si las fechas son inválidas o si ocurre un error.
     */
    public List<Comanda> obtenerComandasPorFecha(LocalDateTime inicio, LocalDateTime fin) throws NegocioException {
        if (inicio != null && fin != null && fin.isBefore(inicio)) {
            throw new NegocioException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        LocalDateTime hoy = LocalDateTime.now();
        if ((inicio != null && inicio.isAfter(hoy)) || (fin != null && fin.isAfter(hoy))) {
            throw new NegocioException("Las fechas no pueden ser posteriores al día de hoy.");
        }

        try {
            return reporteComandasDAO.obtenerComandasFiltros(inicio, fin);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener las comandas filtradas: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene el total acumulado de ventas dentro del rango de fechas proporcionado.
     * @param inicio La fecha de inicio del rango.
     * @param fin La fecha de fin del rango.
     * @return El total acumulado de ventas dentro del rango de fechas.
     * @throws NegocioException Si las fechas son inválidas o si ocurre un error.
     */
    public Double obtenerTotalAcumuladoVentas(LocalDateTime inicio, LocalDateTime fin) throws NegocioException {
        if (inicio != null && fin != null && fin.isBefore(inicio)) {
            throw new NegocioException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        LocalDateTime ahora = LocalDateTime.now();
        if ((inicio != null && inicio.isAfter(ahora)) || (fin != null && fin.isAfter(ahora))) {
            throw new NegocioException("Las fechas no pueden ser posteriores al día de hoy.");
        }

        try {
            return reporteComandasDAO.obtenerTotalAcumuladoVentas(inicio, fin);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener el total acumulado de ventas: " + e.getMessage());
        }
    }
}
