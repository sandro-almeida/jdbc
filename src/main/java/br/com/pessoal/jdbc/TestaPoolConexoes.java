package br.com.pessoal.jdbc;

import java.sql.SQLException;

/**
 * TestaPoolConexoes
 *
 */
public class TestaPoolConexoes {
    public static void main(String[] args) throws SQLException
    {
        ConnectionFactoryWithPool connectionFactoryWithPool = new ConnectionFactoryWithPool();

        // Testing connection pool when the max number of connections in the pool is limited
        //
        for(int i = 0; i < 6; i++) {
            connectionFactoryWithPool.recuperaConexao();
            System.out.println("Obtem conexao do pool: [" + i + "]");
        }
        
        System.out.println( "FIM." );
        
    }
}
