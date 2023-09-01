package service

import Interface.IMatch
import entity.Candidato
import entity.dto.CandidatoDTO
import entity.dto.EmpresaDTO
import entity.Vaga

class MatchService implements IMatch {

    List<CandidatoDTO> encontrarCandidatosQueCurtiramVagaDaEmpresa(Vaga vaga) {
        List<CandidatoDTO> candidatosDTO = []

        vaga.getCurtidas().each { curtida ->
            candidatosDTO.add(new CandidatoDTO(curtida.getCandidato()))
        }

        return candidatosDTO
    }

    List<EmpresaDTO> encontrarEmpresasQueCurtiramCandidato(Candidato candidato) {
        List<EmpresaDTO> empresasDTO = []

        candidato.getCurtidas().each { curtida ->
            empresasDTO.add(new EmpresaDTO(curtida.getEmpresa()))
        }

        return empresasDTO
    }
}

