package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TestaInsercaoComParametrosV3
 * try-with-resources
 *
 */
public class TestaInsercaoComParametrosV3 {
    public static void main(String[] args) throws SQLException
    {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        String sql = "insert into produto (nome, descricao) values (?, ?)";

        // try-with-resources -> Connection
        //
        try (Connection connection = connectionFactory.recuperaConexao()) {
            
            // by default, connections are in auto-commit mode (true);
            // if set to false, then you MUST commit/rollback the transaction.
            // command below is used to control the transaction
            //
            connection.setAutoCommit(false); 
            
            // try-with-resources -> PreparedStatement
            //
            try (PreparedStatement prepStatement = connection.
                prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                adicionarVariavel("SmartTv", "40 polegadas", prepStatement);
                adicionarVariavel("Radio", "Radio de bateria", prepStatement);
    
                connection.commit();

            } catch(Exception e) {
                System.out.println("ERRO: ocorreu um erro. Fazendo ROLLBACK da transacao.");
                connection.rollback();
            }

        } catch (Exception e) {
            System.out.println("ERRO: ocorreu um erro.");
            e.printStackTrace();
        }

        System.out.println("FIM.");
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement prepStatement)
            throws SQLException {
        
        if (nome.equals("Radio")) {
            System.out.println("Throw exception to test transaction control.");
            throw new RuntimeException();
        }

        // Set the SQL parameters
        //
        prepStatement.setString(1, nome);
        prepStatement.setString(2, descricao);

        prepStatement.execute();

        // try-with-resources -> ResultSet
        //
        try (ResultSet resultSet = prepStatement.getGeneratedKeys()) {
            
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
    
                System.out.println("\n New Id created: " + id);
            }
        }
    }
}
