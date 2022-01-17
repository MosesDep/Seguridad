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
public class reportemodelo {
    
    String cedula,turnos;

    public reportemodelo(String cedula, String turnos) {
        this.cedula = cedula;
        this.turnos = turnos;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTurnos() {
        return turnos;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setTurnos(String turnos) {
        this.turnos = turnos;
    }

    

    
    

    
    
    
}
