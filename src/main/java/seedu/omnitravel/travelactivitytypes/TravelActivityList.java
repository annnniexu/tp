package seedu.omnitravel.travelactivitytypes;
import seedu.omnitravel.errorhandlers.CheckParameters;
import seedu.omnitravel.errorhandlers.OmniException;
import seedu.omnitravel.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The TravelActivityList class represents the list of travel activities that the user inputs.
 * Methods include adding, removing, listing, expensing, updating, tagging, and filtering travel activities.
 *
 */
public class TravelActivityList {
    private static Logger logger = Logger.getLogger("TravelActivityListLogger");
    /** Array of travel activity */
    private ArrayList<TravelActivity> travelActivities;

    /** Array of tags given to travel activities */
    private ArrayList<String> travelActivityTags;

    /** Number of TravelActivities */
    private int noOfActivities = 0;
    public TravelActivityList() {
        travelActivities = new ArrayList<>();
        travelActivityTags = new ArrayList<>();
    }


    /**
     * Adds travel activity to the travel activity list
     *
     * @param travelActivity The travel activity
     */
    public void addTravelActivity(TravelActivity travelActivity){
        //logger.log(Level.INFO, "addKeyword function started");
        int initialListSize = noOfActivities;
        travelActivities.add(travelActivity);
        //logger.log(Level.INFO, "travelActivity is added");
        noOfActivities += 1;
        int newSize = noOfActivities;
        assert newSize == initialListSize + 1 :"There is an error with list size!";
    }

    /**
     * Prints out all the travel activities.
     * If sort is enabled, the list will be printed out from oldest to newest date.
     * If filterDate is enabled and a date is provided, only activities from that date will be printed
     *
     * @param sort boolean indicating if list should be sorted
     * @param filterDate boolean indicating if list should be filtered by date
     * @param date the date to be filtered by
     */
    public void listTravelActivities(boolean sort, boolean filterDate, LocalDate date){
        ArrayList<TravelActivity> activities = new ArrayList<>();
        if (filterDate) {
            for (TravelActivity a: travelActivities) {
                if (a.getDate().equals(date)) {
                    activities.add(a);
                }
            }
        } else {
            activities.addAll(travelActivities);
        }
        if (sort) {
            Collections.sort(activities, Comparator.comparing(TravelActivity::getDate));
        }
        int activityCount = 0;
        for (TravelActivity activity: activities) {
            if (activity == null) {
                break;
            }
            activityCount++;
            Ui.printActivity(activity, activityCount);
        }
        if (activityCount == 0) {
            System.out.println("There are no activities to list");
        }
        int finalActivityCount = noOfActivities;
        assert finalActivityCount >= activityCount : "Index out of bounds while listing activities";
    }

    /**
     * returns the number of travel activities in the list
     *
     * @return the number of travel activities
     */
    public int getNoOfTravelActivities(){
        return noOfActivities;
    }

    //@@author ChinYanXu
    /**
     * Removes travel activity from the travel activity list using the travel activity index number
     *
     * @param activityNumber The index number of the travel activity that the user wants to remove
     */

    public void removeTravelActivity(int activityNumber) throws OmniException {
        assert activityNumber != 0 : "There is no activities in the list";
        int indexOfActivity = activityNumber - 1;
        int initialListSize = noOfActivities;
        if(activityNumber > noOfActivities){
            throw new OmniException("I cannot find the travel activity to delete!");
        }
        TravelActivity removedActivity = travelActivities.get(indexOfActivity);
        travelActivities.remove(indexOfActivity);
        System.out.println("I have removed this activity:");
        System.out.println("1. " + removedActivity);
        noOfActivities -= 1;
        int newSize = noOfActivities;
        assert newSize == initialListSize - 1 : "There is an error with list size!";
    }

    /**
     * Overloaded version of removeTravelActivity function to enable user to remove travel activities from the travel
     * activity list using the travel activity description
     *
     * @param activity The description of travel activity that the user wants to remove
     */

    public void removeTravelActivity(String activity){
        int foundCounter = 0;
        for (int iterator = 0; iterator < travelActivities.size(); iterator += 1) {
            if (travelActivities.get(iterator).getPlan().toLowerCase().contains(activity.toLowerCase())) {
                if (foundCounter == 0) {
                    System.out.println("I have removed this activity:");
                }
                System.out.println(Integer.toString(foundCounter + 1) + ". " + travelActivities.get(iterator));
                travelActivities.remove(iterator);
                iterator -= 1;
                noOfActivities -= 1;
                foundCounter += 1;
                assert noOfActivities >= 0 : "There is an error with list size!";
            }
        }
        if (foundCounter == 0) {
            System.out.println("Can't find any related activity to remove");
        }
    }
      
    //@@author EugeneChanJiajun
    /**
     * Obtains the description of the plan that we are looking for from the travel activity list
     *
     * @param plan Plan that we are looking for in the travel activity list
     * @return The travel activity description user is looking for
     */
    public String getDescription(String plan){
        for(TravelActivity travelActivity: travelActivities){
            if(travelActivity.getPlan().equals(plan)){
                return travelActivity.getPlan();
            }
        }
        return "cant be found";
    }
    //@@author ChinYanXu
    /**
     * Finds all activities in the TravelActivity list that contains a keyword specified by the user.
     *
     * @param activityName keyword specified by the user to find travel activities in the TravelActivity list
     *                 with description related to the keyword.
     */

    public void searchKeyword (String activityName) {
        int foundCounter = 0;
        for (TravelActivity travelActivity : travelActivities) {
            assert !(foundCounter > travelActivities.size()) : "Error: There is more activities found than possible";
            if (travelActivity.getPlan().contains(activityName) &&
                    !travelActivity.getPlan().isEmpty()) {
                foundCounter += 1;
                if (foundCounter == 1) {
                    System.out.println("Here are what you are looking for:");
                }
                Ui.printActivity(travelActivity, foundCounter);
            }
        }
        if (foundCounter == 0) {
            System.out.println("Sorry I could not find what you are looking for.");
        }
    }

    /**
     * Overloaded version of searchKeyword function to enable user to exclude certain travel activities from their
     * search
     *
     * @param activityName The description of the travel activities that the user wants to find
     * @param exclusion The keyword that the travel activities uses to remove unwanted results from the search
     */

    public void searchKeyword (String activityName, String exclusion) {
        int foundCounter = 0;
        String lowerCaseActivityName = activityName.toLowerCase();
        String lowerCaseExclusion = exclusion.toLowerCase();
        for (TravelActivity travelActivity : travelActivities) {
            assert !(foundCounter > travelActivities.size()) : "Error: There is more activities found than possible";
            if (travelActivity.getPlan().toLowerCase().contains(lowerCaseActivityName)
                    && !travelActivity.getPlan().toLowerCase().contains(lowerCaseExclusion)
                    && !travelActivity.getPlan().isEmpty()) {
                foundCounter += 1;
                if (foundCounter == 1) {
                    System.out.println("Here are what you are looking for:");
                }
                Ui.printActivity(travelActivity, foundCounter);
            }
        }
        if (foundCounter == 0) {
            System.out.println("Sorry I could not find what you are looking for.");
        }
    }


    //@@author annnniexu
    /**
     * Checks travel activity as completed
     *
     * @param activityNumber The travel activity number on the list
     */
    public void checkTravelActivity(int activityNumber) throws OmniException{
        assert activityNumber != 0 : "There is not activities in the list";
        if (activityNumber > travelActivities.size() || (activityNumber <= 0)) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfActivity = activityNumber - 1;
        TravelActivity markedActivity = travelActivities.get(indexOfActivity);
        if (!markedActivity.getActivityStatus()) {
            markedActivity.setActivityStatus(true);
            System.out.println("I have checked this activity:");
            Ui.printActivity(markedActivity, activityNumber);
        } else {
            System.out.println("This activity is already done!");
        }
    }

    /**
     * Unchecks travel activity and sets it to uncompleted
     *
     * @param activityNumber The travel activity number on the list
     */
    public void uncheckTravelActivity(int activityNumber) throws OmniException{
        assert activityNumber != 0 : "There is not activities in the list";
        if (activityNumber > travelActivities.size() || (activityNumber <= 0)) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfActivity = activityNumber - 1;
        TravelActivity markedActivity  = travelActivities.get(indexOfActivity);
        if (markedActivity.getActivityStatus()) {
            markedActivity.setActivityStatus(false);
            System.out.println("I have unchecked this activity:");
            Ui.printActivity(markedActivity, activityNumber);
        } else {
            System.out.println("This activity is already unchecked!");
        }
    }

    //@@author ChenKangg
    /**
     * Adds a tag to travel activity
     *
     * @param taskNumber The travel activity number on the list
     * @param tag The tag of travel activity
     * @throws OmniException Thrown if the index of the travel activity cannot be found
     */
    public void tagActivity(int taskNumber, String tag) throws OmniException {
        assert taskNumber != 0 : "There is no tasks in the list";
        logger.log(Level.INFO, "Tagging activity: taskNumber = " + taskNumber + ", tag = " + tag);

        if (taskNumber > travelActivities.size() || (taskNumber <= 0)) {
            logger.log(Level.WARNING, "Invalid taskNumber provided: " + taskNumber);
            throw new OmniException("Travel activity cannot be found");
        }

        int indexOfTask = taskNumber - 1;
        TravelActivity taggedTask = travelActivities.get(indexOfTask);
        taggedTask.setTag(tag);
        System.out.println("I have tagged this task:");
        System.out.println(taggedTask);
    }

    /**
     * Removes the tag on a travel activity
     *
     * @param taskNumber The travel activity number on the list
     * @throws OmniException Thrown if the index of the travel activity cannot be found
     */
    public void removeTag(int taskNumber) throws OmniException {
        assert taskNumber != 0 : "There is no task in the list";
        logger.log(Level.INFO, "Removing tag from activity: taskNumber = " + taskNumber);

        if (taskNumber > travelActivities.size() || (taskNumber <= 0)) {
            logger.log(Level.WARNING, "Invalid taskNumber provided: " + taskNumber);
            throw new OmniException("Travel activity cannot be found");
        }

        int indexOfTask = taskNumber - 1;
        TravelActivity taggedTask = travelActivities.get(indexOfTask);
        if (taggedTask.getTag().isBlank()){
            throw new OmniException("Travel activity does not have a tag!");
        }
        taggedTask.removeTag();
        System.out.println("Tag removed from the task:");
        System.out.println(taggedTask);
    }

    //@@author daryltay415
    /**
     * Updates the date, duration and tag of the travel activity
     *
     * @param travelActivityNumber The index of the travel activity
     * @param date The new date of the travel activity
     * @param duration The new duration of the travel activity
     * @param tag The new tag of the travel activity
     * @throws OmniException Thrown if the index of the travel activity cannot be found
     */
    public void updateTravelActivity(int travelActivityNumber, LocalDate date, String duration, String tag)
            throws OmniException{
        if (travelActivityNumber > travelActivities.size() || (travelActivityNumber <= 0)){
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTravelActivity = travelActivityNumber-1;
        TravelActivity updatedTravelActivity = travelActivities.get(indexOfTravelActivity);
        String oldTravelActivity = updatedTravelActivity.toString();
        updatedTravelActivity.setDate(date);
        updatedTravelActivity.setDuration(duration);
        if(!tag.isBlank()){
            updatedTravelActivity.setTag(tag);
        }
        System.out.println("I have updated this task\nfrom: " + oldTravelActivity +
                "\nto: " + updatedTravelActivity);
    }

    //@@author EugeneChanJiajun
    public ArrayList<TravelActivity> getTravelActivities () {
        return travelActivities;
    }

    //@@author ChinYanXu
    /**
     * Find all the travel activities with a particular tag and prints them out
     *
     * @param tag The tag of travel activities that the user wants to find
     */

    public void findTag(String tag){
        int foundCounter = 0;
        for (TravelActivity travelActivity : travelActivities) {
            assert !(foundCounter > travelActivities.size()) : "Error: There is more activities found than possible";
            if (travelActivity.getTag().contains(tag) &&
                    !travelActivity.getTag().isEmpty()) {
                foundCounter += 1;
                if (foundCounter == 1) {
                    System.out.println("Here are what you are looking for:");
                }
                Ui.printActivity(travelActivity, foundCounter);
            }
        }
        if (foundCounter == 0) {
            System.out.println("Sorry I could not find what you are looking for.");
        }
    }

    /**
     * Overloaded version of findtag function to enable user to exclude certain travel activities from their search
     *
     * @param tag The tag of the travel activities that the user wants to find
     * @param exclude The keyword that the user uses to remove unwanted results from the search
     */

    public void findTag(String tag, String exclude){
        int foundCounter = 0;
        String lowerCaseExclude = exclude.toLowerCase();
        for (TravelActivity travelActivity : travelActivities) {
            assert !(foundCounter > travelActivities.size()) : "Error: There is more activities found than possible";
            if (travelActivity.getTag().contains(tag) && !travelActivity.getTag().isEmpty() &&
                    !travelActivity.getPlan().toLowerCase().contains(lowerCaseExclude)) {
                foundCounter += 1;
                if (foundCounter == 1) {
                    System.out.println("Here are what you are looking for:");
                }
                Ui.printActivity(travelActivity, foundCounter);
            }
        }
        if (foundCounter == 0) {
            System.out.println("Sorry I could not find what you are looking for.");
        }
    }

    /**
     * Find all the travel activities of a particular type and prints them out
     *
     * @param type The type of travel activities that the user wants to find
     */

    public void findType(String type){
        int foundCounter = 0;

        for (TravelActivity activity: travelActivities){
            assert !(foundCounter > travelActivities.size()) : "Error: There is more activities found than possible";
            if(activity.getClass().getSimpleName().equalsIgnoreCase(type)){
                foundCounter += 1;
                if (foundCounter == 1) {
                    System.out.println("Here are what you are looking for:");
                }
                Ui.printActivity(activity, foundCounter);
            }
        }
        if (foundCounter == 0) {
            System.out.println("Sorry, I could not find what you are looking for.");
        }
    }

    //@@author ChenKangg
    /**
     * Overloaded version of findtype to enable user to exclude certain activities from their search
     *
     * @param type The type of travel activities that the user wants to find
     * @param exclude The keyword that the user uses to remove unwanted results from the search
     *
     */
    public void findType(String type, String exclude) {
        int foundCounter = 0;
        assert type != null && !type.isEmpty() : "Type parameter should not be null or empty";
        assert exclude != null && !exclude.isEmpty() : "Exclude parameter should not be null or empty";

        logger.log(Level.INFO, "Finding type: " + type + ", excluding: " + exclude);
        String lowerCaseExclude = exclude.toLowerCase();
        for (TravelActivity activity : travelActivities) {
            assert !(foundCounter > travelActivities.size()) : "Error: There are more activities found than possible";

            if (activity.getClass().getSimpleName().equalsIgnoreCase(type)
                    && !activity.getPlan().toLowerCase().contains(lowerCaseExclude)) {
                foundCounter++;
                if (foundCounter == 1) {
                    logger.log(Level.INFO, "Found matching activities:");
                    System.out.println("Here are what you are looking for:");
                }
                Ui.printActivity(activity, foundCounter);
            }
        }

        if (foundCounter == 0) {
            logger.log(Level.INFO, "No matching activities found");
            System.out.println("Sorry, I could not find what you are looking for.");
        }
    }

    /**
     * Adds expense to travel activity
     *
     * @param taskNumber The travel activity number on the list
     * @param expense  The expense of travel activity
     * @throws OmniException Thrown if the index of the travel activity cannot be found
     */
    public void expenseActivity(int taskNumber, String expense) throws OmniException {
        assert taskNumber != 0 : "There is no tasks in the list";
        logger.log(Level.INFO, "Adding expense to activity: taskNumber = " + taskNumber + ", expense = " + expense);

        if (taskNumber > travelActivities.size() || (taskNumber <= 0)) {
            logger.log(Level.WARNING, "Invalid taskNumber provided: " + taskNumber);
            throw new OmniException("Travel activity cannot be found");
        }

        int indexOfTask = taskNumber - 1;
        TravelActivity task = travelActivities.get(indexOfTask);

        if (!(expense.startsWith("$") && CheckParameters.isValidExpense(expense.substring(1)))) {
            logger.log(Level.WARNING, "Invalid expense format: " + expense);
            throw new OmniException("Please follow format for expense: expense $50");
        }

        task.setExpense(expense);

        System.out.println("I have added expense for this task:");
        System.out.println(task + " (" + expense + ")");

    }

    /**
     * Removes the expense on a travel activity
     *
     * @param taskNumber The travel activity number on the list
     * @throws OmniException Thrown if the index of the travel activity cannot be found
     */
    public void removeExpense(int taskNumber) throws OmniException {
        assert taskNumber != 0 : "There is no task in the list";
        logger.log(Level.INFO, "Removing expense from activity: taskNumber = " + taskNumber);

        if (taskNumber > travelActivities.size() || (taskNumber <= 0)) {
            logger.log(Level.WARNING, "Invalid taskNumber provided: " + taskNumber);
            throw new OmniException("Travel activity cannot be found");
        }

        int indexOfTask = taskNumber - 1;
        TravelActivity task = travelActivities.get(indexOfTask);
        if (task.getExpense().isBlank()){
            throw new OmniException("Travel activity does not have a expense!");
        }
        task.removeExpense();
        System.out.println("Expense removed from the task:");
        System.out.println(task);
    }

    //@@author annnniexu
    /**
     * Calculates the total expense for the given type.
     *
     * @param type The type of tasks that the user wants to find
     */

    public void totalExpense(String type) throws OmniException {
        if (!(type.equalsIgnoreCase("food") || type.equalsIgnoreCase("accommodation")
                || type.equalsIgnoreCase("landmark") || type.equalsIgnoreCase("all")
                || type.equalsIgnoreCase("travelactivity"))) {
            throw new OmniException("Not a valid TYPE");
        }

        double tot = 0.0;
        for (TravelActivity activity : travelActivities) {
            if (type.equals("all") || activity.getClass().getSimpleName().equalsIgnoreCase(type)){
                String expense = activity.getExpense();
                if (!expense.equals("")) {
                    if (expense.startsWith("$")) {
                        expense = expense.substring(1);
                    }
                    tot += Double.parseDouble(expense);
                    logger.log(Level.INFO, String.valueOf(tot));
                }
            }
        }
        if(type.equalsIgnoreCase("travelactivity")){
            type = "General";
        }
        System.out.println("The total expense for " + type + " travel activities is: $" + tot);
    }

    //@@author ChenKangg
    /**
     * Lists out all the tags currently in the travel activity list
     */
    public void listTags() {
        assert travelActivities != null : "Travel activities list should not be null";

        logger.log(Level.INFO, "Listing tags");

        for (TravelActivity travelActivity : travelActivities) {
            assert travelActivity != null : "Travel activity should not be null";

            String tag = travelActivity.getTag();
            if (!travelActivityTags.contains(tag) && !tag.isBlank()) {
                travelActivityTags.add(tag);
            }
        }

        Collections.sort(travelActivityTags);

        int tagCount = 1;
        for (String tag : travelActivityTags) {
            assert tag != null : "Tag should not be null";

            System.out.println(tagCount + ". " + tag);
            tagCount++;
        }
    }

}
