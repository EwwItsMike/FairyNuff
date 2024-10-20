package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class TradingCommand extends Command {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        EmbedBuilder builder = new EmbedBuilder();

        builder.setThumbnail("https://cdn.discordapp.com/emojis/979572448884310066.gif?size=96&quality=lossless");
        builder.setColor(0x0000e0);

        builder.setTitle("Please do not post any trade offers in this server.");
        builder.setDescription("We recommend you read our trading rule (#3) in\n <#1283170720599248938> \n<:transparent:1047157438526271488>");

        builder.addField("For all your trading needs:",
                "Please refer to the official RuneScape Discord server. You can find their marketplace here:\n" +
                        "https://discord.com/channels/303835144073248770/1240277796937535632", false);

        event.getHook().sendMessageEmbeds(builder.build()).queue();
    }


}
