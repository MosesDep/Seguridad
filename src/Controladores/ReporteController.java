/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

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
import javafx.scene.control.Alert;
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
 * @author Frank
 */
public class ReporteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label label;
    @FXML
    private TextField txtarea, txtcedula;
    @FXML
    private Button boton_limpiar, boton_guardar, btn_Volver, buscar;

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;
    String reporte, cedula;
    mensajitos msj = new mensajitos();
    @FXML
    private TableView<modelo_problema> tablon;
    @FXML
    private TableColumn<modelo_problema, String> Cedula;
    @FXML
    private TableColumn<modelo_problema, String> Fecha;

    @FXML
    private TableColumn<modelo_problema, String> Contingencia;
    ObservableList<modelo_problema> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Inicio();
        txtcedula.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
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

    @FXML
    private void save(ActionEvent e) {
        cedula = this.txtcedula.getText();
        reporte = this.txtarea.getText();

        if (cedula.equalsIgnoreCase("") || reporte.equalsIgnoreCase("")) {
            msj.error("Error No Dejes Espacios en blanco");

        }
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd ");
        String currentTime = sdf.format(dt);

        try {
            conexion();
            pst = conexion.prepareStatement(" INSERT INTO problema (IDcedula, Contingencia, Fecha) VALUES (?,?,?)");
            pst.setString(1, cedula);
            pst.setString(2, reporte);
            pst.setString(3, currentTime);

            pst.executeUpdate();

            msj.information("Registro Exitoso de imprevisto");

        } catch (Exception ex) {

        }
    }

    @FXML
    private void clear(ActionEvent ex) {
        this.txtarea.setText("");
        this.txtcedula.setText("");
    }

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }

    private void Inicio() {

        btn_Volver.setOnAction(Atras_11);

    }

    private final EventHandler<ActionEvent> Atras_11 = (ActionEvent event) -> {
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
    private void buscador(ActionEvent tt) {

        String buscar = this.txtcedula.getText();

        try {
            conexion();
            pst = conexion.prepareStatement(" SELECT IDcedula, Contingencia, Fecha FROM problema WHERE IDcedula=?");
            pst.setString(1, buscar);
            rs = pst.executeQuery();

            if (rs.next()) {

                while (rs.next()) {

                    oblist.add(new modelo_problema(rs.getString("IDcedula"), rs.getString("Contingencia"), rs.getString("Fecha")));

                }
            }

        } catch (Exception xx) {

        }
        Cedula.setCellValueFactory(new PropertyValueFactory("Cedula"));
        Contingencia.setCellValueFactory(new PropertyValueFactory("Contingencia"));

        Fecha.setCellValueFactory(new PropertyValueFactory("Fecha"));
         tablon.setItems(oblist);

    }

    @FXML
    private void generando(ActionEvent to) {

        String buscar = this.txtcedula.getText();

        try {
            conexion();
            pst = conexion.prepareStatement(" SELECT * FROM problema WHERE IDcedula=?");
            pst.setString(1, buscar);
            rs = pst.executeQuery();

            if (rs.next()) {

                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java_problema.pdf"));
                my_pdf_report.open();

                PdfPTable my_report_table = new PdfPTable(2);
                PdfPCell table_cell;

                while (rs.next()) {
                    String dept_id = rs.getString("IDcedula");
                    table_cell = new PdfPCell(new Phrase(dept_id));
                    my_report_table.addCell(table_cell);
                    String turnox = rs.getString("Contingencia");
                    table_cell = new PdfPCell(new Phrase(turnox));
                    my_report_table.addCell(table_cell);
                    String fecha = rs.getString("Fecha");
                    table_cell = new PdfPCell(new Phrase(fecha));
                    my_report_table.addCell(table_cell);;
                }

                my_pdf_report.add(my_report_table);
                my_pdf_report.close();
                msj.information("Reporte Generado");
            }

        } catch (Exception xx) {

        }

    }

}
