/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.presentacion;

import datos.InsercionMasiva;
import interfaces.IUsuarioNegocio;
import ui.app.frmInicio;
import ui.sesion.frmInicioSesion;

/**
 *
 * @author ang3lfco
 */
public class Presentacion {

    public static void main(String[] args) {
        InsercionMasiva insercion = new InsercionMasiva();
        insercion.insertaDatos();
        System.out.println("Insercin completada correctamente.");
    }
}
