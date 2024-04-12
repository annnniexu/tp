package seedu.omnitravel.parser;
import seedu.omnitravel.exchangerateapi.CurrencyRate;
import seedu.omnitravel.travelactivitytypes.TravelActivityList;
import seedu.omnitravel.errorhandlers.CheckParameters;
import seedu.omnitravel.errorhandlers.OmniException;
import seedu.omnitravel.travelactivitytypes.Accommodation;
import seedu.omnitravel.travelactivitytypes.Food;
import seedu.omnitravel.travelactivitytypes.Landmark;
import seedu.omnitravel.travelactivitytypes.TravelActivity;
import seedu.omnitravel.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * The Parser class contains methods that handles user command inputs and calls the respective methods
 * in TravelActivityList.
 */

public class Parser {

    private static Logger logger = Logger.getLogger("ParserLogger");

    //@@author annnniexu
    /**
     * Obtains the list of travel activities
     *
     * @param line Line that the user inputs into the chatbot
     * @param list List of travel activities.
     */
    public static void getList(String line, TravelActivityList list) throws OmniException {
        Ui.printLine();
        String[] command = line.split(" ");
        String delimiter = "/date | /sort ";
        String[] input = line.split(delimiter);
        CheckParameters.listExceptions(command, input, line);
        boolean sort = false;
        boolean isDate = false;
        LocalDate date = LocalDate.now();
        String dateString = "all dates";
        if (command.length == 2 || command.length == 4) {
            sort = true;
        }
        if (command.length == 3 || command.length == 4) {
            isDate = true;
            try {
                date = LocalDate.parse(command[2].trim());
            } catch (DateTimeParseException e) {
                throw new OmniException("Please provide the date in the format YYYY-MM-DD");
            }
            if(date.isBefore(LocalDate.now())){
                throw new OmniException("Please input a future date.");
            }
            dateString = date.toString();
        }
        System.out.println("Here are the travel activities for " + dateString);
        list.listTravelActivities(sort, isDate, date);
        Ui.printLine();
    }
    //@@author EugeneChanJiajun
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
        String commandType = command[0];
        int commandIndex = commandType.length();
        String delimiter = " /date | /duration | /tag ";
        String[] input = line.split(delimiter);
        CheckParameters.addExceptions(input, commandType, line);
        String description = line.substring(commandIndex + 1, line.indexOf("/date")).trim();
        LocalDate date = LocalDate.parse(input[1]);
        if(date.isBefore(LocalDate.now())){
            throw new OmniException("Please input a future date.");
        }
        String duration = input[2].trim();
        CheckParameters.containsWords(duration);
        String tag = (line.contains("/tag") && input.length == 4) ? input[3].trim() : "";
        TravelActivity activity;
        switch (commandType) {
        case "accommodation":
            activity = new Accommodation(description, date, duration, tag, "");
            System.out.println("I added a new accommodation");
            break;
        case "food":
            activity = new Food(description, date, duration, tag, "");
            System.out.println("I added a new food activity");
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
        String[] command = line.split("/date | /duration | /tag ");
        //logger.log(Level.INFO, command[0] + " // " +  command[1]);
        CheckParameters.addExceptions(command, "add", line);
        String description = line.substring(4, line.indexOf("/date"));
        LocalDate date = LocalDate.parse(command[1]);
        if(date.isBefore(LocalDate.now())){
            throw new OmniException("Please input a future date.");
        }
        String duration = command[2].trim();
        CheckParameters.containsWords(duration);
        String tag = (line.contains("/tag") && command.length == 4) ? command[3].trim() : "";
        TravelActivity newActivity = new TravelActivity(description, date, duration, tag, "");
        list.addTravelActivity(newActivity);
        System.out.println("I added a new travel activity");
        System.out.println(newActivity);
        Ui.printLine();
    }
    //@@author ChinYanXu
    /**
     * Handles the case where the delete command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void deleteCommand(String[] command, TravelActivityList list, String line) throws OmniException {
        if(list.getNoOfTravelActivities() == 0){
            throw new OmniException("The list is empty!");
        }
        try {
            if (command.length < 2) {
                throw new OmniException("Please specify which activity index or description to delete");
            }
            int input = Integer.parseInt(command[1]);
            list.removeTravelActivity(input);
        } catch (NumberFormatException e) {
            int IndexOfDescription = line.indexOf(command[1]);
            String description = line.substring(IndexOfDescription);
            list.removeTravelActivity(description);
        }
    }


    //@@author annnniexu
    /**
     * Handles the case where the check command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void checkCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && CheckParameters.isNumeric(command[1])){
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
        if (command.length == 2 && CheckParameters.isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);

            list.uncheckTravelActivity(listNumber);
        } else {
            throw new OmniException("Please specify which activity to uncheck");
        }
    }

    //@@author ChenKangg
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
        if (command.length >= 3 && CheckParameters.isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            // Extract tags starting from the third element onwards
            String[] tagArray = Arrays.copyOfRange(command, 2, command.length);
            // Join the tags into a single string
            String tags = String.join(" ", tagArray);
            list.tagActivity(listNumber, tags);
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
        if (command.length == 2 && CheckParameters.isNumeric(command[1])) {
            int listNumber = Integer.parseInt(command[1]);
            list.removeTag(listNumber);
        } else {
            throw new OmniException("Please specify which task to remove tag");
        }
    }

    //@@author daryltay415
    /**
     * Handles the case where the update command is given as input
     * @param line The update command given by the user
     * @param list The list of travel activities
     * @throws OmniException Thrown if update index, date, duration is not given
     */
    public static void updateCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("update | /date | /duration | /tag ");
        CheckParameters.updateExceptions(command, line);
        String tag = (line.contains("/tag") && command.length == 5)? command[4].trim() : "";
        LocalDate date = LocalDate.parse(command[2]);
        if(date.isBefore(LocalDate.now())){
            throw new OmniException("Please input a future date.");
        }
        String duration = command[3].trim();
        CheckParameters.containsWords(duration);
        list.updateTravelActivity(Integer.parseInt(command[1]), date, duration,
                tag);
    }

    //@@author ChinYanXu
    /**
     * Handles the case where the findtag command is given as input
     *
     * @param line User's input into Omnitravel
     * @param list List of travel activities
     * @throws OmniException if command.length < 1
     */

    public static void findTagCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("findtag | /exclude");
        if (command.length == 2 && !command[1].isBlank() && !line.contains("/exclude")) {
            String keyword = command[1].trim();
            list.findTag(keyword);
        } else if (command.length == 3 && !command[1].isBlank() && !command[2].isBlank()) {
            String keyword = command[1].trim();
            String exclusion = command[2].trim();
            list.findTag(keyword, exclusion);
        } else {
            throw new OmniException("Please check that your find tag command is in this format: " +
                    "findtag <description> " + "or findtag <description> /exclude <exclusion>");
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
        String[] command = line.split("findtype | /exclude");
        if (command.length == 2 && !command[1].isBlank() && !line.contains("/exclude")) {
            String keyword = command[1].trim();
            keyword = keyword.equalsIgnoreCase("general")? "TravelActivity":keyword;
            list.findType(keyword);
        } else if (command.length == 3 && !command[1].isBlank() && !command[2].isBlank()) {
            String keyword = command[1].trim();
            String exclusion = command[2].trim();
            keyword = keyword.equalsIgnoreCase("general")? "TravelActivity":keyword;
            list.findType(keyword, exclusion);
        } else {
            throw new OmniException("Please check that your find type command is in this format: + " +
                    "findtype <description> " + "or findtype <description> /exclude <exclusion>");
        }
    }

    /**
     *  Handles the case where the find command is given as input
     *
     * @param line input string
     * @param list List of travel activities
     * @throws OmniException if command.length != 2
     */
    public static void findCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("find | /exclude");
        if (command.length == 2 && !command[1].isBlank() && !line.contains("/exclude")) {
            String keyword = command[1].trim();
            list.searchKeyword(keyword);
        } else if (command.length == 3 && !command[1].isBlank() && !command[2].isBlank()) {
            String keyword = command[1].trim();
            String exclusion = command[2].trim();
            list.searchKeyword(keyword, exclusion);
        } else {
            throw new OmniException("Please check that your find command is in this format: + " +
                    "find <description> " + "or find <description> /exclude <exclusion>");
        }
    }

    //@@author ChenKangg
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
        if (command.length == 3 && CheckParameters.isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            String expense = command[2];
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
        if (command.length == 2 && CheckParameters.isNumeric(command[1])) {
            int listNumber = Integer.parseInt(command[1]);
            list.removeExpense(listNumber);
        } else {
            throw new OmniException("Please follow the format: removeexpense INDEX");
        }
    }

    //@@author annnniexu
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
            throw new OmniException("Please check your command is in the format totalexpense [/type TYPE]");
        }
        if (command.length == 1) {
            if(!command[0].trim().equals("totalexpense")) {
                throw new OmniException("Please check your command is in the format totalexpense [/type TYPE]");
            }
            list.totalExpense("all");
        } else if(command[1].trim().equals("general")){
            list.totalExpense("TravelActivity");
        } else {
            list.totalExpense(command[1].trim());
        }
    }
    //@@author ChenKangg
    /**
     * Handles the case whereby the command is listtags
     * @param command The command given by the user
     * @param list The travel activity list
     * @throws OmniException Throws an exception when the command is in an invalid format
     */
    public static void listTagsCommand(String[] command, TravelActivityList list) throws OmniException{
        Ui.printLine();
        if (command.length == 1) {
            System.out.println("These are the tags in your list: ");
            list.listTags();
        } else {
            throw new OmniException("Do you mean the command listtags?");
        }
        Ui.printLine();
    }
    //@@author daryltay415

    /**
     * Handles the case whereby the command is change
     * @param line The input given by the user
     * @throws OmniException Throws an exception when the parameters are invalid
     */
    public static void currencyExchangeCommand(String line) throws OmniException{
        Ui.printLine();
        try {
            String delimiter = "change | /from | /to ";
            String[] command = line.split(delimiter);
            // Check parameters
            CheckParameters.checkCurrencyParameters(command, line);
            float amount = Float.parseFloat(command[1].trim());
            String localCurrency = command[2].toLowerCase().trim();
            String foreignCurrency = command[3].toLowerCase().trim();
            CurrencyRate.convertCurrency(localCurrency, foreignCurrency, amount);
        } catch(InterruptedException | IOException exception){
            System.out.println("Website might be down!");
        }
        Ui.printLine();
    }


}
