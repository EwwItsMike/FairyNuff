package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Objects;

public class TagCommand extends Command {

    private final Long TEST_CHANNEL_ID = Long.parseLong("1075129097757393026");

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String msg = event.getOption("role").getAsString();


        if (event.getOption("message") != null){
            String nwmsg = event.getOption("message").getAsString().replaceAll("\\\\", "\n");
            msg += "\n" + nwmsg;
        }

        Objects.requireNonNull(event.getGuild().getTextChannelById(TEST_CHANNEL_ID))
                .sendMessage("%s just used the Tag command for %s"
                .formatted(event.getUser().getAsTag(), getRoleWithoutTag(Objects.requireNonNull(event.getOption("role")).getAsString()))).queue();

        event.reply(msg).queue();
    }

    private String getRoleWithoutTag(String role) {
        return switch (role){
            case "<@&1064540154125111386>" -> "Clue News";
            case "<@&1071487069232308224>" -> "Clue Records";
            case "<@&1064548406548242442>" -> "Wicked hood promo";
            case "<@&1104143964438810724>" -> "Scan Trainer";
            case "<@&1064542461776637992>" -> "Infohub Changes";
            default -> "Unknown role";
        };
    }
}
