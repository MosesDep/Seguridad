package Controladores;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mensajes.mensajitos;

public class Editar_VisitasController implements Initializable {

    @FXML
    private Label label12, label13, label14;

    @FXML
    private TextField txt21, txt22, txt23, txt24, txt25, txt15, txtregistro, txtnumero;

    @FXML
    private DatePicker Fecha2;

    @FXML
    private RadioButton RB, RB2, rbpie, rbauto, rbmoto, rbpesado;

    @FXML
    private Button Atras8;

    @FXML
    private Button GuardarD5;

    @FXML
    private Button BuscarD5;

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;
    String nombre, apellido, cedula, correo, sexo, direccion, tlfno, NROCASA, cedular, medio, movil;
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
    public void initialize(URL url, ResourceBundle rb) {

        ToggleGroup rg = new ToggleGroup();
        RB.setToggleGroup(rg);
        RB2.setToggleGroup(rg);

        ToggleGroup rg2 = new ToggleGroup();
        rbpie.setToggleGroup(rg2);
        rbauto.setToggleGroup(rg2);
        rbmoto.setToggleGroup(rg2);
        rbpesado.setToggleGroup(rg2);

        tooltip.setText("\nNombre \n \n");
        this.txt22.setTooltip(tooltip);
        tooltip2.setText("\n Apellido \n \n");
        this.txt23.setTooltip(tooltip2);
        tooltip3.setText("\n Numero de Casa \n Ejemplo A-9 \n");
        this.txtnumero.setTooltip(tooltip3);
        tooltip4.setText("\n Telefono \n  Ejemplo XXXX-XXXXXX\n");
        this.txt25.setTooltip(tooltip4);

        tooltip5.setText("\n Cedula del registrante \n ejemplo 20604278 \n");
        this.txtregistro.setTooltip(tooltip5);
        txt22.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt23.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txt24.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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
        txtregistro.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

        Atras8.setOnAction(Atras_8);
        BuscarD5.setOnAction(buscar);
        GuardarD5.setOnAction(guardar);

    }

    private final EventHandler<ActionEvent> buscar = (ActionEvent event) -> {
        cedula = this.txt21.getText();

        try {
            conexion();
            pst = conexion.prepareStatement(" SELECT * FROM visitas  WHERE Cedula=?");
            pst.setString(1, cedula);

            rs = pst.executeQuery();

            if (rs.next()) {

                nombre = rs.getString("Nombre");
                apellido = rs.getString("Apellido");
                sexo = rs.getString("Sexo");
                cedular = rs.getString("CedulaRE");

            } else {
                msj.error("Error el usuario no existe");

            }

            this.txt22.setText(nombre);
            this.txt23.setText(apellido);
            this.txt24.setText(cedula);
            this.txtregistro.setText(cedular);

            if (sexo.equalsIgnoreCase("Hombre")) {
                RB2.setSelected(true);

            } else if (sexo.equalsIgnoreCase("Mujer")) {
                RB.setSelected(true);

            }

        } catch (Exception ex) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement(" SELECT * FROM contactovisita  WHERE IDcedula=?");
            pst.setString(1, cedula);
            rs = pst.executeQuery();

            if (rs.next()) {

                movil = rs.getString("Movil");

            }

            this.txt25.setText(movil);

        } catch (Exception er) {

        }

        try {
            conexion();

            pst = conexion.prepareStatement(" SELECT * FROM casavisitada  WHERE IDcedula=?");
            pst.setString(1, cedula);
            rs = pst.executeQuery();

            if (rs.next()) {

                NROCASA = rs.getString("NROCASA");

            }

            this.txtnumero.setText(NROCASA);

        } catch (Exception er) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement(" SELECT * FROM mediotransporte  WHERE IDcedula=?");
            pst.setString(1, cedula);
            rs = pst.executeQuery();

            if (rs.next()) {

                medio = rs.getString("Medio");

            }

            if (medio.equalsIgnoreCase("Pie")) {
                rbpie.setSelected(true);

            } else if (medio.equalsIgnoreCase("Automovil")) {
                rbauto.setSelected(true);

            } else if (medio.equalsIgnoreCase("Moto")) {
                rbmoto.setSelected(true);

            } else if (medio.equalsIgnoreCase(" Vehiculo Pesado")) {
                rbpesado.setSelected(true);

            }

        } catch (Exception er) {

        }
                cerrar_conexion();


    };

    private final EventHandler<ActionEvent> guardar = (ActionEvent event) -> {

        nombre = this.txt22.getText();
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

        if (txt22.getText().equalsIgnoreCase("") || txt23.getText().equalsIgnoreCase("") || txt25.getText().equalsIgnoreCase("") || txtnumero.getText().equalsIgnoreCase("")) {
            msj.error("No dejes espacios en blanco ");
        }

        try {

            conexion();
            pst = conexion.prepareStatement(" UPDATE visitas SET Nombre=?, Apellido=?, Sexo=? WHERE Cedula='" + cedula + "'");

            pst.setString(1, this.txt22.getText());
            pst.setString(2, this.txt23.getText());
            pst.setString(3, sexo);

            pst.executeUpdate();

        } catch (Exception er) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement(" UPDATE mediotransporte SET Medio=? WHERE IDcedula='" + cedula + "'");

            pst.setString(1, medio);

            pst.executeUpdate();

        } catch (Exception er) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement(" UPDATE contactovisita SET Telefonos=? WHERE IDcedula='" + cedula + "'");

            pst.setString(1, this.txt25.getText());

            pst.executeUpdate();
        } catch (Exception ex) {

        }

        try {

            conexion();
            pst = conexion.prepareStatement(" UPDATE casavisitada SET NROCASA=? WHERE IDcedula='" + cedula + "'");

            pst.setString(1, this.txtnumero.getText());

            pst.executeUpdate();
            msj.information("Modificacion Exitosa");
        } catch (Exception ex) {

        }
        cerrar_conexion();

        limpiar();

    };

    @FXML
    private void eliminado(ActionEvent e) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            cedula = this.txt21.getText();

            try {

                conexion();
                pst = conexion.prepareStatement("DELETE FROM contactovisita WHERE IDcedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();

            } catch (Exception ex) {

            }

            try {

                conexion();
                pst = conexion.prepareStatement("DELETE FROM casavisitada WHERE IDcedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();

            } catch (Exception ex) {

            }

            try {

                conexion();
                pst = conexion.prepareStatement("DELETE FROM fechaentrada WHERE IDcedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();

            } catch (Exception ex) {

            }

            try {

                conexion();
                pst = conexion.prepareStatement("DELETE FROM mediotransporte WHERE IDcedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();

            } catch (Exception ex) {

            }
            try {

                conexion();
                pst = conexion.prepareStatement("DELETE FROM visitas WHERE Cedula=?");
                pst.setString(1, cedula);
                pst.executeUpdate();
                msj.information("Se ha eliminado");
            } catch (Exception ex) {

            }
        }
        cerrar_conexion();

        limpiar();

    }

    private final EventHandler<ActionEvent> Atras_8 = (ActionEvent event) -> {
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

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }

    public void limpiar() {

        this.txt21.setText("");
        this.txt22.setText("");
        this.txt23.setText("");

        this.txt24.setText("");
        this.txt25.setText("");
        this.txtregistro.setText("");
        this.txtnumero.setText("");

        this.RB.setSelected(false);
        this.RB2.setSelected(false);
        this.rbauto.setSelected(false);
        this.rbmoto.setSelected(false);
        this.rbpesado.setSelected(false);
        this.rbpie.setSelected(false);

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
}
