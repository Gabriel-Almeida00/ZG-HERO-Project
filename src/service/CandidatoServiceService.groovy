package service

import Exception.CandidatosNotFoundException
import Exception.CompetenciaNotFoundException
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


class CandidatoServiceService implements ICandidatoService {

    private final ICandidatoDao candidatoDao;
    private final ICandidatoCompetenciaDao candidatoCompetenciaDao;
    private final IExperienciaDao experienciaDao;
    private final IFormacaoDao formacaoDao

    CandidatoServiceService(
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
        return candidatoDao.listarCandidatos();
    }

    Candidato obterCandidatoPorId(Integer id) {
        Candidato candidato = candidatoDao.obterCandidatoPorId(id);
        if (candidato == null) {
            throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + id);
        }
        return candidato;
    }

    void cadastrarCandidato(Candidato candidato) {
        candidatoDao.adicionarCandidato(candidato);
    }

    void atualizarCandidato(Candidato candidato) {
        if(candidato.getId() == null){
            throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + candidato.getId());
        }
        candidatoDao.atualizarCandidato(candidato);
    }

    void deletarCandidato(Integer candidatoId) {
        Candidato candidato = candidatoDao.obterCandidatoPorId(candidatoId);
        if (candidato == null) {
            throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + candidatoId);
        }
       candidatoDao.deletarCandidato(candidatoId);
    }

    List<Competencias> listarCompetenciasCandidato(Integer idCandidato) {
        Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato);
        if(candidato == null){
            throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
        }
        return candidatoCompetenciaDao.listarCompetenciasPorCandidato(idCandidato);
    }

    void adicionarCompetenciaCandidato(Integer idCandidato, Competencias competencias) {
        Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato);
        if(candidato == null){
            throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
        }
        Integer idCompetencia = competencias.getId();
        candidatoCompetenciaDao.adicionarCandidatoCompetencia(idCandidato, idCompetencia);
    }

    void atualizarNivelCompetencia(Integer idCandidato, Integer idCompetencia, String novoNivel) {
        Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato);
        if(candidato == null){
            throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
        }
        candidatoCompetenciaDao.atualizarNivelCompetenciaCandidato(idCandidato, idCompetencia, novoNivel);
    }

    void excluirCompetenciaCandidato(Integer idCandidato, Integer idCompetencia) {
        Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato);
        Competencias competencias = candidatoCompetenciaDao.buscarCompetenciaPorId(idCompetencia);
        if(candidato == null){
            throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
        }
        if(competencias == null){
            throw new CompetenciaNotFoundException("Competencia não encontrada com ID : "+ idCompetencia)
        }
        candidatoCompetenciaDao.excluirCompetenciaCandidato(idCandidato, idCompetencia);
    }


    void adicionarExperiencia(Experiencia experiencia) {
        experienciaDao.adicionarExperiencia(experiencia);
    }


    void atualizarExperiencia(Experiencia experiencia) {
        if(experiencia.getId() == null){
            throw new ExperienciaNotFoundException("Experiencia não encontrada com ID: " + experiencia.getId())
        }
        experienciaDao.atualizarExperiencia(experiencia);
    }


    List<Experiencia> listarExperienciasPorCandidato(Integer idCandidato) {
        Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato);
        if(candidato == null){
            throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
        }
        return experienciaDao.listarExperienciasPorCandidato(idCandidato);
    }


    void excluirExperiencia(Integer idExperiencia) {
        Experiencia experiencia = experienciaDao.buscarExperienciaPorId(idExperiencia)
        if(experiencia == null){
            throw new ExperienciaNotFoundException("Experiencia não encontrada com ID: " + idExperiencia)
        }
        experienciaDao.excluirExperiencia(idExperiencia);
    }

    void adicionarFormacao(Formacao formacao) {
        formacaoDao.adicionarFormacao(formacao);
    }


    void atualizarFormacao(Formacao formacao) {
        formacaoDao.atualizarFormacao(formacao);
    }


    void excluirFormacao(Integer idFormacao) {
        Formacao formacao = formacaoDao.buscarFormacaoPorId(idFormacao)
        if(formacao == null){
            throw new FormacaoNotFoundException("Formação não encontrado com ID: " +idFormacao )
        }
        formacaoDao.excluirFormacao(idFormacao);
    }


    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato) {
        Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato);
        if(candidato == null){
            throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
        }
        return formacaoDao.listarFormacoesPorCandidato(idCandidato);
    }
}