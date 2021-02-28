package br.com.pessoal.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.pessoal.jdbc.modelo.Categoria;
import br.com.pessoal.jdbc.modelo.Produto;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {

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


    public List<Produto> listar () throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();

        String sql = "select id, nome, descricao from produto";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {

            preparedStatement.execute();

            // try-with-resources -> ResultSet
            //
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                
                while (resultSet.next()) {
                    Produto produto = new Produto(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                    System.out.println("\n Produto recuperado: " + produto);
                    produtos.add(produto);
                }
            }
        }

        return produtos;
    }

    /**
     * Metodo apenas de exemplo de como NAO se deve realizar a busca de
     * Produtos por Categoria.
     */
    public List<Produto> buscar (Categoria categoria) throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();

        String sql = "select id, nome, descricao from produto where categoria_id = ?";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, categoria.getId());
            preparedStatement.execute();

            // try-with-resources -> ResultSet
            //
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                
                while (resultSet.next()) {
                    Produto produto = new Produto(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                    System.out.println("\n Produto recuperado: " + produto);
                    produtos.add(produto);
                }
            }
        }

        return produtos;
    }


}