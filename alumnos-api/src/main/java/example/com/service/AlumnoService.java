package example.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.com.dto.AlumnoDTO;
import example.com.model.Alumno;
import example.com.model.Grupo;
import example.com.repository.AlumnoRepository;
import example.com.repository.GrupoRepository;
import example.com.utils.JSendResponse;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    // Obtener todos los alumnos
    public JSendResponse<List<Alumno>> obtenerTodosLosAlumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return JSendResponse.success(alumnos, "Lista de alumnos obtenida correctamente");
    }

    // Obtener alumno por ID
    public JSendResponse<Alumno> obtenerAlumnoPorId(int id) {
        Optional<Alumno> optAlumno = alumnoRepository.findById(id);
        if (optAlumno.isEmpty()) {
            return JSendResponse.fail("Alumno con ID " + id + " no existe");
        }
        return JSendResponse.success(optAlumno.get(), "Alumno obtenido correctamente");
    }

    // Crear alumno
    public JSendResponse<Alumno> crearAlumno(AlumnoDTO dto) {
        if (dto.getNombres() == null || dto.getNombres().isBlank()) {
            return JSendResponse.fail("El nombre del alumno es obligatorio");
        }
        if (dto.getCodigo() == null || dto.getCodigo().isBlank()) {
            return JSendResponse.fail("El código del alumno es obligatorio");
        }

        Alumno alumno = new Alumno();
        alumno.setNombres(dto.getNombres());
        alumno.setApellidos(dto.getApellidos());
        alumno.setCodigo(dto.getCodigo());

        // Validar y asignar grupo
        if (dto.getGrupoId() != null) {
            Optional<Grupo> optGrupo = grupoRepository.findById(dto.getGrupoId());
            if (optGrupo.isEmpty()) {
                return JSendResponse.fail("El grupo con ID " + dto.getGrupoId() + " no existe");
            }
            alumno.setGrupo(optGrupo.get());
        }

        Alumno guardado = alumnoRepository.save(alumno);
        return JSendResponse.success(guardado, "Alumno creado correctamente");
    }

    // Actualizar alumno
    public JSendResponse<Alumno> actualizarAlumno(int id, AlumnoDTO dto) {
        Optional<Alumno> optAlumno = alumnoRepository.findById(id);
        if (optAlumno.isEmpty()) {
            return JSendResponse.fail("Alumno con ID " + id + " no existe");
        }
        Alumno alumnoExistente = optAlumno.get();

        if (dto.getNombres() != null && !dto.getNombres().isBlank()) {
            alumnoExistente.setNombres(dto.getNombres());
        }
        if (dto.getApellidos() != null) {
            alumnoExistente.setApellidos(dto.getApellidos());
        }
        if (dto.getCodigo() != null && !dto.getCodigo().isBlank()) {
            alumnoExistente.setCodigo(dto.getCodigo());
        }

        // Validar grupo
        if (dto.getGrupoId() != null) {
            Optional<Grupo> optGrupo = grupoRepository.findById(dto.getGrupoId());
            if (optGrupo.isEmpty()) {
                return JSendResponse.fail("El grupo con ID " + dto.getGrupoId() + " no existe");
            }
            alumnoExistente.setGrupo(optGrupo.get());
        }

        Alumno actualizado = alumnoRepository.save(alumnoExistente);
        return JSendResponse.success(actualizado, "Alumno actualizado correctamente");
    }

    // Eliminar alumno
    public JSendResponse<Void> eliminarAlumno(int id) {
        Optional<Alumno> optAlumno = alumnoRepository.findById(id);
        if (optAlumno.isEmpty()) {
            return JSendResponse.fail("Alumno con ID " + id + " no existe");
        }
        alumnoRepository.delete(optAlumno.get());
        return JSendResponse.success(null, "Alumno eliminado correctamente");
    }
}
