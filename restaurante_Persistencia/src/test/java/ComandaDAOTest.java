import DAOs.ComandaDAO;
import DAOs.DetallesComandaDAO;
import Entidades.Cliente;
import Entidades.Comanda;
import Entidades.DetallesComanda;
import Entidades.Mesa;
import Entidades.Producto;
import Excepciones.PersistenciaException;
import ENUM.EstadoComanda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ComandaDAOTest {

    private ComandaDAO comandaDAO;
    private EntityManager entityManagerMock;
    private DetallesComandaDAO detallesComandaDAOMock;

    @BeforeEach
    public void setUp() {
        // Mocks
        entityManagerMock = mock(EntityManager.class);
        detallesComandaDAOMock = mock(DetallesComandaDAO.class);

        comandaDAO = new ComandaDAO();
        comandaDAO.em = entityManagerMock;
        comandaDAO.dtDAO = detallesComandaDAOMock;
    }

    @Test
    public void testRegistraComanda() throws PersistenciaException {
        // Datos de prueba
        Cliente cliente = new Cliente();  // Asumiendo que tienes un cliente con los datos necesarios
        Mesa mesa = new Mesa();  // Asumiendo que tienes una mesa con los datos necesarios
        Producto producto1 = new Producto();  // Similar para productos
        Producto producto2 = new Producto();
        List<Producto> productos = Arrays.asList(producto1, producto2);
        List<Integer> cantidades = Arrays.asList(2, 3);
        List<Double> precios = Arrays.asList(10.0, 20.0);
        List<String> comentarios = Arrays.asList("Comentario 1", "Comentario 2");

        // Mocking los métodos
        List<DetallesComanda> detallesComandaMock = Arrays.asList(new DetallesComanda(), new DetallesComanda());
        when(detallesComandaDAOMock.registraDetalles(any(), any(), any(), any(), any())).thenReturn(detallesComandaMock);

        // Llamar al método
        Comanda comanda = comandaDAO.registraComanda(cliente, mesa, productos, cantidades, precios, comentarios);

        // Verificar que se haya persistido la comanda
        verify(entityManagerMock, times(1)).persist(any(Comanda.class));
        assert comanda != null;
        assert comanda.getEstado() == EstadoComanda.Abierto;
    }

    @Test
    public void testActualizaEstadoComanda() throws PersistenciaException {
        // Datos de prueba
        Long comandaId = 1L;
        EstadoComanda nuevoEstado = EstadoComanda.Entregado;

        // Mocking
        Comanda comandaMock = mock(Comanda.class);
        when(entityManagerMock.find(Comanda.class, comandaId)).thenReturn(comandaMock);

        // Llamar al método
        Comanda comandaActualizada = comandaDAO.actualizaEstadoComanda(comandaId, nuevoEstado);

        // Verificar que se haya actualizado el estado
        verify(comandaMock, times(1)).setEstado(nuevoEstado);
        verify(entityManagerMock, times(1)).merge(comandaMock);
        assert comandaActualizada != null;
    }

    @Test
    public void testGeneraFolio() throws PersistenciaException {
        // Llamar al método
        String folio = comandaDAO.generaFolio();

        // Verificar que el folio esté en el formato correcto
        assert folio.startsWith("OB-");
        assert folio.length() == 11;  // Formato esperado: OB-YYYYMMDD-XXX
    }
}
