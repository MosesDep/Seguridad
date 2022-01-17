/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

/**
 *
 * @author Moises
 */
public class modeloadmin {
    
    
    String cedula, nombre, apellido, numerodecasa;

    public modeloadmin(String cedula, String nombre, String apellido, String numerodecasa) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numerodecasa = numerodecasa;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumerodecasa() {
        return numerodecasa;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNumerodecasa(String numerodecasa) {
        this.numerodecasa = numerodecasa;
    }

   
    
}
