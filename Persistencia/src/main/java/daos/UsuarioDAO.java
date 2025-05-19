/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
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
    public Usuario validar(String usuario, String contrasena){
        Bson filtro = Filters.and(
                Filters.eq("usuario", usuario),
                Filters.eq("contraseña", contrasena)
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
}
