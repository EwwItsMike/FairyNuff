package commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RollRandomOysterParticipantCommand extends Command {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        event.getHook().sendMessage("Selecting a random participant, please wait...").queue();

        try {
            long messageID = Long.parseLong(event.getOption("messageid").getAsString());

            ArrayList<Message> messages = getMessagesWithEmbedsOrImages(messageID, event);
            Random r = new Random();

            int selected = r.ints(0, messages.size() - 1).findFirst().getAsInt();
            Message selectedMessage = messages.get(selected);

            event.getHook().editOriginal("Selected message from: " + selectedMessage.getAuthor().getName()).queueAfter(5, TimeUnit.SECONDS);

            if (!selectedMessage.getEmbeds().isEmpty()) {
                event.getMessageChannel().sendMessageEmbeds(selectedMessage.getEmbeds()).queue();
            } else {
                int randomImageIndex = 0;
                if (selectedMessage.getAttachments().size() > 1) {
                    randomImageIndex = r.ints(0, selectedMessage.getAttachments().size() - 1).findFirst().getAsInt();
                }
                Message.Attachment attach = selectedMessage.getAttachments().get(randomImageIndex);
                event.getMessageChannel().sendMessage(attach.getProxyUrl()).queueAfter(5, TimeUnit.SECONDS);
            }
        } catch (NumberFormatException e) {
            event.getHook().sendMessage("MessageID parameter could not be parsed, please make sure you entered the right thing.").queue();
        }
    }

    private ArrayList<Message> getMessagesWithEmbedsOrImages(long messageID, SlashCommandInteractionEvent event) {
        ArrayList<Message> messages = new ArrayList<>();

        long lastMessageID = event.getMessageChannel().getLatestMessageIdLong();
        boolean isDone = false;

        while (!isDone) {
            MessageHistory hist = event.getMessageChannel().getHistoryAfter(messageID, 100).complete();
            messages.addAll(hist.getRetrievedHistory());

            for (Message m : hist.getRetrievedHistory()) {
                if (m.getIdLong() == lastMessageID) {
                    isDone = true;
                    break;
                }
            }

            if (!isDone) {
                messageID = messages.get(0).getIdLong();
            }

        }

        ArrayList<Message> messagesWithImage = new ArrayList<>();

        for (Message m : messages) {
            if (!m.getAttachments().isEmpty()) {
                for (int i = 0; i < m.getAttachments().size(); i++) {
                    messagesWithImage.add(m);
                }
            } else if (!m.getEmbeds().isEmpty()) {
                for (int i = 0; i < m.getEmbeds().size(); i++) {
                    messagesWithImage.add(m);
                }
            }
        }

        return messagesWithImage;
    }
}
