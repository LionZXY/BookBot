package ru.lionzxy.bookbot.sql;

import ru.lionzxy.bookbot.samlib.SamlibBook;
import ru.lionzxy.bookbot.samlib.SamlibBookPage;
import ru.lionzxy.bookbot.users.User;
import ru.lionzxy.bookbot.users.VkGroupUser;
import ru.lionzxy.bookbot.vkbot.Init;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nikit on 15.09.2015.
 */
public class FromSQL {

    public static boolean checkBook(SamlibBookPage bookPage) {
        ResultSet resultSet = null;
        try {
            resultSet = SQLBase.statement.executeQuery("SELECT fileName FROM book_updater.samlibbook");
            while (resultSet.next()) {
                if (resultSet.getString(1).equalsIgnoreCase(bookPage.samlibBook.getFileName()))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static SamlibBook[] getBooks() {
        List<SamlibBook> list = new ArrayList<SamlibBook>();

        try {
            ResultSet resultSet = SQLBase.statement.executeQuery("SELECT * FROM book_updater.samlibbook");
            while (resultSet.next()) {
                list.add(new SamlibBook(resultSet.getString("fileName")).setName(resultSet.getString("name")).
                        setAuthor(resultSet.getString("author")).
                        setLastUpdate(resultSet.getDate("lastUpdate")).
                        setLastSize(resultSet.getInt("lastSize")).
                        setLastCheck(new Date(resultSet.getDate("lastCheck").getTime() + resultSet.getTime("lastTime").getTime())).
                        setId(resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        SamlibBook toExit[] = new SamlibBook[list.size()];
        for (int i = 0; i < list.size(); i++)
            toExit[i] = list.get(i);
        return toExit;
    }

    public static boolean checkUser(User user) {
        ResultSet resultSet = null;
        try {
            resultSet = SQLBase.statement.executeQuery("SELECT auth FROM book_updater.users");
            while (resultSet.next()) {
                if (resultSet.getString(1).equalsIgnoreCase(user.getAuth()))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static User[] getUsers(){
        List<User> list = new ArrayList<User>();

        try {
            ResultSet resultSet = SQLBase.statement.executeQuery("SELECT * FROM book_updater.users");
            while (resultSet.next()) {
                if(Integer.parseInt(resultSet.getString("auth")) != Init.groupIdLitRPG)
                list.add(new User(resultSet.getString("name"),resultSet.getString("auth")).setSamlibId(resultSet.getString("idsamlibbooks")).setSQLId(resultSet.getInt("id")));
                else list.add(new VkGroupUser(resultSet.getString("name"),resultSet.getString("auth")).setSamlibId(resultSet.getString("idsamlibbooks")).setSQLId(resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        User toExit[] = new User[list.size()];
        for (int i = 0; i < list.size(); i++)
            toExit[i] = list.get(i);
        return toExit;
    }
}
