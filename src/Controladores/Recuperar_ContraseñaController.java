package Controladores;

import static Controladores.Editar_VisitasController.conexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import mensajes.mensajitos;

public class Recuperar_ContraseñaController implements Initializable {

    @FXML
    private Label label36, label37, label38;

    @FXML
    private TextField txt62, txt64, txtpass, txtpass2;
    String correo, cedula;
    static String usuariopass = "";
    static int tipo = 0;
    mensajitos msj = new mensajitos();

    @FXML
    private RadioButton radio, radio2;

    // Mano en ese TXT salte del 62 al 64 porque cuando ponia en txt63 el programano copilaba pero no entraba en la opcion de recuperar contraseña por eso salte un numero.
    @FXML
    private Button btn_VD;
    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inicio();
        ToggleGroup rg = new ToggleGroup();
        radio.setToggleGroup(rg);
        radio2.setToggleGroup(rg);
    }

    private void Inicio() {

        btn_VD.setOnAction(Verificar_Usuario);
    }

    private final EventHandler<ActionEvent> Verificar_Usuario = (ActionEvent event) -> {
        String cedula = this.txt62.getText();

            String agarra = this.txtpass.getText();
            String agarra2 = this.txtpass2.getText();


        if (radio.isSelected()) {
           

            if(validar_passes(agarra,agarra2)){
            try {
                conexion();
                pst = conexion.prepareStatement("UPDATE superusuario SET Contraseña=? WHERE Cedula=' " + cedula + "' ");
                pst.setString(1, agarra);
                pst.executeUpdate();
                msj.information("Exito !!!");
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
                
                
                
                
                

            } catch (Exception ex) {

            }
            
            }else{
                msj.error("Contrasñas diferentes");
                
            }
            
        }else if(radio2.isSelected()){
            
            try {
                conexion();
                pst = conexion.prepareStatement("UPDATE vigilante SET Contraseña=? WHERE Cedula=' " + cedula + "' ");
                pst.setString(1, agarra);
                pst.executeUpdate();
                msj.information("Exito !!!");
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

            } catch (Exception ex) {

            }
            
        }

            
            
       

    };

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }
    
    public boolean  validar_passes( String pass, String pass2){
        
      if(!(pass.equalsIgnoreCase(pass2))){
           
          return false;
      }
      return true;
    }
   
    
  

}
