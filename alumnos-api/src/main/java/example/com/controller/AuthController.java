package example.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import example.com.dto.AuthRequestDTO;
import example.com.dto.AuthResponseDTO;
import example.com.model.Usuario;
import example.com.service.AuthService;
import example.com.utils.JSendResponse;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<JSendResponse<Usuario>> register(@RequestBody Usuario usuario) {
        JSendResponse<Usuario> response = authService.register(usuario);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<JSendResponse<AuthResponseDTO>> login(@RequestBody AuthRequestDTO request) {
        JSendResponse<AuthResponseDTO> response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
