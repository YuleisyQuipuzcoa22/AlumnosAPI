package example.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import example.com.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
