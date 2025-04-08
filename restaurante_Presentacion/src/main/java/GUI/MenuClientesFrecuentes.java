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
public class MenuClientesFrecuentes extends javax.swing.JFrame {

    private JButton btnAtras;
    private JButton btnRegistrar;
    private JButton btnBuscador;
    
    
    
    /**
     * Creates new form MenuClientesFrecuentes
     */
    public MenuClientesFrecuentes() {
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
            java.util.logging.Logger.getLogger(MenuClientesFrecuentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuClientesFrecuentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuClientesFrecuentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuClientesFrecuentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuClientesFrecuentes().setVisible(true);
            }
        });
    }
    
    private void initComponents2() {
        JLabel titulo = new JLabel("Clientes Frecuentes");
        btnRegistrar = new JButton("Registrar");
        btnBuscador = new JButton("Buscador");
        btnAtras = new JButton("Atrás");
        
        btnRegistrar.setPreferredSize(new Dimension(300, 60));
        btnBuscador.setPreferredSize(new Dimension(300, 60));
        btnAtras.setPreferredSize(new Dimension(150, 50));

        // Estilos
        titulo.setFont(new Font("SansSerif", Font.BOLD, 50));
        titulo.setForeground(new Color(153, 0, 77)); // Bordó
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        Color rosa = new Color(242, 122, 224);
        Font botonFont = new Font("SansSerif", Font.BOLD, 16);

        for (JButton btn : new JButton[]{btnRegistrar, btnBuscador, btnAtras}) {
            btn.setBackground(rosa);
            btn.setFont(botonFont);
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);
        }

        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuClientesFrecuentes.this.dispose();
                new PantallaPrincipal().setVisible(true);
            }
        });
        
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuClientesFrecuentes.this.dispose();
                new RegistrarCliente().setVisible(true);
            }
        });
        
        btnBuscador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuClientesFrecuentes.this.dispose();
                new BuscadorClientes().setVisible(true);
            }
        });
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titulo, gbc);

        gbc.gridy++;
        add(btnRegistrar, gbc);

        gbc.gridy++;
        add(btnBuscador, gbc);

        gbc.gridy++;
        add(btnAtras, gbc);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
