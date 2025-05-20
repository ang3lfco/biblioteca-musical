/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.UsuarioDTO;

/**
 *
 * @author ang3lfco
 */
public interface IUsuarioNegocio {
    void registrarUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO validarSesion(String usuario, String contrasena);
    UsuarioDTO.FavoritosDTO getFavoritos(String idUsuario);
    UsuarioDTO.NoDeseadosDTO getNoDeseados(String idUsuario);
    void editarUsuario(UsuarioDTO usuarioDTO);
    boolean insertarFavoritoArtista(String idUsuario, String idArtista);
    boolean insertarFavoritoAlbum(String idUsuario, String idAlbum);
    boolean insertarFavoritoCancion(String idUsuario, String idCancion);
    boolean insertarGeneroNoDeseado(String idUsuario, String idGenero);
    boolean eliminarFavoritoCancion(String idUsuario, String idCancion);
    boolean eliminarFavoritoAlbum(String idUsuario, String idAlbum);
    boolean eliminarFavoritoArtista(String idUsuario, String idArtista);
    boolean eliminarGeneroNoDeseado(String idUsuario, String idGenero);
}
