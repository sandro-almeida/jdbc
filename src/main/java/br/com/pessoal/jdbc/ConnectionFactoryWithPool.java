package br.com.pessoal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ConnectionFactoryWithPool
 * ConnectionFactory using Connection Pool
 *
 */
public class ConnectionFactoryWithPool {

    private static final int NUMERO_MAXIMO_DE_CONEXOES = 3;

    public DataSource dataSource;

    public ConnectionFactoryWithPool () {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("<senha>");

        comboPooledDataSource.setMaxPoolSize(NUMERO_MAXIMO_DE_CONEXOES);

        this.dataSource = comboPooledDataSource;
    }

    public Connection recuperaConexao() throws SQLException {
        return this.dataSource.getConnection();
    }

}
