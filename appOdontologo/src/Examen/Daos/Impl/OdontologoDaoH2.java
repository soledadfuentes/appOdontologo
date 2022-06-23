package Examen.Daos.Impl;

import Examen.Daos.IDao;
import Examen.Entidades.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    public static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/odontologos";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";


    @Override
    public Odontologo guardar(Odontologo odontologo) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("INSERT INTO odontologos(id,matricula,nombre,apellido) VALUES(?,?,?,?)");
            preparedStatement.setLong(1, odontologo.getId());
            preparedStatement.setInt(2, odontologo.getMatricula());
            preparedStatement.setString(3, odontologo.getNombre());
            preparedStatement.setString(4, odontologo.getApellido());

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();

            //Logger
            logger.info("Odontologo guardado");

            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Ocurrio un error al intentar guardar un odontologo", e);
            e.printStackTrace();
        }
        return odontologo;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM odontologos where id = ?");
            preparedStatement.setLong(1,id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();

            //Logger
            logger.info("Odontologo eliminado");

            preparedStatement.close();


        } catch (SQLException | ClassNotFoundException throwables) {
            logger.error("Ocurrio un error al intentar eliminar un odontologo");
            throwables.printStackTrace();
        }

    }

    @Override
    public Odontologo buscar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos where id = ?");
            preparedStatement.setLong(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //Logger
            logger.info("Se encontró el odontologo buscado");

            //4 Obtener resultados
            while (result.next()) {
                Long idOdontolgo = result.getLong("id");
                int matricula = result.getInt("matricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                Odontologo o= new Odontologo(id,matricula,nombre,apellido);

            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            logger.error("Ocurrio un error al intentar buscar un odontologo");
            throwables.printStackTrace();
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodo() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM odontologos");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //Logger
            logger.info("Se obtuvo lista de odontologos");

            //4 Obtener resultados
            while (result.next()) {
                Long id = result.getLong("id");
                int matricula = result.getInt("matricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                odontologos.add(new Odontologo(id, matricula, nombre, apellido));

            }
            preparedStatement.close();

        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Ocurrio un error al intentar listar todos los odontologos");
            e.printStackTrace();
        }

        return odontologos;
    }

    // Metodo para conectarme a H2
    public static Connection getConnection() throws Exception {
        Class.forName(DB_JDBC_DRIVER).newInstance();
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

}
