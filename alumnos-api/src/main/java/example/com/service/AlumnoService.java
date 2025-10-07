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
        return alumnoRepository.findById(id);
    }

    @Transactional
    public Alumno createAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Transactional
    public void deleteAlumno(int id) {
        alumnoRepository.deleteById(id);
    }
}
