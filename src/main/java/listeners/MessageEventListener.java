package listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import utils.MessageHandler;

public class MessageEventListener extends ListenerAdapter {

    private final String prefix = "!";
    private MessageReceivedEvent event;
    private String messageRecieved;
    private String[] args;

    private MessageHandler messageHandler = new MessageHandler();
    
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        try {
            this.event = event;
            messageHandler.setEvent(event);
            messageRecieved = event.getMessage().getContentRaw();
            args = messageRecieved.split(" ");

            switch (args[0]) {
                case prefix+"ping":
                    messageHandler.pong();
                    break;
                case prefix+"clear":
                    if(args.length == 2)
                        messageHandler.clear(Integer.parseInt(args[1]));
                    break;
                case prefix+"spamm":
                    if(args.length == 2)
                        messageHandler.spamm(Integer.parseInt(args[1]));
                    break;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private boolean messageContains(String targetStr) {
        if(!event.getAuthor().isBot() && messageRecieved.contains(targetStr)) return true;
        else return false;
    }
}
