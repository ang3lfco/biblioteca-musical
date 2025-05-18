/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.GeneroDTO;
import entidades.Genero;
import interfaces.IGeneroDAO;
import interfaces.IGeneroNegocio;
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
        Genero genero =  generoDAO.buscarGeneroPorId(new ObjectId(id));
        GeneroDTO dto = this.convertirADTO(genero);
        return dto;
    }

    @Override
    public void guardarGenero(String genero) {
        generoDAO.guardarGenero(genero);
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
