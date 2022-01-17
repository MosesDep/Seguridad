package Controladores;

import static Controladores.FXMLDocumentController.conexion;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import mensajes.mensajitos;

public class Verificar_PropietarioController implements Initializable {

    @FXML
    private Label label17;

    @FXML
    private TextField txt27;

    @FXML
    private TableView Tabla3;

    @FXML
    private Button BuscarD8;

    @FXML
    private Button Atras13;

    mensajitos msj = new mensajitos();

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;

     Tooltip tooltip = new Tooltip();
    String casa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inicio();
        tooltip.setText("\nIngresa el numero de casa \n  A-9 ejemplo\n");
        this.txt27.setTooltip(tooltip);
        
    }

    private void Inicio() {

        Atras13.setOnAction(Atras_13);
        BuscarD8.setOnAction(buscador);
    }

    private final EventHandler<ActionEvent> Atras_13 = (ActionEvent event) -> {
        try {
            Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/ventana_principal.fxml"));
            Scene registroScene = new Scene(registroParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(registroScene);
            window.setTitle("Registros necesarios");
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    private final EventHandler<ActionEvent> buscador = (ActionEvent event) -> {

        casa = this.txt27.getText();

        try {
            conexion();
            pst = conexion.prepareStatement("SELECT NROCASA FROM propietario WHERE NROCASA=?");
            pst.setString(1, casa);
            rs = pst.executeQuery();
            if (rs.next()) {
                msj.information("Registro Exitoso ");

            } else {
              msj.warning(" EL Usuario No Existe en el Sistema");

            }

        } catch (Exception ex) {

        }

    };

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }

}
