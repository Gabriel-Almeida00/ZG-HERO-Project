package dao.vaga

import db.IDatabaseConnection
import entity.Vaga

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagaDao implements IVagaDao{

    private final IDatabaseConnection databaseConnection;

    VagaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    List<Vaga> listarTodasVagas() throws SQLException {
        List<Vaga> vagas = new ArrayList<>();

        String sql = "SELECT id, idEmpresa, nome, descricao, cidade, formacaoMinima, experienciaMinima FROM vagas";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer idEmpresa = resultSet.getInt("idEmpresa");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String cidade = resultSet.getString("cidade");
                String formacaoMinima = resultSet.getString("formacaoMinima");
                String experienciaMinima = resultSet.getString("experienciaMinima");

                Vaga vaga = new Vaga(idEmpresa, nome, descricao, cidade, formacaoMinima, experienciaMinima);
                vaga.setId(id);

                vagas.add(vaga);
            }
        }

        return vagas;
    }

    @Override
    Vaga buscarVagaPorId(Integer idVaga) throws SQLException {
        String sql = "SELECT idEmpresa, nome, descricao, cidade, formacaoMinima, experienciaMinima FROM vagas WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVaga);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer idEmpresa = resultSet.getInt("idEmpresa");
                    String nome = resultSet.getString("nome");
                    String descricao = resultSet.getString("descricao");
                    String cidade = resultSet.getString("cidade");
                    String formacaoMinima = resultSet.getString("formacaoMinima");
                    String experienciaMinima = resultSet.getString("experienciaMinima");

                    Vaga vaga = new Vaga(idEmpresa, nome, descricao, cidade, formacaoMinima, experienciaMinima);
                    vaga.setId(idVaga);

                    return vaga;
                }
            }
        }
        return null;
    }


    @Override
    void adicionarVaga(Vaga vaga) throws SQLException {
        String sql = "INSERT INTO vagas (idEmpresa, nome, descricao, cidade, formacaoMinima, experienciaMinina) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, vaga.getIdEmpresa());
            statement.setString(2, vaga.getNome());
            statement.setString(3, vaga.getDescricao());
            statement.setString(4, vaga.getCidade());
            statement.setString(5, vaga.getFormacaoMinima());
            statement.setString(6, vaga.getExperienciaMinima());

            statement.executeUpdate();
        }
    }


    @Override
    void atualizarVaga(Vaga vaga) throws SQLException {
        String sql = "UPDATE vagas SET idEmpresa = ?, nome = ?, descricao = ?, cidade = ?, formacaoMinima = ?, experienciaMinina = ? " +
                "WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, vaga.getIdEmpresa());
            statement.setString(2, vaga.getNome());
            statement.setString(3, vaga.getDescricao());
            statement.setString(4, vaga.getCidade());
            statement.setString(5, vaga.getFormacaoMinima());
            statement.setString(6, vaga.getExperienciaMinima());
            statement.setInt(7, vaga.getId());

            statement.executeUpdate();
        }
    }

    @Override
    void excluirVaga(Integer idVaga) throws SQLException {
        String sql = "DELETE FROM vagas WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVaga);
            statement.executeUpdate();
        }
    }
}
