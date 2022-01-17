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
public class modelo_problema {
   
 String Cedula, Contingencia, Fecha;

    public modelo_problema(String Cedula, String Contingencia, String Fecha) {
        this.Cedula = Cedula;
        this.Contingencia = Contingencia;
        this.Fecha = Fecha;
    }

    public String getCedula() {
        return Cedula;
    }

    public String getContingencia() {
        return Contingencia;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public void setContingencia(String Contingencia) {
        this.Contingencia = Contingencia;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
 
 
}
