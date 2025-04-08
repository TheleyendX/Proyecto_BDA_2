/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOs.IngredienteDTO;
import static ENUM.UnidadMedida.gm;
import static ENUM.UnidadMedida.ml;
import Excepciones.NegocioException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author jorge
 */
public class IngredienteBOTest {
  private IngredienteBO ingredienteBO;

    @BeforeEach
    public void setUp() {
        ingredienteBO = new IngredienteBO();
    }

    @Test
    public void testRegistrarIngredienteValido() {
        IngredienteDTO ingrediente = new IngredienteDTO();
        ingrediente.setNombre("Harina");
        ingrediente.setUndadMedida(gm);
        ingrediente.setStock(100.00);

        try {
            IngredienteDTO registrado = ingredienteBO.registrarIngrediente(ingrediente);
            assertNotNull(registrado);
            assertEquals("Harina", registrado.getNombre());
            assertEquals(gm, registrado.getUndadMedida());
            assertEquals(100, registrado.getStock());
        } catch (NegocioException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }

    @Test
    public void testRegistrarIngredienteDuplicado() {
        IngredienteDTO ingrediente1 = new IngredienteDTO();
        ingrediente1.setNombre("Arroz");
        ingrediente1.setUndadMedida(gm);
        ingrediente1.setStock(50.00);

        IngredienteDTO ingrediente2 = new IngredienteDTO();
        ingrediente2.setNombre("Arroz");  // Mismo nombre
        ingrediente2.setUndadMedida(gm);
        ingrediente2.setStock(20.00);

        try {
            ingredienteBO.registrarIngrediente(ingrediente1);
        } catch (NegocioException e) {
            fail("No debería fallar el primer registro: " + e.getMessage());
        }

        NegocioException ex = assertThrows(NegocioException.class, () -> {
            ingredienteBO.registrarIngrediente(ingrediente2);
        });
        assertTrue(ex.getMessage().contains("ingrediente duplicado"));
    }

    @Test
    public void testBuscarIngredientePorNombre() {
        IngredienteDTO ingrediente = new IngredienteDTO();
        ingrediente.setNombre("Azúcar");
        ingrediente.setUndadMedida(gm);
        ingrediente.setStock(200.00);

        try {
            ingredienteBO.registrarIngrediente(ingrediente);
            IngredienteDTO encontrado = ingredienteBO.buscarPorNombre("Azúcar");
            assertNotNull(encontrado);
            assertEquals("Azúcar", encontrado.getNombre());
        } catch (NegocioException e) {
            fail("Error al registrar o buscar ingrediente: " + e.getMessage());
        }
    }

    @Test
    public void testBuscarIngredientePorNombreNoExistente() {
        NegocioException ex = assertThrows(NegocioException.class, () -> {
            ingredienteBO.buscarPorNombre("Sal");  // Ingrediente no registrado
        });
        assertTrue(ex.getMessage().contains("No se encontró el ingrediente"));
    }

    @Test
    public void testEditarIngrediente() {
        IngredienteDTO ingrediente = new IngredienteDTO();
        ingrediente.setNombre("Leche");
        ingrediente.setUndadMedida(ml);
        ingrediente.setStock(10.00);

        try {
            IngredienteDTO registrado = ingredienteBO.registrarIngrediente(ingrediente);
            registrado.setStock(15.00);  // Cambiar stock
            IngredienteDTO actualizado = ingredienteBO.editarIngrediente(registrado);
            assertEquals(15, actualizado.getStock());
        } catch (NegocioException e) {
            fail("No debería fallar la edición: " + e.getMessage());
        }
    }

    @Test
    public void testEliminarIngrediente() {
        IngredienteDTO ingrediente = new IngredienteDTO();
        ingrediente.setNombre("Manteca");
        ingrediente.setUndadMedida(gm);
        ingrediente.setStock(25.00);

        try {
            IngredienteDTO registrado = ingredienteBO.registrarIngrediente(ingrediente);
            ingredienteBO.eliminarIngrediente(Long.MIN_VALUE);
            NegocioException ex = assertThrows(NegocioException.class, () -> {
                ingredienteBO.buscarPorNombre("Manteca");
            });
            assertTrue(ex.getMessage().contains("No se encontró el ingrediente"));
        } catch (NegocioException e) {
            fail("No debería fallar la eliminación: " + e.getMessage());
        }
    }

    @Test
    public void testFiltrarIngredientesPorUnidadMedida() {
        IngredienteDTO ingrediente1 = new IngredienteDTO();
        ingrediente1.setNombre("Aceite");
        ingrediente1.setUndadMedida(ml);
        ingrediente1.setStock(50.00);

        IngredienteDTO ingrediente2 = new IngredienteDTO();
        ingrediente2.setNombre("Sal");
        ingrediente2.setUndadMedida(gm);
        ingrediente2.setStock(30.00);

        try {
            ingredienteBO.registrarIngrediente(ingrediente1);
            ingredienteBO.registrarIngrediente(ingrediente2);

            List<IngredienteDTO> resultado = ingredienteBO.buscarPorUnidad(gm);
            assertFalse(resultado.isEmpty());
            assertEquals("Arroz", resultado.get(0).getNombre());
        } catch (NegocioException e) {
            fail("Error al filtrar ingredientes: " + e.getMessage());
        }
    }
}
