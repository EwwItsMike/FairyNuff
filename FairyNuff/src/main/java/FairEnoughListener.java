import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class FairEnoughListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()){
            return;
        }
        if (event.getMessage().getContentStripped().contains("fair enough")){
            event.getChannel().sendMessage("%s Fairy Nuff*".formatted(event.getAuthor().getAsMention())).queue();
        }
    }
}
