package br.com.pessoal.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pessoal.jdbc.modelo.Categoria;
import br.com.pessoal.jdbc.modelo.Produto;

public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }


    public List<Categoria> listar () throws SQLException {
        List<Categoria> categorias = new ArrayList<Categoria>();

        String sql = "select id, nome from categoria";

        // try-with-resources -> PreparedStatement
        //
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {

            preparedStatement.execute();

            // try-with-resources -> ResultSet
            //
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                
                while (resultSet.next()) {
                    Categoria categoria = new Categoria(resultSet.getInt(1), resultSet.getString(2));
                    System.out.println("\n Categoria recuperada: " + categoria);
                    categorias.add(categoria);
                }
            }
        }

        return categorias;
    }


    // Este metodo me parece apenas um exemplo, pois sua implementacao esta muito confusa
    // Talvez para garantir que esta logica va funcionar, deveriamos ordenar a query pelo
    // nome da categoria
    //
    public List<Categoria> listarComProdutos () throws SQLException {
        List<Categoria> categorias = new ArrayList<Categoria>();
        Categoria ultima = null;

        System.out.println("Executa query de listagem de categorias associadas a produtos.");

        String sql = "select C.id, C.nome, P.id, P.nome, P.descricao " +
                     "from categoria C inner join produto P on P.categoria_id = C.id";

        // try-with-resources -> PreparedStatement
        //
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {

            preparedStatement.execute();

            // try-with-resources -> ResultSet
            //
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                
                while (resultSet.next()) {
                    if (ultima == null || !ultima.getNome().equals(resultSet.getString(2))) {
                        Categoria categoria = new Categoria(resultSet.getInt(1), resultSet.getString(2));
                        ultima = categoria;
                        System.out.println("\n Categoria recuperada: " + categoria);
                        categorias.add(categoria);
                    }
                    Produto produto = new Produto(resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5));
                    ultima.adicionar(produto);
                }
            }
        }

        return categorias;
    }

}