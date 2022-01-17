package Controladores;

import java.awt.Toolkit;
import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mensajes.mensajitos;

public class Registar_VisitanteController implements Initializable {

    @FXML
    private Label label11;

    @FXML
    private TextField txt17, txt18, txt19, txt20, txtregistro, txtnumero;

    @FXML
    private DatePicker Fecha1;

    @FXML
    private RadioButton RB, RB2, rbpie, rbauto, rbmoto, rbpesado;

    @FXML
    private Button Atras7;

    @FXML
    private Button Rg3;
    @FXML
    private DatePicker fechavisita;
    @FXML
    private ImageView imagen;

    private FileInputStream fis;

    Image Image;

    private File file;

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;
    String nombre, apellido, cedula, correo, sexo, direccion, tlfno, NROCASA, cedular, medio;
    FileChooser fileChooser = new FileChooser();
    String sentencia = "INSERT INTO  propietario(Cedula, Nombre, Apellido, NROCASA) VALUES(?,?,?,?)";

    Tooltip tooltip = new Tooltip();
    Tooltip tooltip2 = new Tooltip();
    Tooltip tooltip3 = new Tooltip();
    Tooltip tooltip4 = new Tooltip();
    Tooltip tooltip5 = new Tooltip();
    Tooltip tooltip6 = new Tooltip();
    Tooltip tooltip7 = new Tooltip();
    Tooltip tooltip8 = new Tooltip();
    mensajitos msj = new mensajitos();

    @Override
    public void initialize(URL url, ResourceBundle rbb) {
        ToggleGroup rg = new ToggleGroup();
        RB.setToggleGroup(rg);
        RB2.setToggleGroup(rg);

        ToggleGroup rg2 = new ToggleGroup();
        rbpie.setToggleGroup(rg2);
        rbauto.setToggleGroup(rg2);
        rbmoto.setToggleGroup(rg2);
        rbpesado.setToggleGroup(rg2);
        tooltip.setText("\nNombre \n \n");
        this.txt17.setTooltip(tooltip);
        tooltip2.setText("\n Apellido \n \n");
        this.txt18.setTooltip(tooltip2);
        tooltip5.setText("\n Numero de Casa \n Ejemplo A-9 \n");
        this.txtnumero.setTooltip(tooltip5);

        tooltip3.setText("\n Cedula \n  Ejemplo 26904279\n");
        this.txt19.setTooltip(tooltip3);

        tooltip4.setText("\n Telefono \n  Ejemplo XXXX-XXXXXX\n");
        this.txt20.setTooltip(tooltip4);

        tooltip5.setText("\n Cedula del registrante \n ejemplo 20604278 \n");
        this.txtregistro.setTooltip(tooltip5);

        txt17.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                char c = event.getCharacter().charAt(0);
                if (!Character.isLetter(c)) {
                    Toolkit.getDefaultToolkit().beep();
                    msj.warning("Coloca solo Letras");
                    event.consume();
                } else {

                }

            }
        });

        txt18.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                char c = event.getCharacter().charAt(0);
                if (!Character.isLetter(c)) {
                    Toolkit.getDefaultToolkit().beep();
                    msj.warning("Coloca solo Letras");
                    event.consume();
                } else {

                }

            }
        });
        txt19.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

        txt20.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txtnumero.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

        Inicio();
    }

    private void Inicio() {

        Atras7.setOnAction(Atras_7);
        Rg3.setOnAction(register);

    }

    private final EventHandler<ActionEvent> Atras_7 = (ActionEvent event) -> {
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

    private final EventHandler<ActionEvent> register = (ActionEvent event) -> {

        nombre = this.txt17.getText();

        apellido = this.txt18.getText();
        cedula = this.txt19.getText();
        tlfno = this.txt20.getText();
        NROCASA = this.txtnumero.getText();
        String numero= this.txtregistro.getText();
 
        if (RB.isSelected()) {
            sexo = "Mujer";

        } else if (RB2.isSelected()) {

            sexo = "Hombre";
        }

        if (rbpie.isSelected()) {

            medio = "pie";
        } else if (rbauto.isSelected()) {
            medio = "Automovil";
        } else if (rbmoto.isSelected()) {
            medio = "Moto";

        } else if (rbpesado.isSelected()) {
            medio = "Vehiculo Pesado";
        }
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd ");
        String currentTime = sdf.format(dt);


     
            try {

                conexion();
                pst = conexion.prepareStatement("INSERT INTO visitas(Cedula, Nombre, Apellido, Sexo, CedulaRE, FechaRegistro) VALUES(?,?,?,?,?,?)");
                pst.setString(1, cedula);
                pst.setString(2, this.txt17.getText());
                pst.setString(3, this.txt18.getText());
                pst.setString(4, sexo);
                pst.setString(5, numero);
                pst.setString(6, currentTime);

                pst.executeUpdate();
            } catch (Exception er) {

            }
       

        try {
            conexion();
            pst = conexion.prepareStatement("INSERT INTO mediotransporte(IDcedula, Medio) VALUES(?,?)");
            pst.setString(1, cedula);
            pst.setString(2, medio);
            pst.executeUpdate();

        } catch (Exception ex) {

        }

        try { 
            conexion();
            pst = conexion.prepareStatement("INSERT INTO casavisitada(IDcedula, NROCASA) VALUES(?,?)");
            pst.setString(1, cedula);
            pst.setString(2, NROCASA);
            pst.executeUpdate();

        } catch (Exception er) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement("INSERT INTO contactovisita(IDcedula, Telefonos,Movil) VALUES(?,?,?)");
            pst.setString(1, cedula);
            pst.setString(2, tlfno);
            pst.setString(3, tlfno);
           
            pst.executeUpdate();
             msj.information("Registro Exitoso ");

            cerrar_conexion();
            limpiar();

        } catch (Exception ex) {

        }
       

      
        

    };

    public void limpiar() {
        this.txt17.setText("");
        this.txt18.setText("");
        this.txt19.setText("");
        this.txt20.setText("");
        this.txtregistro.setText("");
        this.txtnumero.setText("");

        this.Fecha1.setValue(null);
        this.RB.setSelected(false);
        this.RB2.setSelected(false);

        this.rbauto.setSelected(false);
        this.rbmoto.setSelected(false);
        this.rbpie.setSelected(false);
        this.rbpesado.setSelected(false);

    }

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }

    public void cerrar_conexion() {

        try {
            conexion.close();
        } catch (SQLException ex) {

        }
    }

    public boolean cedula() {
        if (cedula.length() < 6 || cedula.length() > 8) {
            msj.error("La cedula debe tener 6 minimo o maximo 8  caracteres");

            return false;
        }
        return true;
    }
    
    public boolean validar_campos(String Cedula, String NROCASA, String nombre, String apellido, String Sexo, String tlfno, String CE) {

        if (Cedula.equalsIgnoreCase("")) {
            return false;
        }
        if (nombre.equals("")) {
            return false;

        }
        if (apellido.equals("")) {
            return false;

        }

        if (Sexo.equals("")) {
            return false;

        }

       

        

        if (tlfno.equals("")) {
            return false;

        }

        if (NROCASA.equals("")) {
            return false;

        }

        if (CE.equals("")) {
            return false;

        }
        return true;

    }

}
