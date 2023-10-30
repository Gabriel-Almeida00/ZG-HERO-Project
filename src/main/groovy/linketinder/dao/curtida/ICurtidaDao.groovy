package linketinder.dao.curtida

import linketinder.model.CandidatoCurtido
import linketinder.model.VagaCurtida
import linketinder.model.dto.CandidatoQueCurtiuVagaDTO
import linketinder.model.dto.EmpresaDTO

interface ICurtidaDao {
    void curtirVaga(VagaCurtida vagaCurtida)
    Integer verificaCurtidaDaEmpresa(Integer idEmpresa, Integer idCandidato)
    void AtualizarCurtidaComIdVaga(Integer idVaga, Integer idEmpresa, Integer idCandidato )

    void curtirCandidato(CandidatoCurtido candidatoCurtido)
    Integer verificaCurtidaDoCandidato( Integer idCandidato)
    void AtualizarCurtidaComIdEmpresa(Integer idVaga, Integer idEmpresa, Integer idCandidato )

    List<EmpresaDTO> listarEmpresasQueCurtiramCandidato(Integer idCandidato)
    List<CandidatoQueCurtiuVagaDTO> listarCandidatosQueCurtiramVaga(Integer idVaga)
}