package commands.embedCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class GatheringEmbedCommand extends EmbedCommandTemplate {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        title = "Gathering Clues";
        description = "";
        color = 0x04336b;

        addField("__Easy Clues__ <:Easy:633728862039179294>", "For Easy clues you pickpocket HAM Members.\nThis [guide](https://discord.com/channels/332595657363685377/1024155876552085594) includes all the information you need.");
        addField("__Medium Clues__ <:Medium:633728704438075435>", "For Medium clues the optimal method is to gather hard clues and downgrade.\nScripture of Bik gathering is also a very good source of Medium clues.");
        addField("__Hard Clues__ <:Hard:633728361918758930>", "For Hard clues you kill either Glacytes or Hellhounds.\nThese guides include all the information you need.\n[Glacytes](https://discord.com/channels/332595657363685377/1024156645267353630) - [Hellhounds](https://discord.com/channels/332595657363685377/1024158713310892112) - [Hellhounds w/ Dummies](https://discord.com/channels/332595657363685377/1066194703550267393)");
        addField("__Elite Clues__ <:Elite:633732493228638209>", "For Elite clues you can kill either Abyssal Beasts or Abyssal Lords.\nThese guides include all the information you need.\n[Abyssal Beasts (off softcap gathering)](https://discord.com/channels/332595657363685377/1024158967754129499) - [Abyssal Lords (on softcap gathering)](https://discord.com/channels/332595657363685377/1024159311913566258)");
        addField("__Scripture of Bik__ <:Bik:908044805475553321>", "For all Clue types you can Prosper Smith or do Croesus front skilling with Scripture of Bik.\nThese guides include all the information you need.\n[Prosper Smithing](https://discord.com/channels/332595657363685377/1024159486644072468) - [Croesus front skilling](https://media.discordapp.net/attachments/862423583400394834/980880520755949638/BikBuk_Gathering.png)");

        super.execute(event);
    }
}
