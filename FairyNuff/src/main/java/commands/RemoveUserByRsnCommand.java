package commands;

import data.DataManager;
import data.DbConnection;
import exceptions.UserNotFoundException;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Objects;

public class RemoveUserByRsnCommand extends Command {

    DataManager dataManager;

    public RemoveUserByRsnCommand(long serverId) {
        dataManager = DbConnection.getInstance().getDataManager(serverId);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            if (dataManager.RemoveUser(Objects.requireNonNull(event.getOption("rsn")).getAsString())) {
                event.reply("User was successfully removed from the datafile.").queue();
            }
        } catch (UserNotFoundException e) {
            event.reply("ERROR: User was not found in the datafile.").queue();
        }
    }
}
