package seedu.omnitravel.storage;
import seedu.omnitravel.travelactivitytypes.TravelActivityList;
import seedu.omnitravel.travelactivitytypes.Accommodation;
import seedu.omnitravel.travelactivitytypes.Food;
import seedu.omnitravel.travelactivitytypes.Landmark;
import seedu.omnitravel.travelactivitytypes.TravelActivity;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The FileSave class is responsible for saving a TravelActivity list to disk and loading
 * a TravelActivity from disk.
 *
 */
public class FileSave {
    //@@author EugeneChanJiajun
    private static Logger logger = Logger.getLogger("LoadFileLogger");
    private static String filePath;

    public FileSave(String path) {
        this.filePath = path;
    }

    /**
     * Reads data from file and loads it into a travel activity list
     * @param list The travel activity list to load data in to
     * @throws FileNotFoundException If the file does not exist
     */
    public void loadFileContents(TravelActivityList list) throws FileNotFoundException {
        logger.log(Level.INFO, "loadFileContents");
        java.io.File f = new java.io.File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String[] line = s.nextLine().split(" / ");
            String type = line[0].toLowerCase();
            String description = line[2];
            LocalDate date = LocalDate.parse(line[3]);
            String duration = line[4];
            String tag = line.length >= 6 ? line[5].trim() : "";
            String expense = line.length >= 7 ? line[6].trim() : "";
            TravelActivity activity = initialiseActivity (type, description, date, duration, tag, expense);
            list.addTravelActivity(activity);
            if (line[1].equals("1")) {
                activity.setActivityStatus(true);
            }
        }
    }

    /**
     * Initializes a travel activity object based on the given parameters
     *
     * @param type The type of travel activity (food, landmark, accommodation, general)
     * @param description The description of the travel activity
     * @param date The date of the travel activity
     * @param duration The duration of the travel activity
     * @param tag The tag of the travel activity
     * @param expense The expense of the travel activity
     * @return The TravelActivity object
     * @throws FileNotFoundException If the type is not valid
     */
    public TravelActivity initialiseActivity (String type, String description,
            LocalDate date, String duration, String tag, String expense) throws FileNotFoundException {
        TravelActivity activity;
        switch (type) {
        case "accommodation":
            activity = new Accommodation(description, date, duration, tag, expense);
            break;
        case "food":
            activity = new Food(description, date, duration, tag, expense);
            break;
        case "landmark":
            activity = new Landmark(description, date, duration, tag, expense);
            break;
        case "general":
            activity = new TravelActivity(description, date, duration, tag, expense);
            break;
        default:
            throw new FileNotFoundException("File is corrupted or has invalid format");
        }
        return activity;
    }

    /**
     * Saves the travel activity list to the file specified by the file path
     * @param list The travel activity list to save to the file
     * @throws IOException If an I/o error occurs while writing to file
     */
    public void saveActivityList(TravelActivityList list) throws IOException {
        logger.log(Level.INFO, "saveActivityList");
        FileWriter fw = new FileWriter(filePath);
        for (TravelActivity travelActivity: list.getTravelActivities()) {
            if (travelActivity instanceof Accommodation) {
                fw.write("accommodation / ");
            } else if (travelActivity instanceof Food) {
                fw.write("food / ");
            } else if (travelActivity instanceof Landmark) {
                fw.write("landmark / ");
            } else {
                fw.write("general / ");
            }
            fw.write((travelActivity.getActivityStatus() ? "1 / " : "0 / ") + travelActivity.getPlan()
                    + " / " + travelActivity.getDate()
                    + " / " + travelActivity.getDuration()
                    + " / " + travelActivity.getTag()
                    + " / " + travelActivity.getExpense()
                    + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Reads the file and loads information into the provided travel activity list.
     *
     * @param list The travel activity list to load the file into
     */
    public void readFile(TravelActivityList list) {
        logger.log(Level.INFO, "readFile");
        try {
            loadFileContents(list);
        } catch (FileNotFoundException e) {
            System.out.println("No existing database found! Creating a new save file for you!");
        }
    }
}
