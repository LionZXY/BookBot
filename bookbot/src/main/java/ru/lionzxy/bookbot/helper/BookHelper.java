package ru.lionzxy.bookbot.helper;

import ru.lionzxy.bookbot.BookBot;
import ru.lionzxy.bookbot.samlib.SamlibBook;
import ru.lionzxy.bookbot.sql.FromSQL;
import ru.lionzxy.bookbot.sql.ToSQL;
import ru.lionzxy.bookbot.users.User;
import ru.lionzxy.bookbot.vkbot.VkHelper;

/**
 * Created by nikit_000 on 20.09.2015.
 */
public class BookHelper {

    public static SamlibBook getSamlibBook(String url){
        String fileName = generateFileName(url);
        for (SamlibBook book : FromSQL.getBooks())
            if (book.getFileName().equalsIgnoreCase(fileName))
                return book;
        ToSQL.addSamlibBook(url);
        return getSamlibBook(url);
    }

    public static SamlibBook getSamlibBook(int id){
        for (SamlibBook book : FromSQL.getBooks())
            if (book.getId() == id)
                return book;
        return null;
    }

    public static String generateFileName(String url){
        String time[] = url.split("/");
        return "/" + time[time.length-3] + "/" + time[time.length-2]+ "/" + time[time.length-1].substring(0,time[time.length-1].length() - 6);
    }
}
