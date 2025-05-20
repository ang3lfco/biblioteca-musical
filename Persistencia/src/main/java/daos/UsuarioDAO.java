/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import conexion.MongoConexion;
import entidades.Usuario;
import interfaces.IUsuarioDAO;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author ang3lfco
 */
public class UsuarioDAO implements IUsuarioDAO{
    private MongoCollection<Usuario> coleccion;
    
    public UsuarioDAO(){
        this.coleccion = MongoConexion.getUsuarioCollection();
    }
    
    @Override
    public void insertar(Usuario usuario){
        coleccion.insertOne(usuario);
    }
    
    @Override
    public void editar(Usuario usuario) {
        Bson filtro = Filters.eq("_id", usuario.getId());
            Bson actualizacion = Updates.combine(
            Updates.set("nombre", usuario.getNombre()),
            Updates.set("apellido", usuario.getApellido()),
            Updates.set("usuario", usuario.getUsuario()),
            Updates.set("contraseña", usuario.getContraseña()),
            Updates.set("correo", usuario.getCorreo()),
            Updates.set("rutaImagen", usuario.getRutaImagen())
        );
        coleccion.updateOne(filtro, actualizacion);
    }
    
    @Override
    public Usuario validar(String usuario){
        Bson filtro = Filters.and(
                Filters.eq("usuario", usuario)
        );
        return coleccion.find(filtro).first();
    }
    
    @Override
    public Usuario.Favoritos getFavoritos(ObjectId id) {
        Usuario u = coleccion.find(Filters.eq("_id", id))
                             .projection(Projections.include("favoritos"))
                             .first();
        if (u != null) {
            return u.getFavoritos();
        }
        return null;
    }
    
    @Override
    public Usuario.NoDeseados getNoDeseados(ObjectId id){
        Usuario u = coleccion.find(Filters.eq("_id", id))
                             .projection(Projections.include("noDeseados"))
                             .first();
        if (u != null) {
            return u.getNoDeseados();
        }
        return null;
    }
    
    @Override
    public boolean insertarFavoritoArtista(ObjectId idUsuario, ObjectId idArtista){
        Bson filtro = Filters.eq("_id", idUsuario);
        Bson actualizacion = Updates.addToSet("favoritos.artistasId", idArtista);
        UpdateResult resultado = coleccion.updateOne(filtro, actualizacion);
        return resultado.getModifiedCount() > 0;
    }
    
    @Override
    public boolean insertarFavoritoAlbum(ObjectId idUsuario, ObjectId idAlbum){
        Bson filtro = Filters.eq("_id", idUsuario);
        Bson actualizacion = Updates.addToSet("favoritos.albumesId", idAlbum);
        UpdateResult resultado = coleccion.updateOne(filtro, actualizacion);
        return resultado.getModifiedCount() > 0;
    }
    
    @Override
    public boolean insertarFavoritoCancion(ObjectId idUsuario, ObjectId idCancion){
        Bson filtro = Filters.eq("_id", idUsuario);
        Bson actualizacion = Updates.addToSet("favoritos.cancionesId", idCancion);
        UpdateResult resultado = coleccion.updateOne(filtro, actualizacion);
        return resultado.getModifiedCount() > 0;
    }
    
    @Override
    public boolean insertarGeneroNoDeseado(ObjectId idUsuario, ObjectId idGenero){
        Bson filtro = Filters.eq("_id", idUsuario);
        Bson actualizacion = Updates.addToSet("noDeseados.generos", idGenero);
        UpdateResult resultado = coleccion.updateOne(filtro, actualizacion);
        return resultado.getModifiedCount() > 0;
    }
}
