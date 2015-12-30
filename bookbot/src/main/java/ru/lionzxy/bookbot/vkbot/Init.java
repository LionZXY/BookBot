package ru.lionzxy.bookbot.vkbot;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nikit_000 on 17.09.2015.
 */
public class Init {
    public static int groupIdLitRPG = -48785893;
    public static String accesToken;
    public static void createAccesToken(String login, String password){

        String url = "https://oauth.vk.com/token?grant_type=password&client_id=2274003&client_secret=hHbZxrka2uZ6jB1inYsH&" +
                "username="+login+"&password="+password+"&scope=notify,friends,wall,groups,offline";
        URL urlObj;
        InputStream is = null;
        BufferedReader br;
        String line;
        try {
            urlObj = new URL(url);
            is = urlObj.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObj = (JSONObject) jsonParser.parse(line);
                accesToken = jsonObj.get("access_token").toString();
                System.out.println(accesToken);
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {

            }
        }
        //Post.post();
    }
}
