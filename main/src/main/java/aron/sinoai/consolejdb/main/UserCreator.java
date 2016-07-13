package aron.sinoai.consolejdb.main;

import aron.sinoai.consolejdb.tools.SchemaManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 */
public class UserCreator {
    private static final String[] DEFAULT_USERS = {"Jo", "Joe", "Ursula", "Urs"};
    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private SchemaManager schemaManager = new SchemaManager();

    public void createDefaultUsers() throws ClassNotFoundException, SQLException {
        //needed for JDBC drivers written prior JDBC 4.0

        final String driverClassName = schemaManager.getDriverClassName();
        Class.forName(driverClassName);

        final String connectionString = schemaManager.getConnectionString();
        try (final Connection connection = DriverManager.getConnection(connectionString)) {
            try (final PreparedStatement statement = connection.prepareStatement("INSERT INTO users (userName, password) VALUES (?, ?)")) {
                for (final String userName : DEFAULT_USERS) {

                    statement.setString(1, userName);
                    statement.setString(2, "welcome" + userName);
                    statement.execute();
                }
            }
        }

        LOGGER.info("Inserted " + DEFAULT_USERS.length + " user records!");
    }

}
