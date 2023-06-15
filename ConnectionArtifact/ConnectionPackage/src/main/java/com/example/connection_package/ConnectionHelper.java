package com.example.connection_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionHelper {
    private static final String url = "jdbc:postgresql://34.116.151.224:5432/postgres?socketFactory=com.google.cloud.sql.postgres.SocketFactory&cloudSqlInstance=tema-orm:europe-central2:tema-orm-db&user=postgres&password=postgres";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(ConnectionHelper.url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
