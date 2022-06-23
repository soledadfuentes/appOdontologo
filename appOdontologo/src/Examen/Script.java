package Examen;

public class Script {
    // Crear tabla
    public static final String SQL_CREATE_TABLE = "DROP TABLE IF EXISTS odontologos; CREATE TABLE odontologos " +
            "(ID BIGINT PRIMARY KEY NOT NULL," +
            "MATRICULA INT NOT NULL," +
            "NOMBRE VARCHAR(100) NOT NULL," +
            "APELLIDO VARCHAR (100) NOT NULL)";
}
