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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Ventana_principalController implements Initializable {
    
    @FXML
    private Label labelRP, labelRV;
   
    
    @FXML
    private Button btn_P2;
   
    @FXML
    private Button btn_E2;
     
    @FXML
    private Button btn_El2;
    
    @FXML
    private Button btn_V3;
   
    @FXML
    private Button btn_E3;
     
    @FXML
    private Button btn_El3;
    
    @FXML
    private Button Verificar;
    
    @FXML
    private Button btn_Cerrar;
    
     @FXML
    private Button entrada;
      @FXML
    private Button salida;

                   
    @FXML
    private AnchorPane Fondo;
    FXMLLoader carga;
    Parent raiz; 
    
       @FXML
    private Button reportico;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Inicio();
    }    
    
     private void Inicio() {
       
    btn_P2.setOnAction(Registros_Propietarios);
    btn_E2.setOnAction(Editar_Propietarios);
    btn_El2.setOnAction(Eliminar_propietarios);
    btn_V3.setOnAction(Registros_Visitantes);
    btn_E3.setOnAction(Editar_Visitantes);
    btn_El3.setOnAction(Eliminar_Visitantes);
    Verificar.setOnAction(Verificacion_Propietario);
    btn_Cerrar.setOnAction(Cerrar_Sesion);
    entrada.setOnAction(fechaentrada);
    salida.setOnAction(fechasalida);
    }

    
    private final EventHandler<ActionEvent> Registros_Propietarios = (ActionEvent event) -> {
           try {
               Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/Registro_Propietario.fxml"));
               Scene registroScene = new Scene(registroParent);
               Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
               window.setScene(registroScene);
               window.setTitle("Registros necesarios");
               window.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
    };
    
    private final EventHandler<ActionEvent> Editar_Propietarios = (ActionEvent event) -> {
           try {
               Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/Editar_Propietarrio.fxml"));
               Scene registroScene = new Scene(registroParent);
               Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
               window.setScene(registroScene);
               window.setTitle("Registros necesarios");
               window.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }   
     
     };
    
     private final EventHandler<ActionEvent> Eliminar_propietarios = (ActionEvent event) -> {
           try {
               Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/menustats.fxml"));
               Scene registroScene = new Scene(registroParent);
               Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
               window.setScene(registroScene);
               window.setTitle("Registros necesarios");
               window.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }   
     
     };
     
      private final EventHandler<ActionEvent> Registros_Visitantes = (ActionEvent event) -> {
           try {
               Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/Registar_Visitante.fxml"));
               Scene registroScene = new Scene(registroParent);
               Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
               window.setScene(registroScene);
               window.setTitle("Registros necesarios");
               window.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
    };
      
      private final EventHandler<ActionEvent> Editar_Visitantes = (ActionEvent event) -> {
           try {
               Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/Editar_Visitas.fxml"));
               Scene registroScene = new Scene(registroParent);
               Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
               window.setScene(registroScene);
               window.setTitle("Registros necesarios");
               window.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }   
     
     };
      
      private final EventHandler<ActionEvent> Eliminar_Visitantes = (ActionEvent event) -> {
           try {
               Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/stastvisita.fxml"));
               Scene registroScene = new Scene(registroParent);
               Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
               window.setScene(registroScene);
               window.setTitle("Registros necesarios");
               window.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }   
     
     };
      
       private final EventHandler<ActionEvent> Verificacion_Propietario = (ActionEvent event) -> {
           try {
               Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/Verificar_Propietario.fxml"));
               Scene registroScene = new Scene(registroParent);
               Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
               window.setScene(registroScene);
               window.setTitle("Registros necesarios");
               window.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
    };
    
       private final EventHandler<ActionEvent> Cerrar_Sesion = (ActionEvent event) -> {
           try {
               Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/FXMLDocument.fxml"));
               Scene registroScene = new Scene(registroParent);
               Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
               window.setScene(registroScene);
               window.setTitle("Registros necesarios");
               window.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
    };
      
       
       private final EventHandler<ActionEvent> fechaentrada = (ActionEvent event) -> {
           try {
               Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/fechaentrada.fxml"));
               Scene registroScene = new Scene(registroParent);
               Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
               window.setScene(registroScene);
               window.setTitle("Registros necesarios");
               window.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
    };
       
       
       private final EventHandler<ActionEvent> fechasalida = (ActionEvent event) -> {
           try {
               Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/fechasalida.fxml"));
               Scene registroScene = new Scene(registroParent);
               Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
               window.setScene(registroScene);
               window.setTitle("Registros necesarios");
               window.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
    };
       
       @FXML
       private void reportando(ActionEvent e){
             try {
               Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/reporte.fxml"));
               Scene registroScene = new Scene(registroParent);
               Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
               window.setScene(registroScene);
               window.setTitle("Registros necesarios");
               window.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           
       }
      
}
