package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.pessoal.jdbc.dao.CategoriaDAO;
import br.com.pessoal.jdbc.dao.ProdutoDAO;
import br.com.pessoal.jdbc.modelo.Categoria;
import br.com.pessoal.jdbc.modelo.Produto;

/**
 * TestaListagemDeCategorias
 * try-with-resources
 *
 */
public class TestaListagemDeCategorias {
    public static void main(String[] args) throws SQLException {
        ConnectionFactoryWithPool connectionFactoryWithPool = new ConnectionFactoryWithPool();
        
        // // try-with-resources -> Connection
        // //
        // try (Connection connection = connectionFactoryWithPool.recuperaConexao()) {

        //     CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
        //     List<Categoria> listaDeCategorias = categoriaDAO.listar();
        //     listaDeCategorias.stream().forEach(lc -> System.out.println(lc));
        // }


        // try-with-resources -> Connection
        //
        try (Connection connection = connectionFactoryWithPool.recuperaConexao()) {

            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            List<Categoria> listaDeCategorias = categoriaDAO.listar();

            // Lista os produtos por categoria.
            // Problema das queries N + 1
            //
            listaDeCategorias.stream().forEach(ct -> {
                System.out.println(ct.getNome());

                try {
                    for (Produto produto : new ProdutoDAO(connection).buscar(ct)) {
                        System.out.println(ct.getNome() + " - " + produto.getNome());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }); 
        }

        //System.out.println("Produto inserido: " + produto);
        System.out.println("FIM.");
    }
}


