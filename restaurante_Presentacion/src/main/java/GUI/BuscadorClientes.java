/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.ClienteFrecuenteBO;
import Controlador.ControladorBuscadorClientes;
import DTOs.ClienteFrecuenteDTO;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author katia
 */
public class BuscadorClientes extends javax.swing.JFrame {
    private JTable tablaClientes;
    private JTextField txtNombre, txtTelefono, txtCorreo;
    private DefaultTableModel modeloTabla;
    private ControladorBuscadorClientes controlador;

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

        // Título
        JLabel lblTitulo = new JLabel("Buscador Clientes");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(153, 0, 77));
        lblTitulo.setBounds(350, 30, 400, 50);
        add(lblTitulo);

        // Tabla
        String[] columnas = {"Nombre", "Teléfono", "Correo", "Visitas", "Puntos", "Gasto total acumulado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(tablaClientes);
        scroll.setBounds(50, 100, 600, 500);
        add(scroll);

        // Etiquetas y campos
        Color rosa = new Color(242, 122, 224);
        Font fuenteCampos = new Font("SansSerif", Font.BOLD, 14);

        txtNombre = crearCampoConEtiqueta("Nombre:", 700, 150, rosa, fuenteCampos);
        txtTelefono = crearCampoConEtiqueta("Teléfono:", 700, 230, rosa, fuenteCampos);
        txtCorreo = crearCampoConEtiqueta("Correo:", 700, 310, rosa, fuenteCampos);

        // Botón atrás
        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(50, 650, 150, 50);
        btnAtras.setBackground(rosa);
        btnAtras.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnAtras.setFocusPainted(false);
        add(btnAtras);
        
        controlador = new ControladorBuscadorClientes(this, new ClienteFrecuenteBO());
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
    
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }
    
    public void actualizarTabla(List<ClienteFrecuenteDTO> clientes) {
        modeloTabla.setRowCount(0); // Limpiar tabla

        for (ClienteFrecuenteDTO cliente : clientes) {
            Object[] fila = {
                cliente.getNombreCompleto(), // puedes tener un método que concatene nombre + apellidos
                cliente.getTelefono(),
                cliente.getCorreo(),
                cliente.getConteoVisitas(),
                cliente.getPuntos(),
                cliente.getGastoTotalAcumulado()
            };
            modeloTabla.addRow(fila);
    }
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuscadorClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscadorClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscadorClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscadorClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscadorClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
