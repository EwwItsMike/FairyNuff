package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class RollOysterValueCommand extends Command {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        Random r = new Random();
        NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH);

        int value = r.ints(25000, 4000000).findFirst().getAsInt();

        event.getHook().sendMessage("The target value for this month is: %s".formatted(format.format(value))).queue();

//        event.reply("The target value for this month is: %s".formatted(NumberFormat.getInstance(Locale.ENGLISH).format(new Random().nextInt(25000, 4000000)))).queue();

    }
}
