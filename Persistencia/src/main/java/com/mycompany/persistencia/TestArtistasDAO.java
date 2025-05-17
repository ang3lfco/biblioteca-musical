/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.persistencia;

import daos.ArtistasDAO;
import dtos.ArtistasDTO;
import java.util.List;

/**
 *
 * @author Oribiel
 */
public class TestArtistasDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      ArtistasDAO artistasDAO = new ArtistasDAO();


// Prueba metodo obtener todos
        List<ArtistasDTO> todosLosArtistas = artistasDAO.obtenerTodos();
        for (ArtistasDTO artista : todosLosArtistas) {
            mostrarArtista(artista);
        }

//prueba buscar por nombre
//        List<ArtistasDTO> artistasEncontrados = artistasDAO.buscarPorNombre("The Beatles");
//        for (ArtistasDTO artista : artistasEncontrados) {
//            mostrarArtista(artista);
//        }
  }


    private static void mostrarArtista(ArtistasDTO artista) {
        System.out.println("ID: " + artista.getId());
        System.out.println("Nombre: " + artista.getNombre());
        System.out.println("Tipo: " + artista.getTipo());
        System.out.println("Ruta Imagen: " + artista.getRutaImagen());
        System.out.println("Generos ID: " + artista.getGenerosId());
        System.out.println("Integrantes: " + artista.getIntegrantes());
        System.out.println("------------------------------------");
    }

    }
    

