package linketinder.service.curtida

import linketinder.entity.dto.CandidatoQueCurtiuVagaDTO
import linketinder.entity.dto.EmpresaDTO

interface ICurtidaService {
    void curtirVaga(Integer idCandidato, Integer idVaga)
    void curtirCandidato(Integer idCandidato, Integer idEmpresa)
    List< EmpresaDTO> listarEmpresasQueCurtiramCandidato(Integer idCandidato)
    List<CandidatoQueCurtiuVagaDTO> listarCandidatosQueCurtiramVaga(Integer idVaga)
}
