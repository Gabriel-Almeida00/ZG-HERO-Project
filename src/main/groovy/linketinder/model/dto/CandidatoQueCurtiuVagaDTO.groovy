package linketinder.model.dto

class CandidatoQueCurtiuVagaDTO {
    private Integer idCandidato
    private String descricao
    private List<String> nomes

    CandidatoQueCurtiuVagaDTO(Integer idCandidato, String descricao, List<String> nomes) {
        this.idCandidato = idCandidato
        this.descricao = descricao
        this.nomes = nomes
    }

    Integer getIdCandidato() {
        return idCandidato
    }

    String getDescricao() {
        return descricao
    }

    List<String> getNomes() {
        return nomes
    }
}
