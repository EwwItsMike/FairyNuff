package commands.embedCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class GatheringEmbedCommand extends EmbedCommandTemplate {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        title = "Gathering Clues";
        description = "";
        color = 0x04336b;

        addField("__Easy Clues <:Easy:1087884597334904892>__", "For Easy clues you pickpocket HAM Members.\nThis [guide](https://discord.com/channels/332595657363685377/1024155876552085594)includes all the information you need.");
        addField("__Medium Clues <:Medium:1087884887295537202>__", "For Medium clues the optimal method is to gather hard clues and downgrade.");
        addField("__Hard Clues <:Hard:1087885141201911939>__", "For Hard clues you kill either Glacytes or Hellhounds.\nThese guides include all the information you need.\n[Glacytes](https://discord.com/channels/332595657363685377/1024156645267353630) - [Hellhounds](https://discord.com/channels/332595657363685377/1024158713310892112) - [Hellhounds w/ Dummies](https://discord.com/channels/332595657363685377/1066194703550267393)");
        addField("__Elite Clues <:Elite:1087885422744584232>__", "For Elite clues you can kill either Abyssal Beasts or Abyssal Lords. You can also Prosper Smith or do Croesus front skilling.\nThese guides include all the information you need.\n[Abyssal Beasts](https://discord.com/channels/332595657363685377/1024158967754129499) - [Abyssal Lords](https://discord.com/channels/332595657363685377/1024159311913566258) - [Prosper Smithing](https://discord.com/channels/332595657363685377/1024159486644072468) - [Croesus front skilling](https://media.discordapp.net/attachments/862423583400394834/980880520755949638/BikBuk_Gathering.png)");

        super.execute(event);
    }
}
