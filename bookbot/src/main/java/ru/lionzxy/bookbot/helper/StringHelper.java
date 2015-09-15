package ru.lionzxy.bookbot.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nikit on 14.09.2015.
 */
public class StringHelper {

    public static int findWord(String from, String word, boolean ignoreCase) {
        for (int i = 0; i < from.length(); i++)
            if (equalsChar(from.charAt(i), word.charAt(0), ignoreCase))
                if (checkAllWord(from, i, word, ignoreCase))
                    return i;
        return -1;
    }

    static boolean equalsChar(char a, char b, boolean ignoreCase) {
        return ignoreCase ? (Character.toLowerCase(a) == Character.toLowerCase(b)) || (Character.toTitleCase(a) == Character.toTitleCase(b)) : a == b;
    }

    static boolean checkAllWord(String from, int charp, String word, boolean ignoreCase) {
        for (int i = 1; i < word.length(); i++)
            if (!equalsChar(from.charAt(charp + i), word.charAt(i), ignoreCase))
                return false;
        return true;
    }


}
