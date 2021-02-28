package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TestaInsercaoComParametrosV2
 *
 */
public class TestaInsercaoComParametrosV2 {
    public static void main(String[] args) throws SQLException
    {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        Connection connection = null;
        PreparedStatement prepStatement = null;
        String sql = "insert into produto (nome, descricao) values (?, ?)";

        try {
            connection = connectionFactory.recuperaConexao();

            // by default, connections are in auto-commit mode (true);
            // if set to false, then you MUST commit/rollback the transaction.
            // command below is used to control the transaction
            //
            connection.setAutoCommit(false); 
            
            // Using prepareStatement to avoid SQL Injection
            //
            prepStatement = connection.
                prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            adicionarVariavel("SmartTv", "40 polegadas", prepStatement);
            adicionarVariavel("Radio", "Radio de bateria", prepStatement);

            connection.commit();
            prepStatement.close();

        } catch (Exception e) {
            System.out.println("ERRO: ocorreu um erro. Fazendo ROLLBACK da transacao.");
            connection.rollback();

            if (prepStatement != null) {
                prepStatement.close();
            }

            e.printStackTrace();
        } finally {
            System.out.println("Fechando a conexao.");
            connection.close();
        }

        System.out.println("FIM.");
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement prepStatement)
            throws SQLException {
        
        if (nome.equals("Radio")) {
            System.out.println("Throw exception to test transaction control.");
            throw new RuntimeException();
        }

        ResultSet resultSet;
        // Set the SQL parameters
        //
        prepStatement.setString(1, nome);
        prepStatement.setString(2, descricao);

        prepStatement.execute();

        resultSet = prepStatement.getGeneratedKeys();

        while (resultSet.next()) {
            Integer id = resultSet.getInt(1);

            System.out.println("\n New Id created: " + id);
        }
        resultSet.close();
    }
}
