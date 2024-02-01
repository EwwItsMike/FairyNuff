package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class InviteCommand extends Command {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        EmbedBuilder builder = new EmbedBuilder();

        builder.setAuthor(event.getUser().getName(), null, event.getUser().getAvatarUrl());
        builder.setColor(0xFD9D06);

        builder.addField("**Join the Clue Chasers discord!**", "[Click here to join](<https://discord.gg/cluechasers>)\n<:transparent:1047157438526271488>", false);
        builder.addField("Invite Fairy Nuff to your server!", "[Click here to invite](<https://discord.com/api/oauth2/authorize?client_id=863764057922994206&permissions=2147535936&scope=bot%20applications.commands>)\n<:transparent:1047157438526271488>", false);
        builder.addField("Join the biggest Clue Opening event yet!", "[Click here for more info](<https://discordapp.com/channels/332595657363685377/333656528777379840/1195842848885583902>)\n<:transparent:1047157438526271488>", false);

        builder.setThumbnail("https://cdn.discordapp.com/attachments/1117131930056536134/1195031783905951825/w30_fixed.gif?ex=65b28313&is=65a00e13&hm=ba2234505319b01dc22e119ccea7ef56fc59f50dfe90132a12b085e971b0c041&");

        event.getHook().sendMessageEmbeds(builder.build()).queue();
    }
}
