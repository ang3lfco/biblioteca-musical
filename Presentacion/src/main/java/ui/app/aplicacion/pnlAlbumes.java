/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.app.aplicacion;

import dtos.AlbumDTO;
import dtos.ArtistaDTO;
import dtos.UsuarioDTO;
import dtos.UsuarioDTO.NoDeseadosDTO;
import interfaces.IAlbumNegocio;
import interfaces.IArtistaNegocio;
import interfaces.IGeneroNegocio;
import interfaces.IUsuarioNegocio;
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
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import ui.componentes.CustomRoundedTextField;
import ui.componentes.RoundedComboBox;
import ui.sesion.Sesion;

/**
 *
 * @author ang3lfco
 */
public class pnlAlbumes extends javax.swing.JPanel {

    private IAlbumNegocio albumNegocio;
    private IArtistaNegocio artistaNegocio;
    private IUsuarioNegocio usuarioNegocio;
    private IGeneroNegocio generoNegocio;
    private UsuarioDTO.NoDeseadosDTO noDeseados;
    private List<String> generosNoDeseadosIds;
    private List<AlbumDTO> albumes;
    RoundedComboBox<String> combo;
    CustomRoundedTextField buscador;

    /**
     * Creates new form pnlCanciones
     */
    public pnlAlbumes(IAlbumNegocio albumNegocio, IArtistaNegocio artistaNegocio, IUsuarioNegocio usuarioNegocio, IGeneroNegocio generoNegocio) {
        initComponents();
        this.albumNegocio = albumNegocio;
        this.artistaNegocio = artistaNegocio;
        this.usuarioNegocio = usuarioNegocio;
        this.generoNegocio = generoNegocio;

        noDeseados = usuarioNegocio.getNoDeseados(Sesion.getUsuarioActual().getId());
        if (noDeseados != null) {
            generosNoDeseadosIds = noDeseados.getGeneros();
        } else {
            generosNoDeseadosIds = new ArrayList<>();
        }

        SwingUtilities.invokeLater(() -> pnlAlbumes.this.getRootPane().requestFocusInWindow());

        iniciarFlechasScroll();
        jScrollPane_albumes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane_albumes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane_albumes.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        jScrollPane_albumes.getVerticalScrollBar().setUnitIncrement(14);

        jScrollPane_albumes.setViewportBorder(null);
        jScrollPane_albumes.setBorder(null);

        cargarAlbumes();

        String[] items = {"Nombre", "Género", "Fecha de Lanzamiento"};
        combo = new RoundedComboBox<>(items);
        combo.setPreferredSize(new Dimension(200, 40));
        pnl_TipoBusqueda.setBackground(new Color(0, 0, 0, 0));
        pnl_TipoBusqueda.setLayout(new BorderLayout());
        pnl_TipoBusqueda.add(combo, BorderLayout.CENTER);

        buscador = new CustomRoundedTextField("Buscar álbumes...", "/iconos/buscar.png");
        pnlBuscador.setLayout(new BorderLayout());
        pnlBuscador.setPreferredSize(new Dimension(330, 40));
        pnlBuscador.setBackground(new Color(0, 0, 0, 0));
        pnlBuscador.add(buscador, BorderLayout.CENTER);

        JTextField campoBusqueda = buscador.getTextField();
        campoBusqueda.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String texto = buscador.getText().trim().toLowerCase();
                if (!texto.isEmpty()) {
                    realizarBusqueda(texto);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String texto = buscador.getText().trim().toLowerCase();
                if (!texto.isEmpty()) {
                    realizarBusqueda(texto);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String texto = buscador.getText().trim().toLowerCase();
                if (!texto.isEmpty()) {
                    realizarBusqueda(texto);
                }
            }

            private void realizarBusqueda(String texto) {
                String criterio = combo.getSelectedItem().toString();

                NoDeseadosDTO usuario = usuarioNegocio.getNoDeseados(Sesion.getUsuarioActual().getId());
                List<String> generosNoDeseados = (usuario != null) ? usuario.getGeneros() : new ArrayList<>();

                List<AlbumDTO> resultados = albumes.stream().filter(album -> {
                    if (album.getGenerosId().stream().anyMatch(generosNoDeseados::contains)) {
                        return false;
                    }

                    switch (criterio) {
                        case "Nombre":
                            return album.getNombre().toLowerCase().contains(texto.toLowerCase());
                        case "Género":
                            try {
                                List<String> generosId = album.getGenerosId();
                                for (String idGenero : generosId) {
                                    String nombreGenero = generoNegocio.buscarGeneroPorId(idGenero).getNombre();
                                    if (nombreGenero.toLowerCase().contains(texto.toLowerCase())) {
                                        return true;
                                    }
                                }
                            } catch (Exception e) {
                                return false;
                            }
                            return false;
                        case "Fecha de Lanzamiento":
                            return album.getLanzamiento() != null
                                    && album.getLanzamiento().toString().toLowerCase().contains(texto.toLowerCase());
                        default:
                            return false;
                    }
                }).toList();

                mostrarResultados(resultados);
            }

        });

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

    private void cargarAlbumes() {
        albumes = albumNegocio.obtenerTodos();
        panelAlbumes.setLayout(new java.awt.GridLayout(0, 3, 10, 10));

        List<AlbumDTO> albumesFiltrados = new ArrayList<>();
        if (generosNoDeseadosIds != null) {
            for (AlbumDTO album : albumes) {
                boolean incluirAlbum = true;
                List<String> generosAlbumActual = album.getGenerosId();
                for (String idGenero : generosAlbumActual) {
                    if (generosNoDeseadosIds.contains(idGenero)) {
                        incluirAlbum = false;
                        break;
                    }
                }
                if (incluirAlbum) {
                    albumesFiltrados.add(album);
                }
            }
        } else {
            albumesFiltrados = albumes;
        }
        panelAlbumes.setLayout(new java.awt.GridLayout(0, 3, 10, 10));

        for (AlbumDTO album : albumesFiltrados) {
            JPanel panel = crearPanelAlbum(album);
            panelAlbumes.add(panel);
        }

        panelAlbumes.revalidate();
        panelAlbumes.repaint();
    }

    private void mostrarResultados(List<AlbumDTO> resultados) {
        panelAlbumes.removeAll();
        for (AlbumDTO album : resultados) {
            JPanel panel = crearPanelAlbum(album);
            panelAlbumes.add(panel);
        }
        panelAlbumes.revalidate();
        panelAlbumes.repaint();
    }

    private JPanel crearPanelAlbum(AlbumDTO album) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new java.awt.Color(23, 30, 49));

        int imagenAncho = 0;
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource(album.getRutaImagen()));
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

        JLabel lblNombre = new JLabel(album.getNombre(), SwingConstants.CENTER);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNombre.setPreferredSize(new Dimension(imagenAncho, lblNombre.getPreferredSize().height));
        lblNombre.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));

        StringBuilder artistasLabel = new StringBuilder();
        List<String> artistasDelAlbum = album.getArtistasId();
        for (String s : artistasDelAlbum) {
            ArtistaDTO adto = artistaNegocio.buscarArtistaporId(s);
            artistasLabel.append(adto.getNombre());
            if (artistasDelAlbum.size() > 1) {
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
            usuarioNegocio.insertarFavoritoAlbum(Sesion.getUsuarioActual().getId(), album.getId());
            JOptionPane.showMessageDialog(null, "Album agregado a favoritos.");
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane_albumes;
    private javax.swing.JLabel lblFlechaAbajo;
    private javax.swing.JLabel lblFlechaArriba;
    private javax.swing.JPanel panelAlbumes;
    private javax.swing.JPanel pnlBuscador;
    private javax.swing.JPanel pnl_TipoBusqueda;
    // End of variables declaration//GEN-END:variables
}
