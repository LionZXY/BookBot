package ru.lionzxy.bookbot.vkbot;
import ru.lionzxy.bookbot.helper.StringHelper;
import ru.lionzxy.bookbot.libs.UpdateEvent;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by nikit_000 on 17.09.2015.
 */
public class Post {

    public static void postToGroup(UpdateEvent updateEvent, int groupid) throws Exception {

        String message ="#прода@litrpg_book\n\n"+
                StringHelper.toTitleCase(updateEvent.getBook().getName()) + "\n\n" +
                updateEvent.getBook().getAnnotation() + "\n\nАвтор: " +
                updateEvent.getBook().getAuthor() + "\n\nДобавлено продолжение +" + updateEvent.getChangeSize() +
                " Кб. (общий размер: " + updateEvent.getBook().getLastSize() + " Кб.)" +
                "\n\nhttp://samlib.ru" +
                updateEvent.getBook().getFileName() + ".shtml";
        System.out.println(message);
        String url = "https://api.vk.com/method/wall.post?attachments=photo286477373_382591564,http://samlib.ru"+updateEvent.getBook().getFileName()+".shtml&owner_id=" + groupid + "&from_group=1"
                + "&access_token=" + Init.accesToken;

        Map<String,Object> params = new LinkedHashMap<>();
        params.put("message", message);
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");


        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataBytes.length));

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(postDataBytes);
        wr.flush();
        wr.close();



        DataInputStream is = new DataInputStream(conn.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println(br.readLine());
        br.close();
    }
}

