package ru.lionzxy.bookbot;

import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.PreparedStatement;
import ru.lionzxy.bookbot.libs.UpdateEvent;
import ru.lionzxy.bookbot.samlib.SamlibBook;
import ru.lionzxy.bookbot.samlib.SamlibBookPage;
import ru.lionzxy.bookbot.sql.FromSQL;
import ru.lionzxy.bookbot.sql.SQLBase;
import ru.lionzxy.bookbot.sql.ToSQL;
import ru.lionzxy.bookbot.updatemanager.UpdateManager;
import ru.lionzxy.bookbot.users.User;
import ru.lionzxy.bookbot.users.VkGroupUser;
import ru.lionzxy.bookbot.vkbot.Init;
import ru.lionzxy.bookbot.vkbot.VkHelper;
import ru.lionzxy.bookbot.vkbot.message.JsonToMessages;
import ru.lionzxy.bookbot.vkbot.message.Message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit on 14.09.2015.
 */
public class BookBot {


    public static void main(String args[]){


        Init.createAccesToken(lwg,paswrd);

        SQLBase.Init();

        while (true){
            UpdateManager.checkUpdate();
            System.out.println("Проверка выполнилась. Ожидание...");

            for(int i = 0; i < UpdateManager.EVERYMINUT; i++){
            Message mess[] = JsonToMessages.getMessages(VkHelper.getAnswer("messages.get?count=20"));
            for(Message message : mess)
                message.checkMessage();
            System.out.println("Сообщения проверенны");
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
