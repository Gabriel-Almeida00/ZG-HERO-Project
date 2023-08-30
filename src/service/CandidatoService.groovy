package service

import Exception.CandidatosNotFoundException
import Exception.CompetenciaNotFoundException
import Exception.DataBaseException
import Exception.ExperienciaNotFoundException
import Exception.FormacaoNotFoundException
import dao.candidato.ICandidatoCompetenciaDao
import dao.candidato.ICandidatoDao
import dao.candidato.IExperienciaDao
import dao.candidato.IFormacaoDao
import entity.Candidato
import entity.Competencias
import entity.Experiencia
import entity.Formacao

import java.sql.SQLException


class CandidatoService implements ICandidatoService {

    private final ICandidatoDao candidatoDao;
    private final ICandidatoCompetenciaDao candidatoCompetenciaDao;
    private final IExperienciaDao experienciaDao;
    private final IFormacaoDao formacaoDao

    CandidatoService(
            ICandidatoDao candidatoDao,
            ICandidatoCompetenciaDao candidatoCompetenciaDao,
            IExperienciaDao experienciaDao,
            IFormacaoDao formacaoDao
    ) {
        this.candidatoDao = candidatoDao;
        this.candidatoCompetenciaDao = candidatoCompetenciaDao;
        this.experienciaDao = experienciaDao
        this.formacaoDao = formacaoDao
    }

    List<Candidato> listarCandidatos() {
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

    List<Competencias> listarCompetenciasCandidato(Integer idCandidato) {
        try {
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato);
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            return candidatoCompetenciaDao.listarCompetenciasPorCandidato(idCandidato);
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    void adicionarCompetenciaCandidato(Integer idCandidato, Competencias competencias) {
        try{
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato);
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            Integer idCompetencia = competencias.getId();
            candidatoCompetenciaDao.adicionarCandidatoCompetencia(idCandidato, idCompetencia);
        }
        catch (SQLException e){
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    void atualizarNivelCompetencia(Integer idCandidato, Integer idCompetencia, String novoNivel) {
        try{
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato);
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            candidatoCompetenciaDao.atualizarNivelCompetenciaCandidato(idCandidato, idCompetencia, novoNivel);
        }
        catch (SQLException e){
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    void excluirCompetenciaCandidato(Integer idCandidato, Integer idCompetencia) {
        try{
            Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato);
            Competencias competencias = candidatoCompetenciaDao.buscarCompetenciaPorId(idCompetencia);
            if (candidato == null) {
                throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
            }
            if (competencias == null) {
                throw new CompetenciaNotFoundException("Competencia não encontrada com ID : " + idCompetencia)
            }
            candidatoCompetenciaDao.excluirCompetenciaCandidato(idCandidato, idCompetencia);
        }
        catch (SQLException e){
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
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }
}