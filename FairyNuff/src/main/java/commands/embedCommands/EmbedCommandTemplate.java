package commands.embedCommands;
import commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;

public class EmbedCommandTemplate extends Command {

    protected String title = "";
    protected String description = "";
    protected String footer = "";

    protected int color = 0;

    protected ArrayList<EmbedField> embedFields = new ArrayList<>();


    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setDescription(description);
        builder.setFooter(footer);
        builder.setColor(color);

        for (EmbedField field : embedFields){
            builder.addField(field.title, field.description, false);
        }

        event.getHook().sendMessageEmbeds(builder.build()).queue();
    }

    protected void addField(String title, String description) {
        embedFields.add(new EmbedField(title, description));
    }
}
