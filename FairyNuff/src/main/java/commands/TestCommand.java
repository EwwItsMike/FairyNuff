package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class TestCommand extends Command {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(event.getUser().getName());
        builder.addField("Field header", "Field content", true);
        builder.addField("Field header", "Field content", true);
        builder.addField("", "[Join Clue Chasers!](<https://discord.com/invite/cluechasers>)", false);

        event.getHook().sendMessageEmbeds(builder.build()).queue();

    }
}
