/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.negocio;

import daos.UsuarioDAO;
import dtos.UsuarioDTO;
import interfaces.IUsuarioDAO;
import interfaces.IUsuarioNegocio;
import negocio.UsuarioNegocio;

/**
 *
 * @author ang3lfco
 */
public class Negocio {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        IUsuarioNegocio usuarioNegocio = new UsuarioNegocio(usuarioDAO);
        
        UsuarioDTO.FavoritosDTO favoritosDTO = usuarioNegocio.getFavoritos("682856dbe09c84ef98441541");
        System.out.println("ArtistasDTO ID favoritos: " + favoritosDTO.getArtistasId());
        System.out.println("ÁlbumesDTO ID favoritos: " + favoritosDTO.getAlbumesId());
        System.out.println("CancionesDTO ID favoritas: " + favoritosDTO.getCancionesId());
    }
}
