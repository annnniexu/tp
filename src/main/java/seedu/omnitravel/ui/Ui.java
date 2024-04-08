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
        System.out.println("");
        System.out.println(
                "1. list: List out the current list\n" +
                "Format: `list`\n" +

                "2. help: Get all commands for the chatbot\n" +
                "Format: `help`\n" +

                "3. bye: Exit the chatbot\n" +
                "Format: `bye`\n" +

                "4. add <travel activity> <date> <duration> <tag>\n" +
                "Format: `add DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`\n" +

                "5. accommodation <travel activity> <date> <duration> <tag>\n" +
                "Format: `accommodation DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`\n" +

                "6. food <travel activity> <date> <duration> <tag>\n" +
                "Format: `food DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`\n" +

                "7. landmark <travel activity> <date> <duration> <tag>\n" +
                "Format: `landmark DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`\n" +

                "8. delete <activity number>\n" +
                "Format: `delete INDEX`\n" +

                "9. find <keyword>\n" +
                "Format: `find DESCRIPTION`\n" +

                "10. check <activity number>\n" +
                "Format: `check INDEX`\n" +

                "11. uncheck <activity number>\n" +
                "Format: `uncheck INDEX`\n" +

                "12. tag <activity number> <tag name>\n" +
                "Format: `tag INDEX TAGNAME`\n" +

                "13. untag <activity number>\n" +
                "Format: `untag INDEX`\n" +

                "14. update <update> <date> <duration> <tag>\n" +
                "Format: `update INDEX /date YYYY-MM-DD /duration DURATION [/tag TAG]`\n" +

                "15. findtag <tag name>\n" +
                "Format: `findtag TAG`\n" +

                "16. findtype <type>\n" +
                "Format: `findtype TYPE`\n" +

                "17. expense <activity number> <expense amount>\n" +
                "Format: `expense INDEX EXPENSE`\n" +

                "18. removeexpense <activity number>\n" +
                "Format: `removeexpense INDEX`\n" +

                "19. totalexpense <type>\n" +
                "Format: `totalexpense [/type TYPE]`\n" +

                "20. change <amount> /from <current currency> /to <changed currency>\n" +
                "Format: `change AMOUNT /from CODE /to CODE`\n" +

                "21. location <activity number> <location>\n" +
                "Format: `location INDEX LOCATION`\n" +

                "22. removelocation <activity number>\n" +
                "Format: `removelocation INDEX`\n" +

                "23. findlocation <location>\n" +
                "Format: `findlocation LOCATION`\n");
        printLine();
    }

    public static void printDateTimeExceptionError(){
        System.out.println("Invalid date, please input the date in the following order: YYYY-MM-DD");
    }

    public static void printSavingError(){
        System.out.println("Something went wrong when saving the file");
    }

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
