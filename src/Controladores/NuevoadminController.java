/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class NuevoadminController implements Initializable {

    @FXML
    private Label label30, label31, label32, label33;

    @FXML
    private TextField txt53, txt54, txt55, txt56, txt57, txt58, txt59, txt60;

    @FXML
    private RadioButton Radio7, Radio8;

    @FXML
    private Button Atras11;

    @FXML
    private Button GuardarD6;

    @FXML
    private Button BuscarD6;

    @FXML
    private Button botonfoto;

    @FXML
    private Button eliminar;

    @FXML
    private ImageView imagen;

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;
    String nombre, apellido, cedula, correo, pass, direccion, tlfno, celab, nombresito, ape, sexo;
    FileChooser fileChooser = new FileChooser();
    private FileInputStream fis;
    private File file;
     

    Image Image;
    Tooltip tooltip = new Tooltip();
    Tooltip tooltip2 = new Tooltip();
    Tooltip tooltip3 = new Tooltip();
    Tooltip tooltip4 = new Tooltip();
    Tooltip tooltip5 = new Tooltip();
    Tooltip tooltip6 = new Tooltip();
    Tooltip tooltip7 = new Tooltip();
    mensajitos msj = new mensajitos();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

       
       

        ToggleGroup rg = new ToggleGroup();
        Radio7.setToggleGroup(rg);
        Radio8.setToggleGroup(rg);
        Inicio();

        tooltip.setText("\nNombre \n \n");
        this.txt54.setTooltip(tooltip);
        tooltip2.setText("\n Apellido \n \n");
        this.txt55.setTooltip(tooltip2);

        tooltip4.setText("\n Telefono \n  Ejemplo XXXX-XXXXXX\n");
        this.txt57.setTooltip(tooltip4);

        tooltip5.setText("\n Contraseña \n Minimo 8 caracteres \n");
        this.txt60.setTooltip(tooltip5);

        tooltip6.setText("\n Correo \n XXXX@Gmail.com\n");
        this.txt58.setTooltip(tooltip6);

        tooltip7.setText("\n Direccion \n carrera X-calle X- urbanizacion X\n");
        this.txt59.setTooltip(tooltip7);
         txt54.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
          txt55.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
            txt56.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
              txt57.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

        Atras11.setOnAction(Atras_11);
        botonfoto.setOnAction(fotisho);

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

    private final EventHandler<ActionEvent> Atras_11 = (ActionEvent event) -> {
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

    @FXML
    private void buscar(ActionEvent e) {
         
        cedula = this.txt53.getText();

        try {
            conexion();
            pst = conexion.prepareStatement(" SELECT  *  FROM superusuario WHERE Cedula=?");
            pst.setString(1, cedula);

            rs = pst.executeQuery();

            if (rs.next()) {
                nombre = rs.getString("Nombre");
                ape = rs.getString("Apellido");
                sexo = rs.getString("Sexo");
                pass = rs.getString("Contraseña");

            } else {
                msj.error("El usuario no Existe");
            }

            if(sexo.equalsIgnoreCase("Hombre")){
                
                Radio7.setSelected(true);
            }else if(sexo.equalsIgnoreCase("Mujer")){
                Radio8.setSelected(true);
            }
            this.txt54.setText(nombre);
            this.txt55.setText(ape);
            this.txt60.setText(pass);

        } catch (Exception er) {

        }

        try {
            conexion();
            pst = conexion.prepareStatement(" SELECT  *  FROM direccion WHERE IDcedula=?");
            pst.setString(1, cedula);

            rs = pst.executeQuery();

            if (rs.next()) {
                direccion = rs.getString("Direccion");

            }

        } catch (Exception er) {

        }

        try {
            conexion();
            pst = conexion.prepareStatement(" SELECT  *  FROM correo WHERE IDcedula=?");
            pst.setString(1, cedula);

            rs = pst.executeQuery();

            if (rs.next()) {
                correo = rs.getString("Correos");

            }

        } catch (Exception er) {

        }
        
          try {

            conexion();
            pst = conexion.prepareStatement(" SELECT * FROM fotos WHERE IDcedula=?");
            pst.setString(1, cedula);
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
            

        } catch (Exception er) {

        }


        try {
            conexion();
            pst = conexion.prepareStatement(" SELECT  *  FROM contacto WHERE IDcedula=?");
            pst.setString(1, cedula);

            rs = pst.executeQuery();

            if (rs.next()) {
                tlfno = rs.getString("Telefonos");

            }

        } catch (Exception er) {

        }

        this.txt54.setText(nombre);
        this.txt55.setText(ape);
        this.txt56.setText(cedula);

        this.txt57.setText(tlfno);
        this.txt58.setText(correo);
        this.txt59.setText(direccion);
        this.txt60.setText(pass);

    }

    @FXML
    private void guardar(ActionEvent er) {
        cedula = this.txt53.getText();
        if (Radio7.isSelected()) {
            sexo = "Hombre";
        } else if (Radio8.isSelected()) {
            sexo = "Mujer";
        }

        if (this.txt54.getText().equalsIgnoreCase("") || this.txt55.getText().equalsIgnoreCase("") || this.txt57.getText().equalsIgnoreCase("") || this.txt58.getText().equalsIgnoreCase("") || this.txt59.getText().equalsIgnoreCase("")) {
           msj.error("No dejes espacios en blanco");
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            try {
                conexion();
                pst = conexion.prepareStatement("UPDATE superusuario SET Nombre=?, Apellido=?, Sexo=? WHERE Cedula='" + cedula + "'");
                pst.setString(1, this.txt54.getText());
                pst.setString(2, this.txt55.getText());
                pst.setString(3, sexo);
                pst.executeUpdate();

            } catch (Exception e) {

            }

            try {
                conexion();
                pst = conexion.prepareStatement("UPDATE direccion SET Direccion=? WHERE IDcedula='" + cedula + "'");
                pst.setString(1, this.txt59.getText());

                pst.executeUpdate();

            } catch (Exception e) {

            }
            if( validar_correo()){
            try {
                conexion();
                pst = conexion.prepareStatement("UPDATE correo SET Correos=? WHERE IDcedula='" + cedula + "'");
                pst.setString(1, this.txt58.getText());

                pst.executeUpdate();

            } catch (Exception e) {

            }
            }else{
                cerrar_conexion();
            }
            try {
                conexion();
                pst = conexion.prepareStatement("UPDATE contacto SET Telefonos=? WHERE IDcedula='" + cedula + "'");
                pst.setString(1, this.txt57.getText());

                pst.executeUpdate();

            
                 
            } catch (Exception e) {

            }
            
            
           try {
            conexion();
            pst = conexion.prepareStatement(" INSERT INTO  fotos(IDcedula,Fotos) VALUES(?,?)");
            pst.setString(1, cedula);
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Registro_VigilanteController.class.getName()).log(Level.SEVERE, null, ex);
            }

            pst.setBinaryStream(2, fis);

            pst.executeUpdate();
            msj.information("Modificacion Exitosa");

        } catch (Exception e) {

        }
            
            
        }

    }

    @FXML
    private void eliminado(ActionEvent e) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            try {

                conexion();
                pst = conexion.prepareStatement("DELETE FROM  contacto  WHERE IDcedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();

            } catch (Exception er) {

            }
            try {

                conexion();
                pst = conexion.prepareStatement("DELETE FROM  correo  WHERE IDcedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();

            } catch (Exception er) {

            }
            try {

                conexion();
                pst = conexion.prepareStatement("DELETE FROM  fotos  WHERE IDcedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();

            } catch (Exception er) {

            }
            try {

                conexion();
                pst = conexion.prepareStatement("DELETE FROM  direccion  WHERE IDcedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();

            } catch (Exception er) {

            }

            try {

                conexion();
                pst = conexion.prepareStatement("DELETE FROM  superusuario  WHERE Cedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();
                msj.error("Se ha eliminado");
            } catch (Exception er) {

            }
        }

    }

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }
    
    
    public void claer (){
        this.txt53.setText("");

        this.txt54.setText("");
        this.txt55.setText("");
        this.txt56.setText("");
        this.txt57.setText("");
        this.txt58.setText("");
        this.txt59.setText("");
        this.txt60.setText("");
 
        this.Radio7.setSelected ( false);
        this.Radio8.setSelected ( false);
       
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
    
    
    public void cerrar_conexion() {

        try {
            conexion.close();
        } catch (SQLException ex) {

        }
    }
    
    
    
    
    
}
