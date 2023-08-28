package test.service

import db.IDatabaseConnection

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import static org.mockito.Mockito.*;


class DatabaseConnectionTest implements IDatabaseConnection{

    @Override
    Connection getConnection() {
        Connection connectionMock = mock(Connection.class);
        return connectionMock;
    }

    @Override
     PreparedStatement prepareStatement(String sql) throws SQLException {
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        return preparedStatementMock;
    }

    @Override
    ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        ResultSet resultSetMock = mock(ResultSet.class);
        return resultSetMock;
    }
}
