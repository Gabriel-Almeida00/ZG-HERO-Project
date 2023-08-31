package Interface

import entity.Candidato
import entity.dto.CandidatoDTO
import entity.dto.EmpresaDTO
import entity.Vaga

interface IMatch {
    List<CandidatoDTO> encontrarCandidatosQueCurtiramVagaDaEmpresa(Vaga vaga)
    List<EmpresaDTO> encontrarEmpresasQueCurtiramCandidato(Candidato candidato)
}