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
public class modelotabla {
    
     String cedula,nombre, apellido, sexo, cedularegistrante;

    public modelotabla(String cedula, String nombre, String apellido, String sexo, String cedularegistrante) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.cedularegistrante = cedularegistrante;
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

    public String getSexo() {
        return sexo;
    }

    public String getCedularegistrante() {
        return cedularegistrante;
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

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setCedularegistrante(String cedularegistrante) {
        this.cedularegistrante = cedularegistrante;
    }

    
    
}
