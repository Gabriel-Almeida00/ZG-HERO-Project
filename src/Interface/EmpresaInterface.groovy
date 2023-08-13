package Interface

import entity.Empresa
import entity.Vaga

interface EmpresaInterface {
    List<Empresa> listarEmpresas();
    void cadastrarEmpresa(Empresa empresa);
    void criarVaga(String nomeEmpresa, Vaga vaga)
}