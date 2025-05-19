/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.persistencia;

import daos.ArtistaDAO;
import dtos.ArtistaDTO;
import entidades.Artista;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Oribiel
 */
public class TestArtistasDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      ArtistaDAO artistasDAO = new ArtistaDAO();


// Prueba metodo obtener todos
//        List<ArtistaDTO> todosLosArtistas = artistasDAO.obtenerTodos();
//        for (ArtistaDTO artista : todosLosArtistas) {
//            mostrarArtista(artista);
//        }

//prueba buscar por nombre
//        List<ArtistasDTO> artistasEncontrados = artistasDAO.buscarPorNombre("The Beatles");
//        for (ArtistaDTO artista : artistasEncontrados) {
//            mostrarArtista(artista);
//        }


        ObjectId personaId1 = new ObjectId();
        ObjectId personaId2 = new ObjectId();
        ObjectId generoId1 = new ObjectId(); 

        // Crear integrantes
        Artista.Integrante integrante1 = new Artista.Integrante(personaId1, "Vocalista", LocalDate.of(2020, 1, 10), null);
        Artista.Integrante integrante2 = new Artista.Integrante(personaId2, "Guitarrista", LocalDate.of(2021, 5, 20), null);

        // Crear lista de géneros (con IDs)
        List<ObjectId> generosId = new ArrayList<>();
        generosId.add(generoId1);

        // Crear artistas
        Artista artista1 = new Artista("Los Riffs", "Grupo", "imagenes/losriffs.png", generosId, List.of(integrante1, integrante2));
        Artista artista2 = new Artista("Solista Rock", "Solista", "imagenes/solistarock.jpg", generosId, List.of(integrante1));

        // Insertar artistas
        artistasDAO.insertarArtistas(List.of(artista1, artista2));
  }
    
    
    
    


    private static void mostrarArtista(ArtistaDTO artista) {
        System.out.println("ID: " + artista.getId());
        System.out.println("Nombre: " + artista.getNombre());
        System.out.println("Tipo: " + artista.getTipo());
        System.out.println("Ruta Imagen: " + artista.getRutaImagen());
        System.out.println("Generos ID: " + artista.getGenerosId());
        System.out.println("Integrantes: " + artista.getIntegrantes());
        System.out.println("------------------------------------");
    }

    }
    

