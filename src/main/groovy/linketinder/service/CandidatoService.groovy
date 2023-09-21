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
        return candidatoDao.listarCandidatos()
    }

    Candidato obterCandidatoPorId(Integer id) {
        Candidato candidato = candidatoDao.obterCandidatoPorId(id)
        return candidato
    }

    void cadastrarCandidato(Candidato candidato) {
        candidatoDao.adicionarCandidato(candidato)
    }

    void atualizarCandidato(Candidato candidato) {
        candidatoDao.atualizarCandidato(candidato)
    }

    void deletarCandidato(Integer candidatoId) {
        candidatoDao.deletarCandidato(candidatoId)
    }

    @Override
    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato) {
        return candidatoCompetenciaDao.listarCompetenciasPorCandidato(idCandidato)
    }


    @Override
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia) {
        candidatoCompetenciaDao.adicionarCandidatoCompetencia(candidatoCompetencia)
    }

    @Override
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia) {
        candidatoCompetenciaDao.atualizarNivelCompetenciaCandidato(candidatoCompetencia)
    }

    @Override
    void excluirCompetenciaCandidato(Integer id) {
        candidatoCompetenciaDao.excluirCompetenciaCandidato(id)
    }


    void adicionarExperiencia(Experiencia experiencia) {
        experienciaDao.adicionarExperiencia(experiencia)
    }


    void atualizarExperiencia(Experiencia experiencia) {
        experienciaDao.atualizarExperiencia(experiencia)
    }


    List<Experiencia> listarExperienciasPorCandidato(Integer idCandidato) {
        return experienciaDao.listarExperienciasPorCandidato(idCandidato)
    }


    void excluirExperiencia(Integer idExperiencia) {
        experienciaDao.excluirExperiencia(idExperiencia)
    }

    void adicionarFormacao(Formacao formacao) {
        formacaoDao.adicionarFormacao(formacao)
    }


    void atualizarFormacao(Formacao formacao) {
        formacaoDao.atualizarFormacao(formacao)
    }


    void excluirFormacao(Integer idFormacao) {
        formacaoDao.excluirFormacao(idFormacao)
    }


    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato) {
        return formacaoDao.listarFormacoesPorCandidato(idCandidato)
    }

    @Override
    void curtirVaga(Integer idCandidato, Integer idVaga) {
        Integer idEmpresa = vagaDao.obterIdEmpresaPorIdVaga(idVaga)
        Integer empresaQueCurtiu = curtidaDao.verificaCurtidaDaEmpresa(idEmpresa, idCandidato)

        if (empresaQueCurtiu == null) {
            curtidaDao.curtirVaga(idCandidato, idVaga)
        } else {
            curtidaDao.AtualizarCurtidaComIdVaga(idVaga, idEmpresa, idCandidato)
        }
    }

    @Override
    List<EmpresaDTO> listarEmpresasQueCurtiramCandidato(Integer idCandidato) {
        curtidaDao.listarEmpresasQueCurtiramCandidato(idCandidato)
    }
}