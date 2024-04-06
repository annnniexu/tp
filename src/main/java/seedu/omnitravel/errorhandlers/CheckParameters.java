package seedu.omnitravel.errorhandlers;

import seedu.omnitravel.ui.Ui;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.NoSuchElementException;

import static seedu.omnitravel.parser.Parser.isNumeric;

public class CheckParameters {

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
            throw new OmniException("Please check that your add command is in this format: add DESCRIPTION " +
                    "/date YYYY-MM-DD /duration DURATION"
                    + " or add DESCRIPTION /date YYYY-MM-DD /duration DURATION /tag TAG");
        }
    }

    /**
     * Checks for all possible input errors that users may make when updating and throws the corresponding exceptions
     *
     * @param command Command array that users placed into the chatbot
     * @throws OmniException when any of the corresponding input format is wrong
     */
    public static void updateExceptions(String[] command) throws OmniException {
        if (command.length >= 4 && (command[1].isBlank() || !isNumeric(command[1]))) {
            throw new OmniException("The update index cannot be empty or non numerical!");
        } else if (command.length >= 4 && command[2].isBlank()) {
            throw new OmniException("The date cannot be empty!");
        } else if (command.length >= 4 && command[3].isBlank()) {
            throw new OmniException("The duration cannot be empty!");
        } else if(command.length >= 5 && command[4].isBlank()){
            throw new OmniException("The tag cannot be empty!");
        } else if (command.length < 4 || command[3].contains("/tag")) {
            throw new OmniException("Please check that your update command is in this format: update INDEX " +
                    "/date YYYY-MM-DD /duration DURATION"
                    + " or update INDEX /date YYYY-MM-DD /duration DURATION /tag TAG");
        }
    }

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
        }
    }
}
