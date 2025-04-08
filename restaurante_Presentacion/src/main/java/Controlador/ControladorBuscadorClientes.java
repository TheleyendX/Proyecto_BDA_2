/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import BO.ClienteFrecuenteBO;
import DTOs.ClienteFrecuenteDTO;
import Encriptador.Encriptador;
import Excepciones.NegocioException;
import GUI.BuscadorClientes;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



/**
 *
 * @author katia
 */
public class ControladorBuscadorClientes {
    
    private final BuscadorClientes vista;
    private final ClienteFrecuenteBO clienteBO;
    
    public ControladorBuscadorClientes(BuscadorClientes vista, ClienteFrecuenteBO clienteBO) {
        this.vista = vista;
        this.clienteBO = clienteBO;

        cargarTodosLosClientes();

        // Agregamos listeners para búsqueda en tiempo real
        agregarListenersBusqueda();
        
        //vista.getBtnBuscarTelefono().addActionListener(e -> buscarPorTelefono());
    }
    
    private void agregarListenersBusqueda() {
        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }
        };
        vista.getTxtNombre().getDocument().addDocumentListener(listener);
        //vista.getTxtTelefono().getDocument().addDocumentListener(listener);
        vista.getTxtCorreo().getDocument().addDocumentListener(listener);
    }
    
    private void buscar() {
        String nombre = vista.getTxtNombre().getText();
        String telefono = vista.getTxtTelefono().getText();
        String correo = vista.getTxtCorreo().getText();

        try {
            if (telefono != null && !telefono.trim().isEmpty()){
                telefono = Encriptador.encrypt(telefono.trim());
            }
            List<ClienteFrecuenteDTO> resultados = clienteBO.filtrarClientesFrecuentes(nombre, telefono, correo);
            vista.actualizarTabla(resultados);
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(vista, "Error al filtrar clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void buscarPorTelefono(String telefonoEncriptado) {
//        String telefono = vista.getTxtTelefono().getText().trim();
//        
//        if (telefono.isEmpty()) {
//            JOptionPane.showMessageDialog(vista, "Por favor ingrese un número de teléfono completo.");
//            return;
//        }
        
        try {
            // Encriptar el teléfono y realizar la búsqueda
            //telefono = Encriptador.encrypt(telefono);
            System.out.println("Buscando por tel encriptado: "+ telefonoEncriptado);
            List<ClienteFrecuenteDTO> resultados = clienteBO.filtrarClientesFrecuentes(null, telefonoEncriptado, null);
            System.out.println("Resultados encontrados por tel: "+ resultados.size());
            vista.actualizarTabla(resultados);
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(vista, "Error al filtrar clientes por teléfono: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarTodosLosClientes() {
        try {
            List<ClienteFrecuenteDTO> clientes = clienteBO.obtenerTodos();
            vista.actualizarTabla(clientes);
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
