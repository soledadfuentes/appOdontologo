package Examen;

import Examen.Daos.Impl.OdontologoDaoH2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {


        Connection connection = null;
        // Conectamos con H2
        try {
            connection = OdontologoDaoH2.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(Script.SQL_CREATE_TABLE);

            OdontologoDaoH2.logger.info("Tabla odontologo creada");
        } catch (Exception e) {

            OdontologoDaoH2.logger.error("Error al crear la tabla");
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
