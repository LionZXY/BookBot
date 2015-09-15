package ru.lionzxy.bookbot.samlib;

import java.util.Date;

/**
 * Created by nikit on 15.09.2015.
 */
public class SamlibBook {
    private Date lastUpdate, lastCheck;
    private String name, author, fileName;
    private int lastSize;

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

    public SamlibBook setSize(int size) {
        this.lastSize = size;
        return this;
    }

    public SamlibBook setLastCheck(Date date) {
        this.lastCheck = date;
        return this;
    }

    public String getFileName() {
        return this.fileName;
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

}
