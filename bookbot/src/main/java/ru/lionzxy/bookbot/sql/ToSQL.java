package ru.lionzxy.bookbot.sql;


import ru.lionzxy.bookbot.samlib.SamlibBookPage;

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
        System.out.println(new Date(Calendar.getInstance().getTimeInMillis()));
        if (!FromSQL.checkBook(book)) {
            try {
                SQLBase.statement.execute("INSERT INTO samlibbook SET " +
                        "name = \"" + book.samlibBook.getName() + "\", " +
                        "fileName = \"" + book.samlibBook.getFileName() + "\", " +
                        "author = \"" + book.samlibBook.getAuthor() + "\", " +
                        "lastUpdate =  \"" + new Date(book.samlibBook.getDate().getTime()) + "\", " +
                        "lastSize = \"" + book.samlibBook.getLastSize() + "\", " +
                        "lastCheck = \"" + new Date(Calendar.getInstance().getTimeInMillis()) + "\", " +
                        "lastTime = \"" + new Time(Calendar.getInstance().getTimeInMillis()) + "\"");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else System.out.println("Книга уже добавлена!");
    }
}
