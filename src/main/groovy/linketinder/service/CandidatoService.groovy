package linketinder.service


import linketinder.Exception.*
import linketinder.dao.candidato.ICandidatoCompetenciaDao
import linketinder.dao.candidato.ICandidatoDao
import linketinder.dao.candidato.IExperienciaDao
import linketinder.dao.candidato.IFormacaoDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.entity.*
import linketinder.entity.dto.CandidatoDTO
import linketinder.entity.dto.CompetenciaDTO
import linketinder.entity.dto.EmpresaDTO

import java.sql.SQLException

class CandidatoService implements ICandidatoService {

    private final ICandidatoDao candidatoDao
    private final ICandidatoCompetenciaDao candidatoCompetenciaDao
    private final IExperienciaDao experienciaDao
    private final IFormacaoDao formacaoDao
    private final IVagaDao vagaDao
    private final ICurtidaDao curtidaDao

    CandidatoService(
            ICandidatoDao candidatoDao,
            ICandidatoCompetenciaDao candidatoCompetenciaDao,
            IExperienciaDao experienciaDao,
            IFormacaoDao formacaoDao,
            IVagaDao vagaDao,
            ICurtidaDao curtidaDao
    ) {
        this.candidatoDao = candidatoDao
        this.candidatoCompetenciaDao = candidatoCompetenciaDao
        this.experienciaDao = experienciaDao
        this.formacaoDao = formacaoDao
        this.vagaDao = vagaDao
        this.curtidaDao = curtidaDao
    }

    List<CandidatoDTO> listarCandidatos() {
        try {
            return candidatoDao.listarCandidatos()
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    Candidato obterCandidatoPorId(Integer id) {
        try {
            Candidato candidato = candidatoDao.obterCandidatoPorId(id)
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + id)
            }
            return candidato
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    void cadastrarCandidato(Candidato candidato) {
        try {
            candidatoDao.adicionarCandidato(candidato)
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    void atualizarCandidato(Candidato candidato) {
        try {
            if (candidato.getId() == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + candidato.getId())
            }
            candidatoDao.atualizarCandidato(candidato)
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    void deletarCandidato(Integer candidatoId) {
        try {
            Candidato candidato = candidatoDao.obterCandidatoPorId(candidatoId)
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + candidatoId)
            }
            candidatoDao.deletarCandidato(candidatoId)
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato) {
        try {
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato)
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            return candidatoCompetenciaDao.listarCompetenciasPorCandidato(idCandidato)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    Competencias buscarCompetenciaPorId(Integer idCompetencia) {
        try {
            return candidatoCompetenciaDao.buscarCompetenciaPorId(idCompetencia)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    @Override
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia) {
        try {
            candidatoCompetenciaDao.adicionarCandidatoCompetencia(candidatoCompetencia)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia) {
        try {
            candidatoCompetenciaDao.atualizarNivelCompetenciaCandidato(candidatoCompetencia)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void excluirCompetenciaCandidato(Integer id) {
        try {
            candidatoCompetenciaDao.excluirCompetenciaCandidato(id)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    void adicionarExperiencia(Experiencia experiencia) {
        try {
            experienciaDao.adicionarExperiencia(experiencia)
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    void atualizarExperiencia(Experiencia experiencia) {
        try {
            if (experiencia.getId() == null) {
                throw new ExperienciaNotFoundException("Experiencia não encontrada com ID: " + experiencia.getId())
            }
            experienciaDao.atualizarExperiencia(experiencia)
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    List<Experiencia> listarExperienciasPorCandidato(Integer idCandidato) {
        try {
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato)
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            return experienciaDao.listarExperienciasPorCandidato(idCandidato)
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    void excluirExperiencia(Integer idExperiencia) {
        try {
            Experiencia experiencia = experienciaDao.buscarExperienciaPorId(idExperiencia)
            if (experiencia == null) {
                throw new ExperienciaNotFoundException("Experiencia não encontrada com ID: " + idExperiencia)
            }
            experienciaDao.excluirExperiencia(idExperiencia)
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    void adicionarFormacao(Formacao formacao) {
        try {
            formacaoDao.adicionarFormacao(formacao)
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    void atualizarFormacao(Formacao formacao) {
        try {
            formacaoDao.atualizarFormacao(formacao)
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    void excluirFormacao(Integer idFormacao) {
        try {
            Formacao formacao = formacaoDao.buscarFormacaoPorId(idFormacao)
            if (formacao == null) {
                throw new FormacaoNotFoundException("Formação não encontrado com ID: " + idFormacao)
            }
            formacaoDao.excluirFormacao(idFormacao)
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato) {
        try {
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato)
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            return formacaoDao.listarFormacoesPorCandidato(idCandidato)
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void curtirVaga(Integer idCandidato, Integer idVaga) {
        try {
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato)
            Vaga vaga = vagaDao.buscarVagaPorId(idVaga)

            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            if (vaga == null) {
                throw new VagaNotFoundException("Vaga não encontrada com ID: " + idVaga)
            }

            Integer idEmpresa = vagaDao.obterIdEmpresaPorIdVaga(idVaga)
            Integer empresaQueCurtiu = curtidaDao.verificaCurtidaDaEmpresa(idEmpresa, idCandidato)

            if(empresaQueCurtiu == null){
                curtidaDao.curtirVaga(idCandidato, idVaga)
            }
            else {
                curtidaDao.AtualizarCurtidaComIdVaga(idVaga, idEmpresa, idCandidato)
            }

        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    List<EmpresaDTO> listarEmpresasQueCurtiramCandidato(Integer idCandidato){
        try{
            Candidato id = candidatoDao.obterCandidatoPorId(idCandidato)

            if(id == null){
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            curtidaDao.listarEmpresasQueCurtiramCandidato(idCandidato)
        }catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }
}