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
                .addEventListeners(new mainClass(), new FairEnoughListener())
                .setActivity(Activity.listening("/commands"))
                .build();

        OptionData tagOptions = new OptionData(OptionType.STRING, "role", "The role to tag", true)
                .addChoice("Unstable air rune", "air rune").addChoice("Wicked hood token", "wicked hood");
        OptionData tiers = new OptionData(OptionType.STRING, "tier", "The tier of clue", true)
                .addChoice("Easy", "Easy").addChoice("Medium", "Medium").addChoice("Hard", "Hard")
                .addChoice("Elite", "Elite").addChoice("Master", "Master");
        OptionData category = new OptionData(OptionType.STRING, "category", "The category of the new record", true)
                .addChoice("Single", "1").addChoice("25", "25").addChoice("50", "50").addChoice("Hourly", "Hourly");
        OptionData yesno = new OptionData(OptionType.STRING, "skips", "Used skips?", true).addChoice("Yes", "yes").addChoice("No", "no");
        OptionData ping = new OptionData(OptionType.STRING, "ping", "Ping the Clue Records role?", true).addChoice("Yes", "y").addChoice("No", "n");

        jda.updateCommands().addCommands(
                Commands.slash("test", "Replies with a test reply."),
                Commands.slash("setrsn", "Set your in-game username.").addOption(OptionType.STRING, "rsn" ,  "Your RSN", true),
                Commands.slash("removeuserbyrsn", "Remove a user from the datafile")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
                        .addOption(OptionType.STRING, "rsn", "RSN of the person to be removed", true),
                Commands.slash("removeuserbyid", "Remove a user from the datafile")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
                        .addOption(OptionType.INTEGER, "id", "Discord ID of the person to be removed", true),
                Commands.slash("clues", "Updates your clue counts in the bot and gives you the stats."),
                Commands.slash("lookup", "Looks up a player's clue stats")
                        .addOption(OptionType.STRING, "rsn", "RSN of the person you're trying to look up.", true),
                Commands.slash("news", "Announces a post in #clue-news with tag.")
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                        .addOption(OptionType.STRING, "message", "The message to send in #clue-news", true)
                        .addOption(OptionType.STRING, "link", "Link of a screenshot", false),
                Commands.slash("tag", "Tag a role to notify people about certain promotions.")
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                        .addOptions(tagOptions)
                        .addOption(OptionType.STRING, "until", "Date until Wicked Hood promo lasts.", false),
                Commands.slash("record", "Tags the @Clue Records role and sends a message in #clue-records.")
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                        .addOptions(ping)
                        .addOption(OptionType.STRING, "name", "Name of the new recordholder.", true)
                        .addOptions(tiers)
                        .addOptions(category)
                        .addOption(OptionType.STRING, "link", "Link to the new record.", true)
                        .addOption(OptionType.INTEGER, "place", "Placement on the leaderboard of the new record. Must be a number.", true)
                        .addOption(OptionType.STRING, "time", "The time in which the stack was solved. Fill in number of clues for Hourly category.", true)
                        .addOptions(yesno),
                Commands.slash("gathering", "Returns an embed detailing the best ways to gather clue scrolls."),
                Commands.slash("lumber", "Returns an embed detailing the new lumberyard steps."),
                Commands.slash("solving", "Returns an embed detailing the best presets and ways to do clue scrolls."),
                Commands.slash("faq", "Returns an embed detailing all the faq about clue scrolls."),
                Commands.slash("usefullinks", "Returns an embed with useful links."),
                Commands.slash("alt1", "Returns an embed with useful information about Alt1 Toolkit.")

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
            case "news":
                c = new NewsCommand();
                break;
            case "tag":
                c = new TagCommand();
                break;
            case "record":
                c = new RecordsCommand();
                break;
            case "gathering":
                c = new GatheringEmbedCommand();
                break;
            case "lumber":
                c = new LumberEmbedCommand();
                break;
            case "solving":
                c = new SolvingEmbedCommand();
                break;
            case "faq":
                c = new FaqEmbedCommand();
                break;
            case "usefullinks":
                c = new UsefulLinksEmbedCommand();
                break;
            case "alt1":
                c = new Alt1EmbedCommand();
                break;
            default:
                break;
        }

        // Execute the event
        if (c != null)
            c.execute(event);
    }
}
