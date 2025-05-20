/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.app.forms;

import dtos.UsuarioDTO;
import interfaces.IUsuarioNegocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import ui.app.frmInicio;
import ui.componentes.CustomRoundedButton;
import ui.componentes.CustomRoundedPasswordField;
import ui.componentes.CustomRoundedTextField;
import ui.componentes.RoundedPanel;
import ui.sesion.Sesion;

/**
 *
 * @author ang3lfco
 */
public class frmUsuarioInfo extends javax.swing.JFrame {
    private int xMouse, yMouse;
    private IUsuarioNegocio usuarioNegocio;
    private String operacion = "";
    /**
     * Creates new form frmEditarInfo
     */
    public frmUsuarioInfo(IUsuarioNegocio usuarioNegocio, String operacion) {
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        initComponents();
        this.usuarioNegocio = usuarioNegocio;
        this.operacion = operacion;
        setLocationRelativeTo(null);
        
        RoundedPanel panelPrincipal = new RoundedPanel(50, new Color(18,25,44));
        panelPrincipal.setOpaque(false);
        setContentPane(panelPrincipal);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10)
                    .addComponent(pnl_contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addGap(10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10)
                    .addComponent(pnl_contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addGap(10))
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
        
        SwingUtilities.invokeLater(() -> frmUsuarioInfo.this.getRootPane().requestFocusInWindow());
        
        CustomRoundedTextField nombre = new CustomRoundedTextField("Nombre", "");
        nombre.setBackgroundColor(new Color(23,30,49));
        nombre.setPreferredSize(new Dimension(308,40));
        nombre.setTextColor(Color.WHITE);
        pnl_nombre.setLayout(new BorderLayout());
        pnl_nombre.setBackground(new Color(0,0,0,0));
        pnl_nombre.add(nombre, BorderLayout.CENTER);
        
        CustomRoundedTextField apellido = new CustomRoundedTextField("Apellido", "");
        apellido.setBackgroundColor(new Color(23,30,49));
        apellido.setPreferredSize(new Dimension(308,40));
        apellido.setTextColor(Color.WHITE);
        pnl_apellido.setLayout(new BorderLayout());
        pnl_apellido.setBackground(new Color(0,0,0,0));
        pnl_apellido.add(apellido, BorderLayout.CENTER);
        
        CustomRoundedTextField usuario = new CustomRoundedTextField("Usuario", "");
        usuario.setBackgroundColor(new Color(23,30,49));
        usuario.setPreferredSize(new Dimension(308,40));
        usuario.setTextColor(Color.WHITE);
        pnl_usuario.setLayout(new BorderLayout());
        pnl_usuario.setBackground(new Color(0,0,0,0));
        pnl_usuario.add(usuario, BorderLayout.CENTER);
        
        CustomRoundedPasswordField contrasena = new CustomRoundedPasswordField("Contraseña", "");
        contrasena.setBackgroundColor(new Color(23,30,49));
        contrasena.setPreferredSize(new Dimension(308,40));
        contrasena.setTextColor(Color.WHITE);
        pnl_contrasena.setLayout(new BorderLayout());
        pnl_contrasena.setBackground(new Color(0,0,0,0));
        pnl_contrasena.add(contrasena, BorderLayout.CENTER);
        
        CustomRoundedTextField correo = new CustomRoundedTextField("Correo", "");
        correo.setBackgroundColor(new Color(23,30,49));
        correo.setPreferredSize(new Dimension(308,40));
        correo.setTextColor(Color.WHITE);
        pnl_correo.setLayout(new BorderLayout());
        pnl_correo.setBackground(new Color(0,0,0,0));
        pnl_correo.add(correo, BorderLayout.CENTER);
        
        CustomRoundedButton btnAccion = null;
        if(operacion.equals("editar")){
            nombre.setText(Sesion.getUsuarioActual().getNombre());
            apellido.setText(Sesion.getUsuarioActual().getApellido());
            usuario.setText(Sesion.getUsuarioActual().getUsuario());
            contrasena.setText(Sesion.getUsuarioActual().getContraseña());
            correo.setText(Sesion.getUsuarioActual().getCorreo());
            btnAccion = new CustomRoundedButton("Editar", new Color(180, 30, 90));
                btnAccion.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    UsuarioDTO usuarioDTO = new UsuarioDTO(
                            Sesion.getUsuarioActual().getId(),
                            nombre.getText(),
                            apellido.getText(),
                            usuario.getText(),
                            contrasena.getText(),
                            correo.getText(),
                            "/iconos/usuario.png",
                            null,
                            null
                    );
                    usuarioNegocio.editarUsuario(usuarioDTO);
                    JOptionPane.showMessageDialog(null, "Datos actualizados.");
                    dispose();
                }
            });
        }
        else if(operacion.equals("registrar")){
            btnAccion = new CustomRoundedButton("Registrar", new Color(180, 30, 90));
                btnAccion.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    UsuarioDTO usuarioDTO = new UsuarioDTO(
                            nombre.getText(),
                            apellido.getText(),
                            usuario.getText(),
                            contrasena.getText(),
                            correo.getText(),
                            "/iconos/usuario.png",
                            null,
                            null
                    );
                    usuarioNegocio.registrarUsuario(usuarioDTO);
                    JOptionPane.showMessageDialog(null, "Usuario registrado.");
                    dispose();
                }
            });
        }
        
        btnAccion.setTextColor(Color.WHITE);
        btnAccion.setPreferredSize(new Dimension(308, 40));
        btnAccion.setOpaque(false);
        
        
        
        pnl_editar.setBackground(new Color(0,0,0,0));
        pnl_editar.removeAll();
        pnl_editar.setLayout(new BorderLayout());
        pnl_editar.add(btnAccion);
        pnl_editar.revalidate();
        pnl_editar.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_contenedor = new javax.swing.JPanel();
        pnl_nombre = new javax.swing.JPanel();
        pnl_apellido = new javax.swing.JPanel();
        pnl_usuario = new javax.swing.JPanel();
        pnl_contrasena = new javax.swing.JPanel();
        pnl_correo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnl_editar = new javax.swing.JPanel();
        lbl_cerrar = new javax.swing.JLabel();
        lbl_minimizar = new javax.swing.JLabel();
        lbl_maximizar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_contenedor.setBackground(new java.awt.Color(18, 25, 44));

        pnl_nombre.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnl_nombreLayout = new javax.swing.GroupLayout(pnl_nombre);
        pnl_nombre.setLayout(pnl_nombreLayout);
        pnl_nombreLayout.setHorizontalGroup(
            pnl_nombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnl_nombreLayout.setVerticalGroup(
            pnl_nombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnl_apellido.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnl_apellidoLayout = new javax.swing.GroupLayout(pnl_apellido);
        pnl_apellido.setLayout(pnl_apellidoLayout);
        pnl_apellidoLayout.setHorizontalGroup(
            pnl_apellidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnl_apellidoLayout.setVerticalGroup(
            pnl_apellidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnl_usuario.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnl_usuarioLayout = new javax.swing.GroupLayout(pnl_usuario);
        pnl_usuario.setLayout(pnl_usuarioLayout);
        pnl_usuarioLayout.setHorizontalGroup(
            pnl_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );
        pnl_usuarioLayout.setVerticalGroup(
            pnl_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnl_contrasena.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnl_contrasenaLayout = new javax.swing.GroupLayout(pnl_contrasena);
        pnl_contrasena.setLayout(pnl_contrasenaLayout);
        pnl_contrasenaLayout.setHorizontalGroup(
            pnl_contrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnl_contrasenaLayout.setVerticalGroup(
            pnl_contrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnl_correo.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnl_correoLayout = new javax.swing.GroupLayout(pnl_correo);
        pnl_correo.setLayout(pnl_correoLayout);
        pnl_correoLayout.setHorizontalGroup(
            pnl_correoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );
        pnl_correoLayout.setVerticalGroup(
            pnl_correoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/usuario.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cambiar");

        pnl_editar.setBackground(new java.awt.Color(255, 255, 255));
        pnl_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout pnl_editarLayout = new javax.swing.GroupLayout(pnl_editar);
        pnl_editar.setLayout(pnl_editarLayout);
        pnl_editarLayout.setHorizontalGroup(
            pnl_editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnl_editarLayout.setVerticalGroup(
            pnl_editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

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

        javax.swing.GroupLayout pnl_contenedorLayout = new javax.swing.GroupLayout(pnl_contenedor);
        pnl_contenedor.setLayout(pnl_contenedorLayout);
        pnl_contenedorLayout.setHorizontalGroup(
            pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_contenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_apellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_contrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_correo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(pnl_contenedorLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbl_cerrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_minimizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_maximizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_contenedorLayout.setVerticalGroup(
            pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_contenedorLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_maximizar)
                    .addComponent(lbl_minimizar)
                    .addComponent(lbl_cerrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addComponent(pnl_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnl_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnl_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnl_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnl_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(pnl_editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
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
//            java.util.logging.Logger.getLogger(frmUsuarioInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmUsuarioInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmUsuarioInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmUsuarioInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmUsuarioInfo().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbl_cerrar;
    private javax.swing.JLabel lbl_maximizar;
    private javax.swing.JLabel lbl_minimizar;
    private javax.swing.JPanel pnl_apellido;
    private javax.swing.JPanel pnl_contenedor;
    private javax.swing.JPanel pnl_contrasena;
    private javax.swing.JPanel pnl_correo;
    private javax.swing.JPanel pnl_editar;
    private javax.swing.JPanel pnl_nombre;
    private javax.swing.JPanel pnl_usuario;
    // End of variables declaration//GEN-END:variables
}
