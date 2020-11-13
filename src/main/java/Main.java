import listeners.MessageEventListener;
import listeners.PassiveEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import secretsanta.SecretSanta;
import utils.Console;
import utils.json.ConfigManager;

import javax.security.auth.login.LoginException;

public class Main {

    public static final String token = ConfigManager.getToken();
    public PassiveEventListener passiveEventListener;

    public static void main(String[] args) {
        JDABuilder builder = JDABuilder.createDefault(token);

        SecretSanta strawPollEvent = new SecretSanta();
        MessageEventListener messageEvent = new MessageEventListener();

        //sends messages and stuff
        PassiveEventListener passiveEventListener = new PassiveEventListener();
        Console console = new Console();

        builder.addEventListeners(messageEvent);
        builder.addEventListeners(strawPollEvent);
        JDA jda;

        try {
            jda = builder.build();
            passiveEventListener.setJda(jda);
            passiveEventListener.init();
            passiveEventListener.start();
            console.init();
            console.start();

        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
