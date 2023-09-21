package linketinder.service


import linketinder.dao.match.IMatchDao
import linketinder.entity.dto.MatchCandidatoDTO
import linketinder.entity.dto.MatchEmpresaDTO

class MatchService implements IMatchService {

    private IMatchDao matchDao

    MatchService(IMatchDao matchDao) {
        this.matchDao = matchDao
    }

    @Override
    List<MatchCandidatoDTO> encontrarMatchesPelaEmpresa(Integer idEmpresa) {
        matchDao.encontrarMatchesPelaEmpresa(idEmpresa)
    }

    @Override
    List<MatchEmpresaDTO> encontrarMatchesPeloCandidato(Integer idCandidato) {
        matchDao.encontrarMatchesPeloCandidato(idCandidato)
    }
}

