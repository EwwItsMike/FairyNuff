import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BanListener extends ListenerAdapter {

    @Override
    public void onGuildBan(GuildBanEvent event) {
        User user = event.getUser();

        event.getGuild().retrieveBan(event.getUser()).queue(ban -> {
            user.openPrivateChannel().queue(channel -> {
                channel.sendMessage("You were banned from Clue Chasers server. The reason that was given is:\n %s"
                        .formatted(ban.getReason())).queue();
            });
        });
    }
}
