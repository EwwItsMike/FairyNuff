package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class TestCommand extends Command {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Test reply").queue();
    }
}
