package linketinder.dao.curtida

interface ICurtidaDao {
    void curtirVaga(Integer idCandidato, Integer idVaga)
    void curtirCandidato(Integer idCandidato, Integer idEmpresa)
}