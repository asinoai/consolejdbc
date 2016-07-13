package aron.sinoai.consolejdb.main;

import aron.sinoai.consolejdb.tools.SchemaManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 */
public class UserDumper {
    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private SchemaManager schemaManager = new SchemaManager();

    public void dumpUsers() throws ClassNotFoundException, SQLException {
        //needed for JDBC drivers written prior JDBC 4.0
        final String driverClassName = schemaManager.getDriverClassName();
        Class.forName(driverClassName);

        final String connectionString = schemaManager.getConnectionString();
        try (final Connection connection = DriverManager.getConnection(connectionString)) {
            try (final Statement statement = connection.createStatement()) {
                try (final ResultSet resultSet = statement.executeQuery("SELECT id, userName, password FROM users")) {
                    System.out.println("=======================");
                    while (resultSet.next()) {
                        final String userName = resultSet.getString("userName");
                        final int id = resultSet.getInt("id");
                        final String password = resultSet.getString("password");

                        System.out.printf("%d; username : %s; password: %s; \n", id, userName, password);
                    }
                    System.out.println("=======================");
                }
            }
        }

    }

}
