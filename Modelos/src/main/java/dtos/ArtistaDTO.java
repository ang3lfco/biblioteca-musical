/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import entidades.Artista;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Oribiel
 */
public class ArtistaDTO {

    private String id;
    private String nombre;
    private String tipo;
    private String rutaImagen;
    private List<String> generosId;
    private List<integranteDTO> integrantes;

    public ArtistaDTO() {
    }

    public ArtistaDTO(String id, String nombre, String tipo, String rutaImagen, List<String> generosId, List<integranteDTO> integrantes) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rutaImagen = rutaImagen;
        this.generosId = generosId;
        this.integrantes = integrantes;
    }

    public ArtistaDTO(String nombre, String tipo, String rutaImagen, List<String> generosId, List<integranteDTO> integrantes) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.rutaImagen = rutaImagen;
        this.generosId = generosId;
        this.integrantes = integrantes;
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

    public List<String> getGenerosId() {
        return generosId;
    }

    public void setGenerosId(List<String> generosId) {
        this.generosId = generosId;
    }

    public List<integranteDTO> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<integranteDTO> integrantes) {
        this.integrantes = integrantes;
    }

    @Override
    public String toString() {
        return "ArtistaDTO{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", rutaImagen=" + rutaImagen + ", generosId=" + generosId + ", integrantes=" + integrantes + '}';
    }

    public static class integranteDTO {

        private String personaId;
        private String rol;
        private LocalDate fechaIngreso;
        private LocalDate fechaSalida;

        public integranteDTO() {
        }

        public integranteDTO(String personaId, String rol, LocalDate fechaIngreso, LocalDate fechaSalida) {
            this.personaId = personaId;
            this.rol = rol;
            this.fechaIngreso = fechaIngreso;
            this.fechaSalida = fechaSalida;
        }

        public String getPersonaId() {
            return personaId;
        }

        public void setPersonaId(String personaId) {
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
            return "integranteDTO{" + "personaId=" + personaId + ", rol=" + rol + ", fechaIngreso=" + fechaIngreso + ", fechaSalida=" + fechaSalida + '}';
        }

    }

}
