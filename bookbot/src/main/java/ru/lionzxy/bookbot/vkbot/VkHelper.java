package ru.lionzxy.bookbot.vkbot;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.lionzxy.bookbot.helper.StringHelper;
import ru.lionzxy.bookbot.libs.UpdateEvent;
import ru.lionzxy.bookbot.sql.ToSQL;
import ru.lionzxy.bookbot.users.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by nikit_000 on 20.09.2015.
 */
public class VkHelper {

    public static String getAnswer(String request) {
        String url = "https://api.vk.com/method/" + request + "&access_token=" + Init.accesToken;

        URL urlObj = null;
        try {
            urlObj = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(urlObj.openConnection().getInputStream(), "UTF-8"))) {

            return br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User getNewUser(int auth) {
        String answer;
        User user = null;
        if (auth > 0) {
            answer = getAnswer("users.get?user_ids=" + auth);
        } else return user;

        JSONParser parser = new JSONParser();
        try {
            JSONArray array = (JSONArray) ((JSONObject) parser.parse(answer)).get("response");
            JSONObject obj = (JSONObject) array.get(0);
            user = new User(obj.get("first_name").toString() + " " + obj.get("last_name").toString(), auth + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ToSQL.addUser(user);
        if (user != null)
            System.out.println("Создан пользователь " + user.getName());
        return user;
    }

    public static void sendUpdate(UpdateEvent ue, User user) throws Exception {
        String message = "#прода@litrpg_book\n\n" +
                StringHelper.toTitleCase(ue.getBook().getName()) + "\n\n" +
                ue.getBook().getAnnotation() + "\n\nАвтор: " +
                ue.getBook().getAuthor() + "\n\nДобавлено продолжение +" + ue.getChangeSize() +
                " Кб. (общий размер: " + ue.getBook().getLastSize() + " Кб.)" +
                "\n\nhttp://samlib.ru" +
                ue.getBook().getFileName() + ".shtml";
        System.out.println(message);
        String url = "https://api.vk.com/method/messages.send?user_id=" + user.getAuth() + "&v=5.37&access_token=" + Init.accesToken;

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("message", message);
        params.put("attachments","photo286477373_382606475");

        //Магия. Я фз как работает
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
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

    public static void sendPrivateMessage(String message, User user) throws Exception {
        System.out.println(message);
        String url = "https://api.vk.com/method/messages.send?user_id=" + user.getAuth() + "&access_token=" + Init.accesToken;

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("message", message);

        //Магия. Я фз как работает
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
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
