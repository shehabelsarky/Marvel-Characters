package shehab.task.com.marvelcharacters.data.model.characters;

import java.util.List;

public class Data {
    public int offset;

    public int limit;

    public int total;

    public int count;

    public List<Results> results;

    public int getOffset(){
        return this.offset;
    }
    public int getLimit(){
        return this.limit;
    }
    public int getTotal(){
        return this.total;
    }
    public int getCount(){
        return this.count;
    }
    public List<Results> getResults(){
        return this.results;
    }
}
