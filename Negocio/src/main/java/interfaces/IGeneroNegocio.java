/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.GeneroDTO;
import java.util.List;

/**
 *
 * @author ReneEzequiel23
 */
public interface IGeneroNegocio {
    List<GeneroDTO> obtenerTodas();
    GeneroDTO buscarGeneroPorId(String id);
    void guardarGenero(String genero);
}
