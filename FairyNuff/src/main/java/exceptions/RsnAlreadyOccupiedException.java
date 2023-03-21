package exceptions;

public class RsnAlreadyOccupiedException extends RuntimeException {

    public RsnAlreadyOccupiedException(){
        super("This RSN is already registered!");
    }

}
