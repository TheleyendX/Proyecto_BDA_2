/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Conexion.Conexion;
import ENUM.EstadoComanda;
import Entidades.Cliente;
import Entidades.Comanda;
import Entidades.DetallesComanda;
import Entidades.Mesa;
import Entidades.Producto;
import Excepciones.PersistenciaException;
import IDAOs.IComandaDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 *
 * @author mmax2
 */
public class ComandaDAO implements IComandaDAO {

    DetallesComandaDAO dtDAO = new DetallesComandaDAO();

    EntityManager em = Conexion.crearConexion();

    @Override
    public Comanda registraComanda(Cliente cliente, Mesa mesa, List<Producto> productos,
            List<Integer> cantidades, List<Double> precios, List<String> Comentarios) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            Comanda comanda = new Comanda();

            comanda.setEstado(EstadoComanda.Abierto);
            comanda.setFechaHora(LocalDateTime.now());
            comanda.setFolio(generaFolio());
            comanda.setCliente(cliente);
            comanda.setMesa(mesa);

            List<DetallesComanda> detallesComanda = dtDAO.registraDetalles(comanda, productos, cantidades, precios, Comentarios);

            double totalventa = detallesComanda.stream().mapToDouble(DetallesComanda::getImporteTotal).sum();

            comanda.setTotalVenta(totalventa);
            comanda.setDetallesComanda(detallesComanda);

            em.getTransaction().begin();
            em.persist(comanda);
            for (DetallesComanda detalle : detallesComanda) {
                em.persist(detalle);
            }
            em.getTransaction().commit();
            return comanda;
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza el estado de una comanda en la base de datos.
     *
     * @param id El identificador de la comanda cuyo estado se actualizará.
     * @param estado El nuevo estado que se asignará a la comanda.
     * @return La comanda actualizada con el nuevo estado.
     * @throws PersistenciaException Si ocurre un error al buscar o actualizar
     * la comanda.
     */
    @Override
    public Comanda actualizaEstadoComanda(Long id, EstadoComanda estado) throws PersistenciaException {

        Comanda comanda = em.find(Comanda.class, id);

        if (comanda == null) {

            throw new PersistenciaException("La comanda no fue encontrada.");
        }

        comanda.setEstado(estado);

        try {
            em.getTransaction().begin();
            em.merge(comanda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al actualizar el estado de la comanda.", e);
        }

        return comanda;
    }

    @Override
    public String generaFolio() throws PersistenciaException {
        Map<String, Integer> folios = new HashMap<>();

        LocalDateTime hoy = LocalDateTime.now();
        String fecha = hoy.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        int num = folios.getOrDefault(fecha, 0) + 1;
        folios.put(fecha, num);

        String folioStr = String.format("%03d", num);

        return "OB-" + fecha + "-" + folioStr;
    }

}
