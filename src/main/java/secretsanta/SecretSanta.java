package secretsanta;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SecretSanta extends ListenerAdapter {

    private int reactions = 0;
    private ArrayList<Santa> santas = new ArrayList<>();
    private long messageId = 0;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        MessageChannel channel = event.getChannel();

        if(message.equals("!poll init")) {

            EmbedBuilder embed = new EmbedBuilder();
            embed.setImage("https://cdn.discordapp.com/attachments/762766815691800627/775565265353703434/artworks-000459298566-3l0iqo-t500x500.png");
            embed.addField("Instructions", "", false);
            embed.addField("1", "add a reaction to this message to enter", false);
            embed.addField("2", "to send a gift suggestion to your secret santa put \n!wish followed by your suggestion\n for example \n!wish gamer girl goth gf", false);

            channel.sendMessage(embed.build()).queue(m -> {
                messageId = m.getIdLong();
            });
        }

        if(message.equals("!poll start")) {
            if(reactions % 2 == 0)
                channel.sendMessage("we need an even amount of ppl sorry").queue();
            else {
                channel.sendMessage("merry christmas!!!!!").queue();
                new ChristmasGiftBuilder().build(santas);
            }
        }

        if(message.startsWith("!wish")) {
            //add the wish to the santa's wishlist
            santas.stream().filter(santa -> santa.user.getAsTag().equals(event.getAuthor().getAsTag()))
            .forEach(
                    santa -> {
                        santa.wishlist.add(message.substring(message.indexOf(" ")));
                    }
            );
            channel.purgeMessages(channel.getHistory().retrievePast(1).complete());
        }
    }

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        if(messageId == event.getMessageIdLong()) {
            User user = event.getUser();
            reactions++;
            System.out.println(user.getName());

            //only add the person if they have not reacted yet
            if (santas.stream().filter(santa -> santa.user.getAsTag().equals(user.getAsTag())).count() == 0)
                santas.add(new Santa(event.getUser()));
            //else
            //  System.out.println("bad "+event.getReaction().toString());

            System.out.println(santas.toString());
        }
    }

    @Override
    public void onMessageReactionRemove(@NotNull MessageReactionRemoveEvent event) {
        if(messageId == event.getMessageIdLong()) {
            User user = event.getUser();
            reactions--;
            santas.removeIf(santa -> santa.user.getAsTag().equals(user.getAsTag()));

            System.out.println(santas.toString());
        }
    }
}

class Santa {
    public ArrayList<String> wishlist = new ArrayList<>();
    public User user;

    public Santa(User user) {
        this.user = user;
    }
}