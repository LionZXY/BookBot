package ru.lionzxy.bookbot.libs;

import ru.lionzxy.bookbot.samlib.SamlibBook;
import ru.lionzxy.bookbot.updatemanager.UpdateManager;

/**
 * Created by nikit_000 on 18.09.2015.
 */
public class UpdateEvent {
    private SamlibBook book;
    private int changeSize;

    public UpdateEvent(SamlibBook book,int changeSize){
        this.book = book;
        this.changeSize = changeSize;
    }

    public SamlibBook getBook(){
        return book;
    }

    public int getChangeSize(){
        return changeSize;
    }
}
