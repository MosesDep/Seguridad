/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.StastvisitaController.pst;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Toolkit;
import java.io.FileOutputStream;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import mensajes.mensajitos;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class MenustatsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<modeloadmin> tablita;
    @FXML
    private TableColumn<modeloadmin, String> cedula;
    @FXML
    private TableColumn<modeloadmin, String> nombre;
    @FXML
    private TableColumn<modeloadmin, String> apellido;

    @FXML
    private TableColumn<modeloadmin, String> NROCASA;
    @FXML
    private Button btn_Volver, buscar;
    @FXML

    private Button reporte;
    @FXML

    private TextField txtbuscar;
    mensajitos msj = new mensajitos();

    ObservableList<modeloadmin> oblist = FXCollections.observableArrayList();
    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
         txtbuscar.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

        try {

            conexion();
            pst = conexion.prepareStatement("SELECT Cedula, Nombre, Apellido, NROCASA  FROM propietario");
            rs = pst.executeQuery();

            while (rs.next()) {
                oblist.add(new modeloadmin(rs.getString("Cedula"), rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("NROCASA")));

            }

        } catch (Exception ex) {

        }

        cedula.setCellValueFactory(new PropertyValueFactory("Cedula"));
        nombre.setCellValueFactory(new PropertyValueFactory("Nombre"));

        apellido.setCellValueFactory(new PropertyValueFactory("Apellido"));

        NROCASA.setCellValueFactory(new PropertyValueFactory("numerodecasa"));

        tablita.setItems(oblist);

        Inicio();

    }

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }

    private final EventHandler<ActionEvent> Volver = (ActionEvent event) -> {
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
    private void reportando(ActionEvent tt) {
        
                String b = this.txtbuscar.getText();

        try {
            conexion();

            pst = conexion.prepareStatement("SELECT Cedula, Nombre, Apellido, NROCASA  FROM propietario WHERE NROCASA=?");
            pst.setString(1, b);
            rs = pst.executeQuery();
            
           
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_reportpropietario_from_sql_using_java.pdf"));
            my_pdf_report.open();

            PdfPTable my_report_table = new PdfPTable(2);
            PdfPCell table_cell;

            while (rs.next()) {
                String dept_id = rs.getString("Cedula");
                table_cell = new PdfPCell(new Phrase(dept_id));
                my_report_table.addCell(table_cell);
                String turnox = rs.getString("Nombre");
                table_cell = new PdfPCell(new Phrase(turnox));
                my_report_table.addCell(table_cell);
                String ape = rs.getString("Apellido");
                table_cell = new PdfPCell(new Phrase(ape));
                my_report_table.addCell(table_cell);
                String casa = rs.getString("NROCASA");
                table_cell = new PdfPCell(new Phrase(casa));
                my_report_table.addCell(table_cell);

            }

            my_pdf_report.add(my_report_table);
            my_pdf_report.close();
            msj.information("Reporte Generado");
        } catch (Exception o) {

        }
    }

    @FXML
    private void search(ActionEvent e) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException xx) {

        }

        tablita.getItems().clear();

        String b = this.txtbuscar.getText();
        try {

            conexion();
            pst = conexion.prepareStatement("SELECT Cedula, Nombre, Apellido, NROCASA  FROM propietario WHERE NROCASA=?");
            pst.setString(1, b);

            rs = pst.executeQuery();
            if (rs.next()) {

                while (rs.next()) {
                    oblist.add(new modeloadmin(rs.getString("Cedula"), rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("NROCASA")));

                }
            }

        } catch (Exception ex) {

        }

        cedula.setCellValueFactory(new PropertyValueFactory("Cedula"));
        nombre.setCellValueFactory(new PropertyValueFactory("Nombre"));

        apellido.setCellValueFactory(new PropertyValueFactory("Apellido"));

        NROCASA.setCellValueFactory(new PropertyValueFactory("numerodecasa"));

        tablita.setItems(oblist);

    }

    private void Inicio() {

        btn_Volver.setOnAction(Volver);

    }

}
