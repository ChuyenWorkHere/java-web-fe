package servlet.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import servlet.configs.DbConfiguration;

public class DataSourceUtil {
    
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    static {
        try {
            dataSource.setDriverClass(DbConfiguration.DB_DRIVER);
            dataSource.setJdbcUrl(DbConfiguration.CONNECTION_URL);
            dataSource.setUser(DbConfiguration.USER_NAME);
            dataSource.setPassword(DbConfiguration.PASSWORD);

            dataSource.setInitialPoolSize(DbConfiguration.DB_MIN_CONNECTIONS);
            dataSource.setMinPoolSize(DbConfiguration.DB_MIN_CONNECTIONS);
            dataSource.setMaxPoolSize(DbConfiguration.DB_MAX_CONNECTIONS);

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
