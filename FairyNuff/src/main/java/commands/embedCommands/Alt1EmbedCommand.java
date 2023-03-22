package commands.embedCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Alt1EmbedCommand extends EmbedCommandTemplate{

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        title = "__Alt1 Frequently Asked Questions__";
        color = 0x04336b;

        description = "[Alt1 general information](https://discord.com/channels/332595657363685377/1022946965039886346) " +
                "- [Alt1 isn't working](https://discord.com/channels/332595657363685377/1022625057165549640) " +
                "- [Alt1 for compass clues](https://discord.com/channels/332595657363685377/1023031997653848104) " +
                "- [Alt1 Bik Clue Tracker](https://discord.com/channels/332595657363685377/1022627285024645131)";

        addField("", "[Alt1 for MacOS and Linux](https://discord.com/channels/332595657363685377/1022628230655639592) " +
                "- [Alt1 Bik Proc Timer](https://discord.com/channels/332595657363685377/1022628445731176538) " +
                "- [Alt1 Clue Timer](https://discord.com/channels/332595657363685377/1022628688480710696) " +
                "- [Alt1 Clue Reward Tracker](https://discord.com/channels/332595657363685377/1022629122113024040)");

        super.execute(event);
    }
}
