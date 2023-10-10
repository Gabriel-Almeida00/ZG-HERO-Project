package linketinder.dao.curtida

import linketinder.model.dto.CandidatoQueCurtiuVagaDTO
import linketinder.model.dto.EmpresaDTO

interface ICurtidaDao {
    void curtirVaga(Integer idCandidato, Integer idVaga)
    Integer verificaCurtidaDaEmpresa(Integer idEmpresa, Integer idCandidato)
    void AtualizarCurtidaComIdVaga(Integer idVaga, Integer idEmpresa, Integer idCandidato )

    void curtirCandidato(Integer idCandidato, Integer idEmpresa)
    Integer verificaCurtidaDoCandidato( Integer idCandidato)
    void AtualizarCurtidaComIdEmpresa(Integer idVaga, Integer idEmpresa, Integer idCandidato )

    List<EmpresaDTO> listarEmpresasQueCurtiramCandidato(Integer idCandidato)
    List<CandidatoQueCurtiuVagaDTO> listarCandidatosQueCurtiramVaga(Integer idVaga)
}