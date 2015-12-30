package ru.lionzxy.bookbot.users;

import ru.lionzxy.bookbot.libs.UpdateEvent;
import ru.lionzxy.bookbot.samlib.SamlibBook;
import ru.lionzxy.bookbot.vkbot.Init;
import ru.lionzxy.bookbot.vkbot.Post;

import java.util.ArrayList;

/**
 * Created by nikit_000 on 19.09.2015.
 */
public class VkGroupUser extends User {

    public VkGroupUser(String name, String auth) {
        super(name, auth);
    }

    @Override
    public void sendUpdate(UpdateEvent ue){
        try {
            Post.postToGroup(ue, Init.groupIdLitRPG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkBook(SamlibBook book){
        return true;
    }

    public User setSamlibId(String from) {
        return this;
    }
}
