/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import conexion.MongoConexion;
import dtos.ArtistaDTO;
import dtos.ArtistaDTO.integranteDTO;
import entidades.Artista;
import entidades.Artista.Integrante;
import entidades.Genero;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import interfaces.IArtistaDAO;

/**
 *
 * @author Oribiel
 */
public class ArtistaDAO implements IArtistaDAO {

    private final MongoCollection<Artista> coleccion;
    private final MongoCollection<Genero> generoCollection;

    public ArtistaDAO() {
        this.coleccion = MongoConexion.getArtistaCollection();
        this.generoCollection = MongoConexion.getGeneroCollection();
    }

    public List<ArtistaDTO> obtenerTodos() {
        List<ArtistaDTO> lista = new ArrayList<>();
        try (MongoCursor<Artista> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Artista artista = cursor.next();
                lista.add(convertirADTO(artista));
            }
        }
        return lista;
    }

    public List<ArtistaDTO> buscarPorNombre(String nombre) {
        List<ArtistaDTO> lista = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        try (MongoCursor<Artista> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Artista artista = cursor.next();
                lista.add(convertirADTO(artista));
            }
        }
        return lista;
    }

    public List<ObjectId> buscarArtistasPorNombre(String nombre) {
        List<ObjectId> ids = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        for (Artista artista : coleccion.find(filtro)) {
            ids.add(artista.getId());
        }
        return ids;
    }

    public List<ObjectId> buscarArtistasPorGenero(String nombreGenero) {
        List<ObjectId> ids = new ArrayList<>();
        Genero genero = generoCollection.find(Filters.eq("nombre", nombreGenero)).first();

        if (genero != null) {
            ObjectId idGenero = genero.getId();
            Bson filtro = Filters.in("generosId", idGenero);
            for (Artista artista : coleccion.find(filtro)) {
                ids.add(artista.getId());
            }
        }

        return ids;
    }

    public Artista buscarArtistaporId(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return coleccion.find(Filters.eq("_id", objectId)).first();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private ArtistaDTO convertirADTO(Artista artista) {
        if (artista == null) {
            return null;
        }

        List<String> generos = new ArrayList<>();
        if (artista.getGenerosId() != null) {
            for (ObjectId id : artista.getGenerosId()) {
                generos.add(id.toHexString());
            }
        }

        List<ArtistaDTO.integranteDTO> integrantes = new ArrayList<>();
        if (artista.getIntegrantes() != null) {
            for (Integrante integrante : artista.getIntegrantes()) {
                ArtistaDTO.integranteDTO dtoIntegrante = new ArtistaDTO.integranteDTO(
                        integrante.getPersonaId().toHexString(),
                        integrante.getRol(),
                        integrante.getFechaIngreso(),
                        integrante.getFechaSalida()
                );
                integrantes.add(dtoIntegrante);
            }
        }

        ArtistaDTO dto = new ArtistaDTO(
                artista.getId().toHexString(),
                artista.getNombre(),
                artista.getTipo(),
                artista.getRutaImagen(),
                generos,
                integrantes
        );

        return dto;
    }

    private Artista convertirAEntidad(ArtistaDTO dto) {
        if (dto == null) {
            return null;
        }

        List<ObjectId> generosId = new ArrayList<>();
        for (String id : dto.getGenerosId()) {
            generosId.add(new ObjectId(id));
        }

        List<Integrante> integrantes = new ArrayList<>();
        if (dto.getIntegrantes() != null) {
            for (ArtistaDTO.integranteDTO integranteDTO : dto.getIntegrantes()) {
                Integrante integrante = new Integrante(
                        new ObjectId(integranteDTO.getPersonaId()),
                        integranteDTO.getRol(),
                        integranteDTO.getFechaIngreso(),
                        integranteDTO.getFechaSalida()
                );
                integrantes.add(integrante);
            }
        }

        Artista artista = new Artista(
                dto.getNombre(),
                dto.getTipo(),
                dto.getRutaImagen(),
                generosId,
                integrantes
        );

        if (dto.getId() != null && !dto.getId().isBlank()) {
            artista.setId(new ObjectId(dto.getId()));
        }

        return artista;
    }

}
