/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.sesion;

import daos.AlbumDAO;
import daos.ArtistaDAO;
import daos.CancionDAO;
import daos.GeneroDAO;
import daos.PersonaDAO;
import daos.UsuarioDAO;
import dtos.UsuarioDTO;
import interfaces.IAlbumDAO;
import interfaces.IAlbumNegocio;
import interfaces.IUsuarioDAO;
import interfaces.IUsuarioNegocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import negocio.AlbumNegocio;
import negocio.ArtistaNegocio;
import negocio.CancionNegocio;
import negocio.UsuarioNegocio;
import ui.app.forms.frmUsuarioInfo;
import ui.app.frmInicio;
import ui.componentes.CustomRoundedButton;
import ui.componentes.CustomRoundedPasswordField;
import ui.componentes.CustomRoundedTextField;
import ui.componentes.RoundedPanel;
import interfaces.IArtistaDAO;
import interfaces.ICancionDAO;
import interfaces.IArtistaNegocio;
import interfaces.ICancionNegocio;
import interfaces.IGeneroDAO;
import interfaces.IGeneroNegocio;
import interfaces.IPersonaDAO;
import interfaces.IPersonaNegocio;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import negocio.GeneroNegocio;
import negocio.PersonaNegocio;

/**
 *
 * @author ang3lfco
 */
public class frmInicioSesion extends javax.swing.JFrame {
    private int xMouse, yMouse;
    private IUsuarioDAO usuarioDAO = new UsuarioDAO();
    private ICancionDAO cancionDAO = new CancionDAO();
    private IAlbumDAO albumDAO = new AlbumDAO();
    private IArtistaDAO artistaDAO = new ArtistaDAO();
    private IGeneroDAO generoDAO = new GeneroDAO();
    private IPersonaDAO personaDAO = new PersonaDAO();
    
    private ICancionNegocio cancionNegocio = new CancionNegocio(cancionDAO);
    private IUsuarioNegocio usuarioNegocio = new UsuarioNegocio(usuarioDAO);
    private IAlbumNegocio albumNegocio = new AlbumNegocio(albumDAO);
    private IArtistaNegocio artistaNegocio = new ArtistaNegocio(artistaDAO);
    private IGeneroNegocio generoNegocio = new GeneroNegocio(generoDAO);
     private IPersonaNegocio personaNegocio = new PersonaNegocio(personaDAO);
    
    /**
     * Creates new form frmInicioSesion
     */
    public frmInicioSesion() {
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        initComponents();
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
        SwingUtilities.invokeLater(() -> frmInicioSesion.this.getRootPane().requestFocusInWindow());
        
        CustomRoundedTextField usuario = new CustomRoundedTextField("Usuario", "");
        usuario.setBackgroundColor(new Color(23,30,49));
        usuario.setPreferredSize(new Dimension(308,40));
        usuario.setTextColor(Color.WHITE);
        pnl_usuario.setLayout(new BorderLayout());
        pnl_usuario.setBackground(new Color(0,0,0,0));
        pnl_usuario.add(usuario, BorderLayout.CENTER);
        
        CustomRoundedPasswordField contrasena = new CustomRoundedPasswordField("Contraseña","");
        contrasena.setBackgroundColor(new Color(23,30,49));
        contrasena.setPreferredSize(new Dimension(308,40));
        contrasena.setTextColor(Color.WHITE);
        pnl_contrasena.setLayout(new BorderLayout());
        pnl_contrasena.setBackground(new Color(0,0,0,0));
        pnl_contrasena.add(contrasena, BorderLayout.CENTER);
        
        CustomRoundedButton iniciar = new CustomRoundedButton("Iniciar sesión ", new Color(30, 180, 180));
        iniciar.setTextColor(Color.WHITE);
        iniciar.setPreferredSize(new Dimension(308, 40));
        iniciar.setOpaque(false);
        
        iniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UsuarioDTO usuarioEnSesion = usuarioNegocio.validarSesion(usuario.getText(), contrasena.getText());
                if(usuarioEnSesion != null){
                    Sesion.iniciarSesion(usuarioEnSesion);
                    frmInicio inicio = new frmInicio(usuarioNegocio, cancionNegocio, albumNegocio, artistaNegocio, generoNegocio, personaNegocio);
                    inicio.setVisible(true);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error, verifique sus datos.");
                }
            }
        });
        
        pnl_iniciar.setBackground(new Color(0,0,0,0));
        pnl_iniciar.removeAll();
        pnl_iniciar.setLayout(new BorderLayout());
        pnl_iniciar.add(iniciar);
        pnl_iniciar.revalidate();
        pnl_iniciar.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_contenedor = new javax.swing.JPanel();
        pnl_usuario = new javax.swing.JPanel();
        pnl_contrasena = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnl_iniciar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_maximizar = new javax.swing.JLabel();
        lbl_minimizar = new javax.swing.JLabel();
        lbl_cerrar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_usuario.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnl_usuarioLayout = new javax.swing.GroupLayout(pnl_usuario);
        pnl_usuario.setLayout(pnl_usuarioLayout);
        pnl_usuarioLayout.setHorizontalGroup(
            pnl_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/image.png"))); // NOI18N

        pnl_iniciar.setBackground(new java.awt.Color(255, 255, 255));
        pnl_iniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_iniciarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnl_iniciarLayout = new javax.swing.GroupLayout(pnl_iniciar);
        pnl_iniciar.setLayout(pnl_iniciarLayout);
        pnl_iniciarLayout.setHorizontalGroup(
            pnl_iniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnl_iniciarLayout.setVerticalGroup(
            pnl_iniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Olvidaste tu contraseña?");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("No tienes una cuenta?");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(30, 180, 180));
        jLabel7.setText("Registrate aqui");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        lbl_maximizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/expandir.png"))); // NOI18N

        lbl_minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/minimizar.png"))); // NOI18N
        lbl_minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_minimizarMouseClicked(evt);
            }
        });

        lbl_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cerrar.png"))); // NOI18N
        lbl_cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_cerrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnl_contenedorLayout = new javax.swing.GroupLayout(pnl_contenedor);
        pnl_contenedor.setLayout(pnl_contenedorLayout);
        pnl_contenedorLayout.setHorizontalGroup(
            pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_contenedorLayout.createSequentialGroup()
                .addGroup(pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_contenedorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnl_contrasena, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnl_iniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_contenedorLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addComponent(pnl_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnl_contenedorLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel4)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel7)
                        .addGap(0, 41, Short.MAX_VALUE)))
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
                .addGap(12, 12, 12)
                .addGroup(pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_maximizar)
                    .addComponent(lbl_minimizar)
                    .addComponent(lbl_cerrar))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(60, 60, 60)
                .addComponent(pnl_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnl_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(pnl_iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addGap(25, 25, 25))
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

    private void pnl_iniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_iniciarMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_pnl_iniciarMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        frmUsuarioInfo registrar = new frmUsuarioInfo(usuarioNegocio, "registrar");
        registrar.setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void lbl_minimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizarMouseClicked
        // TODO add your handling code here:
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lbl_minimizarMouseClicked

    private void lbl_cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_cerrarMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lbl_cerrarMouseClicked

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
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmInicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbl_cerrar;
    private javax.swing.JLabel lbl_maximizar;
    private javax.swing.JLabel lbl_minimizar;
    private javax.swing.JPanel pnl_contenedor;
    private javax.swing.JPanel pnl_contrasena;
    private javax.swing.JPanel pnl_iniciar;
    private javax.swing.JPanel pnl_usuario;
    // End of variables declaration//GEN-END:variables
}
