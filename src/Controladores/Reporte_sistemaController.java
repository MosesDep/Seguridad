/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.TablaController.conexion;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mensajes.mensajitos;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class Reporte_sistemaController implements Initializable {

    @FXML
    private Button buscar;
    @FXML
    private Button reporte;
    @FXML
    private TextField txt;
    @FXML
    private Button btn_Volver;
    @FXML
    private TableView<reportemodelo> tabla;
    @FXML
    private TableView<reportemodelo2> tablita;
    @FXML
    private TableColumn<reportemodelo2, String> CedulaRE;
    @FXML
    private TableColumn<reportemodelo2, String> FechaRegistro;

    @FXML
    private TableColumn<reportemodelo, String> cedula;

    @FXML
    private TableColumn<reportemodelo, String> turnos;
    mensajitos msj = new mensajitos();

    @FXML
    private RadioButton rbfecha;
    @FXML
    private TextField txtfecha;

    ObservableList<reportemodelo> oblist = FXCollections.observableArrayList();
    ObservableList<reportemodelo2> oblist2 = FXCollections.observableArrayList();

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String passs = "02518480907";
    static Connection conexion = null;
    static PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

       

    }

    @FXML
    private void fecheteo(ActionEvent xx) {

        this.txtfecha.setDisable(false);

    }

    @FXML
    private void searching(ActionEvent e) {
         

     
         String perro = this.txt.getText();
         
          try {
            conexion();
            pst = conexion.prepareStatement(" SELECT CedulaRE,FechaRegistro  FROM visitas WHERE CedulaRE=?");
            pst.setString(1,perro);
            rs = pst.executeQuery();
            if (rs.next()) {
                while (rs.next()) {

                    oblist2.add(new reportemodelo2(rs.getString("CedulaRE"), rs.getString("FechaRegistro")));

                }
            } else {

                msj.error("Error no existen ");
            }

        } catch (Exception xx) {

        }

        CedulaRE.setCellValueFactory(new PropertyValueFactory("CedulaRE"));
        FechaRegistro.setCellValueFactory(new PropertyValueFactory("FechaRegistro"));

        tablita.setItems(oblist2);
         try {
         conexion();
         pst = conexion.prepareStatement(" SELECT * FROM turnovigilante WHERE IDcedula=?");
         pst.setString(1, perro);
         rs = pst.executeQuery();
         if (rs.next()) {

         while (rs.next()) {

         oblist.add(new reportemodelo(rs.getString("IDcedula"), rs.getString("Turno")));

         }

         } else {

         msj.error("Error no existen ");
         }
         } catch (Exception er) {

         }
         cedula.setCellValueFactory(new PropertyValueFactory("Cedula"));
         turnos.setCellValueFactory(new PropertyValueFactory("Turnos"));

         tabla.setItems(oblist);
        
          
    }

    @FXML
    private void reportando(ActionEvent tt) {

        String perro = this.txt.getText();

        try {
            conexion();
            pst = conexion.prepareStatement(" SELECT * FROM turnovigilante WHERE IDcedula=?");
            pst.setString(1, perro);
            rs = pst.executeQuery();
            if (rs.next()) {
                
                
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
                my_pdf_report.open();

                PdfPTable my_report_table = new PdfPTable(2);
                PdfPCell table_cell;
                while (rs.next()) {
                    String dept_id = rs.getString("IDcedula");
                    table_cell = new PdfPCell(new Phrase(dept_id));
                    my_report_table.addCell(table_cell);
                    String turnox = rs.getString("Turno");
                    table_cell = new PdfPCell(new Phrase(turnox));
                    my_report_table.addCell(table_cell);
                }
                my_pdf_report.add(my_report_table);
                my_pdf_report.close();
                msj.information("Reporte Generado");
            }

        } catch (Exception o) {

        }
          try {
            conexion();
            pst = conexion.prepareStatement("  SELECT CedulaRE,FechaRegistro  FROM visitas WHERE CedulaRE=?");
            pst.setString(1, perro);
            rs = pst.executeQuery();
            if (rs.next()) {
                
                
                Document my_pdf_report2 = new Document();
                PdfWriter.getInstance(my_pdf_report2, new FileOutputStream("pdf_report_from_sql_using_java_registro_visitas.pdf"));
                my_pdf_report2.open();

                PdfPTable my_report_table2 = new PdfPTable(2);
                PdfPCell table_cell;
                while (rs.next()) {
                    String dept_id = rs.getString("CedulaRE");
                    table_cell = new PdfPCell(new Phrase(dept_id));
                    my_report_table2.addCell(table_cell);
                    String turnox = rs.getString("FechaRegistro");
                    table_cell = new PdfPCell(new Phrase(turnox));
                    my_report_table2.addCell(table_cell);
                }
                my_pdf_report2.add(my_report_table2);
                my_pdf_report2.close();
                msj.information("Reporte Generado");
            }

        } catch (Exception o) {

        }
        
        
    }

    public void conexion() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, usuario, passs);

    }

}
