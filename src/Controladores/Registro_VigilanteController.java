package Controladores;

import static Controladores.FXMLDocumentController.conexion;
import static Controladores.FXMLDocumentController.pst;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

public class Registro_VigilanteController implements Initializable {
    
    @FXML
    private Label label20, label21;
    
    @FXML
    private TextField txt28, txt29, txt30, txt31, txt32, txt33, txt34, txt35;
    
    @FXML
    private Button Atras1;
    
    @FXML
    private Button Rg1;
    
    @FXML
    private Button bnt_I3;
    
    @FXML
    private DatePicker fecha1, fecha2;
    
    @FXML
    private ImageView imagen;
    
    private FileInputStream fis;
    
    Image Image;
    
    private File file;
    @FXML
    private RadioButton radiohombre, radiomujer;
    
    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;
    String nombre, apellido, cedula, correo, pass, direccion, tlfno, RIF;
    FileChooser fileChooser = new FileChooser();
    mensajitos msj = new mensajitos();
    
    Tooltip tooltip = new Tooltip();
    Tooltip tooltip2 = new Tooltip();
    Tooltip tooltip3 = new Tooltip();
    Tooltip tooltip4 = new Tooltip();
    Tooltip tooltip5 = new Tooltip();
    Tooltip tooltip6 = new Tooltip();
    Tooltip tooltip7 = new Tooltip();
    Tooltip tooltip8 = new Tooltip();
    String sexo = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inicio();
        
    }
    
    private void Inicio() {
        
        Rg1.setOnAction(registro);
        Atras1.setOnAction(Atras_1);
        bnt_I3.setOnAction(failer);
        tooltip.setText("\nNombre \n \n");
        this.txt28.setTooltip(tooltip);
        tooltip2.setText("\n Apellido \n \n");
        this.txt29.setTooltip(tooltip2);
        
        tooltip3.setText("\n Cedula \n  Ejemplo 26904279\n");
        this.txt30.setTooltip(tooltip3);
        
        tooltip4.setText("\n Telefono \n  Ejemplo XXXX-XXXXXX\n");
        this.txt31.setTooltip(tooltip4);
        
        tooltip5.setText("\n Contraseña \n Minimo 8 caracteres \n");
        this.txt33.setTooltip(tooltip5);
        
        tooltip6.setText("\n Correo \n XXXX@Gmail.com\n");
        this.txt35.setTooltip(tooltip6);
        
        tooltip6.setText("\n Direccion \n carrera X-calle X- urbanizacion X\n");
        this.txt34.setTooltip(tooltip6);
        txt28.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt29.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt30.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt31.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
    
    private final EventHandler<ActionEvent> Atras_1 = (ActionEvent event) -> {
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
    
    private final EventHandler<ActionEvent> registro = (ActionEvent event) -> {
        
        String SQL = "INSERT INTO  vigilante(Cedula,Contraseña, Nombre, Apellido, Sexo, Fecha, FechaRegistro) VALUES(?,?,?,?,?,?,?)";
        
        nombre = this.txt28.getText();
        apellido = this.txt29.getText();
        
        cedula = this.txt30.getText();
        tlfno = this.txt31.getText();
        
        pass = this.txt33.getText();
        correo = this.txt35.getText();
        direccion = this.txt34.getText();
        RIF = this.txt32.getText();
        if (radiohombre.isSelected()) {
            sexo = "Hombre";
        } else if (radiomujer.isSelected()) {
            sexo = "Mujer";
        }
        
        java.util.Date dt = new java.util.Date();
        
        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd ");
        String currentTime = sdf.format(dt);
        
        if (nombre.equalsIgnoreCase("") || apellido.equalsIgnoreCase("") || cedula.equalsIgnoreCase("") || tlfno.equalsIgnoreCase("") || correo.equalsIgnoreCase("") || pass.equalsIgnoreCase("") || direccion.equalsIgnoreCase("") || RIF.equalsIgnoreCase("")) {
            
            msj.error("Error en la aplicacion, No dejes espacios Vacios");
            
        }
        
        if (validar_campos(cedula, pass, nombre, apellido, sexo, correo, direccion, tlfno)) {
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, usuario, passs);
                pst = conexion.prepareStatement(SQL);
                pst.setString(1, cedula);
                pst.setString(2, pass);
                pst.setString(3, nombre);
                pst.setString(4, apellido);
                pst.setString(5, sexo);
                pst.setString(6, ((TextField) fecha1.getEditor()).getText());
                
                pst.setString(7, currentTime);

                // InputStream  loko= rs.getBinaryStream("Foto);
                pst.executeUpdate();
                
            } catch (Exception e) {
                
                System.err.print("ERROR " + e);
            }
            
            try {
                conexion();
                pst = conexion.prepareStatement("INSERT INTO  contactovigilante(IDCedula,Telefono) VALUES(?,?)");
                pst.setString(1, cedula);
                pst.setString(2, tlfno);
                pst.executeUpdate();
                
            } catch (Exception er) {
                
            }
            
            try {
                conexion();
                pst = conexion.prepareStatement("INSERT INTO  direccionvigilante(IDCedula,Direccion) VALUES(?,?)");
                pst.setString(1, cedula);
                pst.setString(2, direccion);
                pst.executeUpdate();
                
            } catch (Exception er) {
                
            }
            
            if (validar_correo()) {
                
                try {
                    conexion();
                    pst = conexion.prepareStatement("INSERT INTO  correovigilante(IDCedula1,Correos) VALUES(?,?)");
                    pst.setString(1, cedula);
                    pst.setString(2, correo);
                    pst.executeUpdate();
                    
                } catch (Exception er) {
                    
                }
            } else {
                cerrar_conexion();
            }
            
            try {
                conexion();
                pst = conexion.prepareStatement("INSERT INTO  rifvigilante(IDCedula,RIFvigilante) VALUES(?,?)");
                pst.setString(1, cedula);
                pst.setString(2, RIF);
                pst.executeUpdate();
                
            } catch (Exception er) {
                
            }
            
            try {
                conexion();
                pst = conexion.prepareStatement("INSERT INTO  fotosvigilante(IDCedula,Fotos) VALUES(?,?)");
                pst.setString(1, cedula);
                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Registro_VigilanteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                pst.setBinaryStream(2, fis);
                pst.executeUpdate();
                msj.information("Registro Exitoso ");
                cerrar_conexion();
                limpiar();
            } catch (Exception ex) {
                
            }
            
        } else {
            msj.warning("No dejes campos Vacios");
        }
        
    };
    
    public void limpiar() {
        this.txt28.setText("");
        this.txt29.setText("");
        this.txt30.setText("");
        this.txt31.setText("");
        this.txt32.setText("");
        this.txt33.setText("");
        this.txt34.setText("");
        this.txt35.setText("");
        
        this.fecha1.setValue(null);
        this.fecha2.setValue(null);
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
    
    public boolean rif() {
        int t = 0;
        int p = 0;
        
        for (int i = 0; i < RIF.length(); i++) {
            char c = RIF.charAt(i);
            
            if (c == 'j') {
                t++;
            }
            if (c == '-') {
                p++;
            }
        }
        
        if (t == 1 && p == 1) {
            return true;
        }
        
        return false;
    }
    
    public boolean validar_campos(String Cedula, String pass, String nombre, String apellido, String Sexo, String correo, String direccion, String tlfno) {
        
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
