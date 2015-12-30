package ru.lionzxy.bookbot.vkbot.message;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nikit_000 on 20.09.2015.
 */
public class JsonToMessages {

    public static Message[] getMessages(String json){
        List<Message> toExitList = new ArrayList<>();

        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObj = (JSONObject)parser.parse(json);
            JSONArray array = (JSONArray)jsonObj.get("response");
            for(int i = 1; i < array.size(); i++){
                JSONObject mess = (JSONObject) parser.parse(array.get(i).toString());
                toExitList.add(new Message(Integer.parseInt(mess.get("mid").toString()),Integer.parseInt(mess.get("uid").toString()),
                        mess.get("body").toString(),new Date((long) (Integer.parseInt(mess.get("date").toString())*1000)),
                        Integer.parseInt(mess.get("read_state").toString())));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Message toExit[] = new Message[toExitList.size()];
        for(int i = 0; i < toExit.length; i++)
            toExit[i] = toExitList.get(i);

        return toExit;
    }
}
