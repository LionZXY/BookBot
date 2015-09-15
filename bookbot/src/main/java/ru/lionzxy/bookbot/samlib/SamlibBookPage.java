package ru.lionzxy.bookbot.samlib;

import ru.lionzxy.bookbot.helper.StringHelper;
import ru.lionzxy.bookbot.helper.URLHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit on 14.09.2015.
 */
public class SamlibBookPage {


    public SamlibBook samlibBook;

    public SamlibBookPage(String urlS) {
        String page[] = URLHelper.getSite(urlS);
        samlibBook =  new SamlibBook(generateFileName(urlS));

        generateDate(page);
        generateName(page);
        generateAuthor(page);

        System.out.println(samlibBook.toString());
    }

    void generateDate(String page[]) {
        String keyword = "изменен";
        int wordInt;
        for (String line : page) {
            wordInt = StringHelper.findWord(line, keyword, true);
            if (wordInt != -1) {
                this.samlibBook.setSize(Integer.parseInt(line.substring(line.indexOf('.', wordInt + keyword.length() + 2) + 2, line.indexOf('k', wordInt + keyword.length() + 2 + 2))));
                try {
                    this.samlibBook.setLastUpdate(new SimpleDateFormat("dd/MM/yyyy").parse(line.substring(wordInt + keyword.length() + 2, line.indexOf('.', wordInt + keyword.length() + 2))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    void generateName(String page[]) {
        String keyword = "<center><h2>";
        int wordInt;
        for (String line : page) {
            wordInt = StringHelper.findWord(line, keyword, true);
            if (wordInt != -1) {
                this.samlibBook.setName(line.substring(wordInt + keyword.length(), line.indexOf('<', wordInt + keyword.length())));
                return;
            }
        }
    }

    void generateAuthor(String page[]) {
        String keyword = "<div align=right><h3>";
        int wordInt;
        for (int i = 0; i < page.length; i++) {
            wordInt = StringHelper.findWord(page[i], keyword, true);
            if (wordInt != -1) {
                this.samlibBook.setAuthor(page[i + 1].substring(0, page[i + 1].indexOf(':', wordInt + keyword.length())));
                return;
            }
        }
    }

    String generateFileName(String url){
        String time[] = url.split("/");
        return time[time.length-3] + "/" + time[time.length-2]+ "/" + time[time.length-1].substring(0,time[time.length-1].length() - 6);

    }
}
