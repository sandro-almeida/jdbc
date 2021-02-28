package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TestaInsercao
 *
 */
public class TestaInsercao {
    public static void main(String[] args) throws SQLException
    {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionFactory.recuperaConexao();
            
            statement = connection.createStatement();

            // New parameter on execute method to return the generated key from database
            //
            statement.execute("insert into produto (nome, descricao) values ('Mouse', 'Mouse sem fio')",
                Statement.RETURN_GENERATED_KEYS);

            resultSet = statement.getGeneratedKeys();

            while(resultSet.next()) {
                Integer id = resultSet.getInt(1);
               
                System.out.println("\n New Id created: " + id);
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
