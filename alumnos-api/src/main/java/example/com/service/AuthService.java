package example.com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import example.com.dto.AuthRequestDTO;
import example.com.dto.AuthResponseDTO;
import example.com.model.Usuario;
import example.com.repository.UsuarioRepository;
import example.com.security.JwtUtil;
import example.com.utils.JSendResponse;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Register
    public JSendResponse<Usuario> register(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return JSendResponse.fail("El email ya está registrado");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // AQUI SUCEDE LA MAGUA JUASJUASJUAS
        Usuario guardado = usuarioRepository.save(usuario);
        return JSendResponse.success(guardado, "Usuario registrado correctamente");
    }

    // Login
    public JSendResponse<AuthResponseDTO> login(AuthRequestDTO request) {
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(request.getEmail());
        if (optUsuario.isEmpty()) {
            return JSendResponse.fail("Credenciales inválidas");
        }
        Usuario usuario = optUsuario.get();

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return JSendResponse.fail("Credenciales inválidas");
        }

        String token = jwtUtil.generarToken(usuario.getEmail());
        return JSendResponse.success(new AuthResponseDTO(token), "Login exitoso");
    }

    // UserDetailsService (para Spring Security)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles("USER") // rol simple
                .build();
    }
}
