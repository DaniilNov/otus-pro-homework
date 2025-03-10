package ru.otus.java.pro.dbinteraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.java.pro.patterns.part2.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbMigrator {
    private final DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public DbMigrator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void migrate() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = dataSource.getStatement();

            String sql = readSqlFile("init.sql");
            for (String query : sql.split(";")) {
                if (!query.trim().isEmpty()) {
                    statement.executeUpdate(query);
                }
            }
            System.out.println("Database migration completed successfully.");
        } catch (SQLException | IOException e) {
            logger.error("Error during database migration: {}", e.getMessage(), e);
        }
    }

    private String readSqlFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }
}
