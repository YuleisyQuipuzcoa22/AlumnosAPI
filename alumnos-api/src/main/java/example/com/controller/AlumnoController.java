package example.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import example.com.dto.AlumnoDTO;
import example.com.model.Alumno;
import example.com.service.AlumnoService;
import example.com.utils.JSendResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    // Obtener todos los alumnos
    @GetMapping
    public JSendResponse<List<Alumno>> obtenerTodosLosAlumnos() {
        return alumnoService.obtenerTodosLosAlumnos();
    }

    // Obtener alumno por ID
    @GetMapping("/{id}")
    public JSendResponse<Alumno> obtenerAlumnoPorId(@PathVariable int id) {
        return alumnoService.obtenerAlumnoPorId(id);
    }

    // Crear alumno
    @PostMapping
    public JSendResponse<Alumno> crearAlumno(@Valid @RequestBody AlumnoDTO alumnoDTO) {
        return alumnoService.crearAlumno(alumnoDTO);
    }

    // Actualizar alumno
    @PutMapping("/{id}")
    public JSendResponse<Alumno> actualizarAlumno(@Valid @PathVariable int id, @RequestBody AlumnoDTO alumnoDTO) {
        return alumnoService.actualizarAlumno(id, alumnoDTO);
    }

    // Eliminar alumno
    @DeleteMapping("/{id}")
    public JSendResponse<Void> eliminarAlumno(@PathVariable int id) {
        return alumnoService.eliminarAlumno(id);
    }
}
