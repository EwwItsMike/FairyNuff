package data;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class BotSettings implements Serializable {
    private ArrayList<Long> postingChannels = new ArrayList<Long>();
    private String prefix = "=";


    public ArrayList<Long> getPostingChannels() {
        return postingChannels;
    }

    public void setPostingChannels(ArrayList<Long> postingChannels) {
        this.postingChannels = postingChannels;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public static BotSettings convertFromJSON(JSONObject o){
        final BotSettings settings = new BotSettings();

        return settings;
    }
}
