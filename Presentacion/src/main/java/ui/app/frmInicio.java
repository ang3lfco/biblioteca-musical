/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.app;

import interfaces.IAlbumNegocio;
import interfaces.IUsuarioNegocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ui.app.aplicacion.pnlAlbumes;
import ui.app.aplicacion.pnlArtistas;
import ui.app.aplicacion.pnlCanciones;
import ui.app.forms.frmUsuarioInfo;
import ui.app.usuario.pnlFavoritos;
import ui.app.usuario.pnlNoDeseados;
import ui.app.aplicacion.pnlPrincipal;
import ui.componentes.GradientPanel;
import ui.componentes.RoundedPanel;
import interfaces.IArtistaNegocio;
import interfaces.ICancionNegocio;
import interfaces.IGeneroNegocio;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import ui.sesion.Sesion;
import ui.sesion.frmInicioSesion;

/**
 *
 * @author ang3lfco
 */
public class frmInicio extends javax.swing.JFrame {
    private int xMouse, yMouse;
    private JPanel panelSeleccionadoActual;
    private JLabel labelSeleccionadoActual;
    private IUsuarioNegocio usuarioNegocio;
    private ICancionNegocio cancionNegocio;
    private IAlbumNegocio albumNegocio;
    private IArtistaNegocio artistaNegocio;
    private IGeneroNegocio generoNegocio;
    private String rutaDeImagen = "/iconos/usuario.png";
    /**
     * Creates new form frmInicio
     */
    public frmInicio(IUsuarioNegocio usuarioNegocio, ICancionNegocio cancionNegocio, IAlbumNegocio albumNegocio, IArtistaNegocio artistaNegocio, IGeneroNegocio generoNegocio) {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        initComponents();
        this.usuarioNegocio = usuarioNegocio;
        this.cancionNegocio = cancionNegocio;
        this.albumNegocio = albumNegocio;
        this.artistaNegocio = artistaNegocio;
        this.generoNegocio = generoNegocio;
        
        String ruta = Sesion.getUsuarioActual().getRutaImagen();
        if (ruta != null && !ruta.isEmpty()) {
            try {
                Path rutaCompleta = Paths.get("src/main/resources" + ruta);
                if (Files.exists(rutaCompleta)) {
                    BufferedImage img = ImageIO.read(rutaCompleta.toFile());
                    ImageIcon icon = new ImageIcon(img.getScaledInstance(64, 64, Image.SCALE_SMOOTH));
                    lblFotoPerfil.setIcon(icon);
                    rutaDeImagen = ruta;
                }
            } catch (IOException ex) {
                System.err.println("No se pudo cargar la imagen de perfil: " + ex.getMessage());
            }
        }
        
        configurarEventos();
        setLocationRelativeTo(null);
        
        aplicarGradient(opcion_inicio, lblOpcion_inicio);
        panelSeleccionadoActual = opcion_inicio;
        labelSeleccionadoActual = lblOpcion_inicio;
        aplicarGradient(opcion_inicio, lblOpcion_inicio);
        
        RoundedPanel panelPrincipal = new RoundedPanel(50, new Color(23,30,49));
        panelPrincipal.setOpaque(false);
        setContentPane(panelPrincipal);

        RoundedPanel menuPanel = new RoundedPanel(50, new Color(18,25,44));
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(pnlMenu, BorderLayout.CENTER);
        pnlMenu.setOpaque(false);

        RoundedPanel contenidoPanel = new RoundedPanel(50, new Color(23,30,49));
        contenidoPanel.setOpaque(false);
        contenidoPanel.setLayout(new BorderLayout());
        contenidoPanel.add(pnl_contenedor, BorderLayout.CENTER);
        pnl_contenedor.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(contenidoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(contenidoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        
        pnlMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xMouse = evt.getXOnScreen();
                yMouse = evt.getYOnScreen();
            }
        });

