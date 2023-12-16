import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Duration;
import java.util.Locale;
import java.util.Random;

public class MingoListener extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentStripped().toLowerCase(Locale.ROOT);
        if (message.contains("mingo")){
            event.getMessage().addReaction(Emoji.fromUnicode("U+1F96D")).queue();
//            rollForFrogTimeout(event.getMessage().getAuthor().getIdLong(), event.getGuild());
        }

    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getEmoji().toString().contains("mingo")){
            event.getChannel().retrieveMessageById(event.getMessageId()).queue(message -> {
                message.addReaction(Emoji.fromUnicode("U+1F96D")).queue();
            });
//            rollForFrogTimeout(event.getUserIdLong(), event.getGuild());
        }
    }

//    private void rollForFrogTimeout(Long messageSender, Guild server) {
//        Random r = new Random();
//        int roll = r.nextInt(0,15);
//
//        //is it frog?
//        if (messageSender.equals(Long.parseLong("175267021682900992"))){
//            if (roll == 7){
//                server.getMemberById(Long.parseLong("175267021682900992")).timeoutFor(Duration.ofMinutes(30)).queue();
//            }
//        }
//
//    }

}
