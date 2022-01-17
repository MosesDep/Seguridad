package Controladores;

import java.awt.Toolkit;
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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mensajes.mensajitos;

public class Registro_PropietarioController implements Initializable {

    @FXML
    private Label label4, label5;

    @FXML
    private TextField txt2, txt3, txt4, txt5, txt6, txt7, txt8;

    @FXML
    private Button Atras4;

    @FXML
    private Button Rg2;

    @FXML
    private Button btn_I1;

    @FXML
    private DatePicker fechar;
    @FXML
    private RadioButton rb, rb2;

    @FXML
    private ImageView imagen;

    private FileInputStream fis;

    Image Image;

    private File file;
    mensajitos msj = new mensajitos();

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;
    String nombre, apellido, cedula, correo, sexo, direccion, tlfno, NROCASA, tlfnomovil;
    FileChooser fileChooser = new FileChooser();
    String sentencia = "INSERT INTO  propietario(Cedula, Nombre, Apellido, Fechanacimiento, NROCASA, Sexo) VALUES(?,?,?,?,?,?)";
    Tooltip tooltip = new Tooltip();
    Tooltip tooltip2 = new Tooltip();
    Tooltip tooltip3 = new Tooltip();
    Tooltip tooltip4 = new Tooltip();
    Tooltip tooltip5 = new Tooltip();
    Tooltip tooltip6 = new Tooltip();
    Tooltip tooltip7 = new Tooltip();
    Tooltip tooltip8 = new Tooltip();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inicio();
    }

    private void Inicio() {

        Atras4.setOnAction(Atras_4);
        btn_I1.setOnAction(failer);
        tooltip.setText("\nNombre \n \n");
        this.txt2.setTooltip(tooltip);
        tooltip2.setText("\n Apellido \n \n");
        this.txt3.setTooltip(tooltip2);

        tooltip3.setText("\n Cedula \n  Ejemplo 26904279\n");
        this.txt4.setTooltip(tooltip3);

        tooltip4.setText("\n Telefono \n  Ejemplo XXXX-XXXXXX\n");
        this.txt6.setTooltip(tooltip4);

        tooltip5.setText("\n Numero de Casa \n Ejemplo A-9 \n");
        this.txt5.setTooltip(tooltip5);

        tooltip6.setText("\n Telefono \n XXXX-XXXXXX\n");
        this.txt6.setTooltip(tooltip6);

        tooltip7.setText("\n Movil \n XXXX-XXXXXX\n");
        this.txt7.setTooltip(tooltip7);

        tooltip8.setText("\n Correo \n XXXX@Gmail.com\n");
        this.txt8.setTooltip(tooltip8);
        txt2.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt3.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt4.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt6.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt7.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt5.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

    private final EventHandler<ActionEvent> Atras_4 = (ActionEvent event) -> {
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

    @FXML

    private void register(ActionEvent e) {

        if (rb.isSelected()) {
            sexo = "Hombre";
        } else if (rb2.isSelected()) {
            sexo = "Mujer";
        }
        nombre = this.txt2.getText();
        apellido = this.txt3.getText();
        cedula = this.txt4.getText();
        NROCASA = this.txt5.getText();
        tlfno = this.txt6.getText();
        tlfnomovil = this.txt7.getText();
        correo = this.txt8.getText();

        if (nombre.equalsIgnoreCase("") || apellido.equalsIgnoreCase("") || cedula.equalsIgnoreCase("") || NROCASA.equalsIgnoreCase("")) {

            msj.error("Error en la aplicacion, No dejes espacios Vacios");

        }

        
            
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    conexion = DriverManager.getConnection(URL, usuario, passs);
                    pst = conexion.prepareStatement(sentencia);

                    pst.setString(1, cedula);
                    pst.setString(2, nombre);
                    pst.setString(3, apellido);
                    pst.setString(4, ((TextField) fechar.getEditor()).getText());
                    pst.setString(5, NROCASA);
                    pst.setString(6, sexo);
                    pst.executeUpdate();

                } catch (Exception er) {

                }
            

            try {
                conexion();
                pst = conexion.prepareStatement("INSERT INTO  contactopropietario(IDCedula,Telefonos,Movil) VALUES(?,?,?)");
                pst.setString(1, cedula);
                pst.setString(2, tlfno);
                pst.setString(3, tlfnomovil);

                pst.executeUpdate();

            } catch (Exception ex) {

            }

            
                try {

                    conexion();
                    pst = conexion.prepareStatement("INSERT INTO  correopropietario(IDCedula,Correos) VALUES(?,?)");
                    pst.setString(1, cedula);
                    pst.setString(2, correo);
                    pst.executeUpdate();

                } catch (Exception er) {

                }
            

            try {

                conexion();
                pst = conexion.prepareStatement("INSERT INTO  fotospropietario(IDCedula,Fotos) VALUES(?,?)");
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
            } catch (Exception er) {

            }

        

    }

    private final EventHandler<ActionEvent> failer = (ActionEvent event) -> {

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

    public void limpiar() {

        this.txt2.setText("");
        this.txt3.setText("");
        this.txt4.setText("");
        this.txt5.setText("");
        this.txt6.setText("");
        this.txt7.setText("");
        this.txt8.setText("");

        this.fechar.setValue(null);
        this.rb.setSelected(false);
        this.rb2.setSelected(false);
        this.imagen.setImage(null);

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

    public boolean validar_campos(String Cedula, String NROCASA, String nombre, String apellido, String Sexo, String correo, String direccion, String tlfno) {

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

      

        if (tlfno.equals("")) {
            return false;

        }

        if (NROCASA.equals("")) {
            return false;

        }

        return true;

    }
}
