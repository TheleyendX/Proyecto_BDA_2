/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.ClienteFrecuenteBO;
import DTOs.ClienteFrecuenteDTO;
import Excepciones.NegocioException;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author katia
 */
public class BuscadorClientes extends javax.swing.JFrame {
    private JTable tablaClientes;
    private JTextField txtNombre, txtTelefono, txtCorreo;
    private DefaultTableModel modeloTabla;
    
    private ClienteFrecuenteBO clienteBO;

    /**
     * Creates new form BuscadorClientes
     */
    public BuscadorClientes() {
        initComponents();
        
        setTitle("Buscador de clientes");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(18, 18, 18));
        setLayout(null);

        JLabel lblTitulo = new JLabel("Buscador Clientes");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(153, 0, 77));
        lblTitulo.setBounds(350, 30, 400, 50);
        add(lblTitulo);

        String[] columnas = {"Nombre", "Teléfono", "Correo", "Visitas", "Puntos", "Gasto total acumulado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(tablaClientes);
        scroll.setBounds(50, 100, 600, 500);
        add(scroll);

        Color rosa = new Color(242, 122, 224);
        Font fuenteCampos = new Font("SansSerif", Font.BOLD, 14);

        txtNombre = crearCampoConEtiqueta("Nombre:", 700, 150, rosa, fuenteCampos);
        txtTelefono = crearCampoConEtiqueta("Teléfono:", 700, 230, rosa, fuenteCampos);
        txtCorreo = crearCampoConEtiqueta("Correo:", 700, 310, rosa, fuenteCampos);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(50, 650, 150, 50);
        btnAtras.setBackground(rosa);
        btnAtras.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnAtras.setFocusPainted(false);
        add(btnAtras);
        
        btnAtras.addActionListener(e -> {
            this.dispose();
            new MenuClientesFrecuentes().setVisible(true);
        });

        clienteBO = new ClienteFrecuenteBO();
        
        cargarTodosLosClientes();

        agregarListenersBusqueda();        
    }
    
    private JTextField crearCampoConEtiqueta(String textoEtiqueta, int x, int y, Color fondoEtiqueta, Font fuente) {
        JLabel etiqueta = new JLabel(textoEtiqueta);
        etiqueta.setOpaque(true);
        etiqueta.setBackground(fondoEtiqueta);
        etiqueta.setBounds(x, y, 100, 40);
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        etiqueta.setFont(fuente);
        add(etiqueta);

        JTextField campo = new JTextField();
        campo.setBounds(x + 100, y, 200, 40);
        campo.setFont(fuente);
        add(campo);

        return campo;
    }
    
    public void actualizarTabla(List<ClienteFrecuenteDTO> clientes) {
        modeloTabla.setRowCount(0);

        for (ClienteFrecuenteDTO cliente : clientes) {
            Object[] fila = {
                cliente.getNombreCompleto(), 
                cliente.getTelefono(),
                cliente.getCorreo(),
                cliente.getConteoVisitas(),
                cliente.getPuntos(),
                cliente.getGastoTotalAcumulado()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void cargarTodosLosClientes() {
        try {
            List<ClienteFrecuenteDTO> clientes = clienteBO.obtenerTodos();
            actualizarTabla(clientes);
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        getTxtNombre().getDocument().addDocumentListener(listener);
        getTxtTelefono().getDocument().addDocumentListener(listener);
        getTxtCorreo().getDocument().addDocumentListener(listener);
    }
    
    private void buscar() {
        String nombre = getTxtNombre().getText();
        String telefono = getTxtTelefono().getText().trim();
        String correo = getTxtCorreo().getText().trim();

        try {
            List<ClienteFrecuenteDTO> resultados = clienteBO.filtrarClientesFrecuentes(nombre, telefono, correo);
            actualizarTabla(resultados);
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al filtrar clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
