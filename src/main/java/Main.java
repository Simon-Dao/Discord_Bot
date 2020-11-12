import listeners.MessageEventListener;
import listeners.PassiveEventListener;
import secretsanta.SecretSanta;
import utils.Console;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    public static final String token = "NzU0Mzk2NjUxODM3NDU2Mzky.X10IoQ.e08u7kBCjnVnCtBMd23DGGF4uQA";
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
