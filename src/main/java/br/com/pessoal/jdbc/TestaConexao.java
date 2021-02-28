package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * TestaConexao
 *
 */
public class TestaConexao {
    public static void main(String[] args) throws SQLException
    {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        
        Connection connection = null;
        try {
            connection = connectionFactory.recuperaConexao();
        } catch (SQLException e) {
            System.out.println( "ERRO: nao foi possivel obter a conexao." );
            e.printStackTrace();
        } finally {
            System.out.println( "Fechando a conexao." );
            connection.close();
        }

        System.out.println( "FIM." );
    }
}
