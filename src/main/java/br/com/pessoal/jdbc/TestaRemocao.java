package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TestaRemocao
 *
 */
public class TestaRemocao {
    public static void main(String[] args) throws SQLException
    {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        Connection connection = null;
        Statement statement = null;
        try {
            connection = connectionFactory.recuperaConexao();
            
            statement = connection.createStatement();
            statement.execute("delete from produto where id > 2");

            Integer linhasRemovidas = statement.getUpdateCount();

            System.out.println("\n Numero de linhas removidas: " + linhasRemovidas);

        } catch (SQLException e) {
            System.out.println( "ERRO: ocorreu um erro." );
            e.printStackTrace();
        } finally {
            System.out.println( "Fechando a conexao." );

            if (statement != null) {
                statement.close();
            }

            connection.close();
        }

        System.out.println( "FIM." );
    }
}
