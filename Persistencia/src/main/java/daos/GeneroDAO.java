/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import conexion.MongoConexion;
import dtos.GeneroDTO;
import entidades.Album;
import entidades.Artista;
import entidades.Genero;
import interfaces.IGeneroDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author ReneEzequiel23
 */
public class GeneroDAO implements IGeneroDAO{

    private final MongoCollection<Genero> coleccion;

    public GeneroDAO() {
        this.coleccion = MongoConexion.getGeneroCollection();
    }

    @Override
    public List<GeneroDTO> obtenerTodas() {
        List<GeneroDTO> lista = new ArrayList<>();
        try (MongoCursor<Genero> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Genero genero = cursor.next();
                lista.add(convertirADTO(genero));
            }
        }
        return lista;
    }

    @Override
    public void guardarGenero(String nombre){
        Genero genero = new Genero(new ObjectId(), nombre);
        coleccion.insertOne(genero);
    }
    
    @Override
    public Genero buscarGeneroPorId(Object id) {
        if (!(id instanceof ObjectId)) {
            return null;
        }
        return coleccion.find(Filters.eq("_id", id)).first();
    }
    
    @Override
    public void insertarGeneros(List<Genero> generos) {
        
        if (generos != null && !generos.isEmpty()) {
            coleccion.insertMany(generos);
            System.out.println("Se insertaron " + generos.size() + " generos correctamente.");
        } else {
            System.out.println("No se inserto ningun genero.");
        }
    }
    
        @Override
    public List<GeneroDTO> buscarPorNombre(String nombre) {
        List<GeneroDTO> lista = new ArrayList<>();
        Bson filtro = Filters.regex("nombre", Pattern.compile(Pattern.quote(nombre), Pattern.CASE_INSENSITIVE));
        try (MongoCursor<Genero> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Genero genero = cursor.next();
                lista.add(convertirADTO(genero));
            }
        }
        return lista;
    }
    
    private GeneroDTO convertirADTO(Genero genero) {
        if (genero == null) {
            return null;
        }

        GeneroDTO dto = new GeneroDTO(
                genero.getId().toHexString(),
                genero.getNombre()
        );
        return dto;
    }

}
