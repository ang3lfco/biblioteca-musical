/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.CancionDTO;
import entidades.Cancion;
import java.util.List;
import org.bson.types.ObjectId;
import interfaces.ICancionDAO;
import interfaces.ICancionNegocio;
import java.util.ArrayList;

/**
 *
 * @author Oribiel
 */
public class CancionNegocio implements ICancionNegocio {

    private ICancionDAO cancionesDAO;

    public CancionNegocio(ICancionDAO cancionesDAO) {
        this.cancionesDAO = cancionesDAO;
    }

    @Override
    public CancionDTO obtenerCancionPorId(String id) {
        return cancionesDAO.getCancionPorId(id);
    }

    public List<CancionDTO> obtenerTodas() {

        return cancionesDAO.obtenerTodas();
    }

    @Override
    public List<CancionDTO> buscarPorNombre(String nombre) {

        return cancionesDAO.buscarPorNombre(nombre);
    }

    @Override
    public List<ObjectId> buscarCancionesPorNombre(String nombre) {

        return cancionesDAO.buscarCancionesPorNombre(nombre);
    }

    @Override
    public List<Cancion> obtenerTodasEnEntidad() {

        return cancionesDAO.obtenerTodasEnEntidad();
    }

    @Override
    public void insertarCanciones(List<CancionDTO> cancion) {
        List<Cancion> lista = this.ConvertirListaDTOAListaEntidad(cancion);
        this.cancionesDAO.insertarCanciones(lista);
    }

    private List<Cancion> ConvertirListaDTOAListaEntidad(List<CancionDTO> CancionDTO) {
        List<Cancion> resultados = new ArrayList<>();

        if (CancionDTO != null) {

            for (CancionDTO dto : CancionDTO) {
                resultados.add(
                        this.convertirAEntidad(dto)
                );
            }
        }
        return resultados;
    }
    @Override
    public List<CancionDTO> buscarPorArtistaId(String artistaId){
        return cancionesDAO.buscarPorArtistaId(artistaId);
    }

    private Cancion convertirAEntidad(CancionDTO cancionDTO) {
        if (cancionDTO == null) {
            return null;
        }

        List<ObjectId> generos = new ArrayList<>();
        for (String id : cancionDTO.getGenerosId()) {
            generos.add(new ObjectId(id));
        }

        List<ObjectId> artistas = new ArrayList<>();
        for (String id : cancionDTO.getArtistasId()) {
            artistas.add(new ObjectId(id));
        }

        Cancion entidad = new Cancion(
                new ObjectId(cancionDTO.getId()),
                cancionDTO.getNombre(),
                new ObjectId(cancionDTO.getAlbumId()),
                generos,
                artistas
        );

        return entidad;
    }
}
