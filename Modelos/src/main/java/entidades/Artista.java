/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author ang3lfco
 */
public class Artista {
    private ObjectId id;
    private String nombre;
    private String tipo;
    private String rutaImagen;
    private List<ObjectId> generosId;
    private List<Integrante> integrantes;

    public Artista() {
    }

    public Artista(String nombre, String tipo, String rutaImagen, List<ObjectId> generosId, List<Integrante> integrantes) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.rutaImagen = rutaImagen;
        this.generosId = generosId;
        this.integrantes = integrantes;
    }

    public Artista(ObjectId id, String nombre, String tipo, String rutaImagen, List<ObjectId> generosId, List<Integrante> integrantes) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rutaImagen = rutaImagen;
        this.generosId = generosId;
        this.integrantes = integrantes;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public List<ObjectId> getGenerosId() {
        return generosId;
    }

    public void setGenerosId(List<ObjectId> generosId) {
        this.generosId = generosId;
    }

    public List<Integrante> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Integrante> integrantes) {
        this.integrantes = integrantes;
    }

    @Override
    public String toString() {
        return "Artista{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", rutaImagen=" + rutaImagen + ", generosId=" + generosId + ", integrantes=" + integrantes + '}';
    }
    
    public static class Integrante {
        private ObjectId personaId;
        private String rol;
        private LocalDate fechaIngreso;
        private LocalDate fechaSalida;

        public Integrante() {
        }

        public Integrante(ObjectId personaId, String rol, LocalDate fechaIngreso, LocalDate fechaSalida) {
            this.personaId = personaId;
            this.rol = rol;
            this.fechaIngreso = fechaIngreso;
            this.fechaSalida = fechaSalida;
        }

        public ObjectId getPersonaId() {
            return personaId;
        }

        public void setPersonaId(ObjectId personaId) {
            this.personaId = personaId;
        }

        public String getRol() {
            return rol;
        }

        public void setRol(String rol) {
            this.rol = rol;
        }

        public LocalDate getFechaIngreso() {
            return fechaIngreso;
        }

        public void setFechaIngreso(LocalDate fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
        }

        public LocalDate getFechaSalida() {
            return fechaSalida;
        }

        public void setFechaSalida(LocalDate fechaSalida) {
            this.fechaSalida = fechaSalida;
        }

        @Override
        public String toString() {
            return "Integrante{" + "personaId=" + personaId + ", rol=" + rol + ", fechaIngreso=" + fechaIngreso + ", fechaSalida=" + fechaSalida + '}';
        }
    }
}
