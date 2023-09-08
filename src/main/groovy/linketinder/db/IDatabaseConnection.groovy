package linketinder.db

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

interface IDatabaseConnection {
    Connection getConnection();
    PreparedStatement prepareStatement(String sql);
    ResultSet executeQuery(PreparedStatement statement);
}