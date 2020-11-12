package utils;

import java.util.ArrayList;
import java.util.HashMap;

public class Guild {

    public String id;

    public Guild(String guildID) {
        this.id = guildID;
    }

    public HashMap<String, String> channels = new HashMap<>();
}
