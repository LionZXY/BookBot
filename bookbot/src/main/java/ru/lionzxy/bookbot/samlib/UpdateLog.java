package ru.lionzxy.bookbot.samlib;

import ru.lionzxy.bookbot.helper.URLHelper;
import ru.lionzxy.bookbot.libs.UpdateEvent;
import ru.lionzxy.bookbot.updatemanager.UpdateManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nikit on 15.09.2015.
 */
public class UpdateLog {

    private String log[][];
    public int year, mouth, day;

    public UpdateLog(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = cal.get(Calendar.YEAR);
        mouth = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        String url = "http://samlib.ru/logs/" + year + "/" + getTwoNumb(mouth) + "-" + getTwoNumb(day) + ".log";
        String time[] = URLHelper.getSite(url);
        log = new String[time.length][];
        for (int i = 0; i < time.length; i++)
            log[i] = spliteToByte(time[i], (byte) 124);
    }

    public UpdateLog(int year, int mouth, int day) {
        this.year = year;
        this.mouth = mouth + 1;
        this.day = day;
        String url = "http://samlib.ru/logs/" + year + "/" + getTwoNumb(mouth + 1) + "-" + getTwoNumb(day) + ".log";
        String time[] = URLHelper.getSite(url);
        log = new String[time.length][];
        for (int i = 0; i < time.length; i++)
            log[i] = spliteToByte(time[i], (byte) 124);
    }

    private String getTwoNumb(int numb) {
        if (numb / 10 < 1)
            return "0" + numb;
        return numb + "";
    }

    private String[] spliteToByte(String from, byte split) {
        List<String> toExit = new ArrayList<String>();
        String time = "";

        for (int i = 0; i < from.length(); i++)
            if ((byte) from.charAt(i) == split) {
                toExit.add(time);
                time = "";
            } else time += from.charAt(i);
        toExit.add(time);

        String toExitArr[] = new String[toExit.size()];
        for (int i = 0; i < toExit.size(); i++)
            toExitArr[i] = toExit.get(i);
        return toExitArr;
    }

    public void findBook(SamlibBook from) {
        for (int i = 0; i < log.length; i++)
            try {
                if (log[i][0].equalsIgnoreCase(from.getFileName()))
                    if (from.getLastCheck().getTime() < new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(log[i][2]).getTime()||
                            Calendar.getInstance().get(Calendar.YEAR) != year ||
                            Calendar.getInstance().get(Calendar.MONTH) + 1 != mouth ||
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH) != day) {
                        try {
                            int oldSize = from.getLastSize();
                            if (Integer.parseInt(log[i][11].substring(0, log[i][11].length() - 1)) - oldSize > 2) {
                                from.setLastUpdate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(log[i][2]));
                                from.setLastSize(Integer.parseInt(log[i][11].substring(0, log[i][11].length() - 1)));
                                UpdateManager.updateEvent(new UpdateEvent(from, Integer.parseInt(log[i][11].substring(0, log[i][11].length() - 1)) - oldSize));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        try {
            from.setLastCheck(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(year+"-"+mouth+"-"+day + " " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" +
            Calendar.getInstance().get(Calendar.MINUTE)+":"+
            Calendar.getInstance().get(Calendar.SECOND)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
