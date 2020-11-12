package utils;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class MessageHandler {

    MessageReceivedEvent event;

    public void setEvent(MessageReceivedEvent event) {
       this.event = event;
    }

    public void pong() {
        event.getChannel().sendMessage("pong").queue();
    }

    public void spamm(int num) {
        if(checkUser("6619") || checkUser("1898")) {
            for (int i = 1; i <= num; i++)
                event.getChannel().sendMessage("hi").queue();
        }
    }
    
    public void clear(int numOfMessages) {

        new Thread(() ->
        {
            List<Message> messagesToDelete = event.getChannel().getHistory().retrievePast(numOfMessages + 1).complete();
            event.getChannel().purgeMessages(messagesToDelete);
        }).start();
    }

    private boolean checkUser(String tag) {
        String author = event.getAuthor().getAsTag();

        return tag.equals(author.substring(author.length() - 4));
    }
}
