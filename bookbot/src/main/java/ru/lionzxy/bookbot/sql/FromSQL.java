package ru.lionzxy.bookbot.sql;

import ru.lionzxy.bookbot.samlib.SamlibBook;
import ru.lionzxy.bookbot.samlib.SamlibBookPage;

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
            while (resultSet.next()){
                if(resultSet.getString(1).equalsIgnoreCase(bookPage.samlibBook.getFileName()))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static SamlibBook[] getBooks(){
        List<SamlibBook> list = new ArrayList<SamlibBook>();
        try {
            ResultSet resultSet = SQLBase.statement.executeQuery("SELECT * FROM book_updater.samlibbook");
            while (resultSet.next()){
                list.add(new SamlibBook(resultSet.getString("fileName")).setName(resultSet.getString("name"))
                        .setAuthor(resultSet.getString("author")).setLastUpdate(resultSet.getDate("lastUpdate")).
                                setSize(resultSet.getInt("lastSize")).setLastCheck(new Date(resultSet.getDate("lastCheck").getTime() + resultSet.getTime("lastTime").getTime())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SamlibBook toExit[] = new SamlibBook[list.size()];
        for(int i = 0; i < list.size(); i++)
            toExit[i] = list.get(i);
        return toExit;
    }

}
