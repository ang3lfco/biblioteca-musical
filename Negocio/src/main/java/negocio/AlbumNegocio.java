/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.AlbumDTO;
import entidades.Album;
import interfaces.IAlbumDAO;
import interfaces.IAlbumNegocio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author ReneEzequiel23
 */
public class AlbumNegocio implements IAlbumNegocio{

    IAlbumDAO albumDAO;

    public AlbumNegocio(IAlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    @Override
    public List<AlbumDTO> buscarAlbumesPorNombre(String nombre, ObjectId idUsuario) {
        List<AlbumDTO> resultados = new ArrayList<>();
        List<Album> dtos = albumDAO.buscarAlbumesPorNombre(nombre, idUsuario);
        
        resultados = this.ConvertirListaEntidadAListaDTO(dtos);
        
        return resultados;
    }
    
    @Override
    public List<AlbumDTO> buscarAlbumesPorGenero(String genero, ObjectId idUsuario){
        List<AlbumDTO> resultados = new ArrayList<>();
        List<Album> dtos = albumDAO.buscarAlbumesPorGenero(genero, idUsuario);
        
        resultados = this.ConvertirListaEntidadAListaDTO(dtos);
        
        return resultados;
    }
    
    @Override
    public List<AlbumDTO> buscarAlbumesPorFecha(LocalDate fecha, ObjectId idUsuario){
        List<AlbumDTO> resultados = new ArrayList<>();
        List<Album> dtos = albumDAO.buscarAlbumesPorFecha(fecha, idUsuario);
        
        resultados = this.ConvertirListaEntidadAListaDTO(dtos);
        
        return resultados;
    }
    
    @Override
    public List<AlbumDTO> getAlbumes(ObjectId idUsuario){
        List<AlbumDTO> resultados = new ArrayList<>();
        List<Album> dtos = albumDAO.getAlbumes(idUsuario);
        
        resultados = this.ConvertirListaEntidadAListaDTO(dtos);
        
        return resultados;
    }

    private List<AlbumDTO> ConvertirListaEntidadAListaDTO(List<Album> listaDAO) {
        List<AlbumDTO> resultados = new ArrayList<>();

        if (listaDAO != null) {

            for (Album album : listaDAO) {
                resultados.add(new AlbumDTO(
                        album.getId(),
                        album.getNombre(),
                        album.getLanzamiento(),
                        album.getGenerosId(),
                        album.getRutaImagen(),
                        album.getArtistasId()
                ));
            }
        }
        return resultados;
    }
}
