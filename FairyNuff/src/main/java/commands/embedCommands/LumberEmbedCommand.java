package commands.embedCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class LumberEmbedCommand extends EmbedCommandTemplate {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        title = "Lumberyard steps";
        description = "";
        color = 0x9925be;

        addField("__Hard Step__ <:Hard:633728361918758930>", "Lumberyard crate step got moved to [this](https://media.discordapp.net/attachments/894988133794451456/1074683451594256454/image.png)");
        addField("__Elite Steps__ <:Elite:633732493228638209>", "Lumberyard compass steps got move to [these](https://media.discordapp.net/attachments/894988133794451456/1075410402634104952/elite_spots.jpg?width=683&height=683)");

        super.execute(event);
    }
}
