package ru.lionzxy.bookbot.updatemanager;

import ru.lionzxy.bookbot.samlib.SamlibBook;
import ru.lionzxy.bookbot.sql.FromSQL;

/**
 * Created by nikit on 15.09.2015.
 */
public class UpdateManager {
    static SamlibBook samlibBook[];
    public static void checkUpdate(){
        samlibBook = FromSQL.getBooks();
        for(SamlibBook sb : samlibBook)
            System.out.println(sb.toString());
    }
}
