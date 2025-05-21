/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.app.aplicacion;

import dtos.AlbumDTO;
import dtos.ArtistaDTO;
import dtos.CancionDTO;
import dtos.UsuarioDTO;
import interfaces.IAlbumNegocio;
import interfaces.IArtistaNegocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import ui.componentes.CustomRoundedTextField;
import ui.componentes.RoundedComboBox;
import interfaces.ICancionNegocio;
import interfaces.IGeneroNegocio;
import interfaces.IUsuarioNegocio;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import ui.sesion.Sesion;

/**
 *
 * @author ang3lfco
 */
public class pnlCanciones extends javax.swing.JPanel {

    private ICancionNegocio cancionesNegocio;
    private IArtistaNegocio artistaNegocio;
    private IAlbumNegocio albumNegocio;
    private IUsuarioNegocio usuarioNegocio;
    
    List<CancionDTO> canciones;
    private IGeneroNegocio generoNegocio;
    private UsuarioDTO.NoDeseadosDTO noDeseados;
    private List<String> generosNoDeseadosIds;
    /**
     * Creates new form pnlCanciones
     */
    public pnlCanciones(ICancionNegocio cancionesNegocio, IArtistaNegocio artistaNegocio, IAlbumNegocio albumNegocio, IUsuarioNegocio usuarioNegocio, IGeneroNegocio generoNegocio) {
        initComponents();
        this.cancionesNegocio = cancionesNegocio;
        this.artistaNegocio = artistaNegocio;
        this.albumNegocio = albumNegocio;
        this.usuarioNegocio = usuarioNegocio;
        this.generoNegocio = generoNegocio;
        
        noDeseados = usuarioNegocio.getNoDeseados(Sesion.getUsuarioActual().getId());
        if (noDeseados != null) {
            generosNoDeseadosIds = noDeseados.getGeneros();
        } else {
            generosNoDeseadosIds = new ArrayList<>();
        }
        
        SwingUtilities.invokeLater(() -> pnlCanciones.this.getRootPane().requestFocusInWindow());
        
        iniciarFlechasScroll();
        jScrollPane_canciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane_canciones.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane_canciones.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        jScrollPane_canciones.getVerticalScrollBar().setUnitIncrement(14);

        jScrollPane_canciones.setViewportBorder(null);
        jScrollPane_canciones.setBorder(null);
        cargarCanciones();

        CustomRoundedTextField buscador = new CustomRoundedTextField("Buscar canciónes...", "/iconos/buscar.png");
        pnlBuscador.setLayout(new BorderLayout());
        pnlBuscador.setPreferredSize(new Dimension(330, 40));
        pnlBuscador.setBackground(new Color(0, 0, 0, 0));
        pnlBuscador.add(buscador, BorderLayout.CENTER);

        buscador.getDocument().addDocumentListener(new DocumentListener() {
            private void actualizar(String texto) {
                buscarYCargarCanciones(texto);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                String texto = buscador.getText().trim().toLowerCase();
                if(!texto.isEmpty()){
                    actualizar(texto);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String texto = buscador.getText().trim().toLowerCase();
                if(!texto.isEmpty()){
                    actualizar(texto);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String texto = buscador.getText().trim().toLowerCase();
                if(!texto.isEmpty()){
                    actualizar(texto);
                }
            }
        });

        lblFlechaArriba.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JScrollBar barra = jScrollPane_canciones.getVerticalScrollBar();
                int paso = 60;
                barra.setValue(barra.getValue() - paso);
            }
        });

