package example.com.repository;

import example.com.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Acceso a Datos
// Se comunica directamente con la BD 
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    // Puedes agregar métodos personalizados aquí si lo necesitas
}