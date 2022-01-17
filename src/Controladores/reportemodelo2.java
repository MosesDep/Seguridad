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
public class reportemodelo2 {
    
    String CedulaRE, FechaRegistro;

    public reportemodelo2(String CedulaRE, String FechaRegistro) {
        this.CedulaRE = CedulaRE;
        this.FechaRegistro = FechaRegistro;
    }

    public String getCedulaRE() {
        return CedulaRE;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setCedulaRE(String CedulaRE) {
        this.CedulaRE = CedulaRE;
    }

    public void setFechaRegistro(String FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }
    

  
   
    
    
}
