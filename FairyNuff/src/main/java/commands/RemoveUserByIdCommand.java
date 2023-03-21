package commands;

import data.DataManager;
import data.DbConnection;
import exceptions.UserNotFoundException;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Objects;

public class RemoveUserByIdCommand extends Command {

    DataManager dataManager;

    public RemoveUserByIdCommand(long id) {
        dataManager = DbConnection.getInstance().getDataManager(id);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            if (dataManager.RemoveUser(Objects.requireNonNull(event.getOption("id")).getAsLong())) {
                event.reply("User was successfully removed from the datafile.").queue();
            }
        } catch (UserNotFoundException e) {
            event.reply("ERROR: User was not found in the datafile.").queue();
        }
    }

}
