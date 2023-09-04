package service

import Exception.CandidatosNotFoundException
import Exception.DataBaseException
import Exception.EmpresasNotFoundException
import Exception.VagaNotFoundException
import dao.candidato.ICandidatoDao
import dao.curtida.ICurtidaDao
import dao.empresa.IEmpresaDao
import entity.Candidato
import entity.Empresa

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
            return empresaDao.listarTodasEmpresas();
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    Empresa obterEmpresaPorId(Integer id) {
        try {
            return empresaDao.buscarEmpresaPorId(id);
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    void adicionarEmpresa(Empresa empresa) {
        try {
            empresaDao.adicionarEmpresa(empresa);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    void atualizarEmpresa(Empresa empresa) {
        try {
            Empresa idEmpresa = empresaDao.buscarEmpresaPorId(empresa.getId())
            if(idEmpresa == null){
                throw new EmpresasNotFoundException("Empresa não encontrada com ID: " + empresa.getId())
            }
            empresaDao.atualizarEmpresa(empresa);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    void excluirEmpresa(Integer id) {
        try {
            Empresa idEmpresa = empresaDao.buscarEmpresaPorId(id)
            if(idEmpresa == null){
                throw new EmpresasNotFoundException("Empresa não encontrada com ID: " + id)
            }
            empresaDao.excluirEmpresa(id);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e)
        }
    }

    @Override
    void curtirCandidato(Integer idCandidato, Integer idEmpresa) {
       try{
           Candidato candidato = candidatoDao.obterCandidatoPorId(idCandidato)
           Empresa empresa = empresaDao.buscarEmpresaPorId(idEmpresa)

           if (candidato == null) {
               throw new CandidatosNotFoundException("Candidato não encontrado com ID: " + idCandidato)
           }
           if(empresa == null){
               throw new EmpresasNotFoundException("Empresa não encontrada com ID: " + idEmpresa)
           }

           curtidaDao.curtirCandidato(idCandidato, idEmpresa)

       }catch (SQLException e){
           throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e)
       }
    }
}
