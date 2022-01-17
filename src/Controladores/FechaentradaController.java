/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.Registro_AdministradorController.conexion;
import java.awt.Toolkit;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import mensajes.mensajitos;
       

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class FechaentradaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button botonentrada, botonatras;
    @FXML
    private DatePicker fechaentrada;
    @FXML
    private Label labelentrada;
    @FXML
    private TextField txtcedula;

    @FXML
    private ImageView imagenentrada;

    Tooltip tooltip = new Tooltip();
    String cedula;

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;
    mensajitos msj= new mensajitos();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        tooltip.setText("\n Cedula \n  Ejemplo 26904279\n");
        this.txtcedula.setTooltip(tooltip);
          txtcedula.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                char c = event.getCharacter().charAt(0);
                if (!Character.isDigit(c)) {
                    Toolkit.getDefaultToolkit().beep();
                    msj.warning("No coloques Letras");
                    event.consume();
                } else {

                }

            }
        });
    }

    @FXML
    private void back(ActionEvent ex) {

        try {
            Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/ventana_principal.fxml"));
            Scene registroScene = new Scene(registroParent);
            Stage window = (Stage) ((Node) ex.getSource()).getScene().getWindow();
            window.setScene(registroScene);
            window.setTitle("Registros necesarios");
            window.show();
        } catch (IOException e) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void register(ActionEvent e) {
        
        java.util.Date dt = new java.util.Date();
        

        java.text.SimpleDateFormat sdf = 
     new java.text.SimpleDateFormat("yyyy-MM-dd ");
        String currentTime = sdf.format(dt);


        
        cedula=this.txtcedula.getText();
        
        if(cedula.equalsIgnoreCase("") ||  fechaentrada.equals(null)){
            
             msj.error("No dejes campos vacios");
        }
        try {

            conexion();
            pst = conexion.prepareStatement("INSERT INTO turnovigilante (IDcedula, Turno) VALUES(?,?)");
            pst.setString(1, cedula);
            pst.setString(2, currentTime);
            

            pst.executeUpdate();
           msj.information("Turno Registrado");
        } catch (Exception ex) {

        }
        limpiar();

    }

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }
    public void limpiar (){
        this.txtcedula.setText("");
        this.fechaentrada.setValue(null);
        
    }

}
