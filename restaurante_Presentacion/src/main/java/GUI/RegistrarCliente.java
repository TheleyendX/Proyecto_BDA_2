/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.ClienteFrecuenteBO;
import DTOs.ClienteFrecuenteDTO;
import Excepciones.NegocioException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author katia
 */
public class RegistrarCliente extends javax.swing.JFrame {

    private JTextField txtNombre, txtApellidoPaterno, txtApellidoMaterno;
    private JTextField txtTelefono, txtCorreo, txtFecha;
    private JButton btnAtras, btnGuardar;
    /**
     * Creates new form RegistrarCliente
     */
    public RegistrarCliente() {
        setTitle("Registrar Cliente");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(18, 18, 18));
        setLayout(new GridBagLayout());
        initComponents2();
        
    }
    
    private void initComponents2() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titulo = new JLabel("Registrar Cliente");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 40));
        titulo.setForeground(new Color(153, 0, 77));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        // Campos de nombre y apellidos
        add(crearCampo("Nombre:", txtNombre = new JTextField()), gbc);
        gbc.gridy++;

        add(crearCampo("Apellido Paterno:", txtApellidoPaterno = new JTextField()), gbc);
        gbc.gridy++;

        add(crearCampo("Apellido Materno (opcional):", txtApellidoMaterno = new JTextField()), gbc);
        gbc.gridy++;

        // Otros campos
        add(crearCampo("Número de teléfono:", txtTelefono = new JTextField()), gbc);
        gbc.gridy++;

        add(crearCampo("Correo:", txtCorreo = new JTextField()), gbc);
        gbc.gridy++;

        txtFecha = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtFecha.setEditable(false);  
        txtFecha.setBackground(Color.WHITE); 
        add(crearCampo("Fecha de registro:", txtFecha), gbc);
        gbc.gridy++;

        txtNombre.setPreferredSize(new Dimension(300, 40));
        txtApellidoPaterno.setPreferredSize(new Dimension(300, 40));
        txtApellidoMaterno.setPreferredSize(new Dimension(300, 40));
        txtTelefono.setPreferredSize(new Dimension(300, 40));
        txtCorreo.setPreferredSize(new Dimension(300, 40));
        txtFecha.setPreferredSize(new Dimension(300, 40));
                
        // Botones
        btnAtras = new JButton("Atrás");
        btnGuardar = new JButton("Guardar");

        estiloBoton(btnAtras);
        estiloBoton(btnGuardar);

        gbc.gridx = 0;
        add(btnAtras, gbc);

        gbc.gridx = 1;
        add(btnGuardar, gbc);

        // Acción botones
        btnAtras.addActionListener(e -> {
            this.dispose();
            new MenuClientesFrecuentes().setVisible(true);
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClienteFrecuenteDTO dto = new ClienteFrecuenteDTO();
                    dto.setNombre(txtNombre.getText());
                    dto.setApellidoP(txtApellidoPaterno.getText());
                    dto.setApellidoM(txtApellidoMaterno.getText().isEmpty() ? null : txtApellidoMaterno.getText());
                    dto.setTelefono(txtTelefono.getText());
                    dto.setCorreo(txtCorreo.getText());

                    ClienteFrecuenteBO bo = new ClienteFrecuenteBO();
                    ClienteFrecuenteDTO registrado = bo.registrarClienteFrecuente(dto);

                    JOptionPane.showMessageDialog(null, "Cliente registrado con éxito.");
                    limpiarCampos();
                } catch (NegocioException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }            
        });
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");

        txtFecha.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
    
    private JPanel crearCampo(String etiqueta, JTextField campo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(18, 18, 18));

        JLabel lbl = new JLabel(" " + etiqueta);
        lbl.setOpaque(true);
        lbl.setBackground(new Color(242, 122, 224));
        lbl.setPreferredSize(new Dimension(200, 50));
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));

        campo.setPreferredSize(new Dimension(600, 50));
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(lbl, BorderLayout.WEST);
        panel.add(campo, BorderLayout.CENTER);
        return panel;
    }
    
    private void estiloBoton(JButton btn) {
        btn.setPreferredSize(new Dimension(180, 50));
        btn.setBackground(new Color(242, 122, 224));
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
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
