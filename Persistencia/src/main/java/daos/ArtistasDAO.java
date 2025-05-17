/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import conexion.MongoConexion;
import dtos.ArtistasDTO;
import entidades.Artista;
import interfaces.IArtistasDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;

/**
 *
 * @author Oribiel
 */
public class ArtistasDAO implements IArtistasDAO{
    
    
    private final MongoCollection<Artista> coleccion;

    public ArtistasDAO() {
        this.coleccion = MongoConexion.getArtistaCollection();
    }

    
   
     
    public List<ArtistasDTO> obtenerTodos() {
        List<ArtistasDTO> lista = new ArrayList<>();
        try (MongoCursor<Artista> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Artista artista = cursor.next();
                lista.add(convertirADTO(artista));
            }
        }
        return lista;
    }

    public List<ArtistasDTO> buscarPorNombre(String nombre) {
        List<ArtistasDTO> lista = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        try (MongoCursor<Artista> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Artista artista = cursor.next();
                lista.add(convertirADTO(artista));
            }
        }
        return lista;
    }


    private ArtistasDTO convertirADTO(Artista artista) {
        return new ArtistasDTO(
            artista.getId(),
            artista.getNombre(),
            artista.getTipo(),
            artista.getRutaImagen(),
            artista.getGenerosId(),
            artista.getIntegrantes()
        );
    }
}

