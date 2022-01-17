/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class EditarController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    private Button btn_I4;
    @FXML
    private Button eliminar;

    @FXML
    private RadioButton rbs, rbb;

   

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;
    String nombre, apellido, cedula, correo, pass, direccion, tlfno, celab, nombresito, ape, sexo, RIF;
    FileChooser fileChooser = new FileChooser();
    String consulta = " SELECT * FROM vigilante WHERE Cedula=?";
    mensajitos msj = new mensajitos();

    @FXML
    private ImageView imagen;

    private FileInputStream fis;

    Image Image;

    private File file;

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
        // TODO
        this.GuardarD.setDisable(true);
        this.eliminar.setDisable(true);

        ToggleGroup rg = new ToggleGroup();
        rbs.setToggleGroup(rg);
        rbb.setToggleGroup(rg);
        Inicio();
        tooltip.setText("\nNombre \n \n");
        this.txt36.setTooltip(tooltip);
        tooltip2.setText("\n Apellido \n \n");
        this.txt37.setTooltip(tooltip2);

        tooltip3.setText("\n Cedula \n  Ejemplo 26904279\n");
        this.txt38.setTooltip(tooltip3);

        tooltip4.setText("\n Telefono \n  Ejemplo XXXX-XXXXXX\n");
        this.txt39.setTooltip(tooltip4);

        tooltip5.setText("\n Contraseña \n Minimo 8 caracteres \n");
        this.txt41.setTooltip(tooltip5);

        tooltip6.setText("\n Correo \n XXXX@Gmail.com\n");
        this.txt43.setTooltip(tooltip6);

        tooltip6.setText("\n Direccion \n carrera X-calle X- urbanizacion X\n");
        this.txt42.setTooltip(tooltip6);

        txt36.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

        txt37.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

        txt38.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

        txt39.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

        Atras2.setOnAction(Atras_2);
        BuscarD.setOnAction(buscar);
        btn_I4.setOnAction(failer);
        GuardarD.setOnAction(save);

    }

    private final EventHandler<ActionEvent> Atras_2 = (ActionEvent event) -> {
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

    private final EventHandler<ActionEvent> buscar = (ActionEvent event) -> {
        this.celab = this.txt44.getText();
        this.GuardarD.setDisable(false);
        this.eliminar.setDisable(false);
        

        try {
            conexion();
            pst = conexion.prepareStatement(" SELECT * FROM vigilante WHERE Cedula=?");
            pst.setString(1, celab);
            rs = pst.executeQuery();

            if (rs.next()) {
                cedula = rs.getString("Cedula");
                nombresito = rs.getString("Nombre");
                ape = rs.getString("Apellido");
                sexo = rs.getString("Sexo");
                pass = rs.getString("Contraseña");

            } else {
                msj.error("Error el usuario no existe");
            }

        } catch (Exception e) {
            System.err.print("ERROR  " + e);

        }

        try {

            conexion();
            pst = conexion.prepareStatement(" SELECT * FROM contactovigilante WHERE IDcedula=?");
            pst.setString(1, celab);
            rs = pst.executeQuery();

            if (rs.next()) {
                tlfno = rs.getString("Telefono");

            }

        } catch (Exception e) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement(" SELECT * FROM direccionvigilante WHERE IDcedula=?");
            pst.setString(1, celab);
            rs = pst.executeQuery();

            if (rs.next()) {
                direccion = rs.getString("Direccion");

            }

        } catch (Exception e) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement(" SELECT * FROM correovigilante WHERE IDcedula1=?");
            pst.setString(1, celab);
            rs = pst.executeQuery();

            if (rs.next()) {
                correo = rs.getString("Correos");

            }

        } catch (Exception e) {

        }
        try {
            conexion();
            pst = conexion.prepareStatement("SELECT *  FROM  rifvigilante  WHERE IDcedula=?");
            pst.setString(1, celab);
            rs = pst.executeQuery();

            if (rs.next()) {
                RIF = rs.getString("RIFvigilante");

            }
        } catch (Exception er) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement(" SELECT * FROM fotosvigilante WHERE IDcedula=?");
            pst.setString(1, celab);
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
        cerrar_conexion();

        if (sexo.equalsIgnoreCase("Hombre")) {
            rbs.setSelected(true);
        } else if (sexo.equalsIgnoreCase("Mujer")) {
            rbb.setSelected(true);

        }
        this.txt36.setText(nombresito);
        this.txt37.setText(ape);
        this.txt38.setText(cedula);
        this.txt39.setText(tlfno);
        this.txt40.setText(RIF);
        this.txt41.setText(pass);
        this.txt42.setText(direccion);
        this.txt43.setText(correo);

    };

    private final EventHandler<ActionEvent> save = (ActionEvent event) -> {

        nombre = this.txt36.getText().trim();
        ape = this.txt37.getText();

        tlfno = this.txt39.getText();

        RIF = this.txt40.getText();
        pass = this.txt41.getText();
        direccion = this.txt42.getText();

        correo = this.txt43.getText();

        if (rbs.isSelected()) {
            sexo = "Hombre";
        } else if (rbb.isSelected()) {
            sexo = "Mujer";
        }

        if (nombre.equalsIgnoreCase("") || ape.equalsIgnoreCase("") || tlfno.equalsIgnoreCase("") || correo.equalsIgnoreCase("") || pass.equalsIgnoreCase("") || direccion.equalsIgnoreCase("") || RIF.equalsIgnoreCase("")) {

            msj.error("Error no dejes espacios en blanco");
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            
            
          if(validar_campos(nombre,pass, apellido,sexo,correo, direccion,tlfno)){
                
            
            try {

                conexion();
                pst = conexion.prepareStatement(" UPDATE vigilante SET Contraseña=?, Nombre=?, Apellido=?, Sexo=? WHERE Cedula='" + this.txt44.getText() + "'");
                pst.setString(1, pass);
                pst.setString(2, nombre);
                pst.setString(3, ape);
                pst.setString(4, sexo);

                pst.executeUpdate();

            } catch (Exception e) {

            }
            

            try {

                conexion();
                pst = conexion.prepareStatement(" UPDATE rifvigilante SET RIFvigilante=? WHERE IDcedula='" + this.txt44.getText() + "'");
                pst.setString(1, RIF);

                pst.executeUpdate();

            } catch (Exception e) {

            }

            try {

                conexion();
                pst = conexion.prepareStatement(" UPDATE direccionvigilante SET Direccion=? WHERE IDcedula='" + this.txt44.getText() + "'");
                pst.setString(1, direccion);
                pst.executeUpdate();

            } catch (Exception e) {

            }

            if(validar_correo()){
            try {

                conexion();
                pst = conexion.prepareStatement(" UPDATE correovigilante SET Correos=? WHERE IDcedula='" + this.txt44.getText() + "'");
                pst.setString(1, correo);
                pst.executeUpdate();

            } catch (Exception e) {

            }
            }else{
            cerrar_conexion();
        }

            try {

                conexion();
                pst = conexion.prepareStatement(" UPDATE contactovigilante SET Telefono=? WHERE IDcedula='" + this.txt44.getText() + "'");
                pst.setString(1, tlfno);
                pst.executeUpdate();
            } catch (Exception e) {

            }
        }

        try {
            conexion();
            pst = conexion.prepareStatement(" INSERT INTO  fotosvigilante (IDcedula,Fotos) VALUES(?,?)");
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
        cerrar_conexion();
        limpiar();
        
        }else{
            msj.warning("No dejes espacios en blanco");
        }

    };

    @FXML
    private void eliminado(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

        }

        this.cedula = this.txt44.getText();

        try {
            conexion();
            pst = conexion.prepareStatement("DELETE FROM vigilante WHERE Cedula=?");
            pst.setString(1, cedula);
            pst.executeUpdate();
            msj.confirmation("Borrado Exitoso");
        } catch (Exception er) {

        }
        cerrar_conexion();
        limpiar();
        

    }

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }

    public void limpiar() {

        this.txt36.setText("");
        this.txt37.setText("");
        this.txt38.setText("");

        this.txt39.setText("");
        this.txt40.setText("");
        this.txt41.setText("");
        this.txt42.setText("");
        this.txt43.setText("");
        this.txt44.setText("");

        this.rbb.setSelected(false);
        this.rbs.setSelected(false);
        this.imagen.setImage(null);

    }
    public void cerrar_conexion(){
        
          try{
        conexion.close();
    }catch(SQLException ex){

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
    
    public boolean validar_campos(String pass,  String nombre, String apellido, String Sexo, String correo, String direccion, String tlfno) {

       
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
