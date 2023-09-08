package linketinder.dao.empresa


import linketinder.entity.Empresa

interface IEmpresaDao {
    void adicionarEmpresa(Empresa empresa);
    void atualizarEmpresa(Empresa empresa);
    void excluirEmpresa(Integer idEmpresa);
    Empresa buscarEmpresaPorId(Integer idEmpresa);
    List<Empresa> listarTodasEmpresas();
}