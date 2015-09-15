package ru.lionzxy.bookbot;

import ru.lionzxy.bookbot.sql.SQLBase;
import ru.lionzxy.bookbot.sql.ToSQL;
import ru.lionzxy.bookbot.updatemanager.UpdateManager;

/**
 * Created by nikit on 14.09.2015.
 */
public class BookBot {

    public static void main(String args[]){

        SQLBase.Init();
        ToSQL.addSamlibBook("samlib.ru/t/titow_dmitrij_sergeewich/parmironlineigrokponewole.shtml");
        UpdateManager.checkUpdate();
    }
}
