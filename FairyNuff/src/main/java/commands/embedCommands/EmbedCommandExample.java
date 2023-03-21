package commands.embedCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class EmbedCommandExample extends EmbedCommandTemplate {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        title = "Hi, this is a test"; //it says title but it's really just the first header
        description = "Haha description go brrrrrr"; //it says description but it's really just the first bit of text. This is usually left blank. when it's blank it will be -> description = "";
        color = 0xFD9D06; //this is the colour on the left hand side of the embed

        addField("Title of field 1", "Description of field 1");                                                                                                //the first "text here", is the title whilst the second is a description
        addField("Another field! :o", "Wow it's like magic!\nAlso, because of that backslash + n, this is on a new line!");                                    //if you want to add a break (enter key) you would use \n note that if you add a space between the \n and your text there will be a space before the text
        addField("I don't believe fields can have emojis <:KEKWait:673504466871386112>", "But descriptions should be able to <:KEKW:669922267676934164>");
        addField("Links can be done in this format", "[Text goes here](https://www.youtube.com/watch?v=dQw4w9WgXcQ)");

        super.execute(event); //this HAS to be there
    }
}
// this is what it comes out as https://media.discordapp.net/attachments/862423583400394834/1087859419666202716/image.png
