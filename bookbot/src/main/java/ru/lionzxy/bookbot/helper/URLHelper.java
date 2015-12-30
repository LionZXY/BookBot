package ru.lionzxy.bookbot.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit on 15.09.2015.
 */
public class URLHelper {

    public static String[] getSite(String url){
        System.out.println("Получение файла " + url);

        List<String> page = new ArrayList<String>();
        if (url.length() < 8 || !(url.substring(0, 7).equalsIgnoreCase("http://") || url.substring(0, 8).equalsIgnoreCase("https://")) )
            url = "http://" + url;
        URL urlObj;
        InputStream is = null;
        BufferedReader br;
        String line;
        try {
            urlObj = new URL(url);
            is = urlObj.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is, "Cp1251"));
            while ((line = br.readLine()) != null) {
                page.add(line);
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {

            }
        }
        String toExit[] = new String[page.size()];
        for(int i = 0; i < page.size(); i++)
            toExit[i] = page.get(i);
        return toExit;
    }
}
