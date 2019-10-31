package shehab.task.com.marvelcharacters.data.model.characters;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Results implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public String description;

    public String modified;

    @Embedded
    public Thumbnail thumbnail;

    public String resourceURI;

    @Embedded
    public Comics comics;

    @Embedded
    public Series series;

    @Embedded
    public Stories stories;

    @Embedded
    public Events events;

    public int type = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getModified() {
        return modified;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public Comics getComics() {
        return comics;
    }

    public Series getSeries() {
        return series;
    }

    public Stories getStories() {
        return stories;
    }

    public Events getEvents() {
        return events;
    }
}
