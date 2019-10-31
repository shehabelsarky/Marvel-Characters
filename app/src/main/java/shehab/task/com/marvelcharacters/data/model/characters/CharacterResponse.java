package shehab.task.com.marvelcharacters.data.model.characters;

public class CharacterResponse {
    public int code;

    public String status;

    public String copyright;

    public String attributionText;

    public String attributionHTML;

    public String etag;

    public Data data;


    public int getCode(){
        return this.code;
    }
    public String getStatus(){
        return this.status;
    }
    public String getCopyright(){
        return this.copyright;
    }
    public String getAttributionText(){
        return this.attributionText;
    }
    public String getAttributionHTML(){
        return this.attributionHTML;
    }
    public String getEtag(){
        return this.etag;
    }
    public Data getData(){
        return this.data;
    }


}
