package linketinder.service


import linketinder.entity.Empresa

interface IEmpresa {
    List<Empresa> listarTodasEmpresas()
    Empresa obterEmpresaPorId(Integer id)
    void adicionarEmpresa(Empresa empresa)
    void atualizarEmpresa(Empresa empresa)
    void excluirEmpresa(Integer id)

    void curtirCandidato(Integer idCandidato, Integer idEmpresa)
}