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
}
