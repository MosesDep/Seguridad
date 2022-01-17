/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class CreadoresController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Label label, label2,label3,label4,label5,label6,label7,label8,label9;
   
    @FXML
    private Button btn_Volver;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Inicio();
        
    }    
    
    private void Inicio() {

        btn_Volver.setOnAction(Atras);
        

    }
    
    
      private final EventHandler<ActionEvent> Atras = (ActionEvent event) -> {
        try {
            Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/FXMLDocument.fxml"));
            Scene registroScene = new Scene(registroParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(registroScene);
            window.setTitle("Registros necesarios");
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
}
