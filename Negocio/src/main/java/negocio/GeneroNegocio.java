/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.AlbumDTO;
import dtos.GeneroDTO;
import entidades.Album;
import entidades.Genero;
import interfaces.IGeneroDAO;
import interfaces.IGeneroNegocio;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author ReneEzequiel23
 */
public class GeneroNegocio implements IGeneroNegocio {

    private IGeneroDAO generoDAO;

    public GeneroNegocio(IGeneroDAO generoDAO) {
        this.generoDAO = generoDAO;
    }

    @Override
    public List<GeneroDTO> obtenerTodas() {
        return generoDAO.obtenerTodas();
    }

    @Override
    public GeneroDTO buscarGeneroPorId(String id) {
        Genero genero = generoDAO.buscarGeneroPorId(new ObjectId(id));
        GeneroDTO dto = this.convertirADTO(genero);
        return dto;
    }

    @Override
    public void guardarGenero(String genero) {
        generoDAO.guardarGenero(genero);
    }

    @Override
    public void insertarGeneros(List<GeneroDTO> generos) {
        List<Genero> lista = this.ConvertirListaDTOAListaEntidad(generos);
        this.generoDAO.insertarGeneros(lista);
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

    private List<Genero> ConvertirListaDTOAListaEntidad(List<GeneroDTO> GeneroDTO) {
        List<Genero> resultados = new ArrayList<>();

        if (GeneroDTO != null) {

            for (GeneroDTO dto : GeneroDTO) {
                resultados.add(
                        this.convertirAEntidad(dto)
                );
            }
        }
        return resultados;
    }

    private Genero convertirAEntidad(GeneroDTO genero) {
        if (genero == null) {
            return null;
        }
        Genero entidad;
        if (genero.getId() == null) {
            entidad = new Genero(
                    new ObjectId(),
                    genero.getNombre()
            );
        } else {
            entidad = new Genero(
                    new ObjectId(genero.getId()),
                    genero.getNombre()
            );
        }
        return entidad;
    }
}
