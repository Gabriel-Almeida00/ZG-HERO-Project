package service

import Interface.ICompetenciaService
import db.IDatabaseConnection
import entity.Competencias

class CompetenciaService implements ICompetenciaService {
    private IDatabaseConnection databaseConnection;

    public CompetenciaService(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Competencias obterCompetenciaPorId(Integer id) {
        // Implementação para obter uma competência por ID
    }

    @Override
    public List<Competencias> listarCompetencias() {
        // Implementação para listar todas as competências
    }

    @Override
    public void adicionarCompetencia(Competencias competencia) {
        // Implementação para adicionar uma nova competência
    }

    @Override
    public void atualizarCompetencia(Integer id, Competencias competencia) {
        // Implementação para atualizar uma competência por ID
    }

    @Override
    public void deletarCompetencia(Integer id) {
        // Implementação para deletar uma competência por ID
    }
}
