package linketinder.entity.dto

class CandidatoQueCurtiuVagaDTO {
    Integer idCandidato
    String descricao
    List<String> nomes

    CandidatoQueCurtiuVagaDTO(Integer idCandidato, String descricao, List<String> nomes) {
        this.idCandidato = idCandidato
        this.descricao = descricao
        this.nomes = nomes
    }
}
