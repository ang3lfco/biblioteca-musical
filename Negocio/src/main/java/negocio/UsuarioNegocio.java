/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.UsuarioDTO;
import entidades.Usuario;
import interfaces.IUsuarioDAO;
import interfaces.IUsuarioNegocio;

/**
 *
 * @author ang3lfco
 */
public class UsuarioNegocio implements IUsuarioNegocio {
    private IUsuarioDAO usuarioDAO;
    
    public UsuarioNegocio(IUsuarioDAO usuarioDAO){
        this.usuarioDAO = usuarioDAO;
    }
    
    @Override
    public void registrarUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario(
                usuarioDTO.getNombre(),
                usuarioDTO.getApellido(),
                usuarioDTO.getUsuario(),
                usuarioDTO.getContraseña(),
                usuarioDTO.getCorreo(),
                usuarioDTO.getRutaImagen(),
                null,
                null
        );
        usuarioDAO.insertar(usuario);
    }
    
    @Override
    public boolean validarSesion(String usuario, String contrasena){
        return usuarioDAO.validar(usuario, contrasena) != null;
    }
}
