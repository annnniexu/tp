package seedu.omnitravel.ui;


import seedu.omnitravel.errorhandlers.OmniException;
import seedu.omnitravel.travelactivitytypes.TravelActivity;

import java.util.NoSuchElementException;

/**
 * Represents the user interface of the Omnitravel bot
 * It contains all the responses of the Omnitravel bot to the user's commands
 */
public class Ui {
    /**
     * Prints the greetings
     */
    public static void printGreeting() {
        printLine();
        System.out.println(" ____  _      _      _  _____  ____  ____  _     _____ _\n" +
                "/  _ \\/ \\__/|/ \\  /|/ \\/__ __\\/  __\\/  _ \\/ \\ |\\/  __// \\\n" +
                "| / \\|| |\\/||| |\\ ||| |  / \\  |  \\/|| / \\|| | //|  \\  | |\n" +
                "| \\_/|| |  ||| | \\||| |  | |  |    /| |-||| \\// |  /_ | |_/\\\n" +
                "\\____/\\_/  \\|\\_/  \\|\\_/  \\_/  \\_/\\_\\\\_/ \\|\\__/  \\____\\\\____/)");
        System.out.println("Hello");
        System.out.println("How may I assist you?");
        printLine();
    }

    /**
     * Prints the farewell greetings
     */
    public static void printBye(){
        printLine();
        System.out.println("Thank you for using Omnitravel");
        System.out.println("We hope to see you again! Goodbye!");
        printLine();
    }

    /**
     * Prints line
     */
    public static void printLine(){
        System.out.println("_______________________________________________________________________________");
    }

    /**
     * Prints the OmniException
     * @param exception The OmniException to be printed
     */
    public static void printException(OmniException exception){
        System.out.println("Warning! " + exception.getMessage());
        printLine();
    }

    /**
     * Prints the NoSuchElement Exception
     * @param exception The exception to be printed
     */
    public static void printNoSuchElementException(NoSuchElementException exception){
        System.out.println("Warning! " + exception.getMessage());
        printLine();
    }

    /**
     * Prints the NumberFormatException
     * @param exception The exception to be printed
     */
    public static void printNumberTooLargeException(NumberFormatException exception) {
        System.out.println("Warning! " + exception.getMessage() + " number too large!\n" +
                "or number format is wrong!");
        printLine();
    }

    /**
     * Prints out all available commands when the help command is given as input
     */
    //@@author ChenKangg
    public static void helpCommand(){
        printLine();
        System.out.println("These are the available commands!");
        System.out.println("");
        System.out.println(
                "1. list <date> <sort>: List out the current list for given date sorted\n" +
                "2. help: Get all commands for the chatbot\n" +
                "3. bye: Exit the chatbot\n" +
                "4. add <travel activity> <date> <duration> <tag>\n" +
                "5. accommodation <travel activity> <date> <duration> <tag>\n" +
                "6. food <travel activity> <date> <duration> <tag>\n" +
                "7. landmark <travel activity> <date> <duration> <tag>\n" +
                "8. delete <activity number>\n" +
                "9. find <keyword> <exclusion>\n" +
                "10. check <activity number>\n" +
                "11. uncheck <activity number>\n" +
                "12. tag <activity number> <tag name>\n" +
                "13. untag <activity number>\n" +
                "14. update <update> <date> <duration> <tag>\n" +
                "15. findtag <tag name> <exclusion>\n" +
                "16. findtype <type> <exclusion>\n" +
                "17. listtags \n" +
                "18. expense <activity number> <expense amount>\n" +
                "19. removeexpense <activity number>\n" +
                "20. totalexpense <type>\n" +
                "21. change <amount> /from <current currency> /to <changed currency>\n");
        printLine();
    }

    /**
     * Prints out the error message for when the user inputs the date in the wrong format
     */

    //@@author daryltay415
    public static void printDateTimeExceptionError(){
        System.out.println("Invalid date, please input the date in the following order: YYYY-MM-DD");
    }

    /**
     * Prints out the error message for when there is an error when saving the file
     */

    public static void printSavingError(){
        System.out.println("Something went wrong when saving the file");
    }

    /**
     * Prints out the error message for when InterruptedException is thrown
     */

    public static void printInterruptedError(){
        System.out.println("Warning Website might be down!");
    }

    /**
     * Prints out the activity in a list
     * @param activity The travel activity
     * @param activityIndex The index of the activity
     */
    public static void printActivity(TravelActivity activity, int activityIndex) {
        String checked = activity.getActivityStatus()? "[X]" : "[ ]";
        System.out.print(checked + " " + activityIndex + ". ");
        if (activity.getClass().getSimpleName().equals("TravelActivity")){
            System.out.print("General: ");
        }
        System.out.print(activity);
        if(activity.getExpense() != null && !activity.getExpense().isEmpty()){
            System.out.print(" (" + activity.getExpense() + ")");
        }

        System.out.println();
    }

}
