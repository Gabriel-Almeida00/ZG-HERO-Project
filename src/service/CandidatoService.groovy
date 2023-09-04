package service

import Exception.CandidatosNotFoundException
import Exception.CompetenciaNotFoundException
import Exception.DataBaseException
import Exception.ExperienciaNotFoundException
import Exception.FormacaoNotFoundException
import Exception.VagaNotFoundException
import dao.candidato.ICandidatoCompetenciaDao
import dao.candidato.ICandidatoDao
import dao.candidato.IExperienciaDao
import dao.candidato.IFormacaoDao
import dao.curtida.ICurtidaDao
import dao.vaga.IVagaDao
import entity.Candidato
import entity.CandidatoCompetencia
import entity.Competencias
import entity.Vaga
import entity.dto.CandidatoDTO
import entity.Experiencia
import entity.Formacao
import entity.dto.CompetenciaDTO

import java.sql.SQLException


class CandidatoService implements ICandidatoService {

    private final ICandidatoDao candidatoDao
    private final ICandidatoCompetenciaDao candidatoCompetenciaDao
    private final IExperienciaDao experienciaDao
    private final IFormacaoDao formacaoDao
    private  final IVagaDao vagaDao
    private final ICurtidaDao curtidaDao

    CandidatoService(
            ICandidatoDao candidatoDao,
            ICandidatoCompetenciaDao candidatoCompetenciaDao,
            IExperienciaDao experienciaDao,
            IFormacaoDao formacaoDao,
            IVagaDao vagaDao,
            ICurtidaDao curtidaDao
    ) {
        this.candidatoDao = candidatoDao;
        this.candidatoCompetenciaDao = candidatoCompetenciaDao;
        this.experienciaDao = experienciaDao
        this.formacaoDao = formacaoDao
        this.vagaDao = vagaDao
        this.curtidaDao = curtidaDao
    }

    List<CandidatoDTO> listarCandidatos() {
        try {
            return candidatoDao.listarCandidatos();
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    Candidato obterCandidatoPorId(Integer id) {
        try {
            Candidato candidato = candidatoDao.obterCandidatoPorId(id);
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + id);
            }
            return candidato;
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    void cadastrarCandidato(Candidato candidato) {
        try {
            candidatoDao.adicionarCandidato(candidato);
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    void atualizarCandidato(Candidato candidato) {
        try {
            if (candidato.getId() == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + candidato.getId());
            }
            candidatoDao.atualizarCandidato(candidato);
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    void deletarCandidato(Integer candidatoId) {
        try {
            Candidato candidato = candidatoDao.obterCandidatoPorId(candidatoId);
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + candidatoId);
            }
            candidatoDao.deletarCandidato(candidatoId);
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato) {
        try {
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato)
            if(candidato == null){
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato);
            }
            return candidatoCompetenciaDao.listarCompetenciasPorCandidato(idCandidato);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public Competencias buscarCompetenciaPorId(Integer idCompetencia) {
        try {
            return candidatoCompetenciaDao.buscarCompetenciaPorId(idCompetencia);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }


    @Override
    public void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia) {
        try {
            candidatoCompetenciaDao.adicionarCandidatoCompetencia(candidatoCompetencia);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia) {
        try {
            candidatoCompetenciaDao.atualizarNivelCompetenciaCandidato(candidatoCompetencia);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void excluirCompetenciaCandidato(Integer id) {
        try {
            candidatoCompetenciaDao.excluirCompetenciaCandidato(id);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }


    void adicionarExperiencia(Experiencia experiencia) {
        try{
            experienciaDao.adicionarExperiencia(experiencia);
        }
        catch (SQLException e){
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }


    void atualizarExperiencia(Experiencia experiencia) {
        try{
            if (experiencia.getId() == null) {
                throw new ExperienciaNotFoundException("Experiencia não encontrada com ID: " + experiencia.getId())
            }
            experienciaDao.atualizarExperiencia(experiencia);
        }
        catch (SQLException e){
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }


    List<Experiencia> listarExperienciasPorCandidato(Integer idCandidato) {
        try{
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato);
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            return experienciaDao.listarExperienciasPorCandidato(idCandidato);
        }
        catch (SQLException e){
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }


    void excluirExperiencia(Integer idExperiencia) {
        try {
            Experiencia experiencia = experienciaDao.buscarExperienciaPorId(idExperiencia)
            if (experiencia == null) {
                throw new ExperienciaNotFoundException("Experiencia não encontrada com ID: " + idExperiencia)
            }
            experienciaDao.excluirExperiencia(idExperiencia);
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    void adicionarFormacao(Formacao formacao) {
        try {
            formacaoDao.adicionarFormacao(formacao);
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }


    void atualizarFormacao(Formacao formacao) {
        try {
            formacaoDao.atualizarFormacao(formacao);
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }


    void excluirFormacao(Integer idFormacao) {
        try {
            Formacao formacao = formacaoDao.buscarFormacaoPorId(idFormacao)
            if (formacao == null) {
                throw new FormacaoNotFoundException("Formação não encontrado com ID: " + idFormacao)
            }
            formacaoDao.excluirFormacao(idFormacao);
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }


    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato) {
        try {
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato);
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            return formacaoDao.listarFormacoesPorCandidato(idCandidato);
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e)
        }
    }

    @Override
    void curtirVaga(Integer idCandidato, Integer idVaga) {
        try{
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato)
            Vaga vaga = vagaDao.buscarVagaPorId(idVaga)

            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            if(vaga == null){
                throw new VagaNotFoundException("Vaga não encontrada com ID: " + idVaga)
            }

            curtidaDao.curtirVaga(idCandidato, idVaga)

        } catch (SQLException e){
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e)
        }
    }
}