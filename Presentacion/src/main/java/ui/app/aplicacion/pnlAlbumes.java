/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.app.aplicacion;

import interfaces.IAlbumNegocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import ui.componentes.CustomRoundedTextField;
import ui.componentes.RoundedComboBox;

/**
 *
 * @author ang3lfco
 */
public class pnlAlbumes extends javax.swing.JPanel {
    private IAlbumNegocio albumNegocio;
    /**
     * Creates new form pnlCanciones
     */
    public pnlAlbumes(IAlbumNegocio albumNegocio) {
        initComponents();
        this.albumNegocio = albumNegocio;
        iniciarFlechasScroll();
        jScrollPane_albumes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane_albumes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane_albumes.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        jScrollPane_albumes.getVerticalScrollBar().setUnitIncrement(14);

        jScrollPane_albumes.setViewportBorder(null);
        jScrollPane_albumes.setBorder(null);
        cargarCanciones();
        
        String[] items = { "Nombre", "Género", "Fecha de Lanzamiento" };
        RoundedComboBox<String> combo = new RoundedComboBox<>(items);
        combo.setPreferredSize(new Dimension(200, 40));
        pnl_TipoBusqueda.setBackground(new Color(0,0,0,0));
        pnl_TipoBusqueda.setLayout(new BorderLayout());
        pnl_TipoBusqueda.add(combo, BorderLayout.CENTER);
        
        CustomRoundedTextField buscador = new CustomRoundedTextField("Buscar álbumes...", "/iconos/buscar.png");
        pnlBuscador.setLayout(new BorderLayout());
        pnlBuscador.setPreferredSize(new Dimension(330, 40));
        pnlBuscador.setBackground(new Color(0,0,0,0));
        pnlBuscador.add(buscador, BorderLayout.CENTER);
        
        lblFlechaArriba.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JScrollBar barra = jScrollPane_albumes.getVerticalScrollBar();
                int paso = 60;
                barra.setValue(barra.getValue() - paso);
            }
        });

        lblFlechaAbajo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JScrollBar barra = jScrollPane_albumes.getVerticalScrollBar();
                int paso = 60;
                barra.setValue(barra.getValue() + paso);
            }
        });
    }
    
    private void cargarCanciones() {
        List<Album> albumes = new ArrayList<>();
        albumes.add(new Album("BTR", "Big Time Rush", "btr.png"));
        albumes.add(new Album("The Album", "BLACKPINK", "the_album.png"));
        albumes.add(new Album("All Eyez on Me", "2Pac", "all_eyez_on_me.png"));
        albumes.add(new Album("Rolling Papers", "Wiz Khalifa", "rolling_papers.png"));
        albumes.add(new Album("Careless World: Rise of the Last King", "Tyga", "careless_world.png"));
        albumes.add(new Album("Take Care", "Drake", "take_care.png"));
        albumes.add(new Album("After Hours", "The Weeknd", "after_hours.png"));
        albumes.add(new Album("Good Kid, M.A.A.D City", "Kendrick Lamar", "good_kid_maad_city.png"));
        albumes.add(new Album("1989", "Taylor Swift", "1989.png"));
        albumes.add(new Album("Born This Way", "Lady Gaga", "born_this_way.png"));
        

        panelAlbumes.setLayout(new java.awt.GridLayout(0, 3, 10, 10));

        for (Album cancion : albumes) {
            JPanel panel = crearPanelCancion(cancion);
            panelAlbumes.add(panel);
        }

        panelAlbumes.revalidate();
        panelAlbumes.repaint();
    }

    private JPanel crearPanelCancion(Album cancion) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new java.awt.Color(23,30,49));

        int imagenAncho = 0;
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource("/portadas/" + cancion.getPortada()));
            Image imagen = icono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            JLabel lblImagen = new JLabel(new ImageIcon(imagen));
            lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(lblImagen, BorderLayout.CENTER);
            imagenAncho = lblImagen.getPreferredSize().width;
        } catch (Exception e) {
            panel.add(new JLabel("Sin imagen"), BorderLayout.CENTER);
        }

        JPanel contenedorTexto = new JPanel();
        contenedorTexto.setLayout(new BoxLayout(contenedorTexto, BoxLayout.Y_AXIS));
        contenedorTexto.setOpaque(false);

        JLabel lblNombre = new JLabel(cancion.getNombre(), SwingConstants.CENTER);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNombre.setPreferredSize(new Dimension(imagenAncho, lblNombre.getPreferredSize().height));
        lblNombre.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));

        JLabel lblArtista = new JLabel(cancion.getArtista(), SwingConstants.CENTER);
        lblArtista.setForeground(Color.LIGHT_GRAY);
        lblArtista.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblArtista.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblArtista.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));

        contenedorTexto.add(lblNombre);
        contenedorTexto.add(lblArtista);

        panel.add(contenedorTexto, BorderLayout.SOUTH);

        return panel;
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

        jScrollPane_albumes.getVerticalScrollBar().addAdjustmentListener(e -> actualizarFlechasScroll());
    }

    private void actualizarFlechasScroll() {
        JScrollBar barra = jScrollPane_albumes.getVerticalScrollBar();
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

        jScrollPane_albumes = new javax.swing.JScrollPane();
        panelAlbumes = new javax.swing.JPanel();
        lblFlechaArriba = new javax.swing.JLabel();
        lblFlechaAbajo = new javax.swing.JLabel();
        pnlBuscador = new javax.swing.JPanel();
        pnl_TipoBusqueda = new javax.swing.JPanel();

        setBackground(new java.awt.Color(23, 30, 49));

        jScrollPane_albumes.setBackground(new java.awt.Color(23, 30, 49));

        panelAlbumes.setBackground(new java.awt.Color(23, 30, 49));

        javax.swing.GroupLayout panelAlbumesLayout = new javax.swing.GroupLayout(panelAlbumes);
        panelAlbumes.setLayout(panelAlbumesLayout);
        panelAlbumesLayout.setHorizontalGroup(
            panelAlbumesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
        );
        panelAlbumesLayout.setVerticalGroup(
            panelAlbumesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );

        jScrollPane_albumes.setViewportView(panelAlbumes);

        lblFlechaArriba.setText("jLabel2");

        lblFlechaAbajo.setText("jLabel2");

        javax.swing.GroupLayout pnlBuscadorLayout = new javax.swing.GroupLayout(pnlBuscador);
        pnlBuscador.setLayout(pnlBuscadorLayout);
        pnlBuscadorLayout.setHorizontalGroup(
            pnlBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        pnlBuscadorLayout.setVerticalGroup(
            pnlBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnl_TipoBusquedaLayout = new javax.swing.GroupLayout(pnl_TipoBusqueda);
        pnl_TipoBusqueda.setLayout(pnl_TipoBusquedaLayout);
        pnl_TipoBusquedaLayout.setHorizontalGroup(
            pnl_TipoBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        pnl_TipoBusquedaLayout.setVerticalGroup(
            pnl_TipoBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(pnl_TipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane_albumes, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFlechaArriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFlechaAbajo, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlBuscador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_TipoBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFlechaArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFlechaAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane_albumes, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

public static class Album {
    private String nombre;
    private String artista;
    private String portada;

    public Album(String nombre, String artista, String portada) {
        this.nombre = nombre;
        this.artista = artista;
        this.portada = portada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
    
    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane_albumes;
    private javax.swing.JLabel lblFlechaAbajo;
    private javax.swing.JLabel lblFlechaArriba;
    private javax.swing.JPanel panelAlbumes;
    private javax.swing.JPanel pnlBuscador;
    private javax.swing.JPanel pnl_TipoBusqueda;
    // End of variables declaration//GEN-END:variables
}


