/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mensajes;

import javafx.scene.control.Alert;

/**
 *
 * @author Moises
 */
public class mensajitos {
    
    
    
    
    public void information(String mensaje){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Registro Exitoso");
            alert.setContentText(mensaje);
            alert.showAndWait();
    }
    
    public void error(String mensaje){
        
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(mensaje);
            alert.showAndWait();
    }
    
    
    public void warning(String mensaje){
     Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText(mensaje);
            alert.showAndWait();
}
    
    public void confirmation(String mensaje ){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText(mensaje);
            alert.showAndWait();
    }


}