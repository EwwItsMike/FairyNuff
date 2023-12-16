package commands;

import data.User;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class LookupCommand extends Command {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        try {
            String rsn = Objects.requireNonNull(event.getOption("rsn")).getAsString();
            HashMap<String, int[]> ranks = extractHiscores(getHiscoresHTML(rsn));

            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(0xFD9D06);
            eb.setAuthor(event.getUser().getName(), null, event.getUser().getAvatarUrl());
            eb.setTitle("Clue stats for %s".formatted(rsn));
            eb.setFooter("Join the Clue Chasers discord server: discord.gg/cluechasers");
            eb.addField("Tier", "<:Easy:633728862039179294> Easy\n<:Medium:633728704438075435> Medium\n<:Hard:633728361918758930> Hard\n<:Elite:633732493228638209> Elite\n<:Master:1019920500807450666> Master", true);
            eb.addField("Count", "\n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>".formatted(
                    ranks.get("easy")[1],
                    ranks.get("medium")[1],
                    ranks.get("hard")[1],
                    ranks.get("elite")[1],
                    ranks.get("master")[1]
            ), true);
            eb.addField("Rank", "%s <:transparent:1047157438526271488> \n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>\n<:transparent:1047157438526271488>".formatted(
                    ranks.get("easy")[0],
                    ranks.get("medium")[0],
                    ranks.get("hard")[0],
                    ranks.get("elite")[0],
                    ranks.get("master")[0]
            ), true);

            int totalPoints = getTotalCluePoints(ranks.get("easy")[1], ranks.get("medium")[1], ranks.get("hard")[1], ranks.get("elite")[1], ranks.get("master")[1]);
            eb.addField("Total clue points: %s".formatted(totalPoints), "%s %s \n<:transparent:1047157438526271488>".formatted(
                            rsn, getNextRankAndPointsUntil(totalPoints)
            ), false);

            event.getHook().sendMessageEmbeds(eb.build()).queue();

        } catch (IOException e) {
            event.getHook().sendMessage("Something went wrong looking up this person's clue stats. Have you checked the spelling?").queue();
        }
    }

    private String getHiscoresHTML(String rsn) throws IOException {
        String fullUrl = "https://secure.runescape.com/m=hiscore/index_lite.ws?player=" + rsn.toLowerCase(Locale.ROOT).replace(" ", "_");

        URL url = new URL(fullUrl);
        InputStream is = url.openStream();
        int ptr;
        StringBuilder buffer = new StringBuilder();

        while ((ptr = is.read()) != -1) {
            buffer.append((char)ptr);
        }
        is.close();

        return buffer.toString();
    }

    // Master -> easy clue stats are the last results of the HTML (going backwards)
    // Categories split by spaces, rank:value split by comma
    private HashMap<String, int[]> extractHiscores(String html) {
        // Prepare data model
        HashMap<String, int[]> ranks = new HashMap<>();
        String[] rankNames = {"master", "elite", "hard", "medium", "easy"};
        for (String s : rankNames) {
            ranks.put(s, new int[2]);
        }

        // Split hiscores html into rank:value pairs
        String[] htmlKVP = html.split("\n");

        // Split the last 5 entries in htmlKVP into separate strings
        for (int i = 0; i < 5; i++) {
            String[] rankAndValue = htmlKVP[(htmlKVP.length - 1) - i].split(",");

            // And add them to the ranks hashmap as values
            int rank = Integer.parseInt(rankAndValue[0]);
            int count = Integer.parseInt(rankAndValue[1]);

            ranks.replace(rankNames[i], new int[] {Math.max(rank, 0), Math.max(count, 0)});
        }
        return ranks;
    }

    private int getTotalCluePoints(int easyCount, int medCount, int hardCount, int eliteCount, int masterCount) {
        int points = 0;

        points += calculateCluePoints(easyCount, 1);
        points += calculateCluePoints(medCount, 2);
        points += calculateCluePoints(hardCount, 4);
        points += calculateCluePoints(eliteCount, 8);
        points += calculateCluePoints(masterCount, 16);

        return points;
    }

    private int calculateCluePoints(int amount, int baseValue) {
        int points = 0;

        for (int i = 1; i <= amount; i++) {
            if (i % 500 == 0)
                points += (baseValue * 32);
            else if (i % 250 == 0)
                points += (baseValue * 16);
            else if (i % 100 == 0)
                points += (baseValue * 8);
            else if (i % 50 == 0)
                points += (baseValue * 4);
            else if (i % 10 == 0)
                points += (baseValue * 2);
            else
                points += baseValue;
        }
        return points;
    }

    private String getNextRankAndPointsUntil(int totalPoints) {
        String reply = "is ";

        if (totalPoints < 2000)
            reply += "%s points away from the Clue Beginner role!".formatted(2000 - totalPoints);
        else if (totalPoints < 4000)
            reply += "%s points away from the Clue Noob role!".formatted(4000 - totalPoints);
        else if (totalPoints < 8000)
            reply += "%s points away from the Clue Disciple role!".formatted(8000 - totalPoints);
        else if (totalPoints < 16000)
            reply += "%s points away from the Clue Pro role!".formatted(16000 - totalPoints);
        else if (totalPoints < 32000)
            reply += "%s points away from the Clue Master role!".formatted(32000 - totalPoints);
        else if (totalPoints < 64000)
            reply += "%s points away from the Clue Grandmaster role!".formatted(64000 - totalPoints);
        else if (totalPoints < 128000)
            reply += "%s points away from the Legendary Clue Chaser role!".formatted(128000 - totalPoints);
        else if (totalPoints < 256000)
            reply += "%s points away from the God of Clues role!".formatted(256000 - totalPoints);
        else if (totalPoints < 512000)
            reply += "%s points away from the Elder Clue God role!".formatted(512000 - totalPoints);
        else if (totalPoints < 1024000)
            reply += "%s points away from the Apotheosis of Charos role!".formatted(1024000 - totalPoints);
        else
            reply = "has not touched grass in years. Please tell them to go outside.";

        return reply;
    }
}
