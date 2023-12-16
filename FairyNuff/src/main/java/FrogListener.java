import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.time.Duration;

public class FrogListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if (isFrog(event.getAuthor().getId()) && containsTag(event.getMessage())){
            event.getGuild().timeoutFor(event.getAuthor(), Duration.ofMinutes(1)).queue();
        }
    }

    private boolean isFrog(String messageAuthor) {
        return messageAuthor.equals("175267021682900992");
    }

    private boolean containsTag(Message message) {
        return message.getMentions().getMentions().size() > 0;
    }
}
