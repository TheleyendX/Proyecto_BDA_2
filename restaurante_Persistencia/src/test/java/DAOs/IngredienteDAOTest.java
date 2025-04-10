/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import ENUM.UnidadMedida;
import Entidades.Ingrediente;
import java.util.List;
import Excepciones.PersistenciaException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author jorge
 */
public class IngredienteDAOTest {
    
    private IngredienteDAO ingredienteDAO;

    @BeforeEach
    public void setUp() {
        ingredienteDAO = new IngredienteDAO();
    }

    @Test
    public void testRegistrarIngrediente() throws PersistenciaException {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("Tomate");
        ingrediente.setUnidadMedida(UnidadMedida.gm);

        Ingrediente registrado = ingredienteDAO.registrarIngrediente(ingrediente);

        assertNotNull(registrado.getId(), "El ingrediente registrado debe tener un ID.");
        assertEquals("Tomate", registrado.getNombre());
    }

    @Test
    public void testObtenerIngredientes() throws PersistenciaException {
        List<Ingrediente> ingredientes = ingredienteDAO.obtenerIngredientes();
        assertNotNull(ingredientes, "La lista de ingredientes no debe ser nula.");
    }

    @Test
    public void testBuscarNombre() throws PersistenciaException {
//        Ingrediente ingrediente = new Ingrediente();
//        ingrediente.setNombre("Cebolla");
//        ingrediente.setUnidadMedida(UnidadMedida.pz);
//
//        ingredienteDAO.registrarIngrediente(ingrediente);

        Ingrediente buscado = ingredienteDAO.buscarNombre("Cebolla");

        assertNotNull(buscado, "El ingrediente no debe ser nulo.");
        assertEquals("Cebolla", buscado.getNombre(), "El nombre del ingrediente debe coincidir.");
    }

    @Test
    public void testBuscarPorUnidad() throws PersistenciaException {
//        Ingrediente ingrediente = new Ingrediente();
//        ingrediente.setNombre("Sal");
//        ingrediente.setUnidadMedida(UnidadMedida.gm);
//
//        ingredienteDAO.registrarIngrediente(ingrediente);

        Ingrediente buscado = ingredienteDAO.buscarPorUnidad(UnidadMedida.gm);

        assertNotNull(buscado, "El ingrediente con la unidad GRAMO debe existir.");
        assertEquals(UnidadMedida.gm, buscado.getUnidadMedida());
    }

    @Test
    public void testEditarIngrediente() throws PersistenciaException {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("Pimienta");
        ingrediente.setUnidadMedida(UnidadMedida.gm);

        ingrediente = ingredienteDAO.registrarIngrediente(ingrediente);

        ingrediente.setNombre("Pimienta Negra");
        Ingrediente actualizado = ingredienteDAO.editarIngrediente(ingrediente);

        assertEquals("Pimienta Negra", actualizado.getNombre(), "El nombre debe haberse actualizado.");
    }

    @Test
    public void testEliminarIngrediente() throws PersistenciaException {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("Ajo");
        ingrediente.setUnidadMedida(UnidadMedida.pz);

        ingrediente = ingredienteDAO.registrarIngrediente(ingrediente);
        Ingrediente eliminado = ingredienteDAO.eliminarIngrediente(ingrediente);

        assertEquals(ingrediente.getId(), eliminado.getId(), "El ingrediente eliminado debe coincidir con el registrado.");
    }

    @Test
    public void testObtenerIngredientesPorID() throws PersistenciaException {
        List<Ingrediente> ingredientes = ingredienteDAO.obtenerIngredientesPorID();
        assertNotNull(ingredientes, "La lista ordenada por ID no debe ser nula.");
    }
}
