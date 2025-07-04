/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import conexion.MongoConexion;
import dtos.AlbumDTO;
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
    public List<Album> getAlbumes() {
        MongoCollection<Album> coleccion;
        List<Album> resultados = new ArrayList<>();

        coleccion = MongoConexion.getAlbumCollection();
        try (MongoCursor<Album> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Album album = cursor.next();
                resultados.add(album);
            }
        }
        return resultados;
    }
    
    @Override
    public List<Album> buscarAlbumesPorNombre(String nombre) {
        MongoCollection<Album> coleccion;
        List<Album> resultados = new ArrayList<>();

        coleccion = MongoConexion.getAlbumCollection();
        Bson filtro = Filters.and(
                Filters.regex("nombre", nombre, "i")
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
    public List<Album> buscarAlbumesPorGenero(String genero) {
        MongoCollection<Album> coleccion;
        List<Album> resultados = new ArrayList<>();

        coleccion = MongoConexion.getAlbumCollection();
        Bson filtro = Filters.and(
                Filters.in("generosId", this.buscarIdGeneroPorNombre(genero))
                
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
    public List<Album> buscarAlbumesPorFecha(LocalDate fecha) {
        MongoCollection<Album> coleccion;
        List<Album> resultados = new ArrayList<>();

        coleccion = MongoConexion.getAlbumCollection();
        Bson filtro = Filters.and(
                Filters.eq("lanzamiento", fecha)
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
    public void agregarAlbum(Album album){
        MongoCollection<Album> coleccion;

        coleccion = MongoConexion.getAlbumCollection();
        
        coleccion.insertOne(album);
    }
    
    @Override
    public Album buscarAlbumPorId(Object id){
        MongoCollection<Album> coleccion;

        coleccion = MongoConexion.getAlbumCollection();
        
        if (!(id instanceof ObjectId)) {
            return null;
        }
        return coleccion.find(Filters.eq("_id", id)).first();
    }
    
    @Override
    public List<Album> obtenerTodos(){
        MongoCollection<Album> coleccion;

        coleccion = MongoConexion.getAlbumCollection();
        List<Album> resultados = new ArrayList<>();
        try (MongoCursor<Album> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Album album = cursor.next();
                resultados.add(album);
            }
        }
        return resultados;
    }
    
    @Override
    public void insertarAlbumes(List<Album> albumes) {
        MongoCollection<Album> coleccion;

        coleccion = MongoConexion.getAlbumCollection();
        
        if (albumes != null && !albumes.isEmpty()) {
            coleccion.insertMany(albumes);
            System.out.println("Se insertaron " + albumes.size() + " albumes correctamente.");
        } else {
            System.out.println("No se inserto ningun album.");
        }
    }
    
    @Override
    public List<AlbumDTO> buscarPorArtistaId(String artistaId) {
        MongoCollection<Album> coleccion;

        coleccion = MongoConexion.getAlbumCollection();
        List<AlbumDTO> lista = new ArrayList<>();
        Bson filtro;

        if ("sin-artista".equals(artistaId)) {
            // Buscar albumes donde artistasId sea null o esté vacío
            filtro = Filters.or(
                    Filters.eq("artistasId", null),
                    Filters.size("artistasId", 0)
            );
        } else {
            ObjectId idObj;
            try {
                idObj = new ObjectId(artistaId);
            } catch (IllegalArgumentException e) {
                return lista; 
            }

            filtro = Filters.eq("artistasId", idObj);
        }

        try (MongoCursor<Album> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Album album = cursor.next();
                lista.add(convertirADTO(album));
            }
        }

        return lista;
    }
    
    private List<ObjectId> buscarIdGeneroPorNombre(String nombre){
        MongoCollection<Genero> generosCollection = MongoConexion.getGeneroCollection();
        
        List<ObjectId> resultados = new ArrayList<>();
        
        try (MongoCursor<Genero> cursor = generosCollection.find(Filters.regex("nombre","^" + Pattern.quote(nombre), "i")).iterator()) {
            while (cursor.hasNext()) {
                Genero generos = cursor.next();
                resultados.add(generos.getId());
            }
        }
        return resultados;
    }
    
    private AlbumDTO convertirADTO(Album album) {
        if (album == null) {
            return null;
        }

        List<String> generos = new ArrayList<>();
        for (ObjectId id : album.getGenerosId()) {
            generos.add(id.toHexString());
        }

        List<String> artistas = new ArrayList<>();
        for (ObjectId id : album.getArtistasId()) {
            artistas.add(id.toHexString());
        }

        AlbumDTO dto = new AlbumDTO(
                album.getId().toHexString(),
                album.getNombre(),
                album.getLanzamiento(),
                generos,
                album.getRutaImagen(),
                artistas
        );

        return dto;

    }
}
