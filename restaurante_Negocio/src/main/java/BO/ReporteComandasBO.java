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
 *
 * @author katia
 */
public class ReporteComandasBO {
    private final IReporteComandasDAO reporteComandasDAO;
    
    public ReporteComandasBO(){
        this.reporteComandasDAO = new ReporteComandasDAO();
    }
    
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
