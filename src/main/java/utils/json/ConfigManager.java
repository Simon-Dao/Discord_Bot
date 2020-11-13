package utils.json;

import com.google.gson.Gson;

import java.io.FileReader;

public class ConfigManager {

    private static final Gson gson = new Gson();

    public static String getToken() {
        //get token from json file
        try {
            return gson.fromJson(new FileReader("token.json"), Token.class).getToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