        lblFlechaAbajo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JScrollBar barra = jScrollPane_canciones.getVerticalScrollBar();
                int paso = 60;
                barra.setValue(barra.getValue() + paso);
            }
        });
    }

    private void cargarCanciones() {
        canciones = cancionesNegocio.obtenerTodas();
        panelCanciones.setLayout(new java.awt.GridLayout(0, 3, 10, 10));

        List<CancionDTO> cancionesFiltradas = new ArrayList<>();
        
        for (CancionDTO cancion : canciones) {
            boolean incluirArtista = true;
            List<String> generosCancionActual = cancion.getGenerosId();
            for (String idGenero : generosCancionActual){
                if (generosNoDeseadosIds.contains(idGenero)) {
                    incluirArtista = false;
                    break;
                }
            }
            if (incluirArtista) {
                cancionesFiltradas.add(cancion);
            }
        }
        panelCanciones.setLayout(new java.awt.GridLayout(0, 3, 10, 10));

        for (CancionDTO cancion : cancionesFiltradas) {
            JPanel panel = crearPanelCancion(cancion);
            panelCanciones.add(panel);
        }

        panelCanciones.revalidate();
        panelCanciones.repaint();
    }

    private void buscarYCargarCanciones(String texto) {
    List<CancionDTO> resultado = new ArrayList<>();
    Set<String> idsCancionesAgregadas = new HashSet<>();

    if (texto.isEmpty()) {

        resultado = cancionesNegocio.obtenerTodas();
        for (CancionDTO c : resultado) {
            idsCancionesAgregadas.add(c.getId());
        }
    } else {

        List<CancionDTO> cancionesPorNombre = cancionesNegocio.buscarPorNombre(texto);
        for (CancionDTO c : cancionesPorNombre) {
            resultado.add(c);
            idsCancionesAgregadas.add(c.getId());
        }

        List<ArtistaDTO> artistas = artistaNegocio.buscarPorNombre(texto);
        for (ArtistaDTO artista : artistas) {
            List<CancionDTO> cancionesDelArtista = cancionesNegocio.buscarPorArtistaId(artista.getId());
            for (CancionDTO c : cancionesDelArtista) {
                if (!idsCancionesAgregadas.contains(c.getId())) {
                    resultado.add(c);
                    idsCancionesAgregadas.add(c.getId());
                }
            }
        }

        List<CancionDTO> cancionesSinArtista = cancionesNegocio.buscarPorArtistaId("sin-artista");
        for (CancionDTO c : cancionesSinArtista) {
            if (!idsCancionesAgregadas.contains(c.getId())
                    && c.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(c);
                idsCancionesAgregadas.add(c.getId());
            }
        }
    }

    // Mostrar resultados en el panel
    panelCanciones.removeAll();
    panelCanciones.setLayout(new java.awt.GridLayout(0, 3, 10, 10));

    for (CancionDTO cancion : resultado) {
        JPanel panel = crearPanelCancion(cancion);
        panelCanciones.add(panel);
    }

    panelCanciones.revalidate();
    panelCanciones.repaint();
}


    private JPanel crearPanelCancion(CancionDTO cancion) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new java.awt.Color(23, 30, 49));

        int imagenAncho = 0;
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource("/portadas/" + "cancion.png"));
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

        String albumDeCancionId = cancion.getAlbumId();
        AlbumDTO albumDeCancion = albumNegocio.buscarAlbumPorId(albumDeCancionId);
        String artista = "";
        StringBuilder artistasLabel = new StringBuilder();
        List<String> artistasTotalesIds = albumDeCancion.getArtistasId();
        for (String s : artistasTotalesIds) {
            artistasLabel.append(artistaNegocio.buscarArtistaporId(s).getNombre());
            if (artistasTotalesIds.size() > 1) {
                artistasLabel.append(", ");
            }
        }
        String artistasTexto = artistasLabel.toString();

        JLabel lblArtista = new JLabel(artistasTexto, SwingConstants.CENTER);
        lblArtista.setForeground(Color.LIGHT_GRAY);
        lblArtista.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblArtista.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblArtista.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));

        contenedorTexto.add(lblNombre);
        contenedorTexto.add(lblArtista);

        panel.add(contenedorTexto, BorderLayout.SOUTH);

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setBackground(new Color(30, 30, 30));
        popupMenu.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        popupMenu.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JMenuItem itemFavoritos = new JMenuItem("Agregar a Favoritos");
        JMenuItem itemNoDeseados = new JMenuItem("Agregar a No Deseados");

        personalizarMenuItem(itemFavoritos, new Color(18, 25, 44), Color.WHITE);
        personalizarMenuItem(itemNoDeseados, new Color(18, 25, 44), Color.WHITE);

        popupMenu.add(itemFavoritos);
        popupMenu.add(itemNoDeseados);

        itemFavoritos.addActionListener(e -> {
            usuarioNegocio.insertarFavoritoCancion(Sesion.getUsuarioActual().getId(), cancion.getId());
            JOptionPane.showMessageDialog(null, "Canción agregada a favoritos.");
        });
        itemNoDeseados.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Función no disponible.");
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(panel, e.getX(), e.getY());
                } else if (e.getButton() == MouseEvent.BUTTON1) {
                    //Click Derecho
                    JOptionPane.showMessageDialog(null, "Mostrar Informacion.");
                }
            }
        });

        return panel;
    }

    private void personalizarMenuItem(JMenuItem item, Color background, Color foreground) {
        item.setOpaque(true);
        item.setBackground(background);
        item.setForeground(foreground);
        item.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        item.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
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

        jScrollPane_canciones.getVerticalScrollBar().addAdjustmentListener(e -> actualizarFlechasScroll());
    }

    private void actualizarFlechasScroll() {
        JScrollBar barra = jScrollPane_canciones.getVerticalScrollBar();
        int max = barra.getMaximum();
        int visible = barra.getVisibleAmount();
        int value = barra.getValue();

        lblFlechaArriba.setVisible(value > 0);
        lblFlechaAbajo.setVisible(value + visible < max);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane_canciones = new javax.swing.JScrollPane();
        panelCanciones = new javax.swing.JPanel();
        lblFlechaArriba = new javax.swing.JLabel();
        lblFlechaAbajo = new javax.swing.JLabel();
        pnlBuscador = new javax.swing.JPanel();

        setBackground(new java.awt.Color(23, 30, 49));

        jScrollPane_canciones.setBackground(new java.awt.Color(23, 30, 49));

        panelCanciones.setBackground(new java.awt.Color(23, 30, 49));

        javax.swing.GroupLayout panelCancionesLayout = new javax.swing.GroupLayout(panelCanciones);
        panelCanciones.setLayout(panelCancionesLayout);
        panelCancionesLayout.setHorizontalGroup(
            panelCancionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
        );
        panelCancionesLayout.setVerticalGroup(
            panelCancionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );

        jScrollPane_canciones.setViewportView(panelCanciones);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(276, 276, 276))
                    .addComponent(jScrollPane_canciones, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(pnlBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFlechaArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFlechaAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane_canciones, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public static class Cancion {

        private String nombre;
        private String album;
        private String portada;

        public Cancion(String nombre, String album, String portada) {
            this.nombre = nombre;
            this.album = album;
            this.portada = portada;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public String getPortada() {
            return portada;
        }

        public void setPortada(String portada) {
            this.portada = portada;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane_canciones;
    private javax.swing.JLabel lblFlechaAbajo;
    private javax.swing.JLabel lblFlechaArriba;
    private javax.swing.JPanel panelCanciones;
    private javax.swing.JPanel pnlBuscador;
    // End of variables declaration//GEN-END:variables
}
