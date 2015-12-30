package ru.lionzxy.bookbot.samlib;

import ru.lionzxy.bookbot.helper.StringHelper;
import ru.lionzxy.bookbot.helper.URLHelper;

import java.util.Date;

/**
 * Created by nikit on 15.09.2015.
 */
public class SamlibBook {
    private Date lastUpdate, lastCheck;
    private String name, author, fileName;
    private int lastSize, id;

    public SamlibBook(String fileName) {
        this.fileName = fileName;
    }

    public SamlibBook setLastUpdate(Date date) {
        this.lastUpdate = date;
        return this;
    }

    public SamlibBook setName(String name) {
        this.name = name;
        return this;
    }

    public SamlibBook setAuthor(String author) {
        this.author = author;
        return this;
    }

    public SamlibBook setLastSize(int size) {
        this.lastSize = size;
        return this;
    }

    public SamlibBook setLastCheck(Date date) {
        this.lastCheck = date;
        return this;
    }


    public SamlibBook setId(int id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return this.fileName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public Date getDate() {
        return this.lastUpdate;
    }

    public Date getLastCheck() {
        return this.lastCheck;
    }

    public int getLastSize() {
        return this.lastSize;
    }

    public String toString() {
        return "Дата последнего обновления: " + lastUpdate + "\n" +
                "Имя книги: " + name + "\n" +
                "Автор: " + author + "\n" +
                "FileName: " + fileName + "\n" +
                "Последний размер: " + lastSize + "\n" +
                "Последнее обновление: " + lastCheck;
    }

    public SamlibBook copy() {
        return new SamlibBook(this.getFileName()).setLastCheck(this.getLastCheck()).setAuthor(this.getAuthor()).setLastUpdate(this.getDate()).setName(this.getName()).setLastSize(this.getLastSize()).setId(this.getId());
    }

    public String getAnnotation() {
        String page[] = URLHelper.getSite("http://samlib.ru/" + getFileName() + ".shtml");
        String keyword = "<ul><small><li></small><b>Аннотация:</b><br><font color=\"#555555\"><i>";
        int wordInt;
        for (String line : page) {
            wordInt = StringHelper.findWord(line, keyword, true);
            if (wordInt != -1) {
                return line.substring(wordInt + keyword.length(), line.indexOf('<', wordInt + keyword.length()));
            }
        }
        return "Аннотация не найдена";
    }
}
