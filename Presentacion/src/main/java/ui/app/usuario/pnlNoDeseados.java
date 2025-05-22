/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.app.usuario;

import dtos.AlbumDTO;
import dtos.ArtistaDTO;
import dtos.CancionDTO;
import dtos.GeneroDTO;
import dtos.UsuarioDTO;
import interfaces.IAlbumNegocio;
import interfaces.IArtistaNegocio;
import interfaces.ICancionNegocio;
import interfaces.IGeneroNegocio;
import interfaces.IUsuarioNegocio;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import ui.componentes.RoundedComboBox;
import ui.sesion.Sesion;

/**
 *
 * @author ang3lfco
 */
public class pnlNoDeseados extends javax.swing.JPanel {

    List<Map<String, Object>> elementos = new ArrayList<>();
    private IUsuarioNegocio usuarioNegocio;
    private IGeneroNegocio generoNegocio;
    private IAlbumNegocio albumNegocio;
    private ICancionNegocio cancionNegocio;
    private IArtistaNegocio artistaNegocio;
    private UsuarioDTO.NoDeseadosDTO generosNoDeseadosIds;
    private List<GeneroDTO> generosNoDeseados = new ArrayList<>();
    private RoundedComboBox<String> combo;

    /**
     * Creates new form pnlFavoritos
     */
    public pnlNoDeseados(IUsuarioNegocio usuarioNegocio, IGeneroNegocio generoNegocio, IAlbumNegocio albumNegocio, ICancionNegocio cancionNegocio, IArtistaNegocio artistaNegocio) {
        initComponents();
        iniciarFlechasScroll();
        this.albumNegocio = albumNegocio;
        this.usuarioNegocio = usuarioNegocio;
        this.generoNegocio = generoNegocio;
        this.cancionNegocio = cancionNegocio;
        this.artistaNegocio = artistaNegocio;
        this.generosNoDeseadosIds = usuarioNegocio.getNoDeseados(Sesion.getUsuarioActual().getId());
        if (generosNoDeseadosIds == null) {
            JOptionPane.showMessageDialog(null, "Sin no deseados.");
            lblFlechaArriba.setText("");
            lblFlechaAbajo.setText("");
            JLabel lblMensaje = new JLabel("Aquí no hay nada");
            lblMensaje.setForeground(Color.LIGHT_GRAY);
            lblMensaje.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
            lblMensaje.setAlignmentX(CENTER_ALIGNMENT);

            JPanel panelMensaje = new JPanel();
            panelMensaje.setBackground(new Color(23, 30, 49));
            panelMensaje.setLayout(new GridBagLayout());
            panelMensaje.add(lblMensaje);

            jPanel1_noDeseados.setLayout(new BorderLayout());
            jPanel1_noDeseados.add(panelMensaje, BorderLayout.CENTER);
            jPanel1_noDeseados.revalidate();
            jPanel1_noDeseados.repaint();
            generosNoDeseadosIds = new UsuarioDTO.NoDeseadosDTO(new ArrayList<>());
        }

        for (String s : generosNoDeseadosIds.getGeneros()) {
            generosNoDeseados.add(generoNegocio.buscarGeneroPorId(s));
        }

        cargarNoDeseados();
        cargarCombo();

        btnAgregarNoDeseado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int indiceSeleccionado = combo.getSelectedIndex();
                if (indiceSeleccionado >= 0) {
                    GeneroDTO generoSeleccionado = generoNegocio.obtenerTodas().stream().filter(g -> g.getNombre().equals(combo.getSelectedItem())).findFirst().orElse(null);
                    if (generoSeleccionado != null) {
                        String id = generoSeleccionado.getId();

                        if (validarFavoritos(id) > 0) {
                            cargarCombo();
                            cargarNoDeseados();

                        } else {

                            //
                            usuarioNegocio.insertarGeneroNoDeseado(Sesion.getUsuarioActual().getId(), id);
                            usuarioNegocio.getFavoritos(id);
                            generosNoDeseadosIds = usuarioNegocio.getNoDeseados(Sesion.getUsuarioActual().getId());
                            generosNoDeseados = new ArrayList<>();
                            for (String s : generosNoDeseadosIds.getGeneros()) {
                                generosNoDeseados.add(generoNegocio.buscarGeneroPorId(s));
                            }
                            cargarCombo();
                            cargarNoDeseados();

                            System.out.println("ID del género seleccionado: " + id);
                            System.out.println("Nombre del género seleccionado: " + generoSeleccionado.getNombre());
                        }
                    }
                }
            }
        });

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

    private void cargarCombo() {
        List<GeneroDTO> generosDisponibles = generoNegocio.obtenerTodas();
        List<String> generosBloqueados = generosNoDeseadosIds.getGeneros();
        List<GeneroDTO> generosCombo;
        if (generosBloqueados != null) {
            generosCombo = generosDisponibles.stream().filter(g -> !generosBloqueados.contains(g.getId())).toList();
        } else {
            generosCombo = generosDisponibles;
        }
        String[] items = generosCombo.stream().map(GeneroDTO::getNombre).toArray(String[]::new);
        pnl_TipoBusqueda.removeAll();
        combo = new RoundedComboBox<>(items);
        combo.setPreferredSize(new Dimension(200, 40));
        pnl_TipoBusqueda.setBackground(new Color(0, 0, 0, 0));
        pnl_TipoBusqueda.setLayout(new BorderLayout());
        pnl_TipoBusqueda.add(combo, BorderLayout.CENTER);
        pnl_TipoBusqueda.revalidate();
        pnl_TipoBusqueda.repaint();
    }

    public int validarFavoritos(String genero) {
        int cant = 0;
        List<String> nombres = new ArrayList<>();
        AlbumDTO album;
        ArtistaDTO artista;
        CancionDTO cancion;

        List<AlbumDTO> albumesDTO = new ArrayList<>();
        List<ArtistaDTO> artistasDTO = new ArrayList<>();
        List<CancionDTO> cancionesDTO = new ArrayList<>();

        List<String> canciones = new ArrayList<>();
        List<String> artistas = new ArrayList<>();
        List<String> albumes = new ArrayList<>();

        try{
            canciones = Optional.ofNullable(usuarioNegocio.getFavoritos(Sesion.getUsuarioActual().getId()).getCancionesId()).orElse(new ArrayList<>());

            artistas = Optional.ofNullable(usuarioNegocio.getFavoritos(Sesion.getUsuarioActual().getId()).getArtistasId()).orElse(new ArrayList<>());

            albumes = Optional.ofNullable(usuarioNegocio.getFavoritos(Sesion.getUsuarioActual().getId()).getAlbumesId()).orElse(new ArrayList<>());
        } 
        catch(Exception e){
            System.err.println("Error al obtener favoritos: " + e.getMessage());
            // por defecto estan vacias y no nulas.
        }

        for(String id : albumes){
            try{
                album = albumNegocio.buscarAlbumPorId(id);
                if(album != null){
                    albumesDTO.add(album);
                    if(album.getGenerosId() != null && album.getGenerosId().contains(genero)){
                        nombres.add(album.getNombre());
                        for(String g : album.getGenerosId()){
                            if(genero.equals(g)){
                                cant++;
                            }
                        }
                    }
                }
            } 
            catch(Exception e){
                System.err.println("Error con álbum ID " + id + ": " + e.getMessage());
            }
        }

        for(String id : artistas){
            try{
                artista = artistaNegocio.buscarArtistaporId(id);
                if (artista != null){
                    artistasDTO.add(artista);
                    if (artista.getGenerosId() != null && artista.getGenerosId().contains(genero)){
                        nombres.add(artista.getNombre());
                        for (String g : artista.getGenerosId()){
                            if (genero.equals(g)){
                                cant++;
                            }
                        }
                    }
                }
            } 
            catch(Exception e){
                System.err.println("Error con artista ID " + id + ": " + e.getMessage());
            }
        }

        for(String id : canciones){
            try{
                cancion = cancionNegocio.obtenerCancionPorId(id);
                if(cancion != null){
                    cancionesDTO.add(cancion);
                    if(cancion.getGenerosId() != null && cancion.getGenerosId().contains(genero)){
                        nombres.add(cancion.getNombre());
                        for(String g : cancion.getGenerosId()){
                            if(genero.equals(g)){
                                cant++;
                            }
                        }
                    }
                }
            } 
            catch(Exception e){
                System.err.println("Error con canción ID " + id + ": " + e.getMessage());
            }
        }

        mostrarNombresFavoritos(nombres);
        System.out.println(nombres.size());
        return cant;
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
                            usuarioNegocio.eliminarGeneroNoDeseado(Sesion.getUsuarioActual().getId(), genero.getId());
                            generosNoDeseadosIds = usuarioNegocio.getNoDeseados(Sesion.getUsuarioActual().getId());
                            generosNoDeseados = new ArrayList<>();
                            for (String s : generosNoDeseadosIds.getGeneros()) {
                                generosNoDeseados.add(generoNegocio.buscarGeneroPorId(s));
                            }
//                            generosNoDeseados.remove(genero);
                            return null;
                        }

                        @Override
                        protected void done() {
                            cargarNoDeseados();
                            cargarCombo();
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

    private JDialog mostrarNombresFavoritos(List<String> lista) {
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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        for (String nombre : lista) {
            JLabel lbl = new JLabel(nombre);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lbl.setHorizontalAlignment(SwingConstants.CENTER);

            dialogo.setBackground(new Color(0, 0, 0, 0));
            dialogo.getContentPane().add(panel);
            dialogo.setVisible(true);
            panel.add(lbl);
        }

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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane_NoDeseados = new javax.swing.JScrollPane();
        jPanel1_noDeseados = new javax.swing.JPanel();
        lblFlechaArriba = new javax.swing.JLabel();
        lblFlechaAbajo = new javax.swing.JLabel();
        pnl_TipoBusqueda = new javax.swing.JPanel();
        btnAgregarNoDeseado = new javax.swing.JButton();

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

        javax.swing.GroupLayout pnl_TipoBusquedaLayout = new javax.swing.GroupLayout(pnl_TipoBusqueda);
        pnl_TipoBusqueda.setLayout(pnl_TipoBusquedaLayout);
        pnl_TipoBusquedaLayout.setHorizontalGroup(
            pnl_TipoBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        pnl_TipoBusquedaLayout.setVerticalGroup(
            pnl_TipoBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btnAgregarNoDeseado.setBackground(new java.awt.Color(18, 25, 44));
        btnAgregarNoDeseado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAgregarNoDeseado.setForeground(new java.awt.Color(30, 180, 180));
        btnAgregarNoDeseado.setText("+");
        btnAgregarNoDeseado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarNoDeseadoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarNoDeseado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_TipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane_NoDeseados, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFlechaArriba, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(lblFlechaAbajo, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1)
                    .addComponent(pnl_TipoBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregarNoDeseado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnAgregarNoDeseadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarNoDeseadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarNoDeseadoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarNoDeseado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1_noDeseados;
    private javax.swing.JScrollPane jScrollPane_NoDeseados;
    private javax.swing.JLabel lblFlechaAbajo;
    private javax.swing.JLabel lblFlechaArriba;
    private javax.swing.JPanel pnl_TipoBusqueda;
    // End of variables declaration//GEN-END:variables
}
