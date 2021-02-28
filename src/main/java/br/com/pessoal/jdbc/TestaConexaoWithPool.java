package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * TestaConexaoWithPool
 *
 */
public class TestaConexaoWithPool {
    public static void main(String[] args) throws SQLException
    {
        ConnectionFactoryWithPool connectionFactoryWithPool = new ConnectionFactoryWithPool();
        
        try (Connection connection = connectionFactoryWithPool.recuperaConexao()) {
            System.out.println("Obtem conexao do pool.");

        } catch (SQLException e) {
            System.out.println( "ERRO: nao foi possivel obter a conexao do pool." );
            e.printStackTrace();
        }

        System.out.println( "FIM." );
    }
}
