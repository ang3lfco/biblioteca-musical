/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.app.aplicacion;

import dtos.AlbumDTO;
import dtos.ArtistaDTO;
import dtos.CancionDTO;
import interfaces.IAlbumNegocio;
import interfaces.IArtistaNegocio;
import interfaces.ICancionNegocio;
import interfaces.IUsuarioNegocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.Timer;
import ui.componentes.CustomRoundedTextField;

/**
 *
 * @author ang3lfco
 */
public class pnlPrincipal extends javax.swing.JPanel {
    private IUsuarioNegocio usuarioNegocio;
    private IAlbumNegocio albumNegocio;
    private IArtistaNegocio artistaNegocio;
    private ICancionNegocio cancionNegocio;
    
    private List<ArtistaDTO> artistasdtos = new ArrayList<>();
    private List<AlbumDTO> albumesdtos = new ArrayList<>();
    private List<CancionDTO> cancionesdtos = new ArrayList<>();
    /**
     * Creates new form pnlPrincipal
     */
    public pnlPrincipal(IUsuarioNegocio usuarioNegocio, ICancionNegocio cancionesNegocio, IAlbumNegocio albumNegocio, IArtistaNegocio artistaNegocio) {
        initComponents();
        this.usuarioNegocio = usuarioNegocio;
        this.cancionNegocio = cancionesNegocio;
        this.albumNegocio = albumNegocio;
        this.artistaNegocio = artistaNegocio;
        
        artistasdtos = artistaNegocio.obtenerTodos();
        albumesdtos = albumNegocio.obtenerTodos();
        cancionesdtos = cancionNegocio.obtenerTodas();
        
        jScrollPane1_contenido.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1_contenido.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1_contenido.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        jScrollPane1_contenido.getVerticalScrollBar().setUnitIncrement(14);
        jScrollPane1_contenido.setViewportBorder(null);
        jScrollPane1_contenido.setBorder(null);
        pnl_contenido.setLayout(new BoxLayout(pnl_contenido, BoxLayout.Y_AXIS));
        
        CustomRoundedTextField buscador = new CustomRoundedTextField("Buscar canciónes...", "/iconos/buscar.png");
        pnlBuscador.setLayout(new BorderLayout());
        pnlBuscador.setPreferredSize(new Dimension(330, 40));
        pnlBuscador.setBackground(new Color(0,0,0,0));
        pnlBuscador.add(buscador, BorderLayout.CENTER);

        List<Map<String, Object>> elementos = new ArrayList<>();

        Component[] albumes = crearItemsAlbumes(albumesdtos);
        agregarSeccion("Álbumes", albumes, () -> {
            pnlAlbumes albumesPanel = new pnlAlbumes(albumNegocio, artistaNegocio, usuarioNegocio);
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(albumesPanel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });
        
        Component[] canciones = crearItemsCanciones(cancionesdtos);
        agregarSeccion("Canciones", canciones, () -> {
            System.out.println("Ver más canciones");
        });

        Component[] artistas = crearItemsArtistas(artistasdtos);
        agregarSeccion("Artistas", artistas, () -> {
            System.out.println("Ver más artistas");
        });
    }
    
    private Component[] crearItemsAlbumes(List<AlbumDTO> elementos) {
        List<Component> comps = new ArrayList<>();
        for (AlbumDTO album : elementos) {
            comps.add(crearElemento(album));
        }
        return comps.toArray(new Component[0]);
    }
    
    private Component[] crearItemsCanciones(List<CancionDTO> elementos) {
        List<Component> comps = new ArrayList<>();
        for (CancionDTO cancion : elementos) {
            comps.add(crearElemento(cancion));
        }
        return comps.toArray(new Component[0]);
    }

    private Component[] crearItemsArtistas(List<ArtistaDTO> elementos) {
        List<Component> comps = new ArrayList<>();
        for (ArtistaDTO artista : elementos) {
            comps.add(crearElemento(artista));
        }
        return comps.toArray(new Component[0]);
    }

    private Component crearElemento(AlbumDTO album) {
        JPanel panelItem = new JPanel();
        panelItem.setOpaque(true);
        panelItem.setBackground(new Color(18, 25, 44));
        panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.Y_AXIS));
        panelItem.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 4, 4, new Color(0, 200, 200, 100)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panelItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Dimension itemSize = new Dimension(100, 130);
        panelItem.setPreferredSize(itemSize);
        panelItem.setMinimumSize(itemSize);
        panelItem.setMaximumSize(itemSize);

        final Color fondoNormal = panelItem.getBackground();
        final Color fondoHover = new Color(0, 200, 200, 100);

        panelItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelItem.setBackground(fondoHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelItem.setBackground(fondoNormal);
            }
        });

        JLabel lblImagen;
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource(album.getRutaImagen()));
            Image imgEscalada = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            lblImagen = new JLabel(new ImageIcon(imgEscalada));
        } catch (Exception e) {
            lblImagen = new JLabel("Sin imagen");
        }
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNombre = new JLabel(album.getNombre());
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblAtributo = new JLabel(album.getLanzamiento().toString());
        lblAtributo.setForeground(Color.LIGHT_GRAY);
        lblAtributo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblAtributo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelItem.add(lblImagen);
        panelItem.add(Box.createVerticalStrut(5));
        panelItem.add(lblNombre);
        panelItem.add(lblAtributo);

        return panelItem;
    }
    
    private Component crearElemento(CancionDTO cancion) {
        JPanel panelItem = crearPanelBase();
        JLabel lblImagen;
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource("/portadas/cancion.png"));
            Image imgEscalada = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            lblImagen = new JLabel(new ImageIcon(imgEscalada));
        } catch (Exception e) {
            lblImagen = new JLabel("Sin imagen");
        }
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNombre = new JLabel(cancion.getNombre());
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblGenero = new JLabel("Genero");
        lblGenero.setForeground(Color.LIGHT_GRAY);
        lblGenero.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblGenero.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelItem.add(lblImagen);
        panelItem.add(Box.createVerticalStrut(5));
        panelItem.add(lblNombre);
        panelItem.add(lblGenero);

        return panelItem;
    }
    
    private Component crearElemento(ArtistaDTO artista) {
        JPanel panelItem = crearPanelBase();
        JLabel lblImagen;
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource(artista.getRutaImagen()));
            Image imgEscalada = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            lblImagen = new JLabel(new ImageIcon(imgEscalada));
        } catch (Exception e) {
            lblImagen = new JLabel("Sin imagen");
        }
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNombre = new JLabel(artista.getNombre());
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblGenero = new JLabel(artista.getTipo());
        lblGenero.setForeground(Color.LIGHT_GRAY);
        lblGenero.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblGenero.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelItem.add(lblImagen);
        panelItem.add(Box.createVerticalStrut(5));
        panelItem.add(lblNombre);
        panelItem.add(lblGenero);

        return panelItem;
    }
    
    private JPanel crearPanelBase() {
        JPanel panelItem = new JPanel();
        panelItem.setOpaque(true);
        panelItem.setBackground(new Color(18, 25, 44));
        panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.Y_AXIS));
        panelItem.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 4, 4, new Color(0, 200, 200, 100)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panelItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Dimension itemSize = new Dimension(100, 130);
        panelItem.setPreferredSize(itemSize);
        panelItem.setMinimumSize(itemSize);
        panelItem.setMaximumSize(itemSize);

        final Color fondoNormal = panelItem.getBackground();
        final Color fondoHover = new Color(0, 200, 200, 100);

        panelItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelItem.setBackground(fondoHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelItem.setBackground(fondoNormal);
            }
        });

        return panelItem;
    }

    private void agregarSeccion(String titulo, Component[] elementos, Runnable verMasAccion) {
        JPanel panelSeccion = new JPanel();
        panelSeccion.setOpaque(false);
        panelSeccion.setLayout(new BoxLayout(panelSeccion, BoxLayout.Y_AXIS));
        panelSeccion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setOpaque(false);
        pnlHeader.add(lblTitulo, BorderLayout.WEST);

        JButton btnVerMas = new JButton("Ver más");
        btnVerMas.setBackground(new Color(0,0,0,0));
        btnVerMas.setForeground(Color.WHITE);
        btnVerMas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerMas.addActionListener(e -> verMasAccion.run());
        pnlHeader.add(btnVerMas, BorderLayout.EAST);

        panelSeccion.add(pnlHeader);

        JPanel panelItems = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelItems.setOpaque(false);

        panelSeccion.add(panelItems);
        pnl_contenido.add(panelSeccion);

        int maxVisibles = 5;
        final int[] index = {0};

        Runnable actualizarCarrusel = () -> {
            panelItems.removeAll();
            for (int i = 0; i < maxVisibles; i++) {
                int idx = (index[0] + i) % elementos.length;
                panelItems.add(elementos[idx]);
            }
            panelItems.revalidate();
            panelItems.repaint();
        };

        Timer timer = new Timer(1800, e -> {
            index[0] = (index[0] + 1) % elementos.length;
            actualizarCarrusel.run();
        });
        timer.start();

        actualizarCarrusel.run();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1_contenido = new javax.swing.JScrollPane();
        pnl_contenido = new javax.swing.JPanel();
        pnlBuscador = new javax.swing.JPanel();

        setBackground(new java.awt.Color(23, 30, 49));

        jScrollPane1_contenido.setBackground(new java.awt.Color(23, 30, 49));

        pnl_contenido.setBackground(new java.awt.Color(23, 30, 49));

        javax.swing.GroupLayout pnl_contenidoLayout = new javax.swing.GroupLayout(pnl_contenido);
        pnl_contenido.setLayout(pnl_contenidoLayout);
        pnl_contenidoLayout.setHorizontalGroup(
            pnl_contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );
        pnl_contenidoLayout.setVerticalGroup(
            pnl_contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
        );

        jScrollPane1_contenido.setViewportView(pnl_contenido);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1_contenido, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(pnlBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1_contenido, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1_contenido;
    private javax.swing.JPanel pnlBuscador;
    private javax.swing.JPanel pnl_contenido;
    // End of variables declaration//GEN-END:variables
}
