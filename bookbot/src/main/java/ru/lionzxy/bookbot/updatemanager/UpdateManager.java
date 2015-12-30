package ru.lionzxy.bookbot.updatemanager;

import ru.lionzxy.bookbot.BookBot;
import ru.lionzxy.bookbot.libs.UpdateEvent;
import ru.lionzxy.bookbot.samlib.SamlibBook;
import ru.lionzxy.bookbot.samlib.SamlibBookPage;
import ru.lionzxy.bookbot.samlib.UpdateLog;
import ru.lionzxy.bookbot.sql.FromSQL;
import ru.lionzxy.bookbot.sql.ToSQL;
import ru.lionzxy.bookbot.users.User;
import ru.lionzxy.bookbot.vkbot.Init;
import ru.lionzxy.bookbot.vkbot.Post;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by nikit on 15.09.2015.
 */
public class UpdateManager {
    static SamlibBook samlibBook[];
    static UpdateLog todayLog;
    public static int EVERYMINUT = 10;
    public static void checkUpdate() {

        todayLog = new UpdateLog(new Date());

        samlibBook = FromSQL.getBooks();

        System.out.println("[" + new Date() + "] Проверка на обновление " + samlibBook.length + " книг");
        for (SamlibBook aSamlibBook : samlibBook)
            checkBook(aSamlibBook);
    }

    public static void updateEvent(UpdateEvent ue) {

        for(User user : FromSQL.getUsers())
            if(user.checkBook(ue.getBook()))
                user.sendUpdate(ue);

        System.out.println("Book Update!!!");
    }

    public static void checkBook(SamlibBook book) {

        if (Calendar.getInstance().getTime().getTime() - book.getDate().getTime() < EVERYMINUT * 60000)
            return;


        Calendar calToDay = Calendar.getInstance();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(book.getLastCheck().getTime()));


        for (int year = cal.get(Calendar.YEAR); year < calToDay.get(Calendar.YEAR) + 1; year++)
            for (int mouth = cal.get(Calendar.MONTH); mouth < calToDay.get(Calendar.MONTH) + 1; mouth++)
                for (int day = cal.get(Calendar.DAY_OF_MONTH) + 1; day < calToDay.get(Calendar.DAY_OF_MONTH) + 1; day++) {
                    if (day == todayLog.day && mouth + 1 == todayLog.mouth && year == todayLog.year)
                        todayLog.findBook(book);
                    else new UpdateLog(year, mouth, day).findBook(book);
                }

        ToSQL.checkSamlibBook(book);
    }
}
