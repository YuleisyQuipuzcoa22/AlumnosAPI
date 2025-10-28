package example.com.dto;

import jakarta.validation.constraints.NotBlank;

public class GrupoDTO {
    private int id;

    @NotBlank(message = "El nombre del grupo es obligatorio")
    private String nombre;

    public GrupoDTO() {
    }

    public GrupoDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
