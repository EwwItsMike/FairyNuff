package commands;

import data.DataManager;
import data.DbConnection;
import data.User;
import exceptions.UserNotFoundException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CluesCommand extends Command {

    DataManager dataManager;

    HashMap<String, Long> clueRoles = new HashMap<>() {{
        put("clue beginner", Long.parseLong("611921938138529814"));
        put("clue noob", Long.parseLong("611922363294023711"));
        put("clue disciple", Long.parseLong("611922906418511883"));
        put("clue pro", Long.parseLong("611922992523509760"));
        put("clue master", Long.parseLong("611923028951040011"));
        put("clue grandmaster", Long.parseLong("611923063629414410"));
        put("legendary clue chaser", Long.parseLong("611923093505441832"));
        put("god of clues", Long.parseLong("611923144013381642"));
        put("elder clue god", Long.parseLong("853970923988451378"));
        put("uwu cluwu club", Long.parseLong("534028503084040204"));
        put("apotheosis of charos", Long.parseLong("1040964536070586378"));
    }};


    public CluesCommand(long serverID) {
        dataManager = DbConnection.getInstance().getDataManager();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        try {
            updateUserStats(event.getUser().getIdLong(), event);
            User user = dataManager.getUserByID(event.getUser().getIdLong());

            NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH);

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Clue stats for %s".formatted(dataManager.getUserByID(event.getUser().getIdLong()).getRsn()));
            eb.setColor(0xFD9D06);
            eb.setAuthor(event.getUser().getName(), null, event.getUser().getAvatarUrl());
            eb.addField("Tier", "<:Easy:633728862039179294> Easy\n<:Medium:633728704438075435> Medium\n<:Hard:633728361918758930> Hard\n<:Elite:633732493228638209> Elite\n<:Master:1019920500807450666> Master", true);
            eb.addField("Count", "\n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>".formatted(
                    format.format(user.getEasyCount()),
                    format.format(user.getMedCount()),
                    format.format(user.getHardCount()),
                    format.format(user.getEliteCount()),
                    format.format(user.getMasterCount())
            ), true);
            eb.addField("Rank", "%s <:transparent:1047157438526271488> \n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>\n%s <:transparent:1047157438526271488>\n<:transparent:1047157438526271488>".formatted(
                    format.format(user.getEasyRank()),
                    format.format(user.getMedRank()),
                    format.format(user.getHardRank()),
                    format.format(user.getEliteRank()),
                    format.format(user.getMasterRank())
            ), true);
            eb.addField("Total clue points: %s".formatted(format.format(getTotalCluePoints(user))), "%s\n<:transparent:1047157438526271488>".formatted(getNextRankAndPointsUntil(user)), false);

            eb.addField("", "[Join the Clue Chasers Discord server!](<https://discord.gg/cluechasers>)", false);

            event.getHook().sendMessageEmbeds(eb.build()).queue();
        } catch (UserNotFoundException e) {
            event.getHook().sendMessage("I can't find you in the datafile! Please use /setrsn first.").queue();
        } catch (IOException e) {
            event.getHook().sendMessage("Something went wrong with looking up your stats, are you sure you set your name correctly?").queue();
        }
    }

    private String getNextRankAndPointsUntil(User user) {
        String reply = "You are ";
        int totalPoints = getTotalCluePoints(user);

        NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH);

        if (totalPoints < 2000)
            reply += "%s points away from the Clue Beginner role!".formatted(format.format(2000 - totalPoints));
        else if (totalPoints < 4000)
            reply += "%s points away from the Clue Noob role!".formatted(format.format(4000 - totalPoints));
        else if (totalPoints < 8000)
            reply += "%s points away from the Clue Disciple role!".formatted(format.format(8000 - totalPoints));
        else if (totalPoints < 16000)
            reply += "%s points away from the Clue Pro role!".formatted(format.format(16000 - totalPoints));
        else if (totalPoints < 32000)
            reply += "%s points away from the Clue Master role!".formatted(format.format(32000 - totalPoints));
        else if (totalPoints < 64000)
            reply += "%s points away from the Clue Grandmaster role!".formatted(format.format(64000 - totalPoints));
        else if (totalPoints < 128000)
            reply += "%s points away from the Legendary Clue Chaser role!".formatted(format.format(128000 - totalPoints));
        else if (totalPoints < 256000)
            reply += "%s points away from the God of Clues role!".formatted(format.format(256000 - totalPoints));
        else if (totalPoints < 512000)
            reply += "%s points away from the Elder Clue God role!".formatted(format.format(512000 - totalPoints));
        else if (totalPoints < 1024000)
            reply += "%s points away from the Apotheosis of Charos role!".formatted(format.format(1024000 - totalPoints));
        else
            reply = "You have earned the highest possible role. Please, go outside.";

        return reply;
    }

    private void updateUserStats(long discordID, SlashCommandInteractionEvent event) throws IOException {
            User user = updateClueStats(dataManager.getUserByID(discordID), getHiscoresHTML(dataManager.getUserByID(discordID).getRsn()));
            updateDiscordRoles(Objects.requireNonNull(event.getGuild()), user);
    }

    private void updateDiscordRoles(Guild guild, User user) {
        //Only update roles in Clue Chasers discord
        if (guild.getIdLong() != Long.parseLong("332595657363685377"))
            return;

        boolean frontpager = (user.getMasterRank() <= 50 && user.getMasterRank() > 0)|| (user.getEliteRank() <= 50 && user.getEliteRank() > 0)
                || (user.getHardRank() <= 50 && user.getHardRank() > 0)|| (user.getMedRank() <= 50 && user.getMedRank() > 0)
                || (user.getEasyRank() <= 50 && user.getEasyRank() > 0);
        int totalPoints = getTotalCluePoints(user);

        UserSnowflake snowflake = UserSnowflake.fromId(user.getDiscordID());

        // Remove all clue roles before re-adding them (no duplicates or wrong roles)
        guild.retrieveMember(snowflake).queue();
        List<Role> assignedRoles = Objects.requireNonNull(guild.getMemberById(user.getDiscordID())).getRoles();
        for (Long id : clueRoles.values()) {
            if (assignedRoles.contains(guild.getRoleById(id))){
                guild.removeRoleFromMember(snowflake, Objects.requireNonNull(guild.getRoleById(id))).queue();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Add the clue role corresponding with the user's clue points
        if (frontpager)
            guild.addRoleToMember(snowflake, Objects.requireNonNull(guild.getRoleById(clueRoles.get("uwu cluwu club")))).queue();

        if (totalPoints >= 1024000)
            guild.addRoleToMember(snowflake, Objects.requireNonNull(guild.getRoleById(clueRoles.get("apotheosis of charos")))).queue();
        else if (totalPoints >= 512000)
            guild.addRoleToMember(snowflake, Objects.requireNonNull(guild.getRoleById(clueRoles.get("elder clue god")))).queue();
        else if (totalPoints >= 256000)
            guild.addRoleToMember(snowflake, Objects.requireNonNull(guild.getRoleById(clueRoles.get("god of clues")))).queue();
        else if (totalPoints >= 128000)
            guild.addRoleToMember(snowflake, Objects.requireNonNull(guild.getRoleById(clueRoles.get("legendary clue chaser")))).queue();
        else if (totalPoints >= 64000)
            guild.addRoleToMember(snowflake, Objects.requireNonNull(guild.getRoleById(clueRoles.get("clue grandmaster")))).queue();
        else if (totalPoints >= 32000)
            guild.addRoleToMember(snowflake, Objects.requireNonNull(guild.getRoleById(clueRoles.get("clue master")))).queue();
        else if (totalPoints >= 16000)
            guild.addRoleToMember(snowflake, Objects.requireNonNull(guild.getRoleById(clueRoles.get("clue pro")))).queue();
        else if (totalPoints >= 8000)
            guild.addRoleToMember(snowflake, Objects.requireNonNull(guild.getRoleById(clueRoles.get("clue disciple")))).queue();
        else if (totalPoints >= 4000)
            guild.addRoleToMember(snowflake, Objects.requireNonNull(guild.getRoleById(clueRoles.get("clue noob")))).queue();
        else if (totalPoints >= 2000)
            guild.addRoleToMember(snowflake, Objects.requireNonNull(guild.getRoleById(clueRoles.get("clue beginner")))).queue();

    }

    private String getHiscoresHTML(String rsn) throws MalformedURLException, IOException {
        String fullUrl = "https://secure.runescape.com/m=hiscore/index_lite.ws?player=" + rsn.toLowerCase(Locale.ROOT).replace(" ", "_");

        URL url = new URL(fullUrl);
        InputStream is = url.openStream();
        int ptr;
        StringBuilder buffer = new StringBuilder();

        while ((ptr = is.read()) != -1) {
            buffer.append((char) ptr);
        }
        is.close();

        return buffer.toString();
    }


    private User updateClueStats(User user, String html) {
        HashMap<String, int[]> ranks = extractHiscores(html);

        user.setMasterRank(ranks.get("master")[0]);
        user.setMasterCount(ranks.get("master")[1]);

        user.setEliteRank(ranks.get("elite")[0]);
        user.setEliteCount(ranks.get("elite")[1]);

        user.setHardRank(ranks.get("hard")[0]);
        user.setHardCount(ranks.get("hard")[1]);

        user.setMedRank(ranks.get("medium")[0]);
        user.setMedCount(ranks.get("medium")[1]);

        user.setEasyRank(ranks.get("easy")[0]);
        user.setEasyCount(ranks.get("easy")[1]);

        dataManager.SaveUser(user);
        return user;
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

            ranks.replace(rankNames[i], new int[]{Math.max(rank, 0), Math.max(count, 0)});
        }
        return ranks;
    }

    private int getTotalCluePoints(User user) {
        int points = 0;

        points += calculateCluePoints(user.getEasyCount(), 1);
        points += calculateCluePoints(user.getMedCount(), 2);
        points += calculateCluePoints(user.getHardCount(), 4);
        points += calculateCluePoints(user.getEliteCount(), 8);
        points += calculateCluePoints(user.getMasterCount(), 16);

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
}
