/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.UsuarioDTO;
import entidades.Usuario;
import interfaces.IUsuarioDAO;
import interfaces.IUsuarioNegocio;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

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
    public void editarUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario(
                new ObjectId(usuarioDTO.getId()),
                usuarioDTO.getNombre(),
                usuarioDTO.getApellido(),
                usuarioDTO.getUsuario(),
                usuarioDTO.getContraseña(),
                usuarioDTO.getCorreo(),
                usuarioDTO.getRutaImagen(),
                null,
                null
        );
        usuarioDAO.editar(usuario);
    }
    
    @Override
    public UsuarioDTO validarSesion(String usuario, String contrasena){
        Usuario usuarioValidado = usuarioDAO.validar(usuario, contrasena);
        UsuarioDTO usuarioEncontrado = new UsuarioDTO(
                String.valueOf(usuarioValidado.getId()),
                usuarioValidado.getNombre(),
                usuarioValidado.getApellido(),
                usuarioValidado.getUsuario(),
                usuarioValidado.getContraseña(),
                usuarioValidado.getCorreo(),
                usuarioValidado.getRutaImagen(),
                null,
                null
        );
        return usuarioEncontrado;
    }
    
    @Override
    public UsuarioDTO.FavoritosDTO getFavoritos(String idUsuario){
        Usuario.Favoritos Favoritos = usuarioDAO.getFavoritos(new ObjectId(idUsuario));
        UsuarioDTO.FavoritosDTO FavoritosDTO = new UsuarioDTO.FavoritosDTO();
        
        if (Favoritos == null) {
            return null;
        }
        
        List<String> artistasId = new ArrayList<>();
        for(ObjectId id : Favoritos.getArtistasId()){
            artistasId.add(String.valueOf(id));
        }
        FavoritosDTO.setArtistasId(artistasId);
        
        List<String> albumesId = new ArrayList<>();
        for(ObjectId id : Favoritos.getAlbumesId()){
            albumesId.add(String.valueOf(id));
        }
        FavoritosDTO.setAlbumesId(albumesId);
        
        List<String> cancionesId = new ArrayList<>();
        for(ObjectId id : Favoritos.getCancionesId()){
            cancionesId.add(String.valueOf(id));
        }
        FavoritosDTO.setCancionesId(cancionesId);
        
        return FavoritosDTO;
    }
    
    @Override
    public UsuarioDTO.NoDeseadosDTO getNoDeseados(String idUsuario){
        Usuario.NoDeseados noDeseados = usuarioDAO.getNoDeseados(new ObjectId(idUsuario));
        UsuarioDTO.NoDeseadosDTO noDeseadosDTO = new UsuarioDTO.NoDeseadosDTO();
        
        if (noDeseados == null) {
            return null;
        }
        
        List<String> generosId = new ArrayList<>();
        for(ObjectId id : noDeseados.getGeneros()){
            generosId.add(String.valueOf(id));
        }
        noDeseadosDTO.setGeneros(generosId);
        
        return noDeseadosDTO;
    }
    
    @Override
    public boolean insertarFavoritoArtista(String idUsuario, String idArtista){
        return usuarioDAO.insertarFavoritoArtista(new ObjectId(idUsuario), new ObjectId(idArtista));
    }

    @Override
    public boolean insertarFavoritoAlbum(String idUsuario, String idAlbum){
        return usuarioDAO.insertarFavoritoAlbum(new ObjectId(idUsuario), new ObjectId(idAlbum));
    }

    @Override
    public boolean insertarFavoritoCancion(String idUsuario, String idCancion){
        return usuarioDAO.insertarFavoritoCancion(new ObjectId(idUsuario), new ObjectId(idCancion));
    }

    @Override
    public boolean insertarGeneroNoDeseado(String idUsuario, String idGenero){
        return usuarioDAO.insertarGeneroNoDeseado(new ObjectId(idUsuario), new ObjectId(idGenero));
    }
}
