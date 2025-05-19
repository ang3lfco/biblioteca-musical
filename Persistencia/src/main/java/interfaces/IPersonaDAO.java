/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Persona;
import java.util.List;

/**
 *
 * @author Oribiel
 */
public interface IPersonaDAO {
    
    void insertarPersonas(List<Persona> personas);
}
