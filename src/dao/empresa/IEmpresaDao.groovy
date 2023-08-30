package dao.empresa

import entity.Empresa

interface IEmpresaDao {
    void adicionarEmpresa(Empresa empresa);
    void atualizarEmpresa(Empresa empresa);
    void excluirEmpresa(Integer idEmpresa);
    Empresa buscarEmpresaPorId(Integer idEmpresa);
    List<Empresa> listarTodasEmpresas();
}