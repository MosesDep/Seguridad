package Controladores;

import static Controladores.Recuperar_ContraseñaController.conexion;
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

public class Recuperar_ClaveController implements Initializable {

    @FXML
    private Label label39, label40, label41;

    @FXML
    private TextField txt65, txt66;

    @FXML
    private Button GuardarC;

    @FXML
    private TextField txtcedula;

    @FXML
    private Label labelcedula;

    @FXML
    private RadioButton rbadmin, rbvigi;
    mensajitos msj = new mensajitos();

    String clave1, clave2, nivel;
    String nombrecito = Recuperar_ContraseñaController.usuariopass;
    int recibe = 0;
    String ci;
    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;
    Recuperar_ContraseñaController rc = new Recuperar_ContraseñaController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup rg = new ToggleGroup();
        rbadmin.setToggleGroup(rg);
        rbvigi.setToggleGroup(rg);
        Inicio();
        
        
    }

    private void Inicio() {

        GuardarC.setOnAction(Verificar_Clave);
    }

    private final EventHandler<ActionEvent> Verificar_Clave = (ActionEvent event) -> {

        clave1 = this.txt65.getText();
        clave2 = this.txt66.getText();

        if (rbadmin.isSelected()) {
            recibe = 1;
        } else if (rbvigi.isSelected()) {
            recibe = 2;
        }

        if (clave1.equalsIgnoreCase("") || clave2.equalsIgnoreCase("")) {
            msj.error("Error no dejes espacios vacios");
        }

        if (rbadmin.isSelected()) {
            ci = this.txtcedula.getText();

            if (clave1.equalsIgnoreCase(clave2)) {

                try {
                    conexion();
                    pst = conexion.prepareStatement("UPDATE superusuario Contraseña=? WHERE Cedula=?");
                    pst.setString(1, ci);
                    pst.setString(2, clave1);
                    pst.executeUpdate();
                    msj.information("Cmabio Exitoso");

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
                } catch (Exception e) {

                }

            } else {

                msj.error("Claves diferentes");

            }

        } else if (rbvigi.isSelected()) {
            try {
                conexion();
                pst = conexion.prepareStatement("UPDATE vigilante Contraseña=? WHERE Cedula=?");
                pst.setString(1, ci);
                pst.setString(2, clave1);
                pst.executeUpdate();
                msj.information("Cmabio Exitoso");

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
            } catch (Exception e) {

            }
        }

        /*try {
         Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/FXMLDocument.fxml"));
         Scene registroScene = new Scene(registroParent);
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(registroScene);
         window.setTitle("Registros necesarios");
         window.show();
         } catch (IOException ex) {
         Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
    };

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }
}
