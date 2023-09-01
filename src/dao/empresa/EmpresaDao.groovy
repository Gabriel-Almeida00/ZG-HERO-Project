package dao.empresa

import db.IDatabaseConnection
import entity.Empresa

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class EmpresaDao implements IEmpresaDao {

    private final IDatabaseConnection databaseConnection;

    EmpresaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    List<Empresa> listarTodasEmpresas() throws SQLException {
        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT * FROM empresas";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String email = resultSet.getString("email");
                String descricao = resultSet.getString("descricao");
                String pais = resultSet.getString("pais");
                String cep = resultSet.getString("cep");
                String senha = resultSet.getString("senha");

                Empresa empresa = new Empresa( nome, cnpj, email, descricao, pais, cep, senha);
                empresa.setId(id)
                empresas.add(empresa);
            }
        }

        return empresas;
    }

    @Override
    void adicionarEmpresa(Empresa empresa) throws SQLException {
        String sql = "INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setString(1, empresa.getNome());
            statement.setString(2, empresa.getCnpj());
            statement.setString(3, empresa.getEmail());
            statement.setString(4, empresa.getDescricao());
            statement.setString(5, empresa.getPais());
            statement.setString(6, empresa.getCep());
            statement.setString(7, empresa.getSenha());

            statement.executeUpdate();
        }
    }

    @Override
    void atualizarEmpresa(Empresa empresa) throws SQLException {
        String sql = "UPDATE empresas SET nome = ?, cnpj = ?, email = ?, descricao = ?, pais = ?, cep = ?, senha = ? " +
                "WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setString(1, empresa.getNome());
            statement.setString(2, empresa.getCnpj());
            statement.setString(3, empresa.getEmail());
            statement.setString(4, empresa.getDescricao());
            statement.setString(5, empresa.getPais());
            statement.setString(6, empresa.getCep());
            statement.setString(7, empresa.getSenha());
            statement.setInt(8, empresa.getId());

            statement.executeUpdate();
        }
    }

    @Override
    void excluirEmpresa(Integer idEmpresa) throws SQLException {
        String sql = "DELETE FROM empresas WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idEmpresa);
            statement.executeUpdate();
        }
    }

    @Override
    Empresa buscarEmpresaPorId(Integer idEmpresa) {
        String sql = "SELECT * FROM empresas WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idEmpresa);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id")
                    String nome = resultSet.getString("nome");
                    String cnpj = resultSet.getString("cnpj");
                    String email = resultSet.getString("email");
                    String descricao = resultSet.getString("descricao");
                    String pais = resultSet.getString("pais");
                    String cep = resultSet.getString("cep");
                    String senha = resultSet.getString("senha");

                    Empresa empresa = new Empresa(nome, cnpj, email, descricao, pais, cep, senha)
                    empresa.setId(id)

                    return empresa;
                } else {
                    return null;
                }
            }
        }
    }
}
