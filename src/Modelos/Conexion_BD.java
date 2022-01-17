/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Moises
 */
public class Conexion_BD {

    static String bd = "seguridad";
    static String URL = "jdbc:mysql://localhost/" + bd;
    static String usuario = "root";
    static String pass = "02518480907";

    static Connection conexion = null;

    public Connection conectar() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, usuario, pass);
            System.out.print("Conecto ");
        } catch (Exception e) {

            System.err.print("Error" + e);

        }

        return conexion;

    }

    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


