package Controladores;

import java.awt.Toolkit;
import java.io.File;
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
import javafx.concurrent.Task;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import static javafx.scene.input.KeyCode.SPACE;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mensajes.mensajitos;

/**
 *
 * @author pc
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label1;

    @FXML
    private TextField txt1;

    @FXML
    private PasswordField Contraseña;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button botonclave;
    @FXML
    private Button boton_diseñadores;

    @FXML
    private ProgressIndicator progres;
    @FXML
    private AnchorPane Fondo;
    FXMLLoader carga;
    Parent raiz;
    mensajitos msj = new mensajitos();

    @FXML
    private RadioButton radioadmin, radiovigilante;

    Tooltip tooltip = new Tooltip();
    Tooltip tooltip2 = new Tooltip();
    Tooltip tooltip3 = new Tooltip();
    Tooltip tooltip4 = new Tooltip();
    Tooltip tooltip5 = new Tooltip();
    Tooltip tooltip6 = new Tooltip();
    Tooltip tooltip7 = new Tooltip();

    @FXML

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    String SQL = "SELECT Nombre, Contraseña FROM superusuario WHERE Nombre='Moises' AND Contraseña='1234'";
    ResultSet rs;
    String maldita;
    String klk;
    String columna = "Estatus";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inicio();

        ToggleGroup rg = new ToggleGroup();
        radioadmin.setToggleGroup(rg);
        radiovigilante.setToggleGroup(rg);
        tooltip.setText("\nCedula \n \n");
        this.txt1.setTooltip(tooltip);
        tooltip2.setText("\nContraseña\n  \n");
        this.Contraseña.setTooltip(tooltip2);
        
        this.txt1.setOnKeyReleased(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
 
                
            }
            
        }
        );
        

        txt1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {

                if (t.getCode() == SPACE) {
                    Toolkit.getDefaultToolkit().beep();
                    msj.warning(" Error no dejes espacios en blanco");
                    t.consume();

                } else {
                    String palabra = txt1.getText();
                    txt1.setText(palabra);
                    txt1.end();
                }

            }

        });

        
        txt1.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

    private void Inicio() {

        btn1.setOnAction(Inicio_S);

    }

    private final EventHandler<ActionEvent> Inicio_S = (ActionEvent event) -> {

        txt1.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

            }

        });
        String perro, pass;
        perro = this.txt1.getText();
        pass = this.Contraseña.getText();

        if (perro.equalsIgnoreCase("") && pass.equalsIgnoreCase("")) {
            msj.error("Error no dejes espacios en blanco");

        }

        if (radioadmin.isSelected()) {
            try {
                conexion();
                pst = conexion.prepareStatement("SELECT Cedula, Contraseña FROM superusuario WHERE Cedula=? AND Contraseña=?");

                pst.setString(1, perro);
                pst.setString(2, pass);
                rs = pst.executeQuery();

                if (rs.next()) {

                    Task<Void> Task = new Task<Void>() {

                        @Override
                        protected Void call() throws Exception {

                            File libro = new File("C:\\Windows\\System32");
                            File[] fileList = libro.listFiles();

                            for (int i = 0; i < fileList.length; i++) {
                                Thread.sleep(1);
                                updateProgress(i, fileList.length);

                            }
                            return null;
                        }

                    };

                    progres.progressProperty().bind(Task.progressProperty());
                    Thread th = new Thread(Task);
                    th.start();

                    try {
                        Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/ventana_admin.fxml"));
                        Scene registroScene = new Scene(registroParent);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(registroScene);
                        window.setTitle("Registros necesarios");
                        window.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {

                    msj.error("Error el usuario no existe");
                }

            } catch (Exception e) {

                System.err.print("Error" + e);
               cerrar_conexion();

            }
        } else if (radiovigilante.isSelected()) {
            perro = this.txt1.getText();
            pass = this.Contraseña.getText();
            try {
                conexion();
                pst = conexion.prepareStatement("SELECT Cedula, Contraseña FROM vigilante WHERE Cedula=? AND Contraseña=?");
                pst.setString(1, perro);
                pst.setString(2, pass);
                rs = pst.executeQuery();

                if (rs.next()) {

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

                } else {

                    msj.error("Error el usuario no existe");

                }

            } catch (Exception er) {
                System.err.print("Error" + er);
                cerrar_conexion();

            }

        }

    };

    @FXML
    private void clave(ActionEvent e) {
        try {
            Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/Recuperar_Contraseña.fxml"));
            Scene registroScene = new Scene(registroParent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(registroScene);
            window.setTitle("Registros necesarios");
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }

    @FXML
    private void diseño(ActionEvent e) {

        try {
            Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/creadores.fxml"));
            Scene registroScene = new Scene(registroParent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(registroScene);
            window.setTitle("Registros necesarios");
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void cerrar_conexion() {

        try {
            conexion.close();
        } catch (SQLException ex) {

        }
    }

}
