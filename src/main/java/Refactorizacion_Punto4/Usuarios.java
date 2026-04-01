package Refactorizacion_Punto4;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Usuarios {

    public static final String INSERTAR = "insertar";
    public static final String ACTUALIZAR = "actualizar";

    private final String jdbcUrl;

    public Usuarios(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    private void coneccion(Consumer criterio, String SQL, String accion){


        try (Connection connection = DriverManager.getConnection(this.jdbcUrl);
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            connection.setAutoCommit(false);

            criterio.run(statement);

            System.out

            try {
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Error al " + accion + " usuario", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al " + accion + " usuario", e);
        }

    }


    public void insertar(String nombre, String email) {
//        this.coneccion( (statement)-> {  statement.setString(1, nombre);
//            statement.setString(2, email);},"INSERT INTO usuarios (nombre, email) VALUES (?, ?)", INSERTAR );


        /*try (Connection connection = DriverManager.getConnection(this.jdbcUrl);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO usuarios (nombre, email) VALUES (?, ?)")) {
            connection.setAutoCommit(false);
            statement.setString(1, nombre);
            statement.setString(2, email);
            try {
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Error al intertar usuario", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar usuario", e);
        }*/
    }

    public void actualizarEmail(int id, String nuevoEmail) {

        this.coneccion( ()-> {  statement.setString(1, nuevoEmail);
            statement.setInt(2, id);},"UPDATE usuarios SET email = ? WHERE id = ?", ACTUALIZAR );

        /*try (Connection connection = DriverManager.getConnection(this.jdbcUrl);
             PreparedStatement statement = connection.prepareStatement("UPDATE usuarios SET email = ? WHERE id = ?")) {
            connection.setAutoCommit(false);
            statement.setString(1, nuevoEmail);
            statement.setInt(2, id);
            try {
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Error al actualizar usuario", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario", e);
        }*/
    }
}
