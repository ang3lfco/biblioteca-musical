/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.app.usuario;

import dtos.GeneroDTO;
import dtos.UsuarioDTO;
import interfaces.IGeneroNegocio;
import interfaces.IUsuarioNegocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

/**
 *
 * @author ang3lfco
 */
public class pnlNoDeseados extends javax.swing.JPanel {
    List<Map<String, Object>> elementos = new ArrayList<>();
    private IUsuarioNegocio usuarioNegocio;
    private IGeneroNegocio generoNegocio;
    private UsuarioDTO.NoDeseadosDTO generosNoDeseadosIds;
    private List<GeneroDTO> generosNoDeseados = new ArrayList<>();
    /**
     * Creates new form pnlFavoritos
     */
    public pnlNoDeseados(IUsuarioNegocio usuarioNegocio, IGeneroNegocio generoNegocio) {
        initComponents();
        iniciarFlechasScroll();
        this.usuarioNegocio = usuarioNegocio;
        this.generoNegocio = generoNegocio;
        this.generosNoDeseadosIds = usuarioNegocio.getNoDeseados("682856dbe09c84ef98441541");
        
        for(String s : generosNoDeseadosIds.getGeneros()){
            generosNoDeseados.add(generoNegocio.buscarGeneroPorId(s));
        }
        
        cargarNoDeseados();
        
        jScrollPane_NoDeseados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane_NoDeseados.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane_NoDeseados.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        jScrollPane_NoDeseados.getVerticalScrollBar().setUnitIncrement(14);

        jScrollPane_NoDeseados.setViewportBorder(null);
        jScrollPane_NoDeseados.setBorder(null);
        
        lblFlechaArriba.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JScrollBar barra = jScrollPane_NoDeseados.getVerticalScrollBar();
                int paso = 60;
                barra.setValue(barra.getValue() - paso);
            }
        });

        lblFlechaAbajo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JScrollBar barra = jScrollPane_NoDeseados.getVerticalScrollBar();
                int paso = 60;
                barra.setValue(barra.getValue() + paso);
            }
        });
    }
    
    private void cargarNoDeseados() {
        jPanel1_noDeseados.setLayout(new BoxLayout(jPanel1_noDeseados, BoxLayout.Y_AXIS));
        jPanel1_noDeseados.removeAll();

        // --- Sección de Géneros no deseados ---
        for (GeneroDTO genero : generosNoDeseados) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(30, 36, 60));
            panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
            panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

