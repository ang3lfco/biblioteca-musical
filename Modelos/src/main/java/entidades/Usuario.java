/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author ang3lfco
 */
public class Usuario {
    private ObjectId id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contraseña;
    private String correo;
    private String rutaImagen;
    private Favoritos favoritos;
    private NoDeseados noDeseados;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String usuario, String contraseña, String correo, String rutaImagen, Favoritos favoritos, NoDeseados noDeseados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.rutaImagen = rutaImagen;
        this.favoritos = favoritos;
        this.noDeseados = noDeseados;
    }

    public Usuario(ObjectId id, String nombre, String apellido, String usuario, String contraseña, String correo, String rutaImagen, Favoritos favoritos, NoDeseados noDeseados) {
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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public Favoritos getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Favoritos favoritos) {
        this.favoritos = favoritos;
    }

    public NoDeseados getNoDeseados() {
        return noDeseados;
    }

    public void setNoDeseados(NoDeseados noDeseados) {
        this.noDeseados = noDeseados;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", usuario=" + usuario + ", contrase\u00f1a=" + contraseña + ", correo=" + correo + ", rutaImagen=" + rutaImagen + ", favoritos=" + favoritos + ", noDeseados=" + noDeseados + '}';
    }
    
    public static class Favoritos {
        private List<ObjectId> artistasId;
        private List<ObjectId> albumesId;
        private List<ObjectId> cancionesId;

        public Favoritos() {
        }

        public Favoritos(List<ObjectId> artistasId, List<ObjectId> albumesId, List<ObjectId> cancionesId) {
            this.artistasId = artistasId;
            this.albumesId = albumesId;
            this.cancionesId = cancionesId;
        }

        public List<ObjectId> getArtistasId() {
            return artistasId;
        }

        public void setArtistasId(List<ObjectId> artistasId) {
            this.artistasId = artistasId;
        }

        public List<ObjectId> getAlbumesId() {
            return albumesId;
        }

        public void setAlbumesId(List<ObjectId> albumesId) {
            this.albumesId = albumesId;
        }

        public List<ObjectId> getCancionesId() {
            return cancionesId;
        }

        public void setCancionesId(List<ObjectId> cancionesId) {
            this.cancionesId = cancionesId;
        }

        @Override
        public String toString() {
            return "Favoritos{" + "artistasId=" + artistasId + ", albumesId=" + albumesId + ", cancionesId=" + cancionesId + '}';
        }
    }

    public static class NoDeseados {
        private List<ObjectId> generos;

        public NoDeseados() {
        }

        public NoDeseados(List<ObjectId> generos) {
            this.generos = generos;
        }

        public List<ObjectId> getGeneros() {
            return generos;
        }

        public void setGeneros(List<ObjectId> generos) {
            this.generos = generos;
        }

        @Override
        public String toString() {
            return "NoDeseados{" + "generos=" + generos + '}';
        }
    }
}
