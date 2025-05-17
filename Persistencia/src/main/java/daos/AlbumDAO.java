/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import conexion.MongoConexion;
import entidades.Album;
import entidades.Genero;
import entidades.Usuario;
import interfaces.IAlbumDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author ReneEzequiel23
 */
public class AlbumDAO implements IAlbumDAO{

    public AlbumDAO() {
    }

    @Override
    public List<Album> getAlbumes(ObjectId idUsuario) {
        MongoCollection<Album> coleccion;
        List<Album> resultados = new ArrayList<>();

        coleccion = MongoConexion.getAlbumCollection();
        Bson filtro =
                Filters.not(Filters.in("generosId", generosNoDeseadosDelUsuario(idUsuario))
        );

        try (MongoCursor<Album> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Album album = cursor.next();
                resultados.add(album);
            }
        }
        return resultados;
    }
    
    @Override
    public List<Album> buscarAlbumesPorNombre(String nombre, ObjectId idUsuario) {
        MongoCollection<Album> coleccion;
        List<Album> resultados = new ArrayList<>();

        coleccion = MongoConexion.getAlbumCollection();
        Bson filtro = Filters.and(
                Filters.regex("nombre", nombre, "i"),
                Filters.not(Filters.in("generosId", generosNoDeseadosDelUsuario(idUsuario)))
        );

        try (MongoCursor<Album> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Album album = cursor.next();
                resultados.add(album);
            }
        }
        return resultados;
    }

    @Override
    public List<Album> buscarAlbumesPorGenero(String genero, ObjectId idUsuario) {
        MongoCollection<Album> coleccion;
        List<Album> resultados = new ArrayList<>();

        coleccion = MongoConexion.getAlbumCollection();
        Bson filtro = Filters.and(
                Filters.in("generosId", this.buscarIdGeneroPorNombre(genero)),
                Filters.not(Filters.in("generosId", generosNoDeseadosDelUsuario(idUsuario)))
        );

        try (MongoCursor<Album> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Album album = cursor.next();
                resultados.add(album);
            }
        }
        
        return resultados;
    }
    
    @Override
    public List<Album> buscarAlbumesPorFecha(LocalDate fecha, ObjectId idUsuario) {
        MongoCollection<Album> coleccion;
        List<Album> resultados = new ArrayList<>();

        coleccion = MongoConexion.getAlbumCollection();
        Bson filtro = Filters.and(
                Filters.gte("lanzamiento", fecha),
                Filters.not(Filters.in("generosId", generosNoDeseadosDelUsuario(idUsuario)))
        );

        try (MongoCursor<Album> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Album album = cursor.next();
                resultados.add(album);
            }
        }
        return resultados;
    }
    

    private List<ObjectId> generosNoDeseadosDelUsuario(ObjectId idUsuario) {
        MongoCollection<Usuario> usuariosCollection = MongoConexion.getUsuarioCollection();

        Usuario usuario = usuariosCollection.find(Filters.eq("_id", idUsuario)).first();
        if (usuario == null) {
            return List.of();
        }

        if (null == usuario.getNoDeseados()) {
            return List.of();
        }

        List<ObjectId> generosNoDeseados = usuario.getNoDeseados().getGeneros();

        return generosNoDeseados;
    }
    
    private ObjectId buscarIdGeneroPorNombre(String nombre){
        MongoCollection<Genero> generosCollection = MongoConexion.getGeneroCollection();
        
        Genero genero = generosCollection.find(Filters.regex("nombre", "^" + Pattern.quote(nombre) + "$", "i")).first();
        if (genero == null) {
            return new ObjectId();
        }
        
        return genero.getId();
    }
}
