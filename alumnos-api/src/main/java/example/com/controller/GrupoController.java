package example.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import example.com.dto.GrupoDTO;
import example.com.model.Grupo;
import example.com.service.GrupoService;
import example.com.utils.JSendResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    // Obtener todos los grupos
    @GetMapping
    public JSendResponse<List<Grupo>> obtenerTodosLosGrupos() {
        return grupoService.obtenerTodosLosGrupos();
    }

    // Obtener grupo por ID
    @GetMapping("/{id}")
    public JSendResponse<Grupo> obtenerGrupoPorId(@PathVariable int id) {
        return grupoService.obtenerGrupoPorId(id);
    }

    // Crear grupo
    @PostMapping
    public JSendResponse<Grupo> crearGrupo(@RequestBody GrupoDTO grupo) {
        return grupoService.crearGrupo(grupo);
    }

    // Actualizar grupo
    @PutMapping("/{id}")
    public JSendResponse<Grupo> actualizarGrupo(@Valid @PathVariable int id, @RequestBody GrupoDTO grupo) {
        return grupoService.actualizarGrupo(id, grupo);
    }

    // Eliminar grupo
    @DeleteMapping("/{id}")
    public JSendResponse<Void> eliminarGrupo(@PathVariable int id) {
        return grupoService.eliminarGrupo(id);
    }
}
