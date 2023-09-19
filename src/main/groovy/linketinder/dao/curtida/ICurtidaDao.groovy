package linketinder.dao.curtida

import linketinder.entity.dto.EmpresaDTO

interface ICurtidaDao {
    void curtirVaga(Integer idCandidato, Integer idVaga)
    void curtirCandidato(Integer idCandidato, Integer idEmpresa)
    List< EmpresaDTO> listarEmpresasQueCurtiramCandidato(Integer idCandidato)
}