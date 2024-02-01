import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.List;

public class CommandsManager extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {

        List<SlashCommandData> commands = new ArrayList<>();

        OptionData tagRoles = new OptionData(OptionType.STRING, "role", "The role to tag", true)
                .addChoice("Clue News", "<@&1064540154125111386>").addChoice("Clue Records", "<@&1071487069232308224>")
                .addChoice("Wicked hood promo", "<@&1064548406548242442>").addChoice("Scan Trainer", "<@&1104143964438810724>")
                .addChoice("Infohub Changes", "<@&1064542461776637992>");

        //Clue Chasers - Only commands
        if (event.getGuild().getIdLong() == Long.parseLong("332595657363685377")){
            commands.add(Commands.slash("removeuserbyrsn", "Remove a user from the datafile")
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
                    .addOption(OptionType.STRING, "rsn", "RSN of the person to be removed", true));

            commands.add(Commands.slash("removeuserbyid", "Remove a user from the datafile")
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
                    .addOption(OptionType.NUMBER, "id", "Discord ID of the person to be removed", true));

            commands.add(Commands.slash("tag", "Tags the selected role in a message. Use \\ character to indicate new line.")
                    .addOptions(tagRoles)
                    .addOption(OptionType.STRING, "message", "The message to send with the tag. Use \\ character to indicate new line.", false));

            commands.add(Commands.slash("trading", "Returns information about trading within Clue Chasers."));

            commands.add(Commands.slash("updatefrontpage", "Removes frontpage role from people that should no longer have it."));

            commands.add(Commands.slash("oystervalue", "Randomly rolls a value between 25k and 4m for the Oyster competition target value."));

            commands.add(Commands.slash("randomparticipant", "Rolls a random participant of the Oyster competition.")
                    .addOption(OptionType.STRING, "messageid", "The ID of the announcement message at the start of the competition.", true));
        }

        //Personal test server - only commands
        if (event.getGuild().getIdLong() == Long.parseLong("585386371968139274")){
            commands.add(Commands.slash("test", "Replies with a test reply.").addOption(OptionType.STRING, "test", "test", false));
        }


        //Globally available commands
        commands.add(Commands.slash("lookup", "Looks up a player's clue stats")
                .addOption(OptionType.STRING, "rsn", "RSN of the person you're trying to look up.", true));

        commands.add(Commands.slash("setrsn", "Set your in-game username.")
                .addOption(OptionType.STRING, "rsn" ,  "Your RSN", true));

        commands.add(Commands.slash("clues", "Updates your clue counts in the bot and gives you the stats."));

        commands.add(Commands.slash("invite", "Receive invite information for the bot"));


        for (SlashCommandData c : commands){
            event.getGuild().upsertCommand(c).queue();
        }

        event.getJDA().updateCommands().queue();
    }

}
