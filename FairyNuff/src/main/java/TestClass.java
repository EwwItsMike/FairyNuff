public class TestClass {

    // Assuming the script pulls the real number at the start of the stream
    int subsNumberOnStart;
    int previousNumber;

    //Placeholders
    int tier1;
    int tier2;
    int tier3;
    int tier4;
    int tier5;
    int tier6;

    // Only runs once
    public void onStreamStart(){
        // Thingymajig would pull the real number from somewhere instead of zero
        subsNumberOnStart = 0;

        tier1 = subsNumberOnStart + 5;
        tier2 = subsNumberOnStart + 10;
        tier3 = subsNumberOnStart + 20;
        tier4 = subsNumberOnStart + 40;
        tier5 = subsNumberOnStart + 80;
        tier6 = subsNumberOnStart + 160;
    }

    // Runs every time you get a new sub
    public void onReceivedNewSub(){
        // Thingymajig would pull the real new number from somewhere instead of one
        int newSubsNumber = 1;

        // NOTE: the symbol combination >= means "greater than or equal to"
        // the < symbol is just "smaller than"

        if (subsNumberOnStart >= tier6){
            if (previousNumber < tier6){
                // do the tier 6 thing if you haven't done it already
            }
        }
        else if (subsNumberOnStart >= tier5){
            if (previousNumber < tier5){
                // do the tier 5 thing if you haven't done it already
            }
        }
        else if (subsNumberOnStart >= tier4){
            if (previousNumber < tier4){
                // do tier 4 thing if you haven't done it already
            }
        }
        //etc....

        // set the previousNumber for the next time when this code gets executed
        previousNumber = newSubsNumber;
    }

}
