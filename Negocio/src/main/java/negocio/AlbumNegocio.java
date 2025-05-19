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
    public List<AlbumDTO> buscarAlbumesPorNombre(String nombre) {
        List<AlbumDTO> resultados = new ArrayList<>();
        List<Album> dtos = albumDAO.buscarAlbumesPorNombre(nombre);
        
        resultados = this.ConvertirListaEntidadAListaDTO(dtos);
        
        return resultados;
    }
    
    @Override
    public List<AlbumDTO> buscarAlbumesPorGenero(String genero){
        List<AlbumDTO> resultados = new ArrayList<>();
        List<Album> dtos = albumDAO.buscarAlbumesPorGenero(genero);
        
        resultados = this.ConvertirListaEntidadAListaDTO(dtos);
        
        return resultados;
    }
    
    @Override
    public List<AlbumDTO> buscarAlbumesPorFecha(LocalDate fecha){
        List<AlbumDTO> resultados = new ArrayList<>();
        List<Album> dtos = albumDAO.buscarAlbumesPorFecha(fecha);
        
        resultados = this.ConvertirListaEntidadAListaDTO(dtos);
        
        return resultados;
    }
    
    @Override
    public List<AlbumDTO> getAlbumes(){
        List<AlbumDTO> resultados = new ArrayList<>();
        
        List<Album> dtos = albumDAO.getAlbumes();
        
        resultados = this.ConvertirListaEntidadAListaDTO(dtos);
        
        return resultados;
    }
    
    @Override
    public AlbumDTO buscarAlbumPorId(String id){
        if (!(id instanceof String)) {
            return null;
        }
        
        AlbumDTO resultado;
        Album dao = albumDAO.buscarAlbumPorId(new ObjectId(id));
        
        return this.convertirADTO(dao);
        
    }
    
    @Override
    public void agregarAlbum(AlbumDTO albumDTO){
        Album album;
        
        album = this.convertirAEntidad(albumDTO);
        
        albumDAO.agregarAlbum(album);
    }
    
    @Override
    public List<AlbumDTO> obtenerTodos(){
        List<AlbumDTO> resultados = new ArrayList<>();
        List<Album> dtos = albumDAO.obtenerTodos();
        
        resultados = this.ConvertirListaEntidadAListaDTO(dtos);
        
        return resultados;
    }
    
    @Override
    public void insertarAlbumes(List<AlbumDTO> lista){
        List<Album> albumes = this.ConvertirListaDTOAListaEntidad(lista);
        albumDAO.insertarAlbumes(albumes);
    }

    private List<AlbumDTO> ConvertirListaEntidadAListaDTO(List<Album> listaDAO) {
        List<AlbumDTO> resultados = new ArrayList<>();

        if (listaDAO != null) {

            for (Album album : listaDAO) {
                resultados.add(
                        this.convertirADTO(album)
                );
            }
        }
        return resultados;
    }
    
    private List<Album> ConvertirListaDTOAListaEntidad(List<AlbumDTO> listaDTO) {
        List<Album> resultados = new ArrayList<>();

        if (listaDTO != null) {

            for (AlbumDTO album : listaDTO) {
                resultados.add(
                        this.convertirAEntidad(album)
                );
            }
        }
        return resultados;
    }
    
    private AlbumDTO convertirADTO(Album album){
        if (album==null) {
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
    
    private Album convertirAEntidad(AlbumDTO albumDTO){
        if (albumDTO==null) {
            return null;
        }
        
        List<ObjectId> generos = new ArrayList<>();
        for (String id : albumDTO.getGenerosId()) {
            generos.add(new ObjectId(id));
        }
        
        List<ObjectId> artistas = new ArrayList<>();
        for (String id : albumDTO.getArtistasId()) {
            artistas.add(new ObjectId(id));
        }
        
        Album entidad = new Album(
        new ObjectId(albumDTO.getId()),
                albumDTO.getNombre(),
                albumDTO.getLanzamiento(),
                generos,
                albumDTO.getRutaImagen(),
                artistas
        );
        
        return entidad;
    }
}
