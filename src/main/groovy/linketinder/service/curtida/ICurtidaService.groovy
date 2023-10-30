package linketinder.service.curtida

import linketinder.model.CandidatoCurtido
import linketinder.model.VagaCurtida
import linketinder.model.dto.CandidatoQueCurtiuVagaDTO
import linketinder.model.dto.EmpresaDTO

interface ICurtidaService {
    void curtirVaga(VagaCurtida vagaCurtida)
    void curtirCandidato(CandidatoCurtido candidatoCurtido)
    List< EmpresaDTO> listarEmpresasQueCurtiramCandidato(Integer idCandidato)
    List<CandidatoQueCurtiuVagaDTO> listarCandidatosQueCurtiramVaga(Integer idVaga)
}
