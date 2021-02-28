package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TestaListagem
 *
 */
public class TestaListagem {
    public static void main(String[] args) throws SQLException
    {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionFactory.recuperaConexao();
            
            statement = connection.createStatement();
            statement.execute("select id, nome, descricao from produto");

            resultSet = statement.getResultSet();

            while(resultSet.next()) {
                Integer id = resultSet.getInt("id");
                //String id = resultSet.getString("id");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");

                System.out.println("\nId: " + id + "; nome: " + nome + "; descricao: " + descricao);
            }
       
        } catch (SQLException e) {
            System.out.println( "ERRO: ocorreu um erro." );
            e.printStackTrace();
        } finally {
            System.out.println( "Fechando a conexao." );

            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            connection.close();
        }

        System.out.println( "FIM." );
    }
}
