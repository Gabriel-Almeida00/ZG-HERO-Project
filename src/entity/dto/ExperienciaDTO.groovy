package entity.dto

class ExperienciaDTO {
    String cargo
    String nivel

    ExperienciaDTO(String cargo, String nivel) {
        this.cargo = cargo
        this.nivel = nivel
    }

    String getCargo() {
        return cargo
    }

    void setCargo(String cargo) {
        this.cargo = cargo
    }

    String getNivel() {
        return nivel
    }

    void setNivel(String nivel) {
        this.nivel = nivel
    }

    @Override
     String toString() {
        return cargo + " - " + nivel
    }
}
