package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Conexion {

    private static Properties properties;
    private static String USERNAME = "HospitalGuinea";
    private static String PASSWORD = "HospitalGuinea@@@";
    private final static String URL = "jdbc:mysql://guine.c7x2o1ubdsom.eu-west-2.rds.amazonaws.com:3306/Guinea";
    private static Connection connection;

    private static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

    /**
     *
     * @return
     */
    public static Connection conectarse() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("No se ha encontrado el driver", e);
            }
            try {
                connection = DriverManager.getConnection(URL, getProperties());
            } catch (SQLException e) {
                throw new IllegalStateException("No se ha podido conectar a la base de datos", e);
            }
        }
        return connection;
    }

    /**
     *
     */
    public void desconectar() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}