package example.com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AlumnoDTO {

    @NotBlank(message = "El nombre del alumno es obligatorio")
    private String nombres;

    private String apellidos;

    @NotBlank(message = "El código del alumno es obligatorio")
    private String codigo;

    @NotNull(message = "El ID del grupo no puede ser nulo")
    private int grupoId;

    public AlumnoDTO() {
    }

    public AlumnoDTO(String nombres, String apellidos, String codigo, int grupoId) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.codigo = codigo;
        this.grupoId = grupoId;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Integer grupoId) {
        this.grupoId = grupoId;
    }
}
