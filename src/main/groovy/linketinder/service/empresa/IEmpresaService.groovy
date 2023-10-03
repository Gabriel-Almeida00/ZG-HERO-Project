package linketinder.service.empresa


import linketinder.entity.Empresa

interface IEmpresaService {
    List<Empresa> listarTodasEmpresas()
    Empresa obterEmpresaPorId(Integer id)
    void adicionarEmpresa(Empresa empresa)
    void atualizarEmpresa(Empresa empresa)
    void excluirEmpresa(Integer id)
}