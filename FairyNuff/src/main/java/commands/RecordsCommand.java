package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Objects;

public class RecordsCommand extends Command {

    private final Long CLUE_RECORD_ROLE_ID = Long.parseLong("1071487069232308224");
    private final Long RECORDS_CHANNEL_ID = Long.parseLong("1071487786244378715");
    private final Long TEST_CHANNEL_ID = Long.parseLong("419586026533814292");

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        TextChannel channel = Objects.requireNonNull(event.getGuild()).getTextChannelById(RECORDS_CHANNEL_ID);
        TextChannel logChannel = event.getGuild().getTextChannelById(TEST_CHANNEL_ID);
        String roleAsMentionString = Objects.requireNonNull(event.getGuild().getRoleById(CLUE_RECORD_ROLE_ID)).getAsMention();

        String name = event.getOption("name").getAsString();
        String time = event.getOption("time").getAsString();
        String tier = event.getOption("tier").getAsString();
        String place = event.getOption("place").getAsString();
        String category = event.getOption("category").getAsString();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("%s place - %s Clue scrolls - %s category".formatted(getPlacementStringFromNumberString(place), tier, category));
        eb.setColor(getColourCode(tier));


        if(category.equals("Hourly")){
            eb.addField("Clues solved - %s".formatted(time), "%s solved %s %s clues in an hour".formatted(name, time, tier)
                    + (event.getOption("skips").getAsString().equals("no") ? " without " : " with ")
                    + "skip tickets, claiming the %s place in the category!".formatted(getPlacementStringFromNumberString(place)), false);
        }
        else {
            eb.addField("Time - %s".formatted(time), "%s solved %s %s clue(s) in %s".formatted(name, category, tier, time)
                    + (event.getOption("skips").getAsString().equals("no") ? " without " : " with ")
                    + "skip tickets, claiming the %s place in the category!".formatted(getPlacementStringFromNumberString(place)), false);
        }

        eb.addField("", "[Check out the VOD](%s)".formatted(event.getOption("link").getAsString()), false);

        if (event.getOption("ping").getAsString().equals("y"))
            Objects.requireNonNull(channel).sendMessage(roleAsMentionString + "\nA new record has been set!").queue();
        channel.sendMessageEmbeds(eb.build()).queue();

        Objects.requireNonNull(logChannel).sendMessage("%s just used the Records command.".formatted(event.getUser().getAsMention())).queue();
        event.getHook().sendMessage("Message has been sent!").queue();
    }

    int getColourCode(String tier){
        return switch (tier) {
            case "Easy" -> 0x0E5524;
            case "Medium" -> 0x19a1a1;
            case "Hard" -> 0x69068d;
            case "Elite" -> 0xcfb316;
            case "Master" -> 0x830b0d;
            default -> 0;
        };
    }

    String getPlacementStringFromNumberString(String numberAsString) {
        int parsed = Integer.parseInt(numberAsString);
        return switch (parsed) {
            case 1, 21, 31, 41 -> numberAsString + "st";
            case 2, 22, 32, 42 -> numberAsString + "nd";
            case 3, 23, 33, 43 -> numberAsString + "rd";
            default -> numberAsString + "th";
        };
    }
}
