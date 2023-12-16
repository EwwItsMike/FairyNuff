package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class TestCommand extends Command {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("ok").queue();

        event.getChannel().retrieveMessageById("1128842145877467186").queue(m -> {
//            event.getChannel().sendMessageEmbeds(m.getEmbeds().get(0)).queue();
            event.getChannel().sendMessageEmbeds(m.getEmbeds().get(0)).queue();
        });

//        EmbedBuilder builder = new EmbedBuilder();
//        builder.setTitle("Stock for [insert date]");
//        builder.addField("", "uncharted island map\n Unstable air rune \n pulse cores \n d&d token", false);
//        builder.addField("", "Tomorrow: some other items lmao", false);
//
//        event.getChannel().sendMessageEmbeds(builder.build()).queue();

    }
}
