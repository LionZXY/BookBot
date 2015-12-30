package ru.lionzxy.bookbot.sql;


import com.mysql.jdbc.PreparedStatement;
import ru.lionzxy.bookbot.helper.StringHelper;
import ru.lionzxy.bookbot.samlib.SamlibBook;
import ru.lionzxy.bookbot.samlib.SamlibBookPage;
import ru.lionzxy.bookbot.users.User;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by nikit on 14.09.2015.
 */
public class ToSQL {

    public static void addSamlibBook(String url) {
        addSamlibBook(new SamlibBookPage(url));
    }

    public static void addSamlibBook(SamlibBookPage book) {
        if (book.samlibBook.getName() != null && !FromSQL.checkBook(book)) {
            try {

                SQLBase.statement.execute("INSERT INTO samlibbook SET " +
                        "name = \"" + StringHelper.removeQuotes(book.samlibBook.getName()) + "\", " +
                        "fileName = \"" + book.samlibBook.getFileName() + "\", " +
                        "author = \"" + StringHelper.removeQuotes(book.samlibBook.getAuthor()) + "\", " +
                        "lastUpdate =  \"" + new Date(book.samlibBook.getDate().getTime()) + "\", " +
                        "lastSize = \"" + book.samlibBook.getLastSize() + "\", " +
                        "lastCheck = \"" + new Date(Calendar.getInstance().getTimeInMillis()) + "\", " +
                        "lastTime = \"" + new Time(Calendar.getInstance().getTimeInMillis()) + "\"");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else System.out.println("Книга уже добавлена!");
    }

    public static void checkSamlibBook(SamlibBook samlibBook) {
        try {
            SQLBase.statement.execute("UPDATE samlibbook SET lastUpdate = \"" + new Date(samlibBook.getDate().getTime()) + "\" WHERE id = " + samlibBook.getId() + ";");
            SQLBase.statement.execute("UPDATE samlibbook SET lastSize = \"" + samlibBook.getLastSize() + "\" WHERE id = " + samlibBook.getId() + ";");
            SQLBase.statement.execute("UPDATE samlibbook SET lastCheck = \"" + new Date(samlibBook.getLastCheck().getTime()) + "\" WHERE id = " + samlibBook.getId() + ";");
            SQLBase.statement.execute("UPDATE samlibbook SET lastTime = \"" + new Time(samlibBook.getLastCheck().getTime()) + "\" WHERE id = " + samlibBook.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(User user){
        if (!FromSQL.checkUser(user)) {
            try {
                SQLBase.statement.execute("INSERT INTO users SET " +
                        "name = \"" + user.getName() + "\", " +
                        "auth = \"" + user.getAuth() + "\", " +
                        "idsamlibbooks = \"" + user.getBookSamlibIdString() + "\"");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else System.out.println("Пользователь уже добавлен!");
    }

    public static void updateUser(User user){
        try {
            System.out.println(user.getIdSQL());
            SQLBase.statement.execute("UPDATE users SET idsamlibbooks = \"" + user.getBookSamlibIdString() + "\" WHERE id = " + user.getIdSQL() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
