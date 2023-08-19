package Interface

import entity.Candidato
import entity.DTO.CandidatoDTO
import entity.DTO.EmpresaDTO
import entity.Vaga

interface IMatch {
    List<CandidatoDTO> encontrarCandidatosQueCurtiramVagaDaEmpresa(Vaga vaga)
    List<EmpresaDTO> encontrarEmpresasQueCurtiramCandidato(Candidato candidato)
}