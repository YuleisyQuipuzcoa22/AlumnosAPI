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

    // Obtener todos los alumnos :,D
    public List<Alumno> findAll() {
        return entityManager.createQuery("SELECT a FROM Alumno a", Alumno.class)
                .getResultList();
    }

    // Guardar un alumno
    public Alumno save(Alumno alumno) {
        entityManager.persist(alumno);
        return alumno;
    }

    // Buscar por ID
    public Alumno findById(int id) {
        return entityManager.find(Alumno.class, id);
    }

    // Eliminar por ID
    public void deleteById(int id) {
        Alumno alumno = entityManager.find(Alumno.class, id);
        if (alumno != null) {
            entityManager.remove(alumno);
        }
    }
}
