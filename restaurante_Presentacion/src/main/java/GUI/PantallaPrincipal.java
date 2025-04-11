/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author katia
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form PantallaPrincipal
     */
    private JButton BtnComandas;
    private JButton BtnProductos;
    private JButton BtnIngredientes;
    private JButton BtnClientesFrecuentes;
    private JButton BtnReportes;
    private JButton BtnInsercion;
    private JButton BtnCerrarSesion;
    
    public PantallaPrincipal() {
        initComponents2();
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(18, 18, 18));
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

  

    private void initComponents2() {
        JLabel titulo = new JLabel("Menú");
        BtnComandas = new JButton("Comandas");
        BtnProductos = new JButton("Productos");
        BtnIngredientes = new JButton("Ingredientes");
        BtnClientesFrecuentes = new JButton("Clientes Frecuentes");
        BtnReportes = new JButton("Reportes");
        BtnInsercion = new JButton("Insercion Masiva");
        BtnCerrarSesion = new JButton("Cerrar Sesion");
        
        
        BtnComandas.setPreferredSize(new Dimension(300, 60));
        BtnProductos.setPreferredSize(new Dimension(300, 60));
        BtnIngredientes.setPreferredSize(new Dimension(300, 60));
        BtnClientesFrecuentes.setPreferredSize(new Dimension(300, 60));
        BtnReportes.setPreferredSize(new Dimension(300, 60));
        BtnInsercion.setPreferredSize(new Dimension(300, 60));
        BtnCerrarSesion.setPreferredSize(new Dimension(150, 50));

        // Estilos
        titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
        titulo.setForeground(new Color(153, 0, 77)); // Bordó
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        Color rosa = new Color(242, 122, 224);
        Font botonFont = new Font("SansSerif", Font.BOLD, 16);

        for (JButton btn : new JButton[]{BtnComandas, BtnProductos, BtnIngredientes,BtnClientesFrecuentes, BtnReportes, BtnInsercion, BtnCerrarSesion}) {
            btn.setBackground(rosa);
            btn.setFont(botonFont);
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);
            btn.setMinimumSize(new Dimension(300, 50));
            btn.setMaximumSize(new Dimension(300, 50));
        }

        BtnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaPrincipal.this.dispose();
                new Inicio().setVisible(true);
            }
        });
        
        BtnClientesFrecuentes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaPrincipal.this.dispose();
                new MenuClientesFrecuentes().setVisible(true);
            }
        });
        
        BtnReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaPrincipal.this.dispose();
                new MenuReportes().setVisible(true);
            }
        });
        
         BtnIngredientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaPrincipal.this.dispose();
                new Ingrediente().setVisible(true);
            }
        });
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titulo, gbc);

        gbc.gridy++;
        add(BtnComandas, gbc);

        gbc.gridy++;
        add(BtnProductos, gbc);

        gbc.gridy++;
        add(BtnIngredientes, gbc);
        
        gbc.gridy++;
        add(BtnClientesFrecuentes, gbc);
        gbc.gridy++;
        add(BtnReportes, gbc);
        gbc.gridy++;
        add(BtnInsercion, gbc);
        gbc.gridy++;
        add(BtnCerrarSesion, gbc);
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
