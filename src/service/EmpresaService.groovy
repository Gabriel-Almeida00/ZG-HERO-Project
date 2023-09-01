package service

import Exception.DataBaseException
import Exception.EmpresasNotFoundException
import dao.empresa.IEmpresaDao
import entity.Empresa

import java.sql.SQLException

class EmpresaService implements IEmpresa {

    private final IEmpresaDao empresaDao

    EmpresaService(IEmpresaDao empresaDao) {
        this.empresaDao = empresaDao
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
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }
}
