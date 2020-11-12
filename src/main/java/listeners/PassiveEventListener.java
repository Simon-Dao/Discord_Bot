package listeners;

import utils.ID;
import net.dv8tion.jda.api.JDA;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * runs events without needing a listener
 */
public class PassiveEventListener {

    private ID ids = new ID();
    public static String targetTime = "15:02";
    public static boolean sent = false;
    public static String time;
    private JDA jda;
    public Thread listener;

    public void setJda(JDA jda) {
        this.jda = jda;
    }

    public void init() {
        listener = new Thread(() -> {
            String time;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            String message = "fuck you tq";

            while(true) {
                Date date = new Date(System.currentTimeMillis());
                time = formatter.format(date).substring(11);

                if(time.startsWith(targetTime) && !sent) {
                    jda
                        .getGuildById(ids.GARFIELD_ROBOTICS_SERVER.id)
                        .getTextChannelById(ids.GARFIELD_ROBOTICS_SERVER.channels
                        .get("utils"))
                        .sendMessage(message)
                        .queue();
                    sent = true;
                }
            }
        });
    }

    public void start() {
        listener.start();
    }
}