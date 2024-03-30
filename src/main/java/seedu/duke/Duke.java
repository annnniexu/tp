package seedu.duke;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Duke {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Main");
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
                handleException(exception);
            }
        }
    }
    private static void invokeCommand(String[] command, String line, TravelActivityList list) throws OmniException, IOException {
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

    private static void handleException(Exception exception) {
        if (exception instanceof OmniException)
            Ui.printException((OmniException) exception);
        else if (exception instanceof NoSuchElementException)
            Ui.printNoSuchElementException((NoSuchElementException) exception);
        else if (exception instanceof NumberFormatException)
            Ui.printNumberTooLargeException((NumberFormatException) exception);
        else if (exception instanceof DateTimeException)
            Ui.printDateTimeExceptionError();
        else if (exception instanceof IOException)
            Ui.printSavingError();
    }
}



