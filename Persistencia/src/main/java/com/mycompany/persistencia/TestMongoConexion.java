/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import conexion.MongoConexion;
import daos.UsuarioDAO;
import entidades.Album;
import entidades.Artista;
import entidades.Cancion;
import entidades.Genero;
import entidades.Persona;
import entidades.Usuario;
import interfaces.IUsuarioDAO;
import org.bson.types.ObjectId;

/**
 *
 * @author ang3lfco
 */
public class TestMongoConexion {
    public static void main(String[] args) {
        MongoCollection<Artista> colArtistas = MongoConexion.getArtistaCollection();
        MongoCollection<Album> colAlbumes = MongoConexion.getAlbumCollection();
        MongoCollection<Cancion> colCanciones = MongoConexion.getCancionCollection();
        MongoCollection<Persona> colPersonas = MongoConexion.getPersonaCollection();
        MongoCollection<Usuario> colUsuarios = MongoConexion.getUsuarioCollection();
        MongoCollection<Genero> colGeneros = MongoConexion.getGeneroCollection();

        System.out.println("=== ARTISTAS ===");
        try (MongoCursor<Artista> cursor = colArtistas.find().iterator()) {
            while (cursor.hasNext()) {
                Artista artista = cursor.next();
                System.out.println("- " + artista);
            }
        }

        System.out.println("\n=== ALBUMES ===");
        try (MongoCursor<Album> cursor = colAlbumes.find().iterator()) {
            while (cursor.hasNext()) {
                Album album = cursor.next();
                System.out.println("- " + album);
            }
        }

        System.out.println("\n=== CANCIONES ===");
        try (MongoCursor<Cancion> cursor = colCanciones.find().iterator()) {
            while (cursor.hasNext()) {
                Cancion cancion = cursor.next();
                System.out.println("- " + cancion);
            }
        }
        
        System.out.println("=== PERSONAS ===");
        try (MongoCursor<Persona> cursor = colPersonas.find().iterator()) {
            while (cursor.hasNext()) {
                Persona persona = cursor.next();
                System.out.println("- " + persona);
            }
        }
        
        System.out.println("\n=== USUARIOS ===");
        try (MongoCursor<Usuario> cursor = colUsuarios.find().iterator()) {
            while (cursor.hasNext()) {
                Usuario usuario = cursor.next();
                System.out.println("- " + usuario);
            }
        }
        
        System.out.println("\n=== GENEROS ===");
        try (MongoCursor<Genero> cursor = colGeneros.find().iterator()) {
            while (cursor.hasNext()) {
                Genero genero = cursor.next();
                System.out.println("- " + genero);
            }
        }
        
        // Prueba el método getFavoritos
        System.out.println("\n=== FAVORITOS DEL USUARIO ===");
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        String nombreUsuario = "ang3lfco";
        
        Usuario.Favoritos favoritos = usuarioDAO.getFavoritos(new ObjectId(""));
        if (favoritos != null) {
            System.out.println("Artistas ID favoritos: " + favoritos.getArtistasId());
            System.out.println("Álbumes ID favoritos: " + favoritos.getAlbumesId());
            System.out.println("Canciones ID favoritas: " + favoritos.getCancionesId());
        } else {
            System.out.println("No se encontraron favoritos para el usuario: " + nombreUsuario);
        }
    }
}
