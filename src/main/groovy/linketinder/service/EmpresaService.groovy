package linketinder.service


import linketinder.Exception.CandidatosNotFoundException
import linketinder.Exception.DataBaseException
import linketinder.Exception.EmpresasNotFoundException
import linketinder.dao.candidato.ICandidatoDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.empresa.IEmpresaDao
import linketinder.entity.Candidato
import linketinder.entity.Empresa

import java.sql.SQLException

class EmpresaService implements IEmpresa {

    private final IEmpresaDao empresaDao
    private final ICandidatoDao candidatoDao
    private final ICurtidaDao curtidaDao

    EmpresaService(IEmpresaDao empresaDao, ICandidatoDao candidatoDao, ICurtidaDao curtidaDao) {
        this.empresaDao = empresaDao
        this.curtidaDao = curtidaDao
        this.candidatoDao = candidatoDao
    }

    @Override
    List<Empresa> listarTodasEmpresas() {
        try {
            return empresaDao.listarTodasEmpresas()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    Empresa obterEmpresaPorId(Integer id) {
        try {
            return empresaDao.buscarEmpresaPorId(id)
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void adicionarEmpresa(Empresa empresa) {
        try {
            empresaDao.adicionarEmpresa(empresa)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void atualizarEmpresa(Empresa empresa) {
        try {
            Empresa idEmpresa = empresaDao.buscarEmpresaPorId(empresa.getId())
            if (idEmpresa == null) {
                throw new EmpresasNotFoundException("Empresa não encontrada com ID: " + empresa.getId())
            }
            empresaDao.atualizarEmpresa(empresa)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void excluirEmpresa(Integer id) {
        try {
            Empresa idEmpresa = empresaDao.buscarEmpresaPorId(id)
            if (idEmpresa == null) {
                throw new EmpresasNotFoundException("Empresa não encontrada com ID: " + id)
            }
            empresaDao.excluirEmpresa(id)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void curtirCandidato(Integer idCandidato, Integer idEmpresa) {
        try {
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato)
            Empresa empresa = empresaDao.buscarEmpresaPorId(idEmpresa)

            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            if (empresa == null) {
                throw new EmpresasNotFoundException("Empresa não encontrada com ID: " + idEmpresa)
            }

            Integer idVaga = curtidaDao.verificaCurtidaDoCandidato(idCandidato)
            if (idVaga == null) {
                curtidaDao.curtirCandidato(idCandidato, idEmpresa)
            } else {
                curtidaDao.AtualizarCurtidaComIdEmpresa(idVaga, idEmpresa, idCandidato)
            }

        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }
}
