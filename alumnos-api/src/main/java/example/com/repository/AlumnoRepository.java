package example.com.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import example.com.entity.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

// Acceso a Datos
// Se comunica directamente con la BD 
@Repository
public class AlumnoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    // Método para obtener todos los alumnos
    public List<Alumno> findAll() {
        return entityManager.createQuery("SELECT a FROM Alumno a", Alumno.class)
                .getResultList();
    }
}
