/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;


import java.io.FileInputStream;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class NuevoController implements Initializable {

    
      @FXML
    private Label label22, label23, label24, label25;

    @FXML
    private TextField txt36, txt37, txt38, txt39, txt40, txt41, txt42, txt43, txt44;

    @FXML
    private Button Atras2;

    @FXML
    private Button GuardarD;

    @FXML
    private Button BuscarD;

    @FXML
    private Button btn_I;
    
   @FXML
   private Button btn_I4;
   
   @FXML
   private DatePicker f;
   
    
    @FXML
   private DatePicker f2;
   
       private FileInputStream fis;

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;
    String nombre, apellido, cedula, correo, pass, direccion, tlfno, celab, nombresito;
    FileChooser fileChooser = new FileChooser();

    String consulta = " SELECT * FROM vigilante WHERE Nombre=?";
    
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    
    
     private void Inicio() {

         
        

    }
    
     
      
      
       public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }
       
       
       @FXML
       private void save(ActionEvent e){
           
           
       }
       
       @FXML
       private void back(ActionEvent e){
            try {
            Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/ventana_admin.fxml"));
            Scene registroScene = new Scene(registroParent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(registroScene);
            window.setTitle("Registros necesarios");
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
       
         
       @FXML
       private void search (ActionEvent e){
           this.celab = this.txt44.getText();

        try {
            conexion();
            pst = conexion.prepareStatement(" SELECT Nombre FROM vigilante WHERE Nombre=?");
            pst.setString(1, celab);
            rs = pst.executeQuery();

            if (rs.next()) {
               
                

                nombresito = rs.getString("Nombre");

            } else {

                JOptionPane.showMessageDialog(null, "El usuario no existe");
            }

        } catch (Exception ex) {

        }

        this.txt36.setText(nombresito);
           
           
       }
           
       }
      
    
    
    

