package linketinder.entity.dto

import linketinder.entity.Empresa

class EmpresaDTO {
    String estado
    String descricaoEmpresa

    EmpresaDTO(Empresa empresa) {
        this.estado = empresa.getEstado()
        this.descricaoEmpresa = empresa.getDescricaoEmpresa()
    }
}
