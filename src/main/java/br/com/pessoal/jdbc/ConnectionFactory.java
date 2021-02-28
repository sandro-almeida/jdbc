package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionFactory
 *
 */
public class ConnectionFactory {

    public Connection recuperaConexao() throws SQLException {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "<senha>");
        } catch (SQLException e) {
            System.out.println( "ERRO: nao foi possivel obter a conexao." );
            e.printStackTrace();
            throw e;
        }
    }

}
