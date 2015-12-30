package ru.lionzxy.bookbot.vkbot.message;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.lionzxy.bookbot.helper.BookHelper;
import ru.lionzxy.bookbot.helper.StringHelper;
import ru.lionzxy.bookbot.helper.UserHelper;
import ru.lionzxy.bookbot.users.User;
import ru.lionzxy.bookbot.vkbot.VkHelper;

import java.util.Date;

/**
 * Created by nikit_000 on 20.09.2015.
 */
public class Message {
    int idMess, userId, readState;
    Date date;
    String body;

    public Message(int idMess, int userId, String body, Date date, int readState) {
        this.idMess = idMess;
        this.userId = userId;
        this.body = body;
        this.date = date;
        this.readState = readState;
    }

    public String toString() {
        return body;
    }

    public void checkMessage() {
        if (readState == 0) {
            boolean check = false;
            User user = UserHelper.getUser(this.userId);
            String add = "Добавить";
            if (StringHelper.findWord(body, add, true) != -1)
                if(StringHelper.getUrl(body) != null){
                    user.addSamlibId(BookHelper.getSamlibBook(StringHelper.getUrl(body)).getId());
                    check = true;
                }

        if(!check)
            try {
                VkHelper.sendPrivateMessage("Комманда "+body+" не опознана",user);
            } catch (Exception e) {
                e.printStackTrace();
            }

            VkHelper.getAnswer("markAsRead?message_ids="+idMess);
        }


    }

}
