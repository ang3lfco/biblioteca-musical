/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Usuario;
import org.bson.types.ObjectId;

/**
 *
 * @author ang3lfco
 */
public interface IUsuarioDAO {
    void insertar(Usuario usuario);
    Usuario validar(String usuario, String contrasena);
    Usuario.Favoritos getFavoritos(ObjectId id);
    Usuario.NoDeseados getNoDeseados(ObjectId id);
    void editar(Usuario usuario);
    boolean insertarFavoritoArtista(ObjectId idUsuario, ObjectId idArtista);
    boolean insertarFavoritoAlbum(ObjectId idUsuario, ObjectId idAlbum);
    boolean insertarFavoritoCancion(ObjectId idUsuario, ObjectId idCancion);
    boolean insertarGeneroNoDeseado(ObjectId idUsuario, ObjectId idGenero);
}
