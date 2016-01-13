package sa.revenue.general.model;

/**
 * Created by un on 1/10/2016.
 */
public class Message {
    private Advertiser advertiser;
    private Type type;

    public Message(Advertiser advertiser, Type type) {
        this.advertiser = advertiser;
        this.type = type;
    }

    public enum Type {
        UPDATE_API_STATUS,
        PARSING_NEXT_DAY,
        PARSING_DONE
    }

    public Advertiser getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(Advertiser advertiser) {
        this.advertiser = advertiser;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
