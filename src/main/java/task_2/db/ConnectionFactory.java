package task_2.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Class<ConnectionFactory> clazz = ConnectionFactory.class;

    private static final Logger logger = Logger.getLogger(clazz.getSimpleName());

    private static final String fileName = "db.properties";

    private ConnectionFactory(){}

    public static Connection getConnection() throws IOException, SQLException {

        Connection connection = null;

        try (InputStream input = clazz.getClassLoader().getResourceAsStream(fileName)) {

            if (input == null) {
                logger.severe("Sorry, unable to find " + fileName);
                throw new FileNotFoundException();
            }

            Properties prop = new Properties();
            prop.load(input);

            String url = prop.getProperty("db.connection");
            String user = prop.getProperty("db.user");
            String passwd = prop.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, passwd);
        }

        return connection;
    }
}
