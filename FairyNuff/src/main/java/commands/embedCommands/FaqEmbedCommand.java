package commands.embedCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class FaqEmbedCommand extends EmbedCommandTemplate {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        title = "__Frequently Asked Questions__";
        color = 0x04336b;

        addField("", "• [How do I get a rank in Discord?](https://discord.com/channels/332595657363685377/1022623668691546118)" +
                "\n• [What order for Globetrotter pieces?](https://discord.com/channels/332595657363685377/1024461576314568784)" +
                "\n• [What do I put in Passage of the Abyss?](https://discord.com/channels/332595657363685377/1022566907645263892)" +
                "\n• [What is the softcap?](https://discord.com/channels/332595657363685377/1024690532682829905)" +
                "\n• [I'm not getting clues, why?](https://discord.com/channels/332595657363685377/1022630885209677931)" +
                "\n• [How do movement abilities work](https://discord.com/channels/332595657363685377/1023033271107473458)" +
                "\n• [How does Scripture of Bik work?](https://discord.com/channels/332595657363685377/1022589092262842508)");

        addField("", "• [Fortunate Component Rates](https://discord.com/channels/332595657363685377/1022577507033169980)" +
                "\n• [Broadcast Droprates](https://discord.com/channels/332595657363685377/1024459701288706148)" +
                "\n• [Average Casket Value](https://discord.com/channels/332595657363685377/1022580128020172810)" +
                "\n• [How to make Alchemical Onyx/Hydrix](https://discord.com/channels/332595657363685377/1022944438248542258)" +
                "\n• [Why can I not get prismatic stars from clues?](https://discord.com/channels/332595657363685377/1022940030202806342)" +
                "\n• [What do I spend my points on?](https://discord.com/channels/332595657363685377/1036330221495910541)");

        addField("", "• [How often are Puzzles](https://discord.com/channels/332595657363685377/1022581015258071110)" +
                "\n• [Chance for clue steps](https://discord.com/channels/332595657363685377/1024690151605141587)" +
                "\n• [Fastest way to get Masters?](https://discord.com/channels/332595657363685377/1022584947980116138)" +
                "\n• [Are skipping tickets worth it?](https://discord.com/channels/332595657363685377/1023613462854779030)" +
                "\n• [Dark Facet of Luck](https://discord.com/channels/332595657363685377/1073281107769962569)" +
                "\n• [How many clues/hr does Bik give?](https://discord.com/channels/332595657363685377/1022587718506643516)" +
                "\n• [What does Amulet of Nature do?](https://discord.com/channels/332595657363685377/1059984789807038552)" +
                "\n• [How do the pings/pulses in scans work?](https://discord.com/channels/332595657363685377/1072176239323926630)");

        super.execute(event);
    }
}
