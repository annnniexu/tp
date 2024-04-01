package seedu.omnitravel.ui;


import seedu.omnitravel.error_handlers.OmniException;
import seedu.omnitravel.travel_activity_types.TravelActivity;

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

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void printException(OmniException exception){
        printLine();
        System.out.println("Warning! " + exception.getMessage());
        printLine();
    }

    public static void printNoSuchElementException(NoSuchElementException exception){
        printLine();
        System.out.println("Warning! " + exception.getMessage());
        printLine();
    }

    public static void printNumberTooLargeException(NumberFormatException exception) {
        printLine();
        System.out.println("Warning! " + exception.getMessage() + " number too large!");
        printLine();
    }

    public static void helpCommand(){
        printLine();
        System.out.println("These are the available commands!");
        System.out.println("1. list\n" +
                "2. help\n" +
                "3. bye\n" +
                "4. add <travel activity> <date> <duration> <tag>\n" +
                "5. accommodation <travel activity> <date> <duration> <tag>\n" +
                "6. food <travel activity> <date> <duration> <tag>\n" +
                "7. landmark <travel activity> <date> <duration> <tag>\n" +
                "8. delete <activity number>\n" +
                "9. find <keyword>\n" +
                "10. check <activity number>\n" +
                "11. uncheck <activity number>\n" +
                "12. tag <activity number> <tag name>\n" +
                "13. untag <activity number>\n" +
                "14. update <update> <date> <duration> <tag>\n" +
                "15. findtag <tag name>\n" +
                "16. findtype <type>\n" +
                "17. expense <activity number> <expense amount>\n" +
                "18. removeexpense <activity number>\n");
    }

    public static void printDateTimeExceptionError(){
        System.out.println("Invalid date, please input the date in the following order: YYYY-MM-DD");
    }

    public static void printSavingError(){
        System.out.println("Something went wrong when saving the file");
    }

    /**
     * Prints out the activity in a list
     * @param activity The travel activity
     * @param activityIndex The index of the activity
     */
    public static void printActivity(TravelActivity activity, int activityIndex) {
        String checked = activity.getActivityStatus()? "[X]" : "[ ]";
        System.out.print(checked + " " + activityIndex + ". " + activity);
        if(activity.getTag() != null && !activity.getTag().isEmpty()){
            System.out.print(" (" + activity.getTag() + ")");
        }
        if(activity.getExpense() != null && !activity.getExpense().isEmpty()){
            System.out.print(" (" + activity.getExpense() + ")");
        }
        System.out.println();
    }
}
