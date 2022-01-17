package Controladores;

import static Controladores.EditarController.pst;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mensajes.mensajitos;

public class Editar_PropietarrioController implements Initializable {

    @FXML
    private Label label6, label7, label8;

    @FXML
    private TextField txt9, txt10, txt11, txt12, txt13, txt14, txt15, txtmovil;

    @FXML
    private Button Atras5;

    @FXML
    private Button GuardarD3;

    @FXML
    private Button BuscarD3;

    @FXML
    private Button btn_I2;
    mensajitos msj = new mensajitos();

    @FXML
    private ImageView imagen;
    private FileInputStream fis;
    private File file;

    Image Image;
    FileChooser fileChooser = new FileChooser();

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;
    String nombre, apellido, cedula, correo, sexo, direccion, tlfno, NROCASA, cedular, medio, movil;

    String sentencia = "INSERT INTO  propietario(Cedula, Nombre, Apellido, NROCASA) VALUES(?,?,?,?)";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inicio();

        txt10.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

        txt11.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt12.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt14.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txtmovil.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

        Atras5.setOnAction(Atras_5);
        BuscarD3.setOnAction(buscador);
        GuardarD3.setOnAction(guardar);

        btn_I2.setOnAction(fotisho);
    }

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

    private final EventHandler<ActionEvent> buscador = (ActionEvent event) -> {

        try {
            conexion();
            pst = conexion.prepareStatement("SELECT Cedula, Nombre, Apellido, NROCASA FROM  propietario WHERE Cedula=?");
            pst.setString(1, this.txt9.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                cedula = rs.getString("Cedula");
                nombre = rs.getString("Nombre");
                apellido = rs.getString("Apellido");
                NROCASA = rs.getString("NROCASA");

            } else {
                msj.error("Error el usuario no existe");
            }

            this.txt10.setText(nombre);
            this.txt11.setText(apellido);
            this.txt12.setText(cedula);
            this.txt13.setText(NROCASA);

        } catch (Exception ex) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement("SELECT * FROM contactopropietario WHERE IDcedula=?");
            pst.setString(1, this.txt9.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                tlfno = rs.getString("Telefonos");
                movil = rs.getString("Movil");
            }

            this.txt14.setText(tlfno);
            this.txtmovil.setText(movil);
        } catch (Exception er) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement(" SELECT * FROM fotospropietario WHERE IDcedula=?");
            pst.setString(1, this.txt9.getText());
            rs = pst.executeQuery();

            while (rs.next()) {
                byte byteImage[] = null;

                // obtener la columna imagen, luego el arreglo de bytes 
                Blob blob = rs.getBlob("Fotos");
                byteImage = blob.getBytes(1, (int) blob.length());

                // crear el Image y mostrarlo en el ImageView
                Image img = new Image(new ByteArrayInputStream(byteImage));

                imagen.setImage(img);

            }

        } catch (Exception e) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement("SELECT * FROM correopropietario WHERE IDcedula=?");
            pst.setString(1, this.txt9.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                correo = rs.getString("Correos");

            }

            this.txt15.setText(correo);

        } catch (Exception er) {

        }
        cerrar_conexion();

    };

    private final EventHandler<ActionEvent> guardar = (ActionEvent event) -> {

        cedula = this.txt9.getText();

        if (txt10.getText().equalsIgnoreCase("") || txt11.getText().equalsIgnoreCase("") || txt12.getText().equalsIgnoreCase("") || txtmovil.getText().equalsIgnoreCase("")) {
            msj.error("Error no dejes espacios vacios");

        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            
            if(cedula()){
                
            
            try {
                conexion();
                pst = conexion.prepareStatement(" UPDATE propietario SET Nombre=?, Apellido=?, NROCASA=? WHERE Cedula='" + cedula + "'");
                pst.setString(1, this.txt10.getText());
                pst.setString(2, this.txt11.getText());
                pst.setString(3, this.txt13.getText());

                pst.executeUpdate();

            } catch (Exception ex) {

            }
            }else{
                
                cerrar_conexion();
            }

            try {
                conexion();
                pst = conexion.prepareStatement(" UPDATE contactopropietario SET Telefonos=?, Movil=? WHERE IDcedula='" + cedula + "'");
                pst.setString(1, this.txt14.getText());
                pst.setString(2, this.txtmovil.getText());
                pst.executeUpdate();

            } catch (Exception er) {

            }

            try {
                conexion();
                pst = conexion.prepareStatement(" INSERT INTO  fotospropietario (IDcedula,Fotos) VALUES(?,?)");
                pst.setString(1, cedula);
                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Registro_VigilanteController.class.getName()).log(Level.SEVERE, null, ex);
                }

                pst.setBinaryStream(2, fis);

                pst.executeUpdate();
                msj.information("Modificacion Exitosa");

            } catch (Exception er) {

            }

        }
        cerrar_conexion();

        limpiar();

    };

    @FXML
    private void eliminado(ActionEvent ex) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            cedula = this.txt9.getText();
            try {

                conexion();
                pst = conexion.prepareStatement("DELETE FROM contactopropietario WHERE IDcedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();

            } catch (Exception e) {

            }

            try {
                conexion();
                pst = conexion.prepareStatement("DELETE FROM correopropietario WHERE IDcedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();
            } catch (Exception er) {

            }

            try {
                conexion();
                pst = conexion.prepareStatement("DELETE FROM correopropietario WHERE IDcedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();
            } catch (Exception er) {

            }

            try {
                conexion();
                pst = conexion.prepareStatement("DELETE FROM fotospropietario WHERE IDcedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();
            } catch (Exception er) {

            }

            try {

                conexion();
                pst = conexion.prepareStatement("DELETE FROM propietario WHERE Cedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();
                msj.information(" Se  ha eliminado");

            } catch (Exception er) {

            }
        } else {

        }
        cerrar_conexion();

        limpiar();

    }

    private final EventHandler<ActionEvent> Atras_5 = (ActionEvent event) -> {
        try {
            Parent registroParent = FXMLLoader.load(getClass().getResource("/Vistas/ventana_principal.fxml"));
            Scene registroScene = new Scene(registroParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(registroScene);
            window.setTitle("Registros necesarios");
            window.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    };

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }

    public void limpiar() {

        this.txt9.setText("");
        this.txt10.setText("");
        this.txt11.setText("");

        this.txt12.setText("");
        this.txt13.setText("");
        this.txt14.setText("");
        this.txt15.setText("");
        this.txtmovil.setText("");
        this.imagen.setImage(null);

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

        }else{
                    return true;

        }

    }
    
     
    public boolean cedula (){
        if (cedula.length() < 6 || cedula.length() > 8) {
            msj.error("La cedula debe tener 6 minimo o maximo 8  caracteres");
            
            return false;
        }
        return true;
    }
}
