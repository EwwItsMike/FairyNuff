package commands.embedCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class EmbedCommandExample extends EmbedCommandTemplate {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        title = "Hi, this is a test";
        description = "Haha description go brrrrrr";
        color = 0xFD9D06;

        addField("Title of field 1", "Description of field 1");
        addField("Another field! :o", "Wow it's like magic!\nAlso, because of that backslash + n, this is on a new line!");
        addField("I don't believe fields can have emojis <:KEKWait:673504466871386112>", "But descriptions should be able to <:KEKW:669922267676934164>");
        addField("Links can be done in this format", "[Text goes here](https://www.youtube.com/watch?v=dQw4w9WgXcQ)");

        super.execute(event);
    }
}
