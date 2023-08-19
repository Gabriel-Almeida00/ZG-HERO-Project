package Interface

import entity.Empresa
import entity.Vaga

interface IEmpresa {
    List<Empresa> listarEmpresas();
    void cadastrarEmpresa(Empresa empresa);
    void criarVaga(String nomeEmpresa, Vaga vaga)
}