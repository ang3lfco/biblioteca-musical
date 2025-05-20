/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.app.aplicacion;

import dtos.AlbumDTO;
import dtos.ArtistaDTO;
import dtos.UsuarioDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import ui.app.forms.frmIntegrantesInfo;
import ui.componentes.CustomRoundedTextField;
import ui.componentes.RoundedComboBox;
import interfaces.IArtistaNegocio;
import interfaces.IGeneroNegocio;
import interfaces.IUsuarioNegocio;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import ui.sesion.Sesion;

/**
 *
 * @author ang3lfco
 */
public class pnlArtistas extends javax.swing.JPanel {
    private IArtistaNegocio artistaNegocio;
    private IUsuarioNegocio usuarioNegocio;
    
    List<ArtistaDTO> artistas;
    private IGeneroNegocio generoNegocio;
    private UsuarioDTO.NoDeseadosDTO noDeseados;
    private List<String> generosNoDeseadosIds;
    /**
     * Creates new form pnlCanciones
     */
    public pnlArtistas(IArtistaNegocio artistaNegocio, IUsuarioNegocio usuarioNegocio, IGeneroNegocio generoNegocio) {
        initComponents();
        this.artistaNegocio = artistaNegocio;
        this.usuarioNegocio = usuarioNegocio;
        this.generoNegocio = generoNegocio;
        
        noDeseados = usuarioNegocio.getNoDeseados(Sesion.getUsuarioActual().getId());
        generosNoDeseadosIds = noDeseados.getGeneros();
        
        iniciarFlechasScroll();
        jScrollPane_artistas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane_artistas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane_artistas.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        jScrollPane_artistas.getVerticalScrollBar().setUnitIncrement(14);

        jScrollPane_artistas.setViewportBorder(null);
        jScrollPane_artistas.setBorder(null);
        cargarArtistas();
        
        String[] items = { "Nombre", "Género" };
        RoundedComboBox<String> combo = new RoundedComboBox<>(items);
        combo.setPreferredSize(new Dimension(200, 40));
        pnl_TipoBusqueda.setBackground(new Color(0,0,0,0));
        pnl_TipoBusqueda.setLayout(new BorderLayout());
        pnl_TipoBusqueda.add(combo, BorderLayout.CENTER);
        
        CustomRoundedTextField buscador = new CustomRoundedTextField("Buscar artistas...", "/iconos/buscar.png");
        pnlBuscador.setLayout(new BorderLayout());
        pnlBuscador.setPreferredSize(new Dimension(330, 40));
        pnlBuscador.setBackground(new Color(0,0,0,0));
        pnlBuscador.add(buscador, BorderLayout.CENTER);
        
         buscador.getDocument().addDocumentListener(new DocumentListener() {
            private void actualizar() {
                String texto = buscador.getText().trim().toLowerCase();
                buscarYCargarAlbums(texto);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizar();
            }
        });
        
        lblFlechaArriba.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JScrollBar barra = jScrollPane_artistas.getVerticalScrollBar();
                int paso = 60;
                barra.setValue(barra.getValue() - paso);
            }
        });

        lblFlechaAbajo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JScrollBar barra = jScrollPane_artistas.getVerticalScrollBar();
                int paso = 60;
                barra.setValue(barra.getValue() + paso);
            }
        });
    }
    
    private void cargarArtistas() {
        artistas = artistaNegocio.obtenerTodos();
        panelArtistas.setLayout(new java.awt.GridLayout(0, 3, 10, 10));

        List<ArtistaDTO> artistasFiltrados = new ArrayList<>();
        
        for (ArtistaDTO artista : artistas) {
            boolean incluirArtista = true;
            List<String> generosArtistaActual = artista.getGenerosId();
            for (String idGenero : generosArtistaActual){
                if (generosNoDeseadosIds.contains(idGenero)) {
                    incluirArtista = false;
                    break;
                }
            }
            if (incluirArtista) {
                artistasFiltrados.add(artista);
            }
        }
        panelArtistas.setLayout(new java.awt.GridLayout(0, 3, 10, 10));

        for (ArtistaDTO artista : artistasFiltrados) {
            JPanel panel = crearPanelArtista(artista);
            panelArtistas.add(panel);
        }

        panelArtistas.revalidate();
        panelArtistas.repaint();
    }
    
    private void buscarYCargarAlbums(String texto) {
    List<ArtistaDTO> resultado;

    if (texto.isEmpty()) {
        resultado = artistaNegocio.obtenerTodos();
    } else {
        resultado = artistaNegocio.buscarPorNombre(texto);
    }

    panelArtistas.removeAll();
    panelArtistas.setLayout(new java.awt.GridLayout(0, 3, 10, 10));

    for (ArtistaDTO cancion : resultado) {
        JPanel panel = crearPanelArtista(cancion);
        panelArtistas.add(panel);
    }

    panelArtistas.revalidate();
    panelArtistas.repaint();
}

    private JPanel crearPanelArtista(ArtistaDTO artista) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new java.awt.Color(23,30,49));

        int imagenAncho = 0;
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource(artista.getRutaImagen()));
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

        JLabel lblNombre = new JLabel(artista.getNombre(), SwingConstants.CENTER);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNombre.setPreferredSize(new Dimension(imagenAncho, lblNombre.getPreferredSize().height));
        lblNombre.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        
        JLabel lblGenero = new JLabel(artista.getTipo(), SwingConstants.CENTER);
        lblGenero.setForeground(Color.LIGHT_GRAY);
        lblGenero.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblGenero.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblGenero.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));

        contenedorTexto.add(lblNombre);
        contenedorTexto.add(lblGenero);

        panel.add(contenedorTexto, BorderLayout.SOUTH);
        
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setBackground(new Color(30, 30, 30));
        popupMenu.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        popupMenu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JMenuItem itemFavoritos = new JMenuItem("Agregar a Favoritos");
        JMenuItem itemNoDeseados = new JMenuItem("Agregar a No Deseados");
        
        personalizarMenuItem(itemFavoritos, new Color(18,25,44), Color.WHITE);
        personalizarMenuItem(itemNoDeseados, new Color(18,25,44), Color.WHITE);
        
        popupMenu.add(itemFavoritos);
        popupMenu.add(itemNoDeseados);
        

        itemFavoritos.addActionListener(e -> {
            usuarioNegocio.insertarFavoritoArtista(Sesion.getUsuarioActual().getId(), artista.getId());
            JOptionPane.showMessageDialog(null, "Artista agregado a favoritos.");
        });
        itemNoDeseados.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Función no disponible.");
        });
        
        panel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3){
                    popupMenu.show(panel, e.getX(), e.getY());
                } 
                else if (e.getButton() == MouseEvent.BUTTON1){
                    //Click Derecho
                    JOptionPane.showMessageDialog(null, "Mostrar Información.");
                    frmIntegrantesInfo info = new frmIntegrantesInfo(artista);
                    info.setVisible(true);
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

        jScrollPane_artistas.getVerticalScrollBar().addAdjustmentListener(e -> actualizarFlechasScroll());
    }

    private void actualizarFlechasScroll() {
        JScrollBar barra = jScrollPane_artistas.getVerticalScrollBar();
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

        jScrollPane_artistas = new javax.swing.JScrollPane();
        panelArtistas = new javax.swing.JPanel();
        lblFlechaArriba = new javax.swing.JLabel();
        lblFlechaAbajo = new javax.swing.JLabel();
        pnlBuscador = new javax.swing.JPanel();
        pnl_TipoBusqueda = new javax.swing.JPanel();

        setBackground(new java.awt.Color(23, 30, 49));

        jScrollPane_artistas.setBackground(new java.awt.Color(23, 30, 49));

        panelArtistas.setBackground(new java.awt.Color(23, 30, 49));

        javax.swing.GroupLayout panelArtistasLayout = new javax.swing.GroupLayout(panelArtistas);
        panelArtistas.setLayout(panelArtistasLayout);
        panelArtistasLayout.setHorizontalGroup(
            panelArtistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
        );
        panelArtistasLayout.setVerticalGroup(
            panelArtistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );

        jScrollPane_artistas.setViewportView(panelArtistas);

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
                    .addComponent(jScrollPane_artistas, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jScrollPane_artistas, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

public static class Artista {
    private String nombre;
    private String genero;
    private String portada;

    public Artista(String nombre, String genero, String portada) {
        this.nombre = nombre;
        this.genero = genero;
        this.portada = portada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane_artistas;
    private javax.swing.JLabel lblFlechaAbajo;
    private javax.swing.JLabel lblFlechaArriba;
    private javax.swing.JPanel panelArtistas;
    private javax.swing.JPanel pnlBuscador;
    private javax.swing.JPanel pnl_TipoBusqueda;
    // End of variables declaration//GEN-END:variables
}


