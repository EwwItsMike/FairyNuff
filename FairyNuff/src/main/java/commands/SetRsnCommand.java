package commands;

import data.DataManager;
import data.DbConnection;
import exceptions.RsnAlreadyOccupiedException;
import exceptions.UserAlreadyExistsException;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Objects;

public class SetRsnCommand extends Command {

    DataManager dataManager;

    public SetRsnCommand(long serverId) {
       dataManager = DbConnection.getInstance().getDataManager();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        User user = event.getUser();
        String rsn = Objects.requireNonNull(event.getOption("rsn")).getAsString();

        if (rsn.length() > 12){
            event.reply("The name you entered is too long. Please try again.").queue();
            return;
        }

        try {
            dataManager.addNewUser(user.getIdLong(), rsn);
            event.reply("I've added you to the datafile! Use /clues to update your roles.").queue();
        } catch (UserAlreadyExistsException e) {
            dataManager.updateUser(user.getIdLong(), rsn);
            event.reply("I have updated your username!").queue();
        } catch (RsnAlreadyOccupiedException e) {
            event.reply("This RSN is already registered! If this is incorrect, please create a https://discord.com/channels/332595657363685377/1074856081160929290 ticket.").queue();
        }
    }
}
