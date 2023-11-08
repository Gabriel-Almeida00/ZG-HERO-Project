package linketinder.model.dto

class ExperienciaDTO {
    private String cargoExperiencia
    private String empresaExperiencia
    private Integer nivel

    ExperienciaDTO(String cargoExperiencia, String empresaExperiencia, Integer nivel) {
        this.cargoExperiencia = cargoExperiencia
        this.empresaExperiencia = empresaExperiencia
        this.nivel = nivel
    }
}
