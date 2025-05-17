/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import conexion.MongoConexion;
import entidades.Usuario;
import interfaces.IUsuarioDAO;
import org.bson.conversions.Bson;

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
}
