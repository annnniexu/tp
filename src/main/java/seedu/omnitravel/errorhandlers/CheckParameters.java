package seedu.omnitravel.errorhandlers;

import seedu.omnitravel.ui.Ui;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.NoSuchElementException;

/**
 * The CheckParameters class provides methods to check the user input for various commands.
 * An OmniException will be thrown if the input violates the structure of the command.
 * Includes methods that will check parameters for the add command, update command, list command, currency command
 * as well as helper functions to check the validity of certain strings.
 *
 */

public class CheckParameters {
    //@@author EugeneChanJiajun
    /**
     * Checks for all possible input errors that users may make and throws the corresponding exceptions
     * @param input Line of input that users placed into the chatbot
     * @param commandType commandType of the four input commands that add new activities into the list
     * @throws OmniException when any of the corresponding input format is wrong
     */

    public static void addExceptions(String[] input, String commandType, String line) throws OmniException{
        String[] command = line.split("/");
        if (input.length >= 3 && input[0].substring(commandType.length()).isBlank()) {
            throw new OmniException("The description cannot be empty!");
        } else if (input.length >= 3 && input[1].isBlank()) {
            throw new OmniException("The date cannot be empty!");
        } else if (input.length >= 3 && input[2].isBlank()) {
            throw new OmniException("The duration cannot be empty!");
        } else if (input.length >= 4 && input[3].isBlank()) {
            throw new OmniException("The tag cannot be empty!");
        } else if (input.length < 3 || input[2].contains("/tag") || !command[1].contains("date")
                || !command[2].contains("duration")){
            throw new OmniException("Please check that your " + commandType + " command is in this format: \n" +
                    commandType + " DESCRIPTION " +
                    "/date YYYY-MM-DD /duration DURATION\n"
                    + "or " + commandType + " DESCRIPTION /date YYYY-MM-DD /duration DURATION /tag TAG");
        }
    }

    /**
     * Checks for all possible input errors that users may make when updating and throws the corresponding exceptions
     *
     * @param command Command array that users placed into the chatbot
     * @param line The line that the user inputs
     * @throws OmniException when any of the corresponding input format is wrong
     */
    public static void updateExceptions(String[] command, String line) throws OmniException {
        String[] lineSplit = line.split("/");
        if (command.length >= 4 && (command[1].isBlank() || !isNumeric(command[1].trim()))) {
            throw new OmniException("The update index cannot be empty or non numerical!\n" +
                    "It must be a single number");
        } else if (command.length >= 4 && command[2].isBlank()) {
            throw new OmniException("The date cannot be empty!");
        } else if (command.length >= 4 && command[3].isBlank()) {
            throw new OmniException("The duration cannot be empty!");
        } else if(command.length >= 5 && command[4].isBlank()){
            throw new OmniException("The tag cannot be empty!");
        } else if (command.length < 4 || command[3].contains("/tag") || !lineSplit[1].contains("date")
                || !lineSplit[2].contains("duration")) {
            throw new OmniException("Please check that your update command is in this format: \nupdate INDEX " +
                    "/date YYYY-MM-DD /duration DURATION\n"
                    + "or update INDEX /date YYYY-MM-DD /duration DURATION /tag TAG");
        }
    }

    //@@author annnniexu
    /**
     * Checks for all possible input errors that users may make when using the list command and
     * throws the corresponding exceptions
     *
     * @param command Command array that users placed into the chatbot
     * @param input Input arrary that is split at /date and /sort
     * @param line The line that the user inputs
     * @throws OmniException when any of the corresponding input format is wrong
     */
    public static void listExceptions(String[] command, String[] input, String line) throws OmniException {
        //command is split at spaces, input is split at /date and /sort
        // /date included, but no date provided
        boolean case1 = command.length > 1 && command[1].equals("/date") && (command.length < 3 || input.length != 2);
        // something other than /date or /sort is entered after list
        boolean case2 = command.length > 1 && !(command[1].equals("/date") || command[1].equals("/sort"));
        // only /sort tag but command length > 2
        boolean case3 = command.length > 2 && command[1].equals("/sort");
        // command length greater than 4
        boolean case4 = command.length > 4;
        if (case1 || case2 || case3 || case4) {
            throw new OmniException("Please check that your list command is in this format:\n" +
                    "list [/date YYYY-MM-DD] [/sort]");
        }
    }


