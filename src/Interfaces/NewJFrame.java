package Interfaces;

import Analizadores.parser;
import Analizadores.scanner;
import abstracto.Instruccion;
import excepciones.ErrorS;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.filechooser.FileNameExtensionFilter;
import simbolo.Arbol;
import static simbolo.Arbol.global;
import simbolo.Simbolo;
import simbolo.TablaSimbolos;

public class NewJFrame extends javax.swing.JFrame {
    
    ArrayList<File> listaRutas = new ArrayList<>();
    LinkedList<ErrorS> errores = new LinkedList<>();
    JPanel panelEditor;
    JScrollPane scrollEditor;
    JTextArea textEditor;
    public NewJFrame() {
        initComponents();
        textareaSalida.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        superTab = new javax.swing.JTabbedPane();
        labelEntrada = new javax.swing.JLabel();
        labelConsola = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textareaSalida = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        archivoItemAbrir = new javax.swing.JMenuItem();
        archivoItemNuevo = new javax.swing.JMenuItem();
        archivoItemGuardar = new javax.swing.JMenuItem();
        menuPestanias = new javax.swing.JMenu();
        pestaniasItemCerrar = new javax.swing.JMenuItem();
        menuEjecutar = new javax.swing.JMenu();
        ejecutarItemStart = new javax.swing.JMenuItem();
        menuReportes = new javax.swing.JMenu();
        reportesItemErrores = new javax.swing.JMenuItem();
        reportesItemTabS = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CompiJava");
        setName("FramePrincipal"); // NOI18N

        labelEntrada.setText("Entrada");

        labelConsola.setText("Consola");

        textareaSalida.setColumns(20);
        textareaSalida.setRows(5);
        jScrollPane2.setViewportView(textareaSalida);

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 57, Short.MAX_VALUE))
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEntrada)
                                    .addComponent(superTab, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelEntrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(superTab, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(labelConsola)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuArchivo.setText("Archivo");

        archivoItemAbrir.setText("Abrir");
        archivoItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archivoItemAbrirActionPerformed(evt);
            }
        });
        menuArchivo.add(archivoItemAbrir);

        archivoItemNuevo.setText("Nuevo");
        archivoItemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archivoItemNuevoActionPerformed(evt);
            }
        });
        menuArchivo.add(archivoItemNuevo);

        archivoItemGuardar.setText("Guardar");
        menuArchivo.add(archivoItemGuardar);

        jMenuBar1.add(menuArchivo);

        menuPestanias.setText("Pestanias");

        pestaniasItemCerrar.setText("Cerrar Pestania");
        pestaniasItemCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pestaniasItemCerrarActionPerformed(evt);
            }
        });
        menuPestanias.add(pestaniasItemCerrar);

        jMenuBar1.add(menuPestanias);

        menuEjecutar.setText("Ejecutar");

        ejecutarItemStart.setText("Start");
        ejecutarItemStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarItemStartActionPerformed(evt);
            }
        });
        menuEjecutar.add(ejecutarItemStart);

        jMenuBar1.add(menuEjecutar);

        menuReportes.setText("Reportes");

        reportesItemErrores.setText("Tabla de Errores");
        reportesItemErrores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesItemErroresActionPerformed(evt);
            }
        });
        menuReportes.add(reportesItemErrores);

        reportesItemTabS.setText("Tabla de Simbolos");
        reportesItemTabS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesItemTabSActionPerformed(evt);
            }
        });
        menuReportes.add(reportesItemTabS);

        jMenuBar1.add(menuReportes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private JFileChooser buscarRuta(){
        JFileChooser buscarRuta = new JFileChooser();
        buscarRuta.setDialogTitle("Elegir Archivo");
        FileNameExtensionFilter filtro =  new FileNameExtensionFilter("Archivos df (.jc)", "jc");
        buscarRuta.setFileFilter(filtro);
        return buscarRuta;
    }
    
    private void agregarTextoPanel(JFileChooser buscarRuta, JTextArea textArea, int index){
        File ruta = buscarRuta.getSelectedFile();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(ruta));
            String contenido = "";
            String contenidoAux = "";
                while (contenido != null){
                    contenido = bf.readLine();
                    if (contenido != null){
                        contenidoAux += contenido + "\n";
                    }
                }
            String titulo = ruta.getName();
            bf.close();
            textArea.setText(contenidoAux);
            superTab.setTitleAt(index, titulo);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crearPestania(){
        String contador  = superTab.getTabCount()+ "";
        panelEditor = new JPanel();
        textEditor = new JTextArea();
        scrollEditor = new JScrollPane(textEditor);
        scrollEditor.setPreferredSize(new Dimension(377, 254));
        panelEditor.add(scrollEditor, BorderLayout.CENTER);
        superTab.addTab(contador, panelEditor);
    }
    
    private void crearPestania(String nombre){
        String contador  = superTab.getTabCount()+ "";
        panelEditor = new JPanel();
        textEditor = new JTextArea();
        scrollEditor = new JScrollPane(textEditor);
        scrollEditor.setPreferredSize(new Dimension(377, 254));
        panelEditor.add(scrollEditor, BorderLayout.CENTER);
        superTab.addTab(contador, panelEditor);
        int contador1 = Integer.parseInt(contador);
        superTab.setTitleAt(contador1, nombre);
    }
    
    private JTextArea buscarTexto(int index){
        JPanel panelAux = (JPanel) superTab.getComponentAt(index);
        JScrollPane scrollIndexAux = (JScrollPane) panelAux.getComponent(0);
        JViewport enlaceTextIndex = (JViewport) scrollIndexAux.getComponent(0);
        JTextArea textArea = (JTextArea) enlaceTextIndex.getComponent(0);
        return textArea;
    }
    
    private void archivoItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archivoItemAbrirActionPerformed
        int index = superTab.getSelectedIndex();
        String contenido = "";
        JTextArea textArea = null;
        JFileChooser buscarRuta =  buscarRuta();
        int retorno = buscarRuta.showOpenDialog(this);
       
        if (index == -1) {
            System.out.println("Empezando");
        }else{
            textArea = buscarTexto(index);
            contenido = textArea.getText();
        }
        
        if (retorno == JFileChooser.APPROVE_OPTION){
            if (contenido.equals("") && index == -1){
                crearPestania();
                textArea = buscarTexto(0);
                agregarTextoPanel(buscarRuta,textArea,0);
                listaRutas.add(buscarRuta.getSelectedFile());
            }else if(contenido.equals("")){
                agregarTextoPanel(buscarRuta,textArea,index);
            }else{
                crearPestania();
                int indexCreado = superTab.getTabCount()- 1;
                textArea = buscarTexto(indexCreado);
                agregarTextoPanel(buscarRuta,textArea,indexCreado);
                listaRutas.add(buscarRuta.getSelectedFile());
            }
        }
    }//GEN-LAST:event_archivoItemAbrirActionPerformed

    private void reportesItemTabSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesItemTabSActionPerformed
       String nombreArchivo2 = "Tabla Simbolos.html";

        
        crearArchivoHTML(global, nombreArchivo2);
        abrirArchivoEnNavegador(nombreArchivo2);
    }//GEN-LAST:event_reportesItemTabSActionPerformed

    private void ejecutarItemStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarItemStartActionPerformed
        int index = superTab.getSelectedIndex();
        String contenido = "";
        JTextArea textArea = null;
        textArea = buscarTexto(index);
        contenido = textArea.getText();
        
        try {
            scanner s = new scanner(new BufferedReader(new StringReader(contenido)));
            parser p = new parser(s);
            var resultado = p.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new TablaSimbolos();
            tabla.setNombre("GLOBAL");
            ast.setConsola("");
            LinkedList<ErrorS> lista = new LinkedList<>();
            lista.addAll(s.listaErrores);
            lista.addAll(p.listaErrores);
            for (var a : ast.getInstrucciones()) {
                if (a == null) {
                    continue;
                }

                var res = a.interpretar(ast, tabla);
                if (res instanceof ErrorS) {
                    lista.add((ErrorS) res);
                }
            }
            textareaSalida.setText(ast.getConsola());

            for (var i : lista) {
                System.out.println(i);
            }
             global.add(tabla);
            errores.addAll(lista);

        } catch (Exception ex) {
            System.out.println("Algo salio mal");
            System.out.println(ex);
        }
    }//GEN-LAST:event_ejecutarItemStartActionPerformed

    private void pestaniasItemCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pestaniasItemCerrarActionPerformed
        int index = superTab.getSelectedIndex();
        superTab.remove(index);
        listaRutas.remove(index);
    }//GEN-LAST:event_pestaniasItemCerrarActionPerformed

    private void archivoItemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archivoItemNuevoActionPerformed
        JOptionPane panelEmergente = new JOptionPane();
        String nombreArchivo = panelEmergente.showInputDialog("Escriba el nombre del archivo");
        if (!nombreArchivo.equals("")){
            String ruta = "C:/Users/TeFxiu/Documents/NetBeansProjects/Prueba-Interfaz Compi1/" + nombreArchivo +".df";
            listaRutas.add(new File(ruta));
            System.out.println(ruta);
            crearPestania(nombreArchivo+".df");
        }else {
            System.out.println("Nombre vacio");
        }
            
        
        
    }//GEN-LAST:event_archivoItemNuevoActionPerformed

    public static void abrirArchivoEnNavegador(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                Desktop.getDesktop().browse(archivo.toURI());
                System.out.println("Archivo HTML abierto en el navegador.");
            } else {
                System.out.println("El archivo no existe.");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al abrir el archivo HTML en el navegador.");
            e.printStackTrace();
        }
    }
    public static void crearArchivoHTML(LinkedList lista, String nombreArchivo) {
   
         String inicioHTML = "<!DOCTYPE html>\n<html>\n<head>\n<title>Errores</title>\n<style>\n" +
                            "body {background-color: black; color: white; font-family: Arial, sans-serif;}\n" +
                            "table {width: 100%; border-collapse: collapse;}\n" +
                            "table, th, td {border: 1px solid black;}\n" +
                            "th, td {padding: 8px; text-align: left;}\n" +
                 "th {background-color: #333;}\n" +
                            "tr:nth-child(even) {background-color: #444;}\n" +
                            "</style>\n</head>\n<body>\n<h1>Errores</h1>\n<table>\n";
        String finHTML = "</table>\n</body>\n</html>";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            
            writer.write(inicioHTML);
            for (var errors : lista) {
                if (errors instanceof ErrorS){
                    ErrorS error = (ErrorS)errors;
                    writer.write("<tr>\n");
                    writer.write("<td>" + error.getTipo() + "</td>\n");
                    writer.write("<td>" + error.getDescripcion() + "</td>\n");
                    writer.write("<td>" + error.getLinea() + "</td>\n");
                    writer.write("<td>" + error.getColumna() + "</td>\n");
                    writer.write("</tr>\n");
                }
                if (errors instanceof TablaSimbolos){
                    TablaSimbolos repTabla = (TablaSimbolos)errors;
                    HashMap<String, Object> vetTabla = repTabla.getTablaActual();
                   for (Map.Entry<String, Object> entry : vetTabla.entrySet()) {
                    String id = entry.getKey();
                    Simbolo error = (Simbolo)entry.getValue();
                    writer.write("<tr>\n");
                    writer.write("<td>" + id + "</td>\n");
                    writer.write("<td>" + error.getTipo().getTipo().name() + "</td>\n");
                    writer.write("<td>" + error.getMutabilidad() + "</td>\n");
                    writer.write("<td>" + error.getValor() + "</td>\n");
                    writer.write("<td>" + error.linea + "</td>\n");
                    writer.write("<td>" + error.columna + "</td>\n");
                   writer.write("</tr>\n"); 
                   }
                    
                }
            }
            writer.write(finHTML);

            System.out.println("Archivo HTML creado exitosamente: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo HTML.");
            e.printStackTrace();
        }
    }
    
    private void reportesItemErroresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesItemErroresActionPerformed
       String nombreArchivo = "Tabla Errores.html";

        
        crearArchivoHTML(errores, nombreArchivo);
        abrirArchivoEnNavegador(nombreArchivo);
    }//GEN-LAST:event_reportesItemErroresActionPerformed
 
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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JMenuItem archivoItemAbrir;
    private javax.swing.JMenuItem archivoItemGuardar;
    private javax.swing.JMenuItem archivoItemNuevo;
    private javax.swing.JMenuItem ejecutarItemStart;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelConsola;
    private javax.swing.JLabel labelEntrada;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuEjecutar;
    private javax.swing.JMenu menuPestanias;
    private javax.swing.JMenu menuReportes;
    private javax.swing.JMenuItem pestaniasItemCerrar;
    private javax.swing.JMenuItem reportesItemErrores;
    private javax.swing.JMenuItem reportesItemTabS;
    private javax.swing.JTabbedPane superTab;
    private javax.swing.JTextArea textareaSalida;
    // End of variables declaration//GEN-END:variables
}
