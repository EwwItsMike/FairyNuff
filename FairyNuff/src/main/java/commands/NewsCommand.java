package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.Objects;

public class NewsCommand extends Command {

    private Long NEWS_CHANNEL_ID;
    private Long NEWS_ROLE_ID;

    private Long TEST_CHANNEL;

    public NewsCommand() {
        NEWS_CHANNEL_ID = Long.parseLong("894988133794451456");
        NEWS_ROLE_ID = Long.parseLong("1064540154125111386");
        TEST_CHANNEL = Long.parseLong("419586026533814292");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        try {
            Guild guild = event.getGuild();
            TextChannel channel = Objects.requireNonNull(guild).getTextChannelById(NEWS_CHANNEL_ID);

            EmbedBuilder eb = new EmbedBuilder()
                    .setColor(0xFD9D06)
                    .setAuthor(event.getUser().getName(), null, event.getUser().getAvatarUrl())
                    .addField("", Objects.requireNonNull(event.getOption("message")).getAsString(), false);
//                .addField("", Objects.requireNonNull(event.getOption("message")).getAsString(), false);

            if (event.getOption("link") != null) {
                UrlValidator validator = new UrlValidator();
                String linkString = Objects.requireNonNull(event.getOption("link")).getAsString();
                if (validator.isValid(linkString)) {
                    eb.setImage(linkString);
                } else {
                    eb.addField("","The author attempted to add an image here, but gave a faulty link!", false);
                }
            }

            Objects.requireNonNull(channel).sendMessage(Objects.requireNonNull(guild.getRoleById(NEWS_ROLE_ID)).getAsMention()).queue();
            Objects.requireNonNull(channel).sendMessage(MessageCreateData.fromEmbeds(eb.build())).queue();
            event.getHook().sendMessage("Message has been sent!").queue();

        }
        catch(Exception e) {
            event.getHook().sendMessage("Something went wrong! - %s".formatted(e.getCause())).queue();
        }
    }
}
