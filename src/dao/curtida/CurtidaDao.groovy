package dao.curtida

import db.IDatabaseConnection

import java.sql.PreparedStatement

class CurtidaDao implements ICurtidaDao {

    private IDatabaseConnection databaseConnection

    CurtidaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    @Override
    void curtirVaga(Integer idCandidato, Integer idVaga) {
        String sql = "INSERT INTO vagas_curtidas (idCandidato, idVaga) VALUES (?, ?)"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato)
            statement.setInt(2, idVaga)
            statement.executeUpdate()
        }
    }

    @Override
    void curtirCandidato(Integer idCandidato, Integer idEmpresa) {
        String sql = "INSERT INTO candidatos_curtidos (idCandidato, idEmpresa) VALUES (?, ?)"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato)
            statement.setInt(2, idEmpresa)
            statement.executeUpdate()
        }
    }
}
