import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Locale;

public class DsfMerchListener extends ListenerAdapter {

    Long privateServerID = Long.parseLong("585386371968139274");
    Long testChannelID = Long.parseLong("863765389468499999");
    Long clueChasersID = Long.parseLong("332595657363685377");
    Long clueChasersRemindersChannelID = Long.parseLong("1128747827334492180");

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.isFromGuild()) return;

        if (!event.getGuild().equals(event.getJDA().getGuildById("585386371968139274"))
            || !event.getChannel().equals(event.getJDA().getChannelById(MessageChannel.class, "1128752832196116480")))
            return;

        if (event.getMessage().getEmbeds().size() == 0){
            System.out.println("No embeds in message");
            return;
        }

        MessageEmbed embed = event.getMessage().getEmbeds().get(0);

        if (isUnstableAirRuneInStock(embed.getDescription())){
            String toSend = "<@&1064548468611350548>\nThe Travelling Merchant has Unstable Air Rune in stock today!" +
                    "\nGo to https://discord.com/channels/420803245758480405/789279009333575700 for spawn worlds." +
                    "\n\n *This message is powered by Deep Sea Fishing server and Merchant bot created by Proclivity*";

            event.getJDA().getGuildById(clueChasersID).getChannelById(MessageChannel.class, clueChasersRemindersChannelID)
                    .sendMessage(toSend).queue();
        }
    }

    private boolean isUnstableAirRuneInStock(String message) {
        String todayStock = message.toLowerCase(Locale.ROOT);

        if (!todayStock.contains("tomorrow"))
            return false;

        todayStock = todayStock.substring(0, todayStock.indexOf("tomorrow"));
        return todayStock.contains("unstable air rune");
    }
}