//            JLabel lblImg;
//            try {
//                ImageIcon icono = new ImageIcon(getClass().getResource(album.getRutaImagen()));
//                Image imagenAjustada = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
//                lblImg = new JLabel(new ImageIcon(imagenAjustada));
//            } catch (Exception e) {
//                lblImg = new JLabel("Sin imagen");
//            }
//            lblImg.setPreferredSize(new Dimension(70, 70));
//            panel.add(lblImg, BorderLayout.WEST);

            JPanel texto = new JPanel();
            texto.setLayout(new BoxLayout(texto, BoxLayout.X_AXIS));
            texto.setOpaque(false);
            texto.add(Box.createHorizontalStrut(60));

            JLabel lblNombre = new JLabel(genero.getNombre());
            lblNombre.setForeground(Color.WHITE);
            lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblNombre.setPreferredSize(new Dimension(250, 30));
            lblNombre.setMaximumSize(new Dimension(250, 30));

            JLabel lblTipo = new JLabel("Bloqueado");
            lblTipo.setForeground(Color.LIGHT_GRAY);
            lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

            texto.add(lblNombre);
            texto.add(Box.createHorizontalStrut(5));
            texto.add(lblTipo);

            panel.add(texto, BorderLayout.CENTER);

            JLabel btnNoDeseado;
            try {
                ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/noDeseado.png"));
                Image imagenAjustada = icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                btnNoDeseado = new JLabel(new ImageIcon(imagenAjustada));
            } catch (Exception e) {
                btnNoDeseado = new JLabel("X");
            }
            btnNoDeseado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnNoDeseado.setHorizontalAlignment(SwingConstants.CENTER);
            btnNoDeseado.setPreferredSize(new Dimension(50, 50));
            btnNoDeseado.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    JDialog dialogoCargando = mostrarDialogoCargando();
                    SwingWorker<Void, Void> worker = new SwingWorker<>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            generosNoDeseados.remove(genero);
                            return null;
                        }
                        @Override
                        protected void done() {
                            cargarNoDeseados();
                            dialogoCargando.dispose();
                        }
                    };
                    worker.execute();
                }
            });
            panel.add(btnNoDeseado, BorderLayout.EAST);

            jPanel1_noDeseados.add(panel);
            jPanel1_noDeseados.add(Box.createVerticalStrut(8));
        }

        

        jPanel1_noDeseados.revalidate();
        jPanel1_noDeseados.repaint();
    }
    
    private JDialog mostrarDialogoCargando() {
        JDialog dialogo = new JDialog();
        dialogo.setUndecorated(true);
        dialogo.setModal(false);
        dialogo.setSize(250, 100);
        dialogo.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(0, 0, 0, 50));
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 30, 30);

                g2.setColor(new Color(180, 30, 90, 230));
                g2.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 10, 30, 30);

                g2.dispose();
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        panel.setOpaque(false);

        JLabel lbl = new JLabel("Eliminando de No Deseados...");
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lbl);

        dialogo.setBackground(new Color(0, 0, 0, 0));
        dialogo.getContentPane().add(panel);
        dialogo.setVisible(true);

        return dialogo;
    }
    
    private void iniciarFlechasScroll() {
        lblFlechaArriba.setText("↑");
        lblFlechaArriba.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblFlechaArriba.setForeground(Color.WHITE);
//        lblFlechaArriba.setHorizontalAlignment(SwingConstants.CENTER);
        lblFlechaArriba.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblFlechaArriba.setVisible(false);

        lblFlechaAbajo.setText("↓");
        lblFlechaAbajo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblFlechaAbajo.setForeground(Color.WHITE);
//        lblFlechaAbajo.setHorizontalAlignment(SwingConstants.CENTER);
        lblFlechaAbajo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblFlechaAbajo.setVisible(false);

        jScrollPane_NoDeseados.getVerticalScrollBar().addAdjustmentListener(e -> actualizarFlechasScroll());
    }

    private void actualizarFlechasScroll() {
        JScrollBar barra = jScrollPane_NoDeseados.getVerticalScrollBar();
        int max = barra.getMaximum();
        int visible = barra.getVisibleAmount();
        int value = barra.getValue();

        lblFlechaArriba.setVisible(value > 0);
        lblFlechaAbajo.setVisible(value + visible < max);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane_NoDeseados = new javax.swing.JScrollPane();
        jPanel1_noDeseados = new javax.swing.JPanel();
        lblFlechaArriba = new javax.swing.JLabel();
        lblFlechaAbajo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(23, 30, 49));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("No Deseados");

        jPanel1_noDeseados.setBackground(new java.awt.Color(23, 30, 49));

        javax.swing.GroupLayout jPanel1_noDeseadosLayout = new javax.swing.GroupLayout(jPanel1_noDeseados);
        jPanel1_noDeseados.setLayout(jPanel1_noDeseadosLayout);
        jPanel1_noDeseadosLayout.setHorizontalGroup(
            jPanel1_noDeseadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
        );
        jPanel1_noDeseadosLayout.setVerticalGroup(
            jPanel1_noDeseadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );

        jScrollPane_NoDeseados.setViewportView(jPanel1_noDeseados);

        lblFlechaArriba.setText("jLabel2");

        lblFlechaAbajo.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane_NoDeseados, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFlechaArriba, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(lblFlechaAbajo, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane_NoDeseados, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFlechaArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFlechaAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1_noDeseados;
    private javax.swing.JScrollPane jScrollPane_NoDeseados;
    private javax.swing.JLabel lblFlechaAbajo;
    private javax.swing.JLabel lblFlechaArriba;
    // End of variables declaration//GEN-END:variables
}
