package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TestaInsercaoComParametros
 *
 */
public class TestaInsercaoComParametros {
    public static void main(String[] args) throws SQLException
    {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        Connection connection = null;
        ResultSet resultSet = null;
        String sql = "insert into produto (nome, descricao) values (?, ?)";
        String nome = "Mouse'";
        String descricao = "Mouse sem fio; delete from produto"; //this is to validate that SQL Injection will not occur

        try {
            connection = connectionFactory.recuperaConexao();
            
            // Using prepareStatement to avoid SQL Injection
            //
            PreparedStatement prepStatement = connection.
                prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Set the SQL parameters
            //
            prepStatement.setString(1, nome);
            prepStatement.setString(2, descricao);

            prepStatement.execute();

            resultSet = prepStatement.getGeneratedKeys();

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
            connection.close();
        }

        System.out.println( "FIM." );
    }
}
