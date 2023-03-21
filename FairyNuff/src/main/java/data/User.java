package data;

import java.io.Serializable;

public class User implements Serializable {

    private Long discordID;
    private String rsn;

    private int masterRank;
    private int masterCount;

    private int eliteRank;
    private int eliteCount;

    private int hardRank;
    private int hardCount;

    private int medRank;
    private int medCount;

    private int easyRank;
    private int easyCount;

    public User() { }

    public User(long id, String rsn) {
        this.discordID = id;
        this.rsn = rsn;
    }

    public Long getDiscordID() {
        return discordID;
    }

    public void setDiscordID(Long discordID) {
        this.discordID = discordID;
    }

    public String getRsn() {
        return rsn;
    }

    public void setRsn(String rsn) {
        this.rsn = rsn;
    }

    public int getMasterRank() {
        return masterRank;
    }

    public void setMasterRank(int masterRank) {
        this.masterRank = masterRank;
    }

    public int getMasterCount() {
        return masterCount;
    }

    public void setMasterCount(int masterCount) {
        this.masterCount = masterCount;
    }

    public int getEliteRank() {
        return eliteRank;
    }

    public void setEliteRank(int eliteRank) {
        this.eliteRank = eliteRank;
    }

    public int getEliteCount() {
        return eliteCount;
    }

    public void setEliteCount(int eliteCount) {
        this.eliteCount = eliteCount;
    }

    public int getHardRank() {
        return hardRank;
    }

    public void setHardRank(int hardRank) {
        this.hardRank = hardRank;
    }

    public int getHardCount() {
        return hardCount;
    }

    public void setHardCount(int hardCount) {
        this.hardCount = hardCount;
    }

    public int getMedRank() {
        return medRank;
    }

    public void setMedRank(int medRank) {
        this.medRank = medRank;
    }

    public int getMedCount() {
        return medCount;
    }

    public void setMedCount(int medCount) {
        this.medCount = medCount;
    }

    public int getEasyRank() {
        return easyRank;
    }

    public void setEasyRank(int easyRank) {
        this.easyRank = easyRank;
    }

    public int getEasyCount() {
        return easyCount;
    }

    public void setEasyCount(int easyCount) {
        this.easyCount = easyCount;
    }
}
