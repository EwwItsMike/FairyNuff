package commands.embedCommands;

public class EmbedField {

    public String title = "";
    public String description = "";
    public boolean inline = false;

    public EmbedField(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public EmbedField(String title, String description, boolean inline){
        this.title = title;
        this.description = description;
        this.inline = inline;
    }

}
