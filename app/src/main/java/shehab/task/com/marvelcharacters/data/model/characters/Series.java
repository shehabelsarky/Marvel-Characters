package shehab.task.com.marvelcharacters.data.model.characters;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;
import shehab.task.com.marvelcharacters.data.db.DataConverter;

import java.util.List;

public class Series {

    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "series_items")
    public List<Items> items;

    public List<Items> getItems(){
        return this.items;
    }
}
