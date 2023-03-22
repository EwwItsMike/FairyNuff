# FairyNuff
Repository for the Fairy Nuff Discord bot, for Clue Chasers FC Discord server


# Adding a new Embed Reply Command
- Create a new Java file in src/main/java/commands/embedCommands
- Name it [uniquename]EmbedCommand
- Make sure the class extends the EmbedCommandTemplate class as shown in picture 1
- Override the execute method as shown in picture 1
- In the body of the execute method you may edit the parameters of the embed, and add  (almost) as many fields as you wish.
  The parameters you can set are: 
    - title (String)
    - description (String)
    - footer (String)
    - color (integer)
  You may also call the addField() method. This method takes 2 Strings as arguments: the title of the field, and the description of the field. If you wish the fields to be inline (next to eachother if it fits), pass "true" as a third parameter in this method.
- Once you have set all the parameters as you want them, MAKE SURE the last line of code in the method is super.execute(event);
# Picture 1:
![picture](https://cdn.discordapp.com/attachments/585386371968139276/1087862603541397585/image.png)

- After the command code is created, navigate to the [mainClass.java](https://github.com/EwwItsMike/FairyNuff/blob/main/FairyNuff/src/main/java/mainClass.java) file.
- In the main() method there is a long list of commands getting added to the bot, starting at line 54 with "jda.updateCommands().addCommands("
- Scroll down to where the last command gets added, and add a comma after the closing parantheses, as shown on line 83 in picture 2
- Add your new command to the list as shown on line 84 in picture 2. The name of the command can only contain lowercase letters.

# Picture 2:
![picture](https://cdn.discordapp.com/attachments/585386371968139276/1087862833947082814/image.png)

- Now scroll down to the onSlashCommandInteraction() method. 
- Add a new case to the switch with the EXACT same name as you gave the command just now, as shown in picture 3 on line 121
- Set the variable c to a new instance of your EmbedCommand class, as shown in picture 3 on line 122
- Do not forget to add "break;" after the creation of the EmbedCommand instance.
- That should be it!

# Picture 3:
![picture](https://cdn.discordapp.com/attachments/585386371968139276/1087863757738365028/image.png)

# Example usage of the Embed Command Template:
![picture](https://cdn.discordapp.com/attachments/1022566186602467348/1088052887420153986/image.png)
![picture](https://cdn.discordapp.com/attachments/1022566186602467348/1088053328065335326/image.png)
