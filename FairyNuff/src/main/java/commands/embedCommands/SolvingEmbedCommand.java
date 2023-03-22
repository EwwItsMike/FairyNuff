package commands.embedCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SolvingEmbedCommand extends EmbedCommandTemplate {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        title = "Solving Clues";
        description = "";
        color = 0xEC1568;

        addField("__Solving Preset Maker__", "Courtesy of <@145884917627224065>, <@300296497277173761>\nThe [Preset Maker](https://discord.com/channels/332595657363685377/1084955288437784666) will make you optimal presets based on what you have\n*Note: it is still in beta and the creators are asking for suggestions and feedback.*");
        addField("__Easy Clues__ <:Easy:633728862039179294>", "[Preset](https://discord.com/channels/332595657363685377/1023171302405718026) - [Step-by-step guide](https://clue-guide.web.app/clues)");
        addField("__Medium Clues__ <:Medium:633728704438075435>", "[Preset](https://discord.com/channels/332595657363685377/1023171953235873895) - [Step-by-step guide](https://docs.google.com/spreadsheets/d/1DYlbYYRRx_EvEXbpzVJI15GxWQeF8hQj424pzqi1Sr0/edit#gid=1510876691)");
        addField("__Hard Clues__ <:Hard:633728361918758930>", "[Preset](https://discord.com/channels/332595657363685377/1071930109000429630) - [Step-by-step guide](https://docs.google.com/spreadsheets/d/1KLE216RvVQOhOz1ItTY7KPSxZQPN0ybXuQvmUDf3J2M/edit#gid=147294503) - [Requirements](https://media.discordapp.net/attachments/1023179363660861531/1023179364042559549/unknown.png)");
        addField("__Elite Clues__ <:Elite:633732493228638209>", "[Preset](https://discord.com/channels/332595657363685377/1075510722387247155) - [Guide](https://docs.google.com/spreadsheets/d/1VtD5-VmqvKiQqit0OByLYORFIA5Z1P3FctEKbya5VOE/edit#gid=1637918193) - [Scan Guide](https://discord.com/channels/332595657363685377/1023174431943700531)");
        addField("__Master Clues__ <:Master:1019920500807450666>", "[Preset](https://discord.com/channels/332595657363685377/1051980091720216666) - [Arc Compass Guide](https://discord.com/channels/332595657363685377/1071562129410363533) - [Scan Guide](https://discord.com/channels/332595657363685377/1080570681470750790) - [Requirements](https://cdn.discordapp.com/attachments/1023179952746668082/1023179952864100412/Master_reqs.png)");

        super.execute(event);
    }
}
