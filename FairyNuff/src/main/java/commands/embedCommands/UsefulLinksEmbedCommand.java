package commands.embedCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class UsefulLinksEmbedCommand extends EmbedCommandTemplate{

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        title = "__Useful Links__";
        color = 0x04336b;
        description = "[Clue Records](https://docs.google.com/spreadsheets/d/e/2PACX-1vTbkLwyBt5PhUI4dCBJtb-fiTP1QqhStSWdIvioLmj1TDm_kcu0sWeQq89tQCxUhpK1gTJXsVhHBDdk/pubhtml#) " +
                "- [Clue Simulator](https://discord.com/channels/332595657363685377/1022626154970107984) " +
                "- [Point Calculator](https://discord.com/channels/332595657363685377/1022626307760214046) " +
                "- [Daily Clue Completions](https://docs.google.com/spreadsheets/d/1RfxcA5VmEX4xtwAnRnSKQCmgHwaLhEYlFR0BH90tQNU/edit#gid=465613571)";

        addField("", "[Clue Requirements Optimizer](https://docs.google.com/spreadsheets/d/1litrE084z4LRRdVOnoXPiH7yQ6l2l0L7GxGiQzlBgw8/edit#gid=0) " +
                "- [Interactive Maps](https://discord.com/channels/332595657363685377/1022632784478621746) " +
                "- [How many clues to complete a log?](https://runescape.wiki/w/User:Intercal/ClueCollectionLogCalculator)");

        addField("", "[Fortunate Price Calculator](https://docs.google.com/spreadsheets/d/1oV5IKHI4-mNEEx8fU7EWtuZR7PogMedyOCzE2y0pq8E/edit#gid=0) " +
                "- [Broadcast Google Sheets Template](https://docs.google.com/spreadsheets/d/1rhtbJJc58H_USbcSrsLD1_AmOQEXOBrOQphlhA08QLs/edit?usp=sharing) " +
                "- [Desired Clue Updates](https://docs.google.com/spreadsheets/d/1HFJoaLWNI339y7iJVGqeOz3RvubJsFCGAUG7zXzrfnw/edit#gid=375639779)");

        super.execute(event);
    }
}
