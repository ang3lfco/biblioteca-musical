/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.GeneroDTO;
import entidades.Genero;
import java.util.List;

/**
 *
 * @author ReneEzequiel23
 */
public interface IGeneroDAO {
    List<GeneroDTO> obtenerTodas();
    Genero buscarGeneroPorId(Object id);
    void guardarGenero(String genero);
}
