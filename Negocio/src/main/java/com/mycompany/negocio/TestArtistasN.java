/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.negocio;

import daos.ArtistaDAO;
import dtos.ArtistaDTO;
import interfaces.IArtistaDAO;
import interfaces.IArtistaNegocio;
import negocio.ArtistaNegocio;

/**
 *
 * @author Oribiel
 */
public class TestArtistasN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IArtistaDAO artistaDAO = new ArtistaDAO();
        IArtistaNegocio artistaNegocio = new ArtistaNegocio(artistaDAO);
        String idArtista = "682849ecaecd2bb97444152e";

        ArtistaDTO artista = artistaNegocio.buscarArtistaporId(idArtista);


        if (artista != null) {
            System.out.println(" Artista encontrado:");
            System.out.println("ID: " + artista.getId());
            System.out.println("Nombre: " + artista.getNombre());
            System.out.println("Tipo: " + artista.getTipo());
            System.out.println("Ruta imagen: " + artista.getRutaImagen());
            System.out.println("Generos: " + artista.getGenerosId());

            System.out.println(" Integrantes:");
            for (ArtistaDTO.integranteDTO integrante : artista.getIntegrantes()) {
                System.out.println("  PersonaID: " + integrante.getPersonaId());
                System.out.println("    Rol: " + integrante.getRol());
                System.out.println("    Ingreso: " + integrante.getFechaIngreso());
                System.out.println("    Salida: " + integrante.getFechaSalida());
            }
        } else {
            System.out.println(" No se encontró ningún artista con ese ID.");
        }
    }

}