        pnlMenu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int xActual = evt.getXOnScreen();
                int yActual = evt.getYOnScreen();
                setLocation(getX() + xActual - xMouse, getY() + yActual - yMouse);
                xMouse = xActual;
                yMouse = yActual;
            }
        });
        
        pnlPrincipal inicio = new pnlPrincipal(usuarioNegocio, cancionNegocio, albumNegocio, artistaNegocio, generoNegocio);
        pnlSeccion.removeAll();
        pnlSeccion.setLayout(new BorderLayout());
        pnlSeccion.add(inicio, BorderLayout.CENTER);
        pnlSeccion.revalidate();
        pnlSeccion.repaint();
    }
    
    
    private void aplicarGradient(JPanel nuevoPanel, JLabel nuevoLabel) {
        if (panelSeleccionadoActual != null && labelSeleccionadoActual != null) {
            panelSeleccionadoActual.removeAll();
            panelSeleccionadoActual.setPreferredSize(new Dimension(188, 30));
            panelSeleccionadoActual.setLayout(new BorderLayout());
            panelSeleccionadoActual.add(labelSeleccionadoActual, BorderLayout.CENTER);
            panelSeleccionadoActual.revalidate();
            panelSeleccionadoActual.repaint();
        }

        java.awt.Font fuenteOriginal = nuevoLabel.getFont();

        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(new BorderLayout());
        gradientPanel.setOpaque(false);

        nuevoLabel.setFont(fuenteOriginal);
        gradientPanel.add(nuevoLabel, BorderLayout.CENTER);

        nuevoPanel.removeAll();
        nuevoPanel.setLayout(new BorderLayout());
        nuevoPanel.add(gradientPanel, BorderLayout.CENTER);
        nuevoPanel.revalidate();
        nuevoPanel.repaint();

        panelSeleccionadoActual = nuevoPanel;
        labelSeleccionadoActual = nuevoLabel;
    }

    private void configurarEventos() {
        opcion_inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aplicarGradient(opcion_inicio, lblOpcion_inicio);
                pnlPrincipal inicio = new pnlPrincipal(usuarioNegocio, cancionNegocio, albumNegocio, artistaNegocio, generoNegocio);
                pnlSeccion.removeAll();
                pnlSeccion.setLayout(new BorderLayout());
                pnlSeccion.add(inicio, BorderLayout.CENTER);
                pnlSeccion.revalidate();
                pnlSeccion.repaint();
            }
        });

        opcion_favoritos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aplicarGradient(opcion_favoritos, lblOpcion_favoritos);
                pnlFavoritos favoritos = new pnlFavoritos(usuarioNegocio, cancionNegocio, albumNegocio, artistaNegocio);
                pnlSeccion.removeAll();
                pnlSeccion.setLayout(new BorderLayout());
                pnlSeccion.add(favoritos, BorderLayout.CENTER);
                pnlSeccion.revalidate();
                pnlSeccion.repaint();
            }
        });

        opcion_nodeseados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aplicarGradient(opcion_nodeseados, lblOpcion_nodeseados);
                pnlNoDeseados nodeseados = new pnlNoDeseados(usuarioNegocio, generoNegocio,albumNegocio,cancionNegocio,artistaNegocio);
                pnlSeccion.removeAll();
                pnlSeccion.setLayout(new BorderLayout());
                pnlSeccion.add(nodeseados, BorderLayout.CENTER);
                pnlSeccion.revalidate();
                pnlSeccion.repaint();
            }
        });

        opcion_artistas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aplicarGradient(opcion_artistas, lblOpcion_artistas);
                pnlArtistas artistas = new pnlArtistas(artistaNegocio, usuarioNegocio, generoNegocio);
                pnlSeccion.removeAll();
                pnlSeccion.setLayout(new BorderLayout());
                pnlSeccion.add(artistas, BorderLayout.CENTER);
                pnlSeccion.revalidate();
                pnlSeccion.repaint();
            }
        });

        opcion_albumes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aplicarGradient(opcion_albumes, lblOpcion_albumes);
                pnlAlbumes albumes = new pnlAlbumes(albumNegocio, artistaNegocio, usuarioNegocio, generoNegocio);
                pnlSeccion.removeAll();
                pnlSeccion.setLayout(new BorderLayout());
                pnlSeccion.add(albumes, BorderLayout.CENTER);
                pnlSeccion.revalidate();
                pnlSeccion.repaint();
            }
        });

        opcion_canciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aplicarGradient(opcion_canciones, lblOpcion_canciones);
                pnlCanciones canciones = new pnlCanciones(cancionNegocio, artistaNegocio, albumNegocio, usuarioNegocio, generoNegocio);
                pnlSeccion.removeAll();
                pnlSeccion.setLayout(new BorderLayout());
                pnlSeccion.add(canciones, BorderLayout.CENTER);
                pnlSeccion.revalidate();
                pnlSeccion.repaint();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_contenedor = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        opcion_inicio = new javax.swing.JPanel();
        lblOpcion_inicio = new javax.swing.JLabel();
        opcion_favoritos = new javax.swing.JPanel();
        lblOpcion_favoritos = new javax.swing.JLabel();
        opcion_nodeseados = new javax.swing.JPanel();
        lblOpcion_nodeseados = new javax.swing.JLabel();
        opcion_artistas = new javax.swing.JPanel();
        lblOpcion_artistas = new javax.swing.JLabel();
        opcion_albumes = new javax.swing.JPanel();
        lblOpcion_albumes = new javax.swing.JLabel();
        opcion_canciones = new javax.swing.JPanel();
        lblOpcion_canciones = new javax.swing.JLabel();
        lbl_cerrar = new javax.swing.JLabel();
        lbl_minimizar = new javax.swing.JLabel();
        lbl_maximizar = new javax.swing.JLabel();
        lblFotoPerfil = new javax.swing.JLabel();
        lbl_perfil = new javax.swing.JLabel();
        pnlSeccion = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_contenedor.setBackground(new java.awt.Color(23, 30, 49));

        pnlMenu.setBackground(new java.awt.Color(18, 25, 44));

        opcion_inicio.setBackground(new java.awt.Color(18, 25, 44));
        opcion_inicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblOpcion_inicio.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblOpcion_inicio.setForeground(new java.awt.Color(255, 255, 255));
        lblOpcion_inicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOpcion_inicio.setText("Inicio");

        javax.swing.GroupLayout opcion_inicioLayout = new javax.swing.GroupLayout(opcion_inicio);
        opcion_inicio.setLayout(opcion_inicioLayout);
        opcion_inicioLayout.setHorizontalGroup(
            opcion_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcion_inicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpcion_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        opcion_inicioLayout.setVerticalGroup(
            opcion_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcion_inicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpcion_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        opcion_favoritos.setBackground(new java.awt.Color(18, 25, 44));
        opcion_favoritos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblOpcion_favoritos.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblOpcion_favoritos.setForeground(new java.awt.Color(255, 255, 255));
        lblOpcion_favoritos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOpcion_favoritos.setText("Favoritos");

        javax.swing.GroupLayout opcion_favoritosLayout = new javax.swing.GroupLayout(opcion_favoritos);
        opcion_favoritos.setLayout(opcion_favoritosLayout);
        opcion_favoritosLayout.setHorizontalGroup(
            opcion_favoritosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcion_favoritosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpcion_favoritos, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        opcion_favoritosLayout.setVerticalGroup(
            opcion_favoritosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcion_favoritosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpcion_favoritos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        opcion_nodeseados.setBackground(new java.awt.Color(18, 25, 44));
        opcion_nodeseados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblOpcion_nodeseados.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblOpcion_nodeseados.setForeground(new java.awt.Color(255, 255, 255));
        lblOpcion_nodeseados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOpcion_nodeseados.setText("No deseados");

        javax.swing.GroupLayout opcion_nodeseadosLayout = new javax.swing.GroupLayout(opcion_nodeseados);
        opcion_nodeseados.setLayout(opcion_nodeseadosLayout);
        opcion_nodeseadosLayout.setHorizontalGroup(
            opcion_nodeseadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcion_nodeseadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpcion_nodeseados, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        opcion_nodeseadosLayout.setVerticalGroup(
            opcion_nodeseadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcion_nodeseadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpcion_nodeseados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        opcion_artistas.setBackground(new java.awt.Color(18, 25, 44));
        opcion_artistas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblOpcion_artistas.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblOpcion_artistas.setForeground(new java.awt.Color(255, 255, 255));
        lblOpcion_artistas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOpcion_artistas.setText("Artistas");

        javax.swing.GroupLayout opcion_artistasLayout = new javax.swing.GroupLayout(opcion_artistas);
        opcion_artistas.setLayout(opcion_artistasLayout);
        opcion_artistasLayout.setHorizontalGroup(
            opcion_artistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcion_artistasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpcion_artistas, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        opcion_artistasLayout.setVerticalGroup(
            opcion_artistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcion_artistasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpcion_artistas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        opcion_albumes.setBackground(new java.awt.Color(18, 25, 44));
        opcion_albumes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblOpcion_albumes.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblOpcion_albumes.setForeground(new java.awt.Color(255, 255, 255));
        lblOpcion_albumes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOpcion_albumes.setText("Albumes");

        javax.swing.GroupLayout opcion_albumesLayout = new javax.swing.GroupLayout(opcion_albumes);
        opcion_albumes.setLayout(opcion_albumesLayout);
        opcion_albumesLayout.setHorizontalGroup(
            opcion_albumesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcion_albumesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpcion_albumes, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        opcion_albumesLayout.setVerticalGroup(
            opcion_albumesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcion_albumesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpcion_albumes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        opcion_canciones.setBackground(new java.awt.Color(18, 25, 44));
        opcion_canciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblOpcion_canciones.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblOpcion_canciones.setForeground(new java.awt.Color(255, 255, 255));
        lblOpcion_canciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOpcion_canciones.setText("Canciones");

        javax.swing.GroupLayout opcion_cancionesLayout = new javax.swing.GroupLayout(opcion_canciones);
        opcion_canciones.setLayout(opcion_cancionesLayout);
        opcion_cancionesLayout.setHorizontalGroup(
            opcion_cancionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcion_cancionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpcion_canciones, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        opcion_cancionesLayout.setVerticalGroup(
            opcion_cancionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcion_cancionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpcion_canciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        lblFotoPerfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFotoPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/usuario.png"))); // NOI18N

        lbl_perfil.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lbl_perfil.setForeground(new java.awt.Color(255, 255, 255));
        lbl_perfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_perfil.setText("Mi perfil");
        lbl_perfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_perfilMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(opcion_inicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(opcion_favoritos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(opcion_nodeseados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(opcion_artistas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(opcion_albumes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(opcion_canciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMenuLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lbl_cerrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_minimizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_maximizar)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFotoPerfil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_perfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_maximizar)
                    .addComponent(lbl_minimizar)
                    .addComponent(lbl_cerrar))
                .addGap(32, 32, 32)
                .addComponent(lblFotoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_perfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(opcion_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opcion_favoritos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opcion_nodeseados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(opcion_artistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opcion_albumes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opcion_canciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );

        pnlSeccion.setBackground(new java.awt.Color(23, 30, 49));

        javax.swing.GroupLayout pnlSeccionLayout = new javax.swing.GroupLayout(pnlSeccion);
        pnlSeccion.setLayout(pnlSeccionLayout);
        pnlSeccionLayout.setHorizontalGroup(
            pnlSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 732, Short.MAX_VALUE)
        );
        pnlSeccionLayout.setVerticalGroup(
            pnlSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnl_contenedorLayout = new javax.swing.GroupLayout(pnl_contenedor);
        pnl_contenedor.setLayout(pnl_contenedorLayout);
        pnl_contenedorLayout.setHorizontalGroup(
            pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_contenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl_contenedorLayout.setVerticalGroup(
            pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_contenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSeccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
        this.dispose();
        frmInicioSesion iniciar = new frmInicioSesion();
        iniciar.setVisible(true);
    }//GEN-LAST:event_lbl_cerrarMouseClicked

    private void lbl_minimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_minimizarMouseClicked
        // TODO add your handling code here:
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lbl_minimizarMouseClicked

    private void lbl_perfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_perfilMouseClicked
        // TODO add your handling code here:
        frmUsuarioInfo info = new frmUsuarioInfo(usuarioNegocio, "editar");
        info.setVisible(true);
    }//GEN-LAST:event_lbl_perfilMouseClicked

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
//            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmInicio().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblFotoPerfil;
    private javax.swing.JLabel lblOpcion_albumes;
    private javax.swing.JLabel lblOpcion_artistas;
    private javax.swing.JLabel lblOpcion_canciones;
    private javax.swing.JLabel lblOpcion_favoritos;
    private javax.swing.JLabel lblOpcion_inicio;
    private javax.swing.JLabel lblOpcion_nodeseados;
    private javax.swing.JLabel lbl_cerrar;
    private javax.swing.JLabel lbl_maximizar;
    private javax.swing.JLabel lbl_minimizar;
    private javax.swing.JLabel lbl_perfil;
    private javax.swing.JPanel opcion_albumes;
    private javax.swing.JPanel opcion_artistas;
    private javax.swing.JPanel opcion_canciones;
    private javax.swing.JPanel opcion_favoritos;
    private javax.swing.JPanel opcion_inicio;
    private javax.swing.JPanel opcion_nodeseados;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlSeccion;
    private javax.swing.JPanel pnl_contenedor;
    // End of variables declaration//GEN-END:variables
}
