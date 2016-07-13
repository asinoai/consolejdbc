package aron.sinoai.consolejdb.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 */
public class Main {

    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(final String args[]) throws SQLException, ClassNotFoundException {

        if (args.length > 0) {
            final String command = args[0];

            switch (command) {
                case "createDefaultUsers": {
                    final UserCreator userCreator = new UserCreator();
                    userCreator.createDefaultUsers();
                    break;
                }
                case "dumpUsers": {
                    final UserDumper userDumper = new UserDumper();
                    userDumper.dumpUsers();
                    break;
                }
                default: {
                    LOGGER.error("Unknown command: " + command);
                }
            }
        } else {
            LOGGER.info("Usage:");
            LOGGER.info("Use parameter createDefaultUsers to create default users!");
        }
    }

}
