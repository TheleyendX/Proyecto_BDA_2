/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;


import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import BO.ReporteClientesFrecuentesBO;
import DTOs.ClienteFrecuenteDTO;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author katia
 */
public class ReporteClientesFrecuentes extends javax.swing.JFrame {

    private JTextField txtNombre, txtVisitasMin;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private ReporteClientesFrecuentesBO reporteBO;
    
    /**
     * Creates new form ReporteClientesFrecuentes
     */
    public ReporteClientesFrecuentes() {
        this.reporteBO = new ReporteClientesFrecuentesBO();

        setTitle("Reporte clientes frecuentes");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(20, 20, 20));

        JLabel titulo = new JLabel("Clientes Frecuentes");
        titulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 36));
        //titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(new Color(128, 0, 64));
        titulo.setBounds(340, 20, 400, 50);
        add(titulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(730, 100, 200, 30);
        lblNombre.setForeground(Color.WHITE);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(730, 130, 220, 30);
        add(txtNombre);

        JLabel lblVisitasMin = new JLabel("Número mínimo visitas:");
        lblVisitasMin.setBounds(730, 180, 200, 30);
        lblVisitasMin.setForeground(Color.WHITE);
        add(lblVisitasMin);

        txtVisitasMin = new JTextField();
        txtVisitasMin.setBounds(730, 210, 220, 30);
        add(txtVisitasMin);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(770, 260, 140, 35);
        btnBuscar.setBackground(new Color(255, 153, 255));
        btnBuscar.addActionListener(e -> buscarClientes());
        add(btnBuscar);

        modeloTabla = new DefaultTableModel(new String[] {
            "Nombre", "Número visitas", "Total gastado", "Puntos fidelidad", "Fecha última comanda"
        }, 0);
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaClientes);
        scroll.setBounds(60, 100, 640, 600);
        add(scroll);

        JButton btnPDF = new JButton("Generar PDF");
        btnPDF.setBounds(730, 350, 220, 45);
        btnPDF.setBackground(new Color(255, 153, 255));
        btnPDF.addActionListener(e -> generarPDF());
        add(btnPDF);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(730, 410, 220, 45);
        btnAtras.setBackground(new Color(128, 0, 64));
        btnAtras.setForeground(Color.WHITE);
        btnAtras.addActionListener(e -> System.exit(0));
        add(btnAtras);

        setVisible(true);
        
        btnAtras.addActionListener(e -> {
            this.dispose();
            new MenuReportes().setVisible(true);
        });
        buscarClientes();
        //initComponents();
    }
    
    
    
    private void buscarClientes() {
        String nombre = txtNombre.getText();
        Integer visitasMin = null;

        if (!txtVisitasMin.getText().trim().isEmpty()) {
            try {
                visitasMin = Integer.parseInt(txtVisitasMin.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El número mínimo de visitas debe ser un entero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try {
            List<ClienteFrecuenteDTO> clientes = reporteBO.obtenerClientesFrecuentesPorFiltro(nombre, visitasMin);
            modeloTabla.setRowCount(0);
            for (ClienteFrecuenteDTO c : clientes) {
                modeloTabla.addRow(new Object[]{
                    c.getNombreCompleto(),
                    c.getConteoVisitas(),
                    String.format("$%.2f", c.getGastoTotalAcumulado()),
                    c.getPuntos(),
                    c.getUltimaFechaComanda() != null ? c.getUltimaFechaComanda().toString() : "Sin registro"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void generarPDF() {
            try {
            String nombreFiltro = txtNombre.getText();
            Integer visitasMinimas = txtVisitasMin.getText().isEmpty() ? null : Integer.parseInt(txtVisitasMin.getText());

            List<ClienteFrecuenteDTO> clientes = reporteBO.obtenerClientesFrecuentesPorFiltro(nombreFiltro, visitasMinimas);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar reporte PDF");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivo PDF", "pdf"));

            int seleccion = fileChooser.showSaveDialog(this);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
                String rutaArchivo = archivoSeleccionado.getAbsolutePath();
                if (!rutaArchivo.toLowerCase().endsWith(".pdf")) {
                    rutaArchivo += ".pdf";
                }
                generarPDFClientes(clientes, rutaArchivo);

                JOptionPane.showMessageDialog(this, "Reporte generado exitosamente en:\n" + rutaArchivo);
                Desktop.getDesktop().open(new File(rutaArchivo));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void generarPDFClientes(List<ClienteFrecuenteDTO> clientes, String rutaDestino) throws Exception {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(rutaDestino));
        document.open();

        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, new BaseColor(0, 102, 153));
        Paragraph titulo = new Paragraph("Reporte de Clientes Frecuentes", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);

        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{3, 2, 2, 2, 3});

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        BaseColor headerColor = new BaseColor(0, 102, 153);
        String[] headers = {"Nombre", "Visitas", "Total Gastado", "Puntos", "Última Comanda"};

        for (String h : headers) {
            PdfPCell celda = new PdfPCell(new Phrase(h, headerFont));
            celda.setBackgroundColor(headerColor);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(8);
            tabla.addCell(celda);
        }

        NumberFormat monedaMX = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (ClienteFrecuenteDTO c : clientes) {
            tabla.addCell(crearCelda(c.getNombreCompleto()));
            tabla.addCell(crearCelda(String.valueOf(c.getConteoVisitas())));
            tabla.addCell(crearCelda(monedaMX.format(c.getGastoTotalAcumulado())));
            tabla.addCell(crearCelda(String.valueOf(c.getPuntos())));
            tabla.addCell(crearCelda(c.getUltimaFechaComanda() != null ? dtf.format(c.getUltimaFechaComanda()) : "Sin registro"));

        }

        document.add(tabla);
        document.close();
    }
    
    private PdfPCell crearCelda(String texto) {
        PdfPCell celda = new PdfPCell(new Phrase(texto));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(6);
        return celda;
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
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(ReporteClientesFrecuentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteClientesFrecuentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteClientesFrecuentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteClientesFrecuentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteClientesFrecuentes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
