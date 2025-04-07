package DAOs;

import ENUM.EstadoComanda;
import Entidades.Cliente;
import Entidades.Comanda;
import Entidades.Mesa;
import Entidades.Producto;
import Excepciones.PersistenciaException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;

public class ComandaDAOTest {

    private ComandaDAO comandaDAO;
    private Cliente cliente;
    private Mesa mesa;
    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        comandaDAO = new ComandaDAO();

        // Crear cliente y mesa para las pruebas
        cliente = new Cliente();
        cliente.setNombre("Carlos");
        cliente.setApellidoP("García");
        cliente.setApellidoM("Lopez");

        mesa = new Mesa();
        mesa.setNumero(5);
    }

    @Test
    public void testRegistraComanda() throws PersistenciaException {
        // Crear productos y detalles
        producto1 = new Producto();
        producto1.setNombre("Pizza");
        producto1.setPrecio(100.0);

        producto2 = new Producto();
        producto2.setNombre("Ensalada");
        producto2.setPrecio(50.0);

        List<Producto> productos = Arrays.asList(producto1, producto2);
        List<Integer> cantidades = Arrays.asList(1, 2);
        List<Double> precios = Arrays.asList(100.0, 50.0);
        List<String> comentarios = Arrays.asList("Sin cebolla", "Con extra de queso");

        Comanda comanda = comandaDAO.registraComanda(cliente, mesa, productos, cantidades, precios, comentarios);

        assertNotNull(comanda.getFolio(), "El folio de la comanda no puede ser nulo.");
        assertEquals(EstadoComanda.Abierto, comanda.getEstado(), "El estado de la comanda debe ser Abierto.");
        assertEquals(200.0, comanda.getTotalVenta(), "El total de la venta no es correcto.");
        assertNotNull(comanda.getDetallesComanda(), "Los detalles de la comanda no pueden ser nulos.");
        assertEquals(2, comanda.getDetallesComanda().size(), "Debe haber dos productos en los detalles.");
    }

    @Test
    public void testActualizaEstadoComanda() throws PersistenciaException {
        // Crear una comanda
        producto1 = new Producto();
        producto1.setNombre("Hamburguesa");
        producto1.setPrecio(80.0);

        List<Producto> productos = Arrays.asList(producto1);
        List<Integer> cantidades = Arrays.asList(2);
        List<Double> precios = Arrays.asList(80.0);
        List<String> comentarios = Arrays.asList("Con papas");

        Comanda comanda = comandaDAO.registraComanda(cliente, mesa, productos, cantidades, precios, comentarios);

        // Actualizar el estado de la comanda
        Comanda actualizada = comandaDAO.actualizaEstadoComanda(comanda.getId(), EstadoComanda.Entregado);

        assertEquals(EstadoComanda.Entregado, actualizada.getEstado(), "El estado de la comanda no se actualizó correctamente.");
    }

    @Test
    public void testGeneraFolio() throws PersistenciaException {
        String folio = comandaDAO.generaFolio();
        assertNotNull(folio, "El folio no debe ser nulo.");
        assertTrue(folio.startsWith("OB-"), "El folio debe comenzar con 'OB-'.");
    }

    @Test
    public void testRegistraComandaConErrores() {
        assertThrows(PersistenciaException.class, () -> {
            comandaDAO.registraComanda(null, null, null, null, null, null);
        }, "Se esperaba una excepción debido a parámetros nulos.");
    }

    @Test
    public void testActualizarEstadoComandaConComandaInexistente() {
        assertThrows(PersistenciaException.class, () -> {
            comandaDAO.actualizaEstadoComanda(999L, EstadoComanda.Entregado);
        }, "Se esperaba una excepción debido a la comanda no encontrada.");
    }
}
