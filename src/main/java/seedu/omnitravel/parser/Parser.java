package seedu.omnitravel.parser;
import seedu.omnitravel.travelactivitytypes.TravelActivityList;
import seedu.omnitravel.errorhandlers.CheckParameters;
import seedu.omnitravel.errorhandlers.OmniException;
import seedu.omnitravel.travelactivitytypes.Accommodation;
import seedu.omnitravel.travelactivitytypes.Food;
import seedu.omnitravel.travelactivitytypes.Landmark;
import seedu.omnitravel.travelactivitytypes.TravelActivity;
import seedu.omnitravel.ui.Ui;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

    private static Logger logger = Logger.getLogger("ParserLogger");
    /**
     * Checks if the string is a number
     *
     * @param str The string that is to be defined as a number or sentence
     * @return true or false
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Obtains the list of travel activities
     *
     * @param list List of travel activities.
     */
    public static void getList(String[] command, TravelActivityList list) throws OmniException {
        Ui.printLine();
        if (command.length == 1) {
            System.out.println("Here are the travel activities in your list:");
            list.listTravelActivities();
        } else {
            throw new OmniException("Do you mean the command list?");
        }
        Ui.printLine();
    }

    /**
     * Handles the case where the add command is given as input
     *
     * @param line Line that the user inputs into the chatbot
     * @param list List of travel activities
     * @throws OmniException if command.length < 2
     */
    public static void activityCommand(String line, TravelActivityList list) throws OmniException {
        Ui.printLine();
        String[] command = line.split(" ");
        String delimiter = command[0] + "| /date | /duration | /tag ";
        String[] input = line.split(delimiter);
        CheckParameters.addExceptions(input);
        String description = input[1].trim();
        LocalDate date = LocalDate.parse(input[2]);
        String duration = input[3].trim();
        String tag = (line.contains("/tag") && input.length == 5) ? input[4].trim() : "";
        TravelActivity activity;
        switch (command[0]) {
        case "accommodation":
            activity = new Accommodation(description, date, duration, tag, "");
            System.out.println("I added a new accommodation");
            break;
        case "food":
            activity = new Food(description, date, duration, tag, "");
            System.out.println("I added a new restaurant");
            break;
        case "landmark":
            activity = new Landmark(description, date, duration, tag, "");
            System.out.println("I added a new landmark");
            break;
        default:
            throw new OmniException("Unknown activity type");
        }
        list.addTravelActivity(activity);
        System.out.println(activity);
        Ui.printLine();
    }

    /**
     * Handles the case where the add command is given as input
     *
     * @param line Line that the user inputs into the chatbot
     * @param list List of travel activities
     * @throws OmniException if any of the conditions are not met in the addExceptions() method
     */
    public static void addCommand(String line, TravelActivityList list) throws OmniException{
        Ui.printLine();
        String[] command = line.split("add | /date | /duration | /tag ");
        logger.log(Level.INFO, command[0] + " // " +  command[1]);
        CheckParameters.addExceptions(command);
        String description = command[1].trim();
        LocalDate date = LocalDate.parse(command[2]);
        String duration = command[3].trim();
        String tag = (line.contains("/tag") && command.length == 5) ? command[4].trim() : "";
        TravelActivity newActivity = new TravelActivity(description, date, duration, tag, "");
        list.addTravelActivity(newActivity);
        System.out.println("I added a new travel activity");
        System.out.println(newActivity);
        Ui.printLine();
    }

    /**
     * Handles the case where the delete command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void deleteCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            list.removeTravelActivity(listNumber);
        } else {
            throw new OmniException("Please specify which activity to delete");
        }
    }

    /**
     * Handles the case where the check command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void checkCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            list.checkTravelActivity(listNumber);
        } else {
            throw new OmniException("Please specify which activity to check");
        }
    }

    /**
     * Handles the case where the uncheck command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void uncheckCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            list.uncheckTravelActivity(listNumber);
        } else {
            throw new OmniException("Please specify which activity to uncheck");
        }
    }

    /**
     * Handles the case where the tag command is given as input
     *
     * @param line array of input string
     * @param list List of travel activities
     * @throws OmniException if command.length == 2
     * @throws OmniException if command.length == 1
     */
    public static void tagCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split(" ");
        if (command.length >= 3 && isNumeric(command[1])){
            String index = command[1];
            String[] tagSplit = line.split(index);
            String tag = tagSplit[1].trim();
            int listNumber = Integer.parseInt(index);
            list.tagActivity(listNumber, tag);
        } else if (command.length == 2) {
            throw new OmniException("Please specify a tag name");
        } else {
            throw new OmniException("Please specify which task to tag");
        }
    }

    /**
     * Handles the case where the untag command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void removeTagCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])) {
            int listNumber = Integer.parseInt(command[1]);
            list.removeTag(listNumber);
        } else {
            throw new OmniException("Please specify which task to remove tag");
        }
    }

    /**
     * Handles the case where the update command is given as input
     * @param line The update command given by the user
     * @param list The list of travel activities
     * @throws OmniException Thrown if update index, date, duration is not given
     */
    public static void updateCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("update | /date | /duration | /tag ");
        CheckParameters.updateExceptions(command);
        String tag = (line.contains("/tag") && command.length == 5)? command[4].trim() : "";
        list.updateTravelActivity(Integer.parseInt(command[1]), LocalDate.parse(command[2]), command[3].trim(),
                tag);
    }

    /**
     * Handles the case where the findtag command is given as input
     *
     * @param line User's input into Omnitravel
     * @param list List of travel activities
     * @throws OmniException if command.length < 1
     */

    public static void findTagCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("findtag");
        if (command.length < 1) {
            throw new OmniException("Please check that your update command is in this format: findtag <tag>");
        } else {
            list.findTag(command[1].trim());
        }
    }

    /**
     * Handles the case where the findtype command is given as input
     *
     * @param line User's input into Omnitravel
     * @param list List of travel activities
     * @throws OmniException if command.length < 1
     */

    public static void findTypeCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("findtype");
        if (command.length < 1) {
            throw new OmniException("Please check that your find type command is in this format: findtype <type>");
        } else {
            list.findType(command[1].trim());
        }
    }

    /**
     *  Handles the case where the find command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2
     */
    public static void findCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2) {
            String keyword = command[1];
            list.searchKeyword(keyword);
        } else {
            throw new OmniException("Please check that your find type command is in this format: find <description>");
        }
    }

    /**
     * Handles the case where the expense command is given as input
     *
     * @param line array of input string
     * @param list List of travel activities
     * @throws OmniException if command.length == 2
     * @throws OmniException if command.length == 1
     */
    public static void expenseCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split(" ");
        if (command.length == 3 && isNumeric(command[1])){
            String index = command[1];
            String[] expenseSplit = line.split(index);
            int listNumber = Integer.parseInt(command[1]);
            String expense = expenseSplit[1].trim();
            list.expenseActivity(listNumber, expense);
        } else if (command.length == 2) {
            throw new OmniException("Please specify expense amount");
        } else {
            throw new OmniException("Please specify which task to add expense");
        }
    }

    /**
     * Handles the case where the removeexpense command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void removeExpenseCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])) {
            int listNumber = Integer.parseInt(command[1]);
            list.removeExpense(listNumber);
        } else {
            throw new OmniException("Please specify which task to remove expense");
        }
    }

    /**
     * Handles the case where totalexpense command is given as input
     *
     * @param line User's input into Omnitravel
     * @param list List of travel activities
     * @throws OmniException
     */
    public static void totalExpenseCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("/type");
        if (command.length < 1 || command.length > 2) {
            throw new OmniException("Please check your command is in the format totalexpense [/tag TYPE]");
        }
        if (command.length == 1) {
            if(!command[0].trim().equals("totalexpense")) {
                throw new OmniException("Please check your command is in the format totalexpense [/tag TYPE]");
            }
            list.totalExpense("all");
        } else {
            list.totalExpense(command[1].trim());
        }
    }

}
