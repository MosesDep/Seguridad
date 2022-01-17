package Controladores;

import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class Registro_AdministradorController implements Initializable {

    @FXML
    private Label label28, label29;

    @FXML
    private TextField txt46, txt47, txt48, txt49, txt50, txt51, txt52;

    @FXML
    private DatePicker Fecha3, Fecha4;

    @FXML
    private RadioButton Radio5, Radio6;

    @FXML
    private Button Rg4;

    @FXML
    private Button Atras10;

    @FXML
    private Button bnt_I5;

    @FXML
    private ImageView imagen;

    private FileInputStream fis;

    Image Image;

    mensajitos msj = new mensajitos();

    boolean banderita = false;
    boolean lokita = true;

    private File file;
    Tooltip tooltip = new Tooltip();
    Tooltip tooltip2 = new Tooltip();
    Tooltip tooltip3 = new Tooltip();
    Tooltip tooltip4 = new Tooltip();
    Tooltip tooltip5 = new Tooltip();
    Tooltip tooltip6 = new Tooltip();
    Tooltip tooltip7 = new Tooltip();

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;
    String nombre, apellido, cedula, correo, pass, direccion, tlfno, Sexo;
    FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup rg = new ToggleGroup();
        Radio5.setToggleGroup(rg);
        Radio6.setToggleGroup(rg);

        tooltip.setText("\nNombre \n \n");
        this.txt46.setTooltip(tooltip);
        tooltip2.setText("\n Apellido \n \n");
        this.txt47.setTooltip(tooltip2);

        tooltip3.setText("\n Cedula \n  Ejemplo 26904279\n");
        this.txt48.setTooltip(tooltip3);

        tooltip4.setText("\n Telefono \n  Ejemplo XXXX-XXXXXX\n");
        this.txt49.setTooltip(tooltip4);

        tooltip5.setText("\n Contraseña \n Minimo 8 caracteres \n");
        this.txt50.setTooltip(tooltip5);

        tooltip6.setText("\n Correo \n XXXX@Gmail.com\n");
        this.txt51.setTooltip(tooltip6);

        tooltip7.setText("\n Direccion \n carrera X-calle X- urbanizacion X\n");
        this.txt52.setTooltip(tooltip7);
        txt46.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt47.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt48.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt49.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

        Atras10.setOnAction(Atras_10);
        bnt_I5.setOnAction(fotisho);
        Rg4.setOnAction(register);

    }

    private final EventHandler<ActionEvent> Atras_10 = (ActionEvent event) -> {
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
    };

    private final EventHandler<ActionEvent> fotisho = (ActionEvent event) -> {

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            fileChooser.setTitle("Buscar Imagen");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            Image = new Image(file.toURI().toString());
            ImageView fotico = new ImageView(Image);

            imagen.setImage(Image);
            // fis = new FileInputStream(file);

        }

    };

    private final EventHandler<ActionEvent> register = (ActionEvent event) -> {
        nombre = this.txt46.getText();
        apellido = this.txt47.getText();

        cedula = this.txt48.getText();
        tlfno = this.txt49.getText();

        pass = this.txt50.getText();
        correo = this.txt51.getText();
        direccion = this.txt52.getText();

        if (Radio5.isSelected()) {

            Sexo = "Hombre";
        } else if (Radio6.isSelected()) {
            Sexo = "Mujer";
        }
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd ");
        String currentTime = sdf.format(dt);

        if(validar_campos(cedula,nombre,pass, apellido,Sexo,correo,direccion,tlfno)){

        try {

            conexion();
            pst = conexion.prepareStatement("INSERT INTO  superusuario(Cedula,Contraseña, Nombre, Apellido, Estatus, Fecha, FechaRegistro, Sexo) VALUES(?,?,?,?,?,?,?,?)");
            pst.setString(1, cedula);
            pst.setString(2, pass);
            pst.setString(3, nombre);
            pst.setString(4, apellido);
            pst.setString(5, "Admin");
            pst.setString(6, ((TextField) Fecha3.getEditor()).getText());
            pst.setString(7, currentTime);
            pst.setString(8, Sexo);

            pst.executeUpdate();

        } catch (Exception e) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement("INSERT INTO  contacto(IDcedula,Telefonos) VALUES(?,?)");
            pst.setString(1, cedula);
            pst.setString(2, tlfno);
            pst.executeUpdate();
        } catch (Exception er) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement("INSERT INTO  correo(IDcedula,Correos) VALUES(?,?)");
            pst.setString(1, cedula);
            pst.setString(2, correo);

            pst.executeUpdate();

        } catch (Exception e) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement("INSERT INTO  direccion(IDcedula,Direccion) VALUES(?,?)");
            pst.setString(1, cedula);
            pst.setString(2, direccion);

            pst.executeUpdate();

        } catch (Exception ert) {

        }

        try {
            conexion();
            pst = conexion.prepareStatement("INSERT INTO  fotos(IDcedula,Fotos) VALUES(?,?)");
            pst.setString(1, cedula);

            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Registro_AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            pst.setBinaryStream(2, fis);
            pst.executeUpdate();
            msj.information("Registro Exitoso ");
            cerrar_conexion();
            limpiar();
        } catch (Exception ex) {

        }
        
        }else {
            msj.error("No dejes campos en blanco");
        }

    };

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

    public void limpiar() {

        this.txt46.setText("");
        this.txt47.setText("");
        this.txt48.setText("");

        this.txt49.setText("");
        this.txt50.setText("");
        this.txt51.setText("");
        this.txt52.setText("");

        this.Fecha3.setValue(null);
        this.Fecha4.setValue(null);
        this.Radio5.setSelected(false);
        this.Radio6.setSelected(false);
        this.imagen.setImage(null);

    }

    public boolean validar_correo() {
        int bandera = 0, daps = 0;
        boolean band = true;

        for (int i = 0; i < correo.length(); i++) {
            char c = correo.charAt(i);
            if (c == '@') {
                bandera++;
            }
            if (c == '.') {
                daps++;
            }
        }

        if (bandera == 1 && daps >= 1) {
            band = true;
        } else {
            band = false;
        }

        if (band == false) {
            return false;

        } else {
            return true;

        }

    }

    public boolean cedula() {
        if (cedula.length() < 6 || cedula.length() > 8) {
            msj.error("La cedula debe tener 6 minimo o maximo 8  caracteres");

            return false;
        }
        return true;
    }

    public boolean validar_campos(String Cedula,String pass,  String nombre, String apellido, String Sexo, String correo, String direccion, String tlfno) {

        if (cedula.equalsIgnoreCase("")) {
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

        if (correo.equals("")) {
            return false;

        }

        if (direccion.equals("")) {
            return false;

        }

        if (tlfno.equals("")) {
            return false;

        }
        
        
         return true;

    }
}
