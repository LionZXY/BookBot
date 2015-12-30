package ru.lionzxy.bookbot.users;


import ru.lionzxy.bookbot.BookBot;
import ru.lionzxy.bookbot.helper.BookHelper;
import ru.lionzxy.bookbot.libs.UpdateEvent;
import ru.lionzxy.bookbot.samlib.SamlibBook;
import ru.lionzxy.bookbot.sql.ToSQL;
import ru.lionzxy.bookbot.vkbot.VkHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit_000 on 17.09.2015.
 */
public class User {
    List<Integer> bookSamlibId = new ArrayList<Integer>();
    String name, auth;
    int idSQL;

    public User(String name, String auth) {
        this.name = name;
        this.auth = auth;
    }

    public User setSamlibId(String from) {
        if (!from.equalsIgnoreCase("")) {
            String time[] = from.split(",");
            bookSamlibId = new ArrayList<>();
            for (String aTime : time)
                bookSamlibId.add(Integer.parseInt(aTime));
        }
        return this;
    }


    public User setSQLId(int sqlId) {
        this.idSQL = sqlId;
        return this;
    }

    public User addSamlibId(int id) {
        try {
            if (!bookSamlibId.contains(id)) {
                bookSamlibId.add(id);
                VkHelper.sendPrivateMessage("Книга " + BookHelper.getSamlibBook(id).getName() + " добавленна успешно!", this);
            } else
                VkHelper.sendPrivateMessage("Книга " + BookHelper.getSamlibBook(id).getName() + " уже добавленна", this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ToSQL.updateUser(this);
        return this;
    }

    public List<Integer> getBookSamlibId() {
        return bookSamlibId;
    }

    public String getBookSamlibIdString() {
        String toExit = "";
        for (int i : bookSamlibId)
            toExit += i + ",";
        return toExit;
    }

    public String getAuth() {
        return auth;
    }

    public String getName() {
        return name;
    }

    public int getIdSQL() {
        return idSQL;
    }

    public boolean checkBook(SamlibBook book) {
        return bookSamlibId.contains(book.getId());
    }

    public void sendUpdate(UpdateEvent ue) {
        try {
            VkHelper.sendUpdate(ue, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
