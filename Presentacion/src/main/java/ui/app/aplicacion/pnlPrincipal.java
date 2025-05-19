/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.app.aplicacion;

import interfaces.IAlbumNegocio;
import interfaces.IArtistaNegocio;
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
    private IAlbumNegocio albumNegocio;
    private IArtistaNegocio artistaNegocio;
    /**
     * Creates new form pnlPrincipal
     */
    public pnlPrincipal(IAlbumNegocio albumNegocio, IArtistaNegocio artistaNegocio) {
        initComponents();
        this.albumNegocio = albumNegocio;
        this.artistaNegocio = artistaNegocio;
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

        for (int i = 0; i < 3; i++) {
            elementos.add(Map.of("tipo", "album", "data", new Album("BTR", "Big Time Rush", "btr.png")));
            elementos.add(Map.of("tipo", "album", "data", new Album("Take Care", "Drake", "take_care.png")));
            elementos.add(Map.of("tipo", "album", "data", new Album("1989", "Taylor Swift", "1989.png")));

            elementos.add(Map.of("tipo", "cancion", "data", new Cancion("Boyfriend", "BTR", "btr.png")));
            elementos.add(Map.of("tipo", "cancion", "data", new Cancion("HYLT", "The Album", "the_album.png")));
            elementos.add(Map.of("tipo", "cancion", "data", new Cancion("See You Again", "Rolling Papers", "rolling_papers.png")));

            elementos.add(Map.of("tipo", "artista", "data", new Artista("BLACKPINK", "K-pop", "blackpink.png")));
            elementos.add(Map.of("tipo", "artista", "data", new Artista("2Pac", "Rap", "2pac.png")));
            elementos.add(Map.of("tipo", "artista", "data", new Artista("Tyga", "Hip-Hop", "tyga.png")));
            
            elementos.add(Map.of("tipo", "album", "data", new Album("BTR", "Big Time Rush", "btr.png")));
            elementos.add(Map.of("tipo", "album", "data", new Album("Take Care", "Drake", "take_care.png")));
            elementos.add(Map.of("tipo", "album", "data", new Album("1989", "Taylor Swift", "1989.png")));

            elementos.add(Map.of("tipo", "cancion", "data", new Cancion("Boyfriend", "BTR", "btr.png")));
            elementos.add(Map.of("tipo", "cancion", "data", new Cancion("HYLT", "The Album", "the_album.png")));
            elementos.add(Map.of("tipo", "cancion", "data", new Cancion("See You Again", "Rolling Papers", "rolling_papers.png")));

            elementos.add(Map.of("tipo", "artista", "data", new Artista("BLACKPINK", "K-pop", "blackpink.png")));
            elementos.add(Map.of("tipo", "artista", "data", new Artista("2Pac", "Rap", "2pac.png")));
            elementos.add(Map.of("tipo", "artista", "data", new Artista("Tyga", "Hip-Hop", "tyga.png")));
        }

        Component[] albumes = crearItems(elementos, "album");
        agregarSeccion("Álbumes", albumes, () -> {
            pnlAlbumes albumesPanel = new pnlAlbumes(albumNegocio, artistaNegocio);
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.add(albumesPanel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });

        Component[] canciones = crearItems(elementos, "cancion");
        agregarSeccion("Canciones", canciones, () -> System.out.println("Ver más canciones"));

        Component[] artistas = crearItems(elementos, "artista");
        agregarSeccion("Artistas", artistas, () -> System.out.println("Ver más artistas"));
    }

    private Component[] crearItems(List<Map<String, Object>> elementos, String tipo) {
        List<Component> comps = new ArrayList<>();
        for (Map<String, Object> item : elementos) {
            if (item.get("tipo").equals(tipo)) {
                Object data = item.get("data");
                Component comp = null;
                if (data instanceof Album album) {
                    comp = crearElemento(album.getImagen(), album.getNombre(), album.getArtista());
                } else if (data instanceof Cancion cancion) {
                    comp = crearElemento(cancion.getImagen(), cancion.getNombre(), "Género");
                } else if (data instanceof Artista artista) {
                    comp = crearElemento(artista.getImagen(), artista.getNombre(), artista.getGenero());
                }
                if (comp != null) {
                    comps.add(comp);
                }
            }
        }
        return comps.toArray(new Component[0]);
    }

    private Component crearElemento(String imagen, String nombre, String atributo) {
        JPanel panelItem = new JPanel();
        panelItem.setOpaque(true);
        panelItem.setBackground(new Color(18,25,44));
        panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.Y_AXIS));
        panelItem.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 4, 4, new Color(0, 200, 200, 100)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panelItem.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Dimension itemSize = new Dimension(100, 130); 
        panelItem.setPreferredSize(itemSize);
        panelItem.setMinimumSize(itemSize);
        panelItem.setMaximumSize(itemSize);

        Color fondoNormal = panelItem.getBackground();
        Color fondoHover = new Color(0, 200, 200, 100);

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
            ImageIcon icono = new ImageIcon(getClass().getResource("/portadas/" + imagen));
            Image imgEscalada = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            lblImagen = new JLabel(new ImageIcon(imgEscalada));
        } catch (Exception e) {
            lblImagen = new JLabel("Sin imagen");
        }
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblAtributo = new JLabel(atributo);
        lblAtributo.setForeground(Color.LIGHT_GRAY);
        lblAtributo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblAtributo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelItem.add(lblImagen);
        panelItem.add(Box.createVerticalStrut(5));
        panelItem.add(lblNombre);
        panelItem.add(lblAtributo);

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
public class Album {
    private String nombre;
    private String artista;
    private String imagen;

    public Album(String nombre, String artista, String imagen) {
        this.nombre = nombre;
        this.artista = artista;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }

    public String getImagen() {
        return imagen;
    }
}

public class Cancion {
    private String nombre;
    private String album;
    private String imagen;

    public Cancion(String nombre, String album, String imagen) {
        this.nombre = nombre;
        this.album = album;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAlbum() {
        return album;
    }

    public String getImagen() {
        return imagen;
    }
}

public class Artista {
    private String nombre;
    private String genero;
    private String imagen;

    public Artista(String nombre, String genero, String imagen) {
        this.nombre = nombre;
        this.genero = genero;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getImagen() {
        return imagen;
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1_contenido;
    private javax.swing.JPanel pnlBuscador;
    private javax.swing.JPanel pnl_contenido;
    // End of variables declaration//GEN-END:variables
}
