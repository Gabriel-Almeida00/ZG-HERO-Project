package linketinder.dao.empresa


import linketinder.model.Empresa

interface IEmpresaDao {
    void atualizarEmpresa(Empresa empresa);
    void excluirEmpresa(Integer idEmpresa);
    Empresa buscarEmpresaPorId(Integer idEmpresa);
    List<Empresa> listarTodasEmpresas();
}