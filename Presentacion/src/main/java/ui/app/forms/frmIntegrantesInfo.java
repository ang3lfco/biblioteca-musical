/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.app.forms;

import dtos.ArtistaDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import ui.componentes.RoundedPanel;

/**
 *
 * @author ang3lfco
 */
public class frmIntegrantesInfo extends javax.swing.JFrame {
    private int xMouse, yMouse;
    private List<Integrante> integrantes = new ArrayList<>();
    private ArtistaDTO artista;
    /**
     * Creates new form frmBandaInfo
     */
    public frmIntegrantesInfo(ArtistaDTO artista) {
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        initComponents();
        this.artista = artista;
        jScrollPane1.setBorder(null);
        
        setLocationRelativeTo(null);
        integrantes.add(new Integrante("Kendall", "Schmidt", "Vocalista", LocalDate.of(2009, 1, 1), true, "/perfiles/kendall.png"));
        integrantes.add(new Integrante("James", "Maslow", "Vocalista", LocalDate.of(2009, 1, 1), true, "/perfiles/james.png"));
        integrantes.add(new Integrante("Carlos", "Pena Jr.", "Vocalista", LocalDate.of(2009, 1, 1), true, "/perfiles/carlos.png"));
        integrantes.add(new Integrante("Logan", "Henderson", "Vocalista", LocalDate.of(2009, 1, 1), true, "/perfiles/logan.png"));
        
        RoundedPanel panelPrincipal = new RoundedPanel(50, new Color(18,25,44));
        panelPrincipal.setOpaque(false);
        setContentPane(panelPrincipal);
        
        // Añadir un layout para respetar margenes internos
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(layout);

        // Ajustar márgenes para evitar que los componentes cubran los bordes redondeados
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10) // Márgenes izquierdos
                    .addComponent(pnl_contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addGap(10)) // Márgenes derechos
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10) // Márgenes superiores
                    .addComponent(pnl_contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addGap(10)) // Márgenes inferiores
        );
        
        pnl_contenedor.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xMouse = evt.getXOnScreen();
                yMouse = evt.getYOnScreen();
            }
        });

        pnl_contenedor.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int xActual = evt.getXOnScreen();
                int yActual = evt.getYOnScreen();
                setLocation(getX() + xActual - xMouse, getY() + yActual - yMouse);
                xMouse = xActual;
                yMouse = yActual;
            }
        });

        pnl_contenedor.setOpaque(false);
        cargarIntegrantes();
    }
    
    private void cargarIntegrantes() {
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.removeAll();

        for (Integrante integrante : integrantes) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(30, 36, 60));
            panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
            panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));

            ImageIcon icono = new ImageIcon(getClass().getResource(integrante.getImagen()));
            Image imagenAjustada = icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon iconoAjustado = new ImageIcon(imagenAjustada);
            JLabel lblImagen = new JLabel(iconoAjustado);
            lblImagen.setPreferredSize(new Dimension(50, 50));
            lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel imagenContenedor = new JPanel(new BorderLayout());
            imagenContenedor.setOpaque(false);
            imagenContenedor.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));
            imagenContenedor.add(lblImagen, BorderLayout.CENTER);
            panel.add(imagenContenedor, BorderLayout.WEST);

            String nombreCompleto = integrante.getNombre() + " " + integrante.getApellido();
            JLabel lblNombre = new JLabel(nombreCompleto);
            lblNombre.setForeground(Color.WHITE);
            lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));

            JLabel lblRol = new JLabel(integrante.getRol());
            lblRol.setForeground(Color.LIGHT_GRAY);
            lblRol.setFont(new Font("Segoe UI", Font.PLAIN, 12));

            JPanel textosPanel = new JPanel();
            textosPanel.setLayout(new BoxLayout(textosPanel, BoxLayout.Y_AXIS));
            textosPanel.setOpaque(false);
            textosPanel.add(lblNombre);
            textosPanel.add(lblRol);

            int espacioHorizontal = 14;
            JPanel contenedorCentro = new JPanel();
            contenedorCentro.setLayout(new BoxLayout(contenedorCentro, BoxLayout.X_AXIS));
            contenedorCentro.setOpaque(false);
            lblImagen.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, espacioHorizontal));
            contenedorCentro.add(lblImagen);
            contenedorCentro.add(textosPanel);
            panel.add(contenedorCentro, BorderLayout.CENTER);

            String estado = integrante.isEstadoActivo() ? "Activo" : "Inactivo";
            JLabel lblEstado = new JLabel(estado);
            lblEstado.setForeground(integrante.isEstadoActivo() ? new Color(144, 238, 144) : Color.RED);
            lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
            panel.add(lblEstado, BorderLayout.EAST);

            jPanel1.add(panel);
            jPanel1.add(Box.createVerticalStrut(8));
        }
        jPanel1.revalidate();
        jPanel1.repaint();
    }


    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_contenedor = new javax.swing.JPanel();
        lbl_cerrar = new javax.swing.JLabel();
        lbl_minimizar = new javax.swing.JLabel();
        lbl_maximizar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_contenedor.setBackground(new java.awt.Color(18, 25, 44));

        lbl_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cerrar.png"))); // NOI18N
        lbl_cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_cerrarMouseClicked(evt);
            }
        });

        lbl_minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/minimizar.png"))); // NOI18N
        lbl_minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_minimizarMouseClicked(evt);
            }
        });

        lbl_maximizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/expandir.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(18, 25, 44));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout pnl_contenedorLayout = new javax.swing.GroupLayout(pnl_contenedor);
        pnl_contenedor.setLayout(pnl_contenedorLayout);
        pnl_contenedorLayout.setHorizontalGroup(
            pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_contenedorLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbl_cerrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_minimizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_maximizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnl_contenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnl_contenedorLayout.setVerticalGroup(
            pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_contenedorLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_maximizar)
                    .addComponent(lbl_minimizar)
                    .addComponent(lbl_cerrar))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_cerrarMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lbl_cerrarMouseClicked

    private void lbl_minimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizarMouseClicked
        // TODO add your handling code here:
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lbl_minimizarMouseClicked

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(frmIntegrantesInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmIntegrantesInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmIntegrantesInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmIntegrantesInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmIntegrantesInfo().setVisible(true);
//            }
//        });
//    }
    
    public static class Integrante {
        private String nombre;
        private String apellido;
        private String rol;
        private LocalDate fechaIngreso;
        private boolean estadoActivo;
        private String imagen;

        public Integrante(String nombre, String apellido, String rol, LocalDate fechaIngreso, boolean estadoActivo, String imagen) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.rol = rol;
            this.fechaIngreso = fechaIngreso;
            this.estadoActivo = estadoActivo;
            this.imagen = imagen;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getRol() {
            return rol;
        }

        public void setRol(String rol) {
            this.rol = rol;
        }

        public LocalDate getFechaIngreso() {
            return fechaIngreso;
        }

        public void setFechaIngreso(LocalDate fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
        }

        public boolean isEstadoActivo() {
            return estadoActivo;
        }

        public void setEstadoActivo(boolean estadoActivo) {
            this.estadoActivo = estadoActivo;
        }

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_cerrar;
    private javax.swing.JLabel lbl_maximizar;
    private javax.swing.JLabel lbl_minimizar;
    private javax.swing.JPanel pnl_contenedor;
    // End of variables declaration//GEN-END:variables
}
