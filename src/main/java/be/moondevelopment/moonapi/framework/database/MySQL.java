package be.moondevelopment.moonapi.framework.database;


import be.moondevelopment.moonapi.framework.utils.ColorUtil;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

public class MySQL {

    @Getter
    private Connection connection;

    private String HOST, DATABASE, USERNAME, PASSWORD;
    private int PORT;

    public MySQL(String HOST, int PORT, String DATABASE, String USERNAME, String PASSWORD) {
        this.HOST = HOST;
        this.DATABASE = DATABASE;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?autoReconnect=true&useSSL=false",
                    USERNAME,
                    PASSWORD);

            final TimerTask timerTask =
                    new TimerTask() {
                        @Override
                        public void run() {
                            try {

                                if (connection != null && !connection.isClosed()) {
                                    connection.createStatement().execute("SELECT 1");
                                } else {
                                    connection = getNewConnection();
                                }

                            } catch (SQLException ex) {
                                connection = getNewConnection();
                            }
                        }
                    };

            final Timer timer = new Timer();
            timer.schedule(timerTask, 60000, 60000);
        } catch (SQLException x) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("&4Error whilst connecting to the database: \n&c" + x.getMessage()));
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException x) {
                x.printStackTrace();
            }
        }
    }

    private Connection getNewConnection() {
        try {
            disconnect();
            return DriverManager.getConnection(
                    "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?autoReconnect=true&useSSL=false",
                    USERNAME,
                    PASSWORD);
        } catch (SQLException x) {
            x.printStackTrace();
        }
        return null;
    }

    public void executeUpdate(String sql, Object... placeholders) throws SQLException {

        final PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < placeholders.length; i++) {
            statement.setObject(i + 1, placeholders[i]);
        }

        statement.executeUpdate();
    }

    public ResultSet executeQuery(String sql, Object... placeholders) throws SQLException {

        final PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < placeholders.length; i++) {
            statement.setObject(i + 1, placeholders[i]);
        }

        return statement.executeQuery();
    }
}
