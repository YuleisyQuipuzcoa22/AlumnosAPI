package example.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.com.entity.Alumno;
import example.com.repository.AlumnoRepository;
import jakarta.transaction.Transactional;

@Service
public class AlumnoService {
    
    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    public Alumno getAlumnoById(int id) {
        Alumno alumno = alumnoRepository.findById(id);
        if (alumno == null) {
            throw new RuntimeException("Alumno con ID " + id + " no existe");
        }
        return alumno;
    }

    @Transactional
    public Alumno createAlumno(Alumno alumno) {
        if (alumno.getNombres() == null || alumno.getNombres().isBlank()) {
            throw new RuntimeException("El nombre del alumno es obligatorio");
        }
        if (alumno.getCodigo() == null || alumno.getCodigo().isBlank()) {
            throw new RuntimeException("El código del alumno es obligatorio");
        }
        return alumnoRepository.save(alumno);
    }

    @Transactional
    public void deleteAlumno(int id) {
        Alumno alumno = alumnoRepository.findById(id);
        if (alumno == null) {
            throw new RuntimeException("Alumno con ID " + id + " no existe");
        }
        alumnoRepository.deleteById(id);
    }
}