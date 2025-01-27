package o2024.Casino;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "mysql://root:HuFlPJURjSSjUWuzoZIjLWlnIxFHPFEt@junction.proxy.rlwy.net:55595/railway"; // Cambia 'casino' por el nombre de tu BD
    private static final String USER = "root"; // Tu usuario de MySQL
    private static final String PASSWORD = "HuFlPJURjSSjUWuzoZIjLWlnIxFHPFEt"
    		+ "\r\n"
    		+ "\r\n"
    		+ "\r\n"
    		+ ""; // Tu contraseña de MySQL

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }
}
