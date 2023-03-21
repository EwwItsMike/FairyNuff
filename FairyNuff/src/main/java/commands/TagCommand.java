package commands;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Objects;

public class TagCommand extends Command {

    private final Long CHANNEL_ID = Long.parseLong("332595657363685377");
    private final Long TEST_CHANNEL_ID = Long.parseLong("419586026533814292");

    private final Long WICKED_HOOD_ROLE_ID = Long.parseLong("1064548406548242442");
    private final Long UNSTABLE_AIR_RUNE_ROLE_ID = Long.parseLong("1064548468611350548");

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        TextChannel channel = Objects.requireNonNull(event.getGuild()).getTextChannelById(CHANNEL_ID);

        switch (Objects.requireNonNull(event.getOption("role")).getAsString()){
            case "air rune":
                Objects.requireNonNull(channel).sendMessage("Hey %s , the Travelling Merchant has Unstable Air Rune in stock today!"
                        .formatted(Objects.requireNonNull(event.getGuild().getRoleById(UNSTABLE_AIR_RUNE_ROLE_ID)).getAsMention())).queue();
                break;
            case "wicked hood":
                String message = "Hey %s , there is currently a Wicked Hood token promotion active in Treasure Hunter!"
                        .formatted(Objects.requireNonNull(event.getGuild().getRoleById(WICKED_HOOD_ROLE_ID)).getAsMention());
                if (event.getOption("until") != null) {
                    message += " It will be active until: %s".formatted(Objects.requireNonNull(event.getOption("until")).getAsString());
                } else {
                    message += " The creator of this message did not specify when the promotion ends.";
                }

                Objects.requireNonNull(channel).sendMessage(message).queue();

                break;
            default:
                Objects.requireNonNull(event.getGuild().getTextChannelById(TEST_CHANNEL_ID)).sendMessage("%s just used the Tag command for %s"
                        .formatted(event.getUser().getAsMention(), Objects.requireNonNull(event.getOption("role")).getAsString())).queue();
                event.getHook().sendMessage("I'm not sure what you did, but that was not the correct way to use this command!").queue();
                return;
        }

        Objects.requireNonNull(event.getGuild().getTextChannelById(TEST_CHANNEL_ID)).sendMessage("%s just used the Tag command for %s"
                .formatted(event.getUser().getAsMention(), Objects.requireNonNull(event.getOption("role")).getAsString())).queue();
        event.getHook().sendMessage("Message has been sent!").queue();
    }
}
