/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOs;

import Conexion.Conexion;
import ENUM.EstadoComanda;
import Entidades.ClienteFrecuente;
import Entidades.Comanda;
import Excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katia
 */
public class ReporteComandasDAOTest {
    private ReporteComandasDAO reporteComandasDAO;
    
    @BeforeEach
    public void setUp() {
        reporteComandasDAO = new ReporteComandasDAO();
    }
    
    @AfterEach
    public void tearDown(){
        EntityManager em = Conexion.crearConexion();
        EntityTransaction transaction= em.getTransaction();
        try{
            transaction.begin();
            em.createQuery("DELETE FROM Comanda").executeUpdate();
            em.createQuery("DELETE FROM ClienteFrecuente").executeUpdate();
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        } finally{
            em.close();
        }
    }
    
    @Test
    public void testObtenerComandasSinFiltros() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Laura");
        cliente.setApellidoP("Martínez");
        cliente.setCorreo("laura.martinez@example.com");
        cliente.setTelefono("4564564560");
        reporteComandasDAO.registrarClienteFrecuente(cliente);
        for (int i = 0; i < 2; i++) {
            Comanda comanda = new Comanda();
            comanda.setCliente(cliente);
            comanda.setEstado(EstadoComanda.Entregado);
            comanda.setFechaHora(LocalDateTime.now());
            comanda.setTotalVenta(800.00);
            
            String folio = "CM" + (i+1);
            comanda.setFolio(folio);
            reporteComandasDAO.persistirComanda(comanda);
        }    
        List<Comanda> result = reporteComandasDAO.obtenerComandasFiltros(null, null);

        assertNotNull(result, "La lista no debe ser nula.");
        assertEquals(2, result.size(), "Debe haber 2 comandas.");
    }
    
    @Test
    public void testObtenerComandasConFiltroInicio() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Carlos");
        cliente.setApellidoP("Pérez");
        cliente.setCorreo("carlos.perez@example.com");
        cliente.setTelefono("9876543210");
        reporteComandasDAO.registrarClienteFrecuente(cliente);
        
        for (int i = 0; i < 3; i++) {
            Comanda comanda = new Comanda();
            comanda.setCliente(cliente);
            comanda.setEstado(EstadoComanda.Entregado);
            comanda.setFechaHora(LocalDateTime.now().minusDays(i)); // Diferentes fechas
            comanda.setTotalVenta(800.00);

            String folio = "CMD" + (i + 1);
            comanda.setFolio(folio);
            reporteComandasDAO.persistirComanda(comanda);
        }

        LocalDateTime inicio = LocalDateTime.now().minusDays(2);

        List<Comanda> result = reporteComandasDAO.obtenerComandasFiltros(inicio, null);

        assertNotNull(result, "La lista no debe ser nula.");
        assertEquals(2, result.size(), "Debe haber 2 comandas, ya que solo una es posterior a la fecha de inicio.");
    }
    
    @Test
    public void testObtenerComandasConFiltroFin() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Ana");
        cliente.setApellidoP("González");
        cliente.setCorreo("ana.gonzalez@example.com");
        cliente.setTelefono("1234567890");
        reporteComandasDAO.registrarClienteFrecuente(cliente);

        for (int i = 0; i < 3; i++) {
            Comanda comanda = new Comanda();
            comanda.setCliente(cliente);
            comanda.setEstado(EstadoComanda.Entregado);
            comanda.setFechaHora(LocalDateTime.now().minusDays(i)); // Diferentes fechas
            comanda.setTotalVenta(800.00);

            String folio = "CMNDA" + (i + 1);
            comanda.setFolio(folio);
            reporteComandasDAO.persistirComanda(comanda);
        }

        LocalDateTime fin = LocalDateTime.now().minusDays(1);

        List<Comanda> result = reporteComandasDAO.obtenerComandasFiltros(null, fin);

        assertNotNull(result, "La lista no debe ser nula.");
        assertEquals(2, result.size(), "Debe haber 2 comandas, ya que solo dos son anteriores o iguales a la fecha de fin.");
    }
    
    @Test
    public void testObtenerComandasConFiltros() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Pedro");
        cliente.setApellidoP("López");
        cliente.setCorreo("pedro.lopez@example.com");
        cliente.setTelefono("1122334455");
        reporteComandasDAO.registrarClienteFrecuente(cliente);

        for (int i = 0; i < 3; i++) {
            Comanda comanda = new Comanda();
            comanda.setCliente(cliente);
            comanda.setEstado(EstadoComanda.Entregado);
            comanda.setFechaHora(LocalDateTime.now().minusDays(i)); // Diferentes fechas
            comanda.setTotalVenta(800.00);

            String folio = "COM" + (i + 1);
            comanda.setFolio(folio);

            reporteComandasDAO.persistirComanda(comanda);
        }

        LocalDateTime inicio = LocalDateTime.now().minusDays(2);
        LocalDateTime fin = LocalDateTime.now().minusDays(1);

        List<Comanda> result = reporteComandasDAO.obtenerComandasFiltros(inicio, fin);

        assertNotNull(result, "La lista no debe ser nula.");
        assertEquals(1, result.size(), "Debe haber 1 comanda, ya que solo una está dentro del rango de fechas.");
    }
    
    
}
