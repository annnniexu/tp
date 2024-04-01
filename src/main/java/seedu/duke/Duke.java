package seedu.duke;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.LogManager;

public class Duke {

    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger("Main");
        FileHandler filehandler = new FileHandler("mylog.txt");
        SimpleFormatter formatter = new SimpleFormatter();
        filehandler.setFormatter(formatter);
        logger.addHandler(filehandler);
        LogManager.getLogManager().reset();
        logger.setLevel(java.util.logging.Level.OFF);
        Ui.printGreeting();
        FileSave file = new FileSave("omni.txt");
        TravelActivityList list = new TravelActivityList();
        file.readFile(list);
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String line = in.nextLine();
                assert line != null :"Input does not exist!";
                String[] command = line.split(" ");
                logger.log(Level.INFO, command[0]);

                switch (command[0].toLowerCase()) {
                case "list":
                    Parser.getList(command, list);
                    break;
                case "add":
                    Parser.addCommand(line, list);
                    break;
                case "accommodation":
                case "food":
                case "landmark":
                    Parser.activityCommand(line, list);
                    break;
                case "delete":
                case "check":
                case "uncheck":
                case "find":
                case "tag":
                case "untag":
                case "update":
                case "findtag":
                case "findtype":
                case "expense":
                case "removeexpense":
                    invokeCommand(command, line, list);
                    break;
                case "help":
                    Ui.helpCommand();
                    break;
                case "bye":
                    Ui.printBye();
                    return;
                default:
                    Ui.printLine();
                    System.out.println("This is not a valid command");
                    Ui.printLine();
                }
                file.saveActivityList(list);
            } catch (OmniException | NoSuchElementException | NumberFormatException | DateTimeException
                     | IOException exception) {
                CheckParameters.handleException(exception);
            }
        }
    }

    /**
     * Handles the respective command inputs used by the user
     *
     * @param command Command array of the input without spaces
     * @param line Line arry of the full input
     * @param list List of travel activities
     * @throws OmniException when any input format is wrong
     */
    private static void invokeCommand(String[] command,
                                      String line, TravelActivityList list) throws OmniException {
        Ui.printLine();
        switch (command[0].toLowerCase()) {
        case "delete":
            Parser.deleteCommand(command, list);
            break;
        case "check":
            Parser.checkCommand(command, list);
            break;
        case "uncheck":
            Parser.uncheckCommand(command, list);
            break;
        case "find":
            Parser.findCommand(command, list);
            break;
        case "tag":
            Parser.tagCommand(line, list);
            break;
        case "untag":
            Parser.removeTagCommand(command, list);
            break;
        case "update":
            Parser.updateCommand(line, list);
            break;
        case "findtag":
            Parser.findTagCommand(line, list);
            break;
        case "findtype":
            Parser.findTypeCommand(line, list);
            break;
        case "expense":
            Parser.expenseCommand(line, list);
            break;
        case "removeexpense":
            Parser.removeExpenseCommand(command, list);
            break;
        default:
            throw new OmniException("Invalid command");
        }
        Ui.printLine();
    }
}



