/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import dtos.UsuarioDTO;
import encriptador.Encriptador;
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
                Encriptador.encriptarContraseña(usuarioDTO.getContraseña()),
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
                Encriptador.encriptarContraseña(usuarioDTO.getContraseña()),
                usuarioDTO.getCorreo(),
                usuarioDTO.getRutaImagen(),
                null,
                null
        );
        usuarioDAO.editar(usuario);
    }
    
    @Override
    public UsuarioDTO validarSesion(String usuario, String contrasena){
        Usuario usuarioEncontrado = usuarioDAO.validar(usuario);
        if (usuarioEncontrado == null) {
            return null;
        }
        if(!Encriptador.verificarContraseña(contrasena, usuarioEncontrado.getContraseña())){
            return null;
        }
        UsuarioDTO usuarioValidado = new UsuarioDTO(
                String.valueOf(usuarioEncontrado.getId()),
                usuarioEncontrado.getNombre(),
                usuarioEncontrado.getApellido(),
                usuarioEncontrado.getUsuario(),
                usuarioEncontrado.getContraseña(),
                usuarioEncontrado.getCorreo(),
                usuarioEncontrado.getRutaImagen(),
                null,
                null
            );
        return usuarioValidado;
    }
    
    @Override
    public UsuarioDTO.FavoritosDTO getFavoritos(String idUsuario) {
        System.out.println("sadadsa");
        Usuario.Favoritos favoritos = usuarioDAO.getFavoritos(new ObjectId(idUsuario));
        UsuarioDTO.FavoritosDTO favoritosDTO = new UsuarioDTO.FavoritosDTO();

        if(favoritos == null){
            return favoritosDTO;
        }

        List<String> artistasId = new ArrayList<>();
        for(ObjectId id : Optional.ofNullable(favoritos.getArtistasId()).orElse(new ArrayList<>())){
            artistasId.add(String.valueOf(id));
        }
        favoritosDTO.setArtistasId(artistasId);

        List<String> albumesId = new ArrayList<>();
        for (ObjectId id : Optional.ofNullable(favoritos.getAlbumesId()).orElse(new ArrayList<>())){
            albumesId.add(String.valueOf(id));
        }
        favoritosDTO.setAlbumesId(albumesId);

        List<String> cancionesId = new ArrayList<>();
        for (ObjectId id : Optional.ofNullable(favoritos.getCancionesId()).orElse(new ArrayList<>())){
            cancionesId.add(String.valueOf(id));
        }
        favoritosDTO.setCancionesId(cancionesId);

        return favoritosDTO;
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
    
    @Override
    public boolean eliminarFavoritoCancion(String idUsuario, String idCancion) {
        return usuarioDAO.eliminarFavoritoCancion(new ObjectId(idUsuario), new ObjectId(idCancion));
    }

    @Override
    public boolean eliminarFavoritoAlbum(String idUsuario, String idAlbum) {
        return usuarioDAO.eliminarFavoritoAlbum(new ObjectId(idUsuario), new ObjectId(idAlbum));
    }

    @Override
    public boolean eliminarFavoritoArtista(String idUsuario, String idArtista) {
        return usuarioDAO.eliminarFavoritoArtista(new ObjectId(idUsuario), new ObjectId(idArtista));
    }

    @Override
    public boolean eliminarGeneroNoDeseado(String idUsuario, String idGenero) {
        return usuarioDAO.eliminarGeneroNoDeseado(new ObjectId(idUsuario), new ObjectId(idGenero));
    }
}
