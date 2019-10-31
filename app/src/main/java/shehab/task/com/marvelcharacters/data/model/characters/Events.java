package shehab.task.com.marvelcharacters.data.model.characters;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;
import shehab.task.com.marvelcharacters.data.db.DataConverter;

import java.util.List;

public class Events {

    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "events_items")
    public List<Items> items;

    public void setItems(List<Items> items){
        this.items = items;
    }
    public List<Items> getItems(){
        return this.items;
    }

}
