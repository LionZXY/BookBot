package ru.lionzxy.bookbot.helper;

import ru.lionzxy.bookbot.BookBot;
import ru.lionzxy.bookbot.sql.FromSQL;
import ru.lionzxy.bookbot.users.User;
import ru.lionzxy.bookbot.vkbot.VkHelper;

/**
 * Created by nikit_000 on 20.09.2015.
 */
public class UserHelper {

    public static boolean findUser(int auth) {
        for (User user : FromSQL.getUsers())
            if (Integer.parseInt(user.getAuth()) == auth)
                return true;
        return false;
    }

    public static User getUser(int auth){
        for (User user : FromSQL.getUsers())
            if (Integer.parseInt(user.getAuth()) == auth)
                return user;
        return VkHelper.getNewUser(auth);
    }
}
