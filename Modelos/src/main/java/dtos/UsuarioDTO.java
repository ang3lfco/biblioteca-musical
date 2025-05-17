/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;

/**
 *
 * @author ang3lfco
 */
public class UsuarioDTO {
    private String id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contraseña;
    private String correo;
    private String rutaImagen;
    private FavoritosDTO favoritos;
    private NoDeseadosDTO noDeseados;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String apellido, String usuario, String contraseña, String correo, String rutaImagen, FavoritosDTO favoritos, NoDeseadosDTO noDeseados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.rutaImagen = rutaImagen;
        this.favoritos = favoritos;
        this.noDeseados = noDeseados;
    }

    public UsuarioDTO(String id, String nombre, String apellido, String usuario, String contraseña, String correo, String rutaImagen, FavoritosDTO favoritos, NoDeseadosDTO noDeseados) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.rutaImagen = rutaImagen;
        this.favoritos = favoritos;
        this.noDeseados = noDeseados;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public FavoritosDTO getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(FavoritosDTO favoritos) {
        this.favoritos = favoritos;
    }

    public NoDeseadosDTO getNoDeseados() {
        return noDeseados;
    }

    public void setNoDeseados(NoDeseadosDTO noDeseados) {
        this.noDeseados = noDeseados;
    }
    
    public class FavoritosDTO {
        private List<String> artistasId;
        private List<String> albumesId;
        private List<String> cancionesId;

        public FavoritosDTO() {
        }

        public FavoritosDTO(List<String> artistasId, List<String> albumesId, List<String> cancionesId) {
            this.artistasId = artistasId;
            this.albumesId = albumesId;
            this.cancionesId = cancionesId;
        }

        public List<String> getArtistasId() {
            return artistasId;
        }

        public void setArtistasId(List<String> artistasId) {
            this.artistasId = artistasId;
        }

        public List<String> getAlbumesId() {
            return albumesId;
        }

        public void setAlbumesId(List<String> albumesId) {
            this.albumesId = albumesId;
        }

        public List<String> getCancionesId() {
            return cancionesId;
        }

        public void setCancionesId(List<String> cancionesId) {
            this.cancionesId = cancionesId;
        }
    }
    
    public static class NoDeseadosDTO {
        private List<String> generos;

        public NoDeseadosDTO() {
        }

        public NoDeseadosDTO(List<String> generos) {
            this.generos = generos;
        }

        public List<String> getGeneros() {
            return generos;
        }

        public void setGeneros(List<String> generos) {
            this.generos = generos;
        }
    }
}
