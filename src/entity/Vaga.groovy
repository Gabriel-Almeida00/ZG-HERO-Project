package entity

import entity.enums.NivelExperiencia
import entity.enums.NivelFormacao

class Vaga {
    String nome
    String descricao
    List<CurtidaVaga> curtidas = []
    List<Competencias> requisitos = []
    NivelFormacao formacaoMinima
    NivelExperiencia experienciaMinima

    Vaga(String nome, String descricao, List<Competencias> requisitos, NivelFormacao formacaoMinima, NivelExperiencia experienciaMinima) {
        this.nome = nome
        this.descricao = descricao
        this.requisitos = requisitos
        this.formacaoMinima = formacaoMinima
        this.experienciaMinima = experienciaMinima
    }

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    Competencias[] getRequisitos() {
        return requisitos
    }

    void setRequisitos(Competencias[] requisitos) {
        this.requisitos = requisitos
    }

    NivelFormacao getFormacaoMinima() {
        return formacaoMinima
    }

    void setFormacaoMinima(NivelFormacao formacaoMinima) {
        this.formacaoMinima = formacaoMinima
    }

    NivelExperiencia getExperienciaMinima() {
        return experienciaMinima
    }

    void setExperienciaMinima(NivelExperiencia experienciaMinima) {
        this.experienciaMinima = experienciaMinima
    }

    void adicionarCurtida(CurtidaVaga curtida) {
        curtidas.add(curtida)
    }

    List<CurtidaVaga> getCurtidas() {
        return curtidas
    }

}
