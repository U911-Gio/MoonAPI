package be.moondevelopment.moonapi.framework.database;


import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class SQLite {

    @Getter
    private Connection connection;

    public void connect(Plugin plugin)  {
        File file = new File(plugin.getDataFolder(), "data.db");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:"
                    + plugin.getDataFolder().getAbsolutePath() + File.separator + "data.db");
        } catch (SQLException | ClassNotFoundException x) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Error whilst connecting to the database: \n&c" + x.getMessage()));
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                getConnection().close();
            } catch (SQLException x) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Error whilst connecting to the database: \n&c" + x.getMessage()));
            }
        }
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
