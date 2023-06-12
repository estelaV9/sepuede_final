package com.example.sepuede_final;

import java.sql.*;
import java.sql.DriverManager;
public class DBconexion {
    private static String url;
    private static String usuarioBD;
    private static String password;

    public DBconexion(String url, String usuarioBD, String password) {
        this.url = url;
        this.usuarioBD = usuarioBD;
        this.password = password;
    }

    public DBconexion() {
        this.url = "jdbc:mysql://localhost:3306/shruk_museum?serverTimezone=UTC";
        this.usuarioBD = "root";
        this.password = "";
    }


    public static void DBcontroller() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Controlador correcto");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Statement crearconexion(boolean sololectura) {
        DBcontroller();
        try {
            Connection conn = DriverManager.getConnection(this.url,this.usuarioBD,this.password);
            // Obtenemos un Statement de la conexión
            Statement st;
            if (sololectura) {
                st = conn.createStatement();
            } else {
                st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            }
            System.out.println("Conexión creada");
            return st;
        } catch (SQLException e) {
            System.out.println("Error base de datos");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cerrarconexion(Statement st) {
        try {
            st.close();
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            System.out.println("Error base de datos");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet ejecutarsentencia(String sql, Statement st) {
        try {
            ResultSet rs = st.executeQuery(sql);
            System.out.println("Conexión ejecutada");
            return rs;
        } catch (SQLException e) {
            System.out.println("Error base de datos");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
