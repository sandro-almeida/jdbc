package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.pessoal.jdbc.dao.CategoriaDAO;
import br.com.pessoal.jdbc.dao.ProdutoDAO;
import br.com.pessoal.jdbc.modelo.Categoria;
import br.com.pessoal.jdbc.modelo.Produto;

/**
 * TestaListagemDeCategoriasComProdutos
 * try-with-resources
 *
 */
public class TestaListagemDeCategoriasComProdutos {
    public static void main(String[] args) throws SQLException {
        ConnectionFactoryWithPool connectionFactoryWithPool = new ConnectionFactoryWithPool();
        
        // try-with-resources -> Connection
        //
        try (Connection connection = connectionFactoryWithPool.recuperaConexao()) {

            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            List<Categoria> listaDeCategorias = categoriaDAO.listarComProdutos();
            listaDeCategorias.stream().forEach(ct -> {
                System.out.println(ct.getNome());
                for (Produto produto : ct.getProdutos()) {
                    System.out.println(ct.getNome() + " - " + produto.getNome());
                }
            });
        }


 

        //System.out.println("Produto inserido: " + produto);
        System.out.println("FIM.");
    }
}


