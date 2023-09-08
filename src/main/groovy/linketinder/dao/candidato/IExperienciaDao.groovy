package linketinder.dao.candidato


import linketinder.entity.Experiencia

import java.sql.SQLException

interface IExperienciaDao {

    void adicionarExperiencia(Experiencia experiencia) throws SQLException;
    void atualizarExperiencia(Experiencia experiencia) throws SQLException;
    List<Experiencia> listarExperienciasPorCandidato(Integer idCandidato) throws SQLException;
    void excluirExperiencia(Integer idExperiencia) throws SQLException;
    Experiencia buscarExperienciaPorId(Integer idExperiencia);
}
