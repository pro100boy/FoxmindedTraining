package dbutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import static dbutils.ConnectionFactory.getConnection;

public class PopulateDB {
    private static final Class<PopulateDB> clazz = PopulateDB.class;

    private static final Logger logger = Logger.getLogger(clazz.getSimpleName());

    public static void recreateAndPopulateDB(String fileName) {
        logger.info("Populate DB. SQL file: " + fileName);

        try (InputStream inputStream = clazz.getClassLoader().getResourceAsStream("dbinit/" + fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             Statement statement = getConnection().createStatement()) {

            StringBuilder stringBuilder = new StringBuilder();
            while (reader.ready()) {
                stringBuilder.append(reader.readLine()).append(System.lineSeparator());
            }

            statement.execute(stringBuilder.toString());
        } catch (IOException | SQLException e) {
            logger.severe(e.getMessage());
        }
    }
}
