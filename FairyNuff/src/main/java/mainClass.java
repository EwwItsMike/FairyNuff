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
                .addEventListeners(new mainClass(), new FairEnoughListener(), new MingoListener(), new DsfMerchListener())
                .setActivity(Activity.listening("/commands"))
                .build();

        OptionData tagRoles = new OptionData(OptionType.STRING, "role", "The role to tag", true)
                .addChoice("Clue News", "<@&1064540154125111386>").addChoice("Clue Records", "<@&1071487069232308224>")
                .addChoice("Wicked hood promo", "<@&1064548406548242442>").addChoice("Scan Trainer", "<@&1104143964438810724>")
                .addChoice("Infohub Changes", "<@&1064542461776637992>");

        jda.updateCommands().addCommands(
                Commands.slash("test", "Replies with a test reply.").addOption(OptionType.STRING, "test", "test", false),
                Commands.slash("setrsn", "Set your in-game username.").addOption(OptionType.STRING, "rsn" ,  "Your RSN", true),
                Commands.slash("removeuserbyrsn", "Remove a user from the datafile")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
                        .addOption(OptionType.STRING, "rsn", "RSN of the person to be removed", true),
                Commands.slash("removeuserbyid", "Remove a user from the datafile")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
                        .addOption(OptionType.NUMBER, "id", "Discord ID of the person to be removed", true),
                Commands.slash("clues", "Updates your clue counts in the bot and gives you the stats."),
                Commands.slash("lookup", "Looks up a player's clue stats")
                        .addOption(OptionType.STRING, "rsn", "RSN of the person you're trying to look up.", true),
                Commands.slash("tag", "Tags the selected role in a message. Use \\ character to indicate new line.")
                    .addOptions(tagRoles)
                    .addOption(OptionType.STRING, "message", "The message to send with the tag. Use \\ character to indicate new line.", false),
                Commands.slash("trading", "Returns information about trading within Clue Chasers."),
                Commands.slash("updatefrontpage", "Removes frontpage role from people that should no longer have it")
        ).queue();
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
            default:
                break;
        }

        // Execute the event
        if (c != null)
            c.execute(event);
    }
}
