package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.pessoal.jdbc.dao.ProdutoDAO;
import br.com.pessoal.jdbc.modelo.Produto;

/**
 * TestaInsercaoEListagemComProduto
 * try-with-resources
 *
 */
public class TestaInsercaoEListagemComProduto {
    public static void main(String[] args) throws SQLException {
        ConnectionFactoryWithPool connectionFactoryWithPool = new ConnectionFactoryWithPool();
        Produto produto = new Produto("Cômoda", "Cômoda horizontal");
        
        // try-with-resources -> Connection
        //
        try (Connection connection = connectionFactoryWithPool.recuperaConexao()) {

            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.salvar(produto);
            List<Produto> listaDeProdutos = produtoDAO.listar();
            listaDeProdutos.stream().forEach(lp -> System.out.println(lp));
        }

        //System.out.println("Produto inserido: " + produto);
        System.out.println("FIM.");
    }
}


