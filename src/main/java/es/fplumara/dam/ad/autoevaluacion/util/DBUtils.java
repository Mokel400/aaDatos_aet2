package es.fplumara.dam.ad.autoevaluacion.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {

    public int insertarPelicula(Connection conexion, int id, String titulo, String estreno, double puntuacion, String descripcion) throws SQLException {
        String sql = "INSERT INTO peliculas (id_pelicula, titulo,  estreno, puntuacion, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            sentencia.setString(2, titulo);
            sentencia.setString(3, estreno);
            sentencia.setDouble(4, puntuacion);
            sentencia.setString(5, descripcion);
            return sentencia.executeUpdate();
        }
    }

    public int insertarPersona(Connection conexion, int id, String nombre) throws SQLException {
        String sql = "INSERT INTO personas (id_persona, nombre) VALUES (?, ?)";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            sentencia.setString(2, nombre);
            return sentencia.executeUpdate();
        }
    }

    public int insertarPuesto(Connection conexion, int idPelicula, int idPersona, String puesto) throws SQLException {
        String sql = "INSERT INTO puestos (fk_pelicula, fk_persona, puesto) VALUES (?, ?, ?)";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, idPelicula);
            sentencia.setInt(2, idPersona);
            sentencia.setString(3, puesto);
            return sentencia.executeUpdate();
        }
    }

    //Mostrar todos los datos de la película y sus personas
    public void verPelicula(Connection conexion, int idPelicula) throws SQLException {
        String sql = "SELECT * FROM peliculas WHERE id_pelicula = ?";
    }
}
