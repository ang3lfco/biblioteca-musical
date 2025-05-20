/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encriptador;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ang3lfco
 */
public class Encriptador {
    public static String encriptarContraseña(String contraseña){
        String contraseñaEncriptada = BCrypt.hashpw(contraseña, BCrypt.gensalt());
        return contraseñaEncriptada;
    }
    public static boolean verificarContraseña(String contraseñaOriginal, String contraseñaEncriptada){
        return BCrypt.checkpw(contraseñaOriginal, contraseñaEncriptada);
    }
}
