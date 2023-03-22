package commands.embedCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class FaqEmbedCommand extends EmbedCommandTemplate {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        title = "Frequently asked questions";
        description = "";
        color = 0x04336b;

        addField("General FAQ", "[How do I get a rank in Discord?](https://discord.com/channels/332595657363685377/1022623668691546118) " +
                "- [What do I put in Passage of the Abyss](https://discord.com/channels/332595657363685377/1022566907645263892) " +
                "- [What order for Globetrotter pieces](https://discord.com/channels/332595657363685377/1024461576314568784)");

        addField("", "[Fortunate Component Rates](https://discord.com/channels/332595657363685377/1022577507033169980) " +
                "- [Average Casket Value](https://discord.com/channels/332595657363685377/1022580128020172810) " +
                "- [How often are Puzzles](https://discord.com/channels/332595657363685377/1022581015258071110) " +
                "- [Fastest way to get Masters?](https://discord.com/channels/332595657363685377/1022584947980116138)");

        addField("", "[Broadcast Droprates](https://discord.com/channels/332595657363685377/1024459701288706148) " +
                "- [How does Scripture of Bik work?](https://discord.com/channels/332595657363685377/1022589092262842508) " +
                "- [I'm not getting clues, why?](https://discord.com/channels/332595657363685377/1022630885209677931) " +
                "- [What is the softcap?](https://discord.com/channels/332595657363685377/1024690532682829905)");

        addField("", "[Chance for clue steps](https://discord.com/channels/332595657363685377/1024690151605141587) " +
                "- [Why can I not get prismatic stars from clues?](https://discord.com/channels/332595657363685377/1022940030202806342) " +
                "- [How to make Alchemical Onyx/Hydrix](https://discord.com/channels/332595657363685377/1022944438248542258)");

        addField("", "[How do movement abilities work](https://discord.com/channels/332595657363685377/1023033271107473458) " +
                "- [What do I spend my points on?](https://discord.com/channels/332595657363685377/1036330221495910541) " +
                "- [Are skipping tickets worth it?](https://discord.com/channels/332595657363685377/1023613462854779030) " +
                "- [Dark Facet of Luck](https://discord.com/channels/332595657363685377/1073281107769962569)");

        addField("", "[How many clues do I get an hour from Scripture of Bik?](https://discord.com/channels/332595657363685377/1022587718506643516) " +
                "- [What does Amulet of Nature do?](https://discord.com/channels/332595657363685377/1059984789807038552) " +
                "- [How do the pings/pulses in scans work?](https://discord.com/channels/332595657363685377/1072176239323926630)");

        addField("Alt1 FAQ", "[Alt1 general information](https://discord.com/channels/332595657363685377/1022946965039886346) " +
                "- [Alt1 isn't working](https://discord.com/channels/332595657363685377/1022625057165549640) " +
                "- [Alt1 for compass clues](https://discord.com/channels/332595657363685377/1023031997653848104) " +
                "- [Alt1 Bik Clue Tracker](https://discord.com/channels/332595657363685377/1022627285024645131)");

        addField("", "[Alt1 for MacOS and Linux](https://discord.com/channels/332595657363685377/1022628230655639592) " +
                "- [Alt1 Bik Proc Timer](https://discord.com/channels/332595657363685377/1022628445731176538) " +
                "- [Alt1 Clue Timer](https://discord.com/channels/332595657363685377/1022628688480710696) " +
                "- [Alt1 Clue Reward Tracker](https://discord.com/channels/332595657363685377/1022629122113024040)");

        addField("Useful Links", "[Clue Records](https://docs.google.com/spreadsheets/d/e/2PACX-1vTbkLwyBt5PhUI4dCBJtb-fiTP1QqhStSWdIvioLmj1TDm_kcu0sWeQq89tQCxUhpK1gTJXsVhHBDdk/pubhtml#) " +
                "- [Clue Simulator](https://discord.com/channels/332595657363685377/1022626154970107984) " +
                "- [Point Calculator](https://discord.com/channels/332595657363685377/1022626307760214046) " +
                "- [Daily Clue Completions](https://docs.google.com/spreadsheets/d/1RfxcA5VmEX4xtwAnRnSKQCmgHwaLhEYlFR0BH90tQNU/edit#gid=465613571)");

        addField("", "[Clue Requirements Optimizer](https://docs.google.com/spreadsheets/d/1litrE084z4LRRdVOnoXPiH7yQ6l2l0L7GxGiQzlBgw8/edit#gid=0) " +
                "- [Interactive Maps](https://discord.com/channels/332595657363685377/1022632784478621746) " +
                "- [How many clues to complete a log?](https://runescape.wiki/w/User:Intercal/ClueCollectionLogCalculator)");

        addField("", "[Fortunate Price Calculator](https://docs.google.com/spreadsheets/d/1oV5IKHI4-mNEEx8fU7EWtuZR7PogMedyOCzE2y0pq8E/edit#gid=0) " +
                "- [Broadcast Google Sheets Template](https://docs.google.com/spreadsheets/d/1rhtbJJc58H_USbcSrsLD1_AmOQEXOBrOQphlhA08QLs/edit?usp=sharing) " +
                "- [Desired Clue Updates](https://docs.google.com/spreadsheets/d/1HFJoaLWNI339y7iJVGqeOz3RvubJsFCGAUG7zXzrfnw/edit#gid=375639779)");

        super.execute(event);
    }
}
