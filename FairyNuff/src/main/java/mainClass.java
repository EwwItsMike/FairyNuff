import commands.*;
import commands.embedCommands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class mainClass extends ListenerAdapter {

    public static void main(String[] args) {
        String token = "";

        try (FileReader read = new FileReader("token.txt")){
            int i;
            while ((i = read.read())!= -1){
                token += (char)i;
            }
        } catch (FileNotFoundException e) {
            System.out.println("token.txt was not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .addEventListeners(new mainClass(), new FairEnoughListener(), new DsfMerchListener())
                .setActivity(Activity.listening("/commands"))
                .build();


        jda.addEventListener(new CommandsManager());
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        Command c = null;
        switch (event.getName()){
            case "test":
                c = new TestCommand();
                break;
            case "setrsn":
                c = new SetRsnCommand(Objects.requireNonNull(event.getGuild()).getIdLong());
                break;
            case "removeuserbyrsn":
                c = new RemoveUserByRsnCommand(Objects.requireNonNull(event.getGuild()).getIdLong());
                break;
            case "removeuserbyid":
                c = new RemoveUserByIdCommand(Objects.requireNonNull(event.getGuild()).getIdLong());
                break;
            case "clues":
                c = new CluesCommand(Objects.requireNonNull(event.getGuild()).getIdLong());
                break;
            case "lookup":
                c = new LookupCommand();
                break;
            case "tag":
                c = new TagCommand();
                break;
            case "trading":
                c = new TradingCommand();
                break;
            case "updatefrontpage":
                c = new UpdateFrontpageCommand();
                break;
            case "invite":
                c = new InviteCommand();
                break;
            case "oystervalue":
                c = new RollOysterValueCommand();
                break;
            case "randomparticipant":
                c = new RollRandomOysterParticipantCommand();
                break;
            default:
                break;
        }

        // Execute the event
        if (c != null)
            c.execute(event);
    }
}
