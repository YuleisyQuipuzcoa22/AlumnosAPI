package example.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.com.dto.GrupoDTO;
import example.com.model.Grupo;
import example.com.repository.GrupoRepository;
import example.com.utils.JSendResponse;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    // Obtener todos los grupos
    public JSendResponse<List<Grupo>> obtenerTodosLosGrupos() {
        List<Grupo> grupos = grupoRepository.findAll();
        return JSendResponse.success(grupos, "Lista de grupos obtenida correctamente");
    }

    // Obtener grupo por ID
    public JSendResponse<Grupo> obtenerGrupoPorId(int id) {
        Optional<Grupo> optGrupo = grupoRepository.findById(id);
        if (optGrupo.isEmpty()) {
            return JSendResponse.fail("Grupo con ID " + id + " no existe");
        }
        return JSendResponse.success(optGrupo.get(), "Grupo obtenido correctamente");
    }

    // Crear grupo
    public JSendResponse<Grupo> crearGrupo(GrupoDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            return JSendResponse.fail("El nombre del grupo es obligatorio");
        }

        if (grupoRepository.existsByNombre(dto.getNombre())) {
            return JSendResponse.fail("Ya existe un grupo con ese nombre");
        }

        Grupo grupo = new Grupo();
        grupo.setNombre(dto.getNombre());

        Grupo guardado = grupoRepository.save(grupo);
        return JSendResponse.success(guardado, "Grupo creado correctamente");
    }

    // Actualizar grupo
    public JSendResponse<Grupo> actualizarGrupo(int id, GrupoDTO grupoActualizado) {
        Optional<Grupo> optGrupo = grupoRepository.findById(id);
        if (optGrupo.isEmpty()) {
            return JSendResponse.fail("Grupo con ID " + id + " no existe");
        }
        Grupo grupoExistente = optGrupo.get();

        if (grupoActualizado.getNombre() != null && !grupoActualizado.getNombre().isBlank()) {
            grupoExistente.setNombre(grupoActualizado.getNombre());
        }

        Grupo actualizado = grupoRepository.save(grupoExistente);
        return JSendResponse.success(actualizado, "Grupo actualizado correctamente");
    }

    // Eliminar grupo
    public JSendResponse<Void> eliminarGrupo(int id) {
        Optional<Grupo> optGrupo = grupoRepository.findById(id);
        if (optGrupo.isEmpty()) {
            return JSendResponse.fail("Grupo con ID " + id + " no existe");
        }

        Grupo grupo = optGrupo.get();
        if (grupo.getAlumnos() != null && !grupo.getAlumnos().isEmpty()) {
            return JSendResponse.fail("No se puede eliminar un grupo que tiene alumnos asignados");
        }

        grupoRepository.delete(grupo);
        return JSendResponse.success(null, "Grupo eliminado correctamente");
    }
}
