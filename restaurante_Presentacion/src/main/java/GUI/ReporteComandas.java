/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.ReporteComandasBO;
import Entidades.Comanda;
import Excepciones.NegocioException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
//import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author katia
 */
public class ReporteComandas extends javax.swing.JFrame {
    private ReporteComandasBO reporteBO = new ReporteComandasBO();
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotal;
    private JDateChooser dateChooserInicio;
    private JDateChooser dateChooserFin;

    /**
     * Creates new form ReporteComandas
     */
    public ReporteComandas() {
        setTitle("Reporte comandas");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(20, 20, 20)); // Fondo oscuro

        JLabel lblTitulo = new JLabel("Comandas");
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 36));
        lblTitulo.setForeground(new Color(204, 0, 102));
        lblTitulo.setBounds(400, 20, 300, 40);
        add(lblTitulo);

        modeloTabla = new DefaultTableModel(new Object[]{"Fecha", "Hora", "Mesa", "Total venta", "Estado", "Cliente"}, 0);
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(30);
        tabla.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        tabla.getTableHeader().setBackground(new Color(128, 0, 64));
        tabla.getTableHeader().setForeground(Color.BLACK);
        //tabla.setBackground(new Color(255, 182, 255));
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(60, 100, 640, 400);
        add(scroll);

        JLabel lblFechaInicio = new JLabel("Fecha inicio");
        lblFechaInicio.setBounds(740, 100, 200, 20);
        lblFechaInicio.setForeground(Color.WHITE);
        add(lblFechaInicio);
        
        dateChooserInicio = new JDateChooser();
        dateChooserInicio.setBounds(740, 120, 200, 30);
        dateChooserInicio.setDateFormatString("dd/MM/yyyy");
        add(dateChooserInicio);

        JLabel lblFechaFin = new JLabel("Fecha fin");
        lblFechaFin.setBounds(740, 170, 200, 20);
        lblFechaFin.setForeground(Color.WHITE);
        add(lblFechaFin);

        dateChooserFin = new JDateChooser();
        dateChooserFin.setBounds(740, 190, 200, 30);
        dateChooserFin.setDateFormatString("dd/MM/yyyy");
        add(dateChooserFin);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(760, 240, 160, 40);
        btnBuscar.setBackground(new Color(255, 153, 255));
        btnBuscar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        btnBuscar.addActionListener(e -> buscarComandas());
        add(btnBuscar);

        JPanel panelTotal = new JPanel();
        panelTotal.setLayout(null);
        panelTotal.setBounds(60, 520, 640, 50);
        panelTotal.setBackground(new Color(255, 153, 255));
        add(panelTotal);

        JLabel lblTextoTotal = new JLabel("Total acumulado ventas");
        lblTextoTotal.setBounds(10, 10, 300, 30);
        lblTextoTotal.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        panelTotal.add(lblTextoTotal);

        lblTotal = new JLabel("0.00");
        lblTotal.setBounds(400, 10, 150, 30);
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotal.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        panelTotal.add(lblTotal);

        JButton btnPDF = new JButton("Generar PDF");
        btnPDF.setBounds(740, 320, 200, 45);
        btnPDF.setBackground(new Color(255, 153, 255));
        btnPDF.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        btnPDF.addActionListener(e -> generarPDF());
        add(btnPDF);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(740, 380, 200, 45);
        btnAtras.setBackground(new Color(128, 0, 64));
        btnAtras.setForeground(Color.WHITE);
        btnAtras.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        add(btnAtras);

        setVisible(true);
        
        buscarComandas();
        btnAtras.addActionListener(e -> {
            this.dispose();
            new MenuReportes().setVisible(true);
        });
        //initComponents();
    }
    
    private void buscarComandas(){
        try{
            LocalDateTime fechaInicio = convertirADateTime(dateChooserInicio.getDate());
            LocalDateTime fechaFin = convertirADateTime(dateChooserFin.getDate());

            List<Comanda> comandas = reporteBO.obtenerComandasPorFecha(fechaInicio, fechaFin);
            Double total = reporteBO.obtenerTotalAcumuladoVentas(fechaInicio, fechaFin);

            cargarTablaComandas(comandas);
            lblTotal.setText(String.format("%.2f", total));
        }catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private LocalDateTime convertirADateTime(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private void cargarTablaComandas(List<Comanda> comandas) {
        modeloTabla.setRowCount(0);
        for (Comanda c : comandas) {
            Object[] fila = {
                c.getFechaHora().toLocalDate(),
                c.getFechaHora().toLocalTime(),
                c.getMesa(),
                c.getTotalVenta(),
                c.getEstado().toString(),
                c.getCliente() != null ? c.getCliente().getNombreCompleto() : "Sin cliente"
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void generarPDF() {
        Document documento = new Document();
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar reporte PDF");

            int seleccion = fileChooser.showSaveDialog(this);
            if (seleccion != JFileChooser.APPROVE_OPTION) return;

            File archivo = fileChooser.getSelectedFile();
            String rutaArchivo = archivo.getAbsolutePath();
            if (!rutaArchivo.toLowerCase().endsWith(".pdf")) {
                rutaArchivo += ".pdf";
            }
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();

            
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD, new BaseColor(128, 0, 64));
            Paragraph titulo = new Paragraph("Reporte de Comandas", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            documento.add(titulo);

            PdfPTable tablaPDF = new PdfPTable(modeloTabla.getColumnCount());
            tablaPDF.setWidthPercentage(100);
            tablaPDF.setSpacingBefore(10f);
            tablaPDF.setSpacingAfter(10f);

            for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
                Font letrasHeader = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(255, 255, 255));
                PdfPCell header = new PdfPCell(new Phrase(modeloTabla.getColumnName(i), letrasHeader));
                header.setBackgroundColor(new BaseColor(128, 0, 64));
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablaPDF.addCell(header);
            }

            for (int fila = 0; fila < modeloTabla.getRowCount(); fila++) {
                for (int col = 0; col < modeloTabla.getColumnCount(); col++) {
                    Object valor = modeloTabla.getValueAt(fila, col);
                    tablaPDF.addCell(valor != null ? valor.toString() : "");
                }
            }

            documento.add(tablaPDF);

            Font totalFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Paragraph total = new Paragraph("Total acumulado de ventas: $" + lblTotal.getText(), totalFont);
            total.setAlignment(Element.ALIGN_CENTER);
            documento.add(total);

            JOptionPane.showMessageDialog(this, "PDF generado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (documento.isOpen()) {
                documento.close();
            }
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
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
