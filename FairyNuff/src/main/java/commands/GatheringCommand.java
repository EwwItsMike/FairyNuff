import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class GatheringCommand {
    public MessageEmbed getGatheringEmbed() {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setTitle("Gathering");
        builder.setDescription("");
        builder.setColor(39432);

        builder.addField("__Easy Clues <:Easy:>__",
                "For Easy clues you pickpocket HAM Members.\nThis [guide](https://discord.com/channels/332595657363685377/1024155876552085594)includes all the information you need.",
                false);
        builder.addField("__Medium Clues <:Medium:>__",
                "For Medium clues the optimal method is to gather hard clues and downgrade.",
                false);
        builder.addField("__Hard Clues <:Hard:>__",
                "For Hard clues you kill either Glacytes or Hellhounds.\nThese guides include all the information you need.\n[Glacytes](https://discord.com/channels/332595657363685377/1024156645267353630) - [Hellhounds](https://discord.com/channels/332595657363685377/1024158713310892112) - [Hellhounds w/ Dummies](https://discord.com/channels/332595657363685377/1066194703550267393)",
                false);
        builder.addField("__Elite Clues <:Elite:>__",
                "For Elite clues you can kill either Abyssal Beasts or Abyssal Lords. You can also Prosper Smith or do Croesus front skilling.\nThese guides include all the information you need.\n[Abyssal Beasts](https://discord.com/channels/332595657363685377/1024158967754129499) - [Abyssal Lords](https://discord.com/channels/332595657363685377/1024159311913566258) - [Prosper Smithing](https://discord.com/channels/332595657363685377/1024159486644072468) - [Croesus front skilling](https://media.discordapp.net/attachments/862423583400394834/980880520755949638/BikBuk_Gathering.png)",
                false);

        return builder.build();
    }
}
