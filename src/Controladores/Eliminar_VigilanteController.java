
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Eliminar_VigilanteController implements Initializable {
    
    @FXML
    private Label label26, label27;
    
    @FXML
    private TextField txt45;
    
    @FXML
    private TableView Tabla4;

    @FXML
    private Button BuscarD2;
      
    @FXML
    private Button EliminarD;
      
    @FXML
    private Button GuardarD2;
    
    @FXML
    private Button Atras3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inicio();
    }    
    
    private void Inicio() {
        
    Atras3.setOnAction(Atras_3);
    }
        private final EventHandler<ActionEvent> Atras_3 = (ActionEvent event) -> {
           try {
               Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/ventana_admin.fxml"));
               Scene registroScene = new Scene(registroParent);
               Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
               window.setScene(registroScene);
               window.setTitle("Registros necesarios");
               window.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }; 
    

    
}
