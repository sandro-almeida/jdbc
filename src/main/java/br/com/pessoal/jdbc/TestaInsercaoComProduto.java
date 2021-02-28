package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.pessoal.jdbc.modelo.Produto;

/**
 * TestaInsercaoComProduto
 * try-with-resources
 *
 */
public class TestaInsercaoComProduto {
    public static void main(String[] args) throws SQLException {
        ConnectionFactoryWithPool connectionFactoryWithPool = new ConnectionFactoryWithPool();
        Produto produto = new Produto("Cômoda", "Cômoda vertical");
        
        // try-with-resources -> Connection
        //
        try (Connection connection = connectionFactoryWithPool.recuperaConexao()) {
            
            String sql = "insert into produto (nome, descricao) values (?, ?)";

            // try-with-resources -> PreparedStatement
            //
            try (PreparedStatement prepStatement = connection.
                prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                // Set the SQL parameters
                //
                prepStatement.setString(1, produto.getNome());
                prepStatement.setString(2, produto.getDescricao());

                prepStatement.execute();

                // try-with-resources -> ResultSet
                //
                try (ResultSet resultSet = prepStatement.getGeneratedKeys()) {
                    
                    while (resultSet.next()) {
                        produto.setId(resultSet.getInt(1));
            
                        System.out.println("\n New Id created: " + produto.getId());
                    }
                }
            } 
        }

        System.out.println("Produto inserido: " + produto);
        System.out.println("FIM.");
    }
}


