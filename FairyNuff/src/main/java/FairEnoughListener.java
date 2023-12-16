import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Locale;

public class FairEnoughListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()){
            return;
        }

        String message = event.getMessage().getContentStripped();
        message = message.toLowerCase(Locale.ROOT).replaceAll(" ", "");
        message = transliterate(message);
        message = message.replaceAll("ƒ", "f").replaceAll("4", "a").replaceAll("F", "f")
                .replaceAll("1", "i").replaceAll("\\|", "i").replaceAll("€", "e")
                .replaceAll("Fair enоugh", "fairenough").replaceAll("н", "h")
                .replaceAll("enuf", "enough").replaceAll("[^a-zA-Z0-9`]", "")
                .replaceAll("\u200B", "").replaceAll("fur", "fair").replaceAll("faer", "fair")
                .replaceAll("\u043E", "o").replaceAll("\u0435", "f").replaceAll("\u0430", "a")
                .replaceAll("\u03BF", "o").replaceAll("fare", "fair").replaceAll("ɹᴉɐℲ", "fair")
                .replaceAll("ɥƃnouǝ", "enough").replaceAll("\u200B", "").replaceAll("\u0578", "n")
                .replaceAll("\u0585", "o");



        if (message.contains("fairenough") || message.contains("fairenouga") || message.contains("enoughfair")){
            event.getMessage().reply("Fairy Nuff*").queue();
        }
    }

    private String transliterate(String message){
        char[] abcCyr =   {' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х', 'ц','ч', 'ш','щ','ъ','ы','ь','э', 'ю','я','А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч','Ш', 'Щ','Ъ','Ы','Ь','Э','Ю','Я','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String[] abcLat = {" ","a","b","v","g","d","e","e","zh","z","i","y","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch", "","i", "","e","ju","ja","A","B","V","G","D","E","E","Zh","Z","I","Y","K","L","M","N","O","P","R","S","T","U","F","H","Ts","Ch","Sh","Sch", "","I", "","E","Ju","Ja","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++ ) {
                if (message.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                }
            }
        }
        return builder.toString();
    }
}
