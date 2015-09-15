package ru.lionzxy.bookbot.sql;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by nikit on 14.09.2015.
 */
public class SQLBase {
    public static Connection connection;
    public static Statement statement;


    public static void Init() {

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_updater", "root", "root");
            statement = connection.createStatement();

            if(!connection.isClosed())
                System.out.println("Соединение с БД установленно!");
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установленно!");
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