    //@@author daryltay415
    /**
     * Checks if a string contains all the words
     * @param input The input String
     * @throws OmniException if duration is invalid
     */
    public static void containsWords(String input) throws OmniException{
        String[] inputSplit = input.split(" ");
        if (inputSplit.length == 2){
            String[] durationKeyWords = {"day", "week", "month", "year", "hour", "minute", "second"
                                        ,"days", "weeks", "months", "years", "hours", "minutes", "seconds"};
            for(String word:durationKeyWords){
                if(inputSplit[1].equalsIgnoreCase(word)){
                    return;
                }
            }
        }
        throw new OmniException("Your duration is invalid. Please input in terms of \n\"1 " +
                "day/s, week/s, month/s, year/s, hour/s, minutue/s or second/s\"");
    }

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
     * Checks if the expense given is valid
     * @param str
     * @return True or false
     * @throws OmniException Throws an exception when the expense given is less than $0
     */
    public static boolean isValidExpense(String str) throws OmniException{
        if(isNumeric(str)){
            float expense = Float.parseFloat(str);
            if(expense <= 0){
                throw new OmniException("Your expense cannot be less than $0");
            }
        } else{
            return false;
        }
        return true;
    }

    /**
     * Checks the parameters for the currency exchange command against a list of available currencies.
     * It also checks that the parameters are valid
     * @param command The input given by the user that has been split into an array
     * @param line The input given by the user
     * @throws OmniException Throws an exception when parameters are invalid
     */
    public static void checkCurrencyParameters(String[] command, String line) throws OmniException{
        String delimiter = "/";
        String[] lineSplit = line.split(delimiter);

        if(command.length == 4 && (!isNumeric(command[1].trim()) || Float.parseFloat(command[1].trim()) <= 0)){
            throw new OmniException("Please ensure that the amount is a number that is more than 0 and not blank");
        } else if(command.length == 4 && command[2].trim().equalsIgnoreCase(command[3].trim())){
            throw new OmniException("The 2 currencies cannot be the same!");
        } else if(command.length < 4 || !lineSplit[1].contains("from")){
            throw new OmniException("Please check that your format is correct:\n" +
                                    "change AMOUNT /from CURRENCY /to CURRENCY");
        }

    }

    //@@author EugeneChanJiajun
    /**
     * Checks for all format errors in the user input and throes the correct exceptions
     *
     * @param exception Exception thrown
     */
    public static void handleException(Exception exception) {
        if (exception instanceof OmniException) {
            Ui.printException((OmniException) exception);
        } else if (exception instanceof NoSuchElementException) {
            Ui.printNoSuchElementException((NoSuchElementException) exception);
        } else if (exception instanceof NumberFormatException) {
            Ui.printNumberTooLargeException((NumberFormatException) exception);
        } else if (exception instanceof DateTimeException) {
            Ui.printDateTimeExceptionError();
        } else if (exception instanceof IOException) {
            Ui.printSavingError();
        } else if (exception instanceof InterruptedException) {
            Ui.printInterruptedError();
        }
    }

    /**
     * Checks the input that the users placed into the chatbot and checks if the input contains any ASCII characters.
     *
     * @param input Input line that users placed into the chatbot
     * @throws OmniException if the input contains any non-ASCII characters
     */
    public static void asciiCheck(String input) throws OmniException {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c < 32 || c > 126) {
                throw new OmniException("Input contains non-ASCII characters.");
            }
        }
    }
}
