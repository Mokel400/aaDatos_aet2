package es.fplumara.dam.ad.autoevaluacion.util;

import es.fplumara.dam.ad.autoevaluacion.modelo.Pelicula;
import es.fplumara.dam.ad.autoevaluacion.modelo.Persona;
import es.fplumara.dam.ad.autoevaluacion.modelo.Puesto;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class ServicioJson {

    private final ObjectMapper mapper;

    public ServicioJson() {
        mapper = new ObjectMapper();
    }

    public List<Pelicula> leerPelicula(String ruta) {
        return mapper.readValue(new File(ruta), new TypeReference<List<Pelicula>>() {});
    }

    public List<Persona> leerPersona(String ruta) {
        return mapper.readValue(new File(ruta), new TypeReference<List<Persona>>() {});
    }

    public List<Puesto> leerPuesto(String ruta) {
        return mapper.readValue(new File(ruta), new TypeReference<List<Puesto>>() {});
    }


}
