package ru.job4j.jdbc;

import org.postgresql.util.PSQLException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
       Class.forName(properties.getProperty("driver_class"));
       String url = properties.getProperty("url");
       String username = properties.getProperty("username");
       String password = properties.getProperty("password");
       connection = DriverManager.getConnection(url, username, password);
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement st = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s();",
                    tableName
            );
            st.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement st = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE IF EXISTS %s;",
                    tableName
            );
            st.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement st = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s;",
                    tableName,
                    columnName,
                    type
            );
            st.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement st = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s DROP COLUMN %s;",
                    tableName,
                    columnName
            );
            st.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement st = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s",
                    tableName,
                    columnName,
                    newColumnName
            );
            st.execute(sql);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("statement_jdbc.properties")) {
            config.load(in);
        }
        try (TableEditor tab = new TableEditor(config)) {
            tab.initConnection();
            tab.createTable("my_tab");
            System.out.println(tab.getTableScheme("my_tab"));
            tab.addColumn("my_tab", "name", "text");
            System.out.println(tab.getTableScheme("my_tab"));
            tab.addColumn("my_tab", "age", "int8");
            System.out.println(tab.getTableScheme("my_tab"));
            tab.renameColumn("my_tab", "name", "surname");
            System.out.println(tab.getTableScheme("my_tab"));
            tab.dropColumn("my_tab", "age");
            System.out.println(tab.getTableScheme("my_tab"));
            tab.dropTable("my_tab");
            try {
                tab.getTableScheme("my_tab");
            } catch (PSQLException e) {
                System.out.println("Таблица c введенным наименоваем не найдена.");
            }
        }
    }
}