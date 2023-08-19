package service

import Interface.IMatch
import entity.Candidato
    import entity.CurtidaCandidato
import entity.CurtidaVaga
import entity.DTO.CandidatoDTO
import entity.DTO.EmpresaDTO
import entity.Empresa
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

