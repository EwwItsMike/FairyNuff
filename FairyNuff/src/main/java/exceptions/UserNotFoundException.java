package exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("Cannot find that user!");
    }
}
