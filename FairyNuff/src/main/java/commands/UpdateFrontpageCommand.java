package commands;

import exceptions.UserNotFoundException;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import data.*;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class UpdateFrontpageCommand extends Command {

    DataManager dataManager;


    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        dataManager = DbConnection.getInstance().getDataManager();

        //Grab all members with frontpage role
        Guild server = event.getGuild();
//        Role frontPageRole = server.getRoleById("1115985595072778341"); //TEST ROLE
        Role frontPageRole = server.getRoleById("534028503084040204");
        List<Member> frontpagers = new ArrayList<>();
        server.loadMembers().onSuccess(m -> {
            for (Member member : m){
                if (member.getRoles().contains(frontPageRole)){
                    frontpagers.add(member);
                }
            }
            System.out.println("frontpagers size: " + frontpagers.size());

            for (Member member : frontpagers) {
                //Check if user is registered in Fairy Nuff datafile, and update them
                try {
                    User u = dataManager.getUserByID(member.getIdLong());
                    updateUserStats(u.getDiscordID());

                    if (!isFrontpager(u)){
                        event.getMessageChannel().sendMessage("User [ %s ] is no longer frontpager. Removing role.".formatted(member.getEffectiveName())).queue();
                        server.removeRoleFromMember(member.getUser(), frontPageRole).queue();
                    }
                } catch (UserNotFoundException e){
                    event.getMessageChannel().sendMessage("User not found in datafile: %s . Removing role.".formatted(member.getEffectiveName())).queue();
                    server.removeRoleFromMember(member.getUser(), frontPageRole).queue();
                } catch (IOException e) {
                    event.getMessageChannel().sendMessage("IO Exception occured. Stacktrace: %s".formatted(e.toString())).queue();
                }
            }

            event.getHook().sendMessage("Command finished.").queue();

        });
    }

    private void updateUserStats(long discordID) throws IOException {
        User user = updateClueStats(dataManager.getUserByID(discordID), getHiscoresHTML(dataManager.getUserByID(discordID).getRsn()));
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

    private Boolean isFrontpager(User user){
        return (user.getMasterRank() <= 50 && user.getMasterRank() > 0)|| (user.getEliteRank() <= 50 && user.getEliteRank() > 0)
                || (user.getHardRank() <= 50 && user.getHardRank() > 0)|| (user.getMedRank() <= 50 && user.getMedRank() > 0)
                || (user.getEasyRank() <= 50 && user.getEasyRank() > 0);
    }


    }
