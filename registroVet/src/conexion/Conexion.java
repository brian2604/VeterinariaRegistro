package conexion;

import controlador.Coordinador;
import properties.Credenciales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Conexion instancia; // instancia Singleton
    private Connection conn;
    private Coordinador coordinador;

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    Credenciales.URL,
                    Credenciales.USUARIO,
                    Credenciales.PASSWORD
            );
            System.out.println(" Conexión exitosa a " + Credenciales.NOMBRE_BD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(" Error al conectar con la base de datos:");
            e.printStackTrace();
        }
    }


    public static Conexion getInstance() { // metodo para obtener la única instancia
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conn;
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }
}



