package es.fplumara.dam.ad.autoevaluacion;

import es.fplumara.dam.ad.autoevaluacion.modelo.Pelicula;
import es.fplumara.dam.ad.autoevaluacion.modelo.Persona;
import es.fplumara.dam.ad.autoevaluacion.modelo.Puesto;
import es.fplumara.dam.ad.autoevaluacion.util.Conexion;
import es.fplumara.dam.ad.autoevaluacion.util.DBUtils;
import es.fplumara.dam.ad.autoevaluacion.util.ServicioJson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Autoevaluación de los temas 1 y 2: el catálogo del cine.
 */
public class Principal {

    // Ficheros de datos, relativos a la carpeta del proyecto
    private static final String RUTA_PERSONAS = "datos/personas.json";
    private static final String RUTA_PELICULAS = "datos/peliculas.json";
    private static final String RUTA_PUESTOS = "datos/puestos.json";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Comprobación inicial: borra estas líneas cuando empieces el apartado 4
        try (Connection conexion = Conexion.abrir()) {
            // Apartado 1: leer los tres ficheros JSON
            ServicioJson json = new ServicioJson();
            json.leerPelicula(RUTA_PELICULAS);
            json.leerPersona(RUTA_PERSONAS);
            json.leerPuesto(RUTA_PUESTOS);

            // Apartado 2: guardar los datos en MariaDB con JDBC
            DBUtils db = new DBUtils();
            List<Pelicula> peliculas = json.leerPelicula(RUTA_PELICULAS);
            List<Persona> personas = json.leerPersona(RUTA_PERSONAS);
            List<Puesto> puestos = json.leerPuesto(RUTA_PUESTOS);
            for (Pelicula pelicula : peliculas) {
                db.insertarPelicula(conexion, pelicula.getId(), pelicula.getTitulo(), pelicula.getEstreno(), pelicula.getPuntuacion(), pelicula.getDescripcion());
            }
            System.out.println("Peliculas insertadas: " + peliculas.size());
            for (Persona persona : personas) {
                db.insertarPersona(conexion, persona.getId(), persona.getNombre());
            }
            System.out.println("Personas insertadas: " + personas.size());
            for (Puesto puesto : puestos) {
                db.insertarPuesto(conexion, puesto.getIdPelicula(), puesto.getIdPersona(), puesto.getPuesto());
            }
            System.out.println("Puestos insertados: " + puestos.size());

            // Apartado 3: pedir el id de una película y mostrar sus datos y sus personas
            System.out.println("Que película quieres buscar? Inserta su id: ");
            Integer idPelicula = sc.nextInt();


            System.out.println("Conexión correcta con " + conexion.getCatalog());
        } catch (IOException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }







        // Apartado 4: hacer las conexiones con un pool
    }
}
