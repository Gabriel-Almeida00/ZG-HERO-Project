package linketinder.service.match


import linketinder.dao.match.IMatchDao
import linketinder.model.dto.MatchCandidatoDTO
import linketinder.model.dto.MatchEmpresaDTO

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

