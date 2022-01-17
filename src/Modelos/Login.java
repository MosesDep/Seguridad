/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Moises
 */
import java.sql.*;
import Modelos.Conexion_BD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;


public class Login {

    Conexion_BD x = new Conexion_BD();
    private String nombre;
    private String pass;
    
    public Login(String Nombre, String pass){
        this.nombre=Nombre;
        this.pass=pass;
        
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    

    public void  login(String user, String password) {

        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        int state = -1;

        try {
            connection = x.conectar();
            if (connection != null) {
                String SQL = "SELECT Nombre, Contraseña FROM superusuario WHERE Nombre='Moises' AND Contraseña='1234'";
                pst = connection.prepareStatement(SQL);
                pst.setString(2, user);
                pst.setString(3, password);

                rs = pst.executeQuery();

                if (rs.next()) {
                    state = 1;
                } else {
                    state = 0;
                }

            }
        } catch (Exception e ) {

        }
    }

}
