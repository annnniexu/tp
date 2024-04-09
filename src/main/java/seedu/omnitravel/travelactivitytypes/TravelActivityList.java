package seedu.omnitravel.travelactivitytypes;
import seedu.omnitravel.errorhandlers.CheckParameters;
import seedu.omnitravel.errorhandlers.OmniException;
import seedu.omnitravel.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;


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
     * Prints out all the travel activities
     */
    public void listTravelActivities(){
        int activityCount = 0;
        for (TravelActivity activity: travelActivities) {
            if (activity == null) {
                break;
            }
            activityCount++;
            Ui.printActivity(activity, activityCount);
        }
        int finalActivityCount = noOfActivities;
        assert finalActivityCount == activityCount : "Index out of bounds while listing activities";
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
     * Removes travel activity from the travel activity list
     * @param activityNumber The travel activity index number or description on the list
     */
    public void removeTravelActivity(String activityNumber) throws OmniException {
        try {
            assert Integer.parseInt(activityNumber) != 0 : "There is not activities in the list";
            int indexOfActivity = Integer.parseInt(activityNumber) - 1;
            int initialListSize = noOfActivities;
            TravelActivity removedActivity = travelActivities.get(indexOfActivity);
            travelActivities.remove(indexOfActivity);
            System.out.println("I have removed this activity:");
            System.out.println(removedActivity);
            noOfActivities -= 1;
            int newSize = noOfActivities;
            assert newSize == initialListSize - 1 : "There is an error with list size!";
        } catch (NumberFormatException e) {
            /*
            int foundCounter = 0;
            for (int iterator = 0; iterator < travelActivities.size(); iterator += 1) {
                if (travelActivities.get(iterator).getPlan().toLowerCase().contains(activityNumber.toLowerCase())) {
                    if (foundCounter == 0) {
                        System.out.println("I have removed this activity:");
                    }
                    System.out.println(Integer.toString(foundCounter + 1) + ". " + travelActivities.get(iterator));
                    travelActivities.remove(iterator);
                    foundCounter += 1;
                    assert noOfActivities >= 0 : "There is an error with list size!";
                }
            }
            noOfActivities -= foundCounter;
            if (foundCounter == 0) {
                System.out.println("Travel activity cannot be found!");
            }*/
            ArrayList<TravelActivity> found = new ArrayList<>();
            for (TravelActivity activity: travelActivities){
                if (activity.getPlan().toLowerCase().contains(activityNumber)){
                    found.add(activity);
                }
            }
            travelActivities.removeAll(found);
        }
    }
    //@@author ChinYanXu
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

    /**
     * Finds all activities in the TravelActivity list that contains a keyword specified
     * by the user.
     *
     * @param activityName keyword specified by the user to find activities in the TravelActivity list
     *                 related to the keyword.
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


    /**
     * Adds a tag to travel activity
     *
     * @param taskNumber The travel activity number on the list
     * @param tag The tag of travel activity
     */
    public void tagActivity(int taskNumber, String tag) throws OmniException {
        assert taskNumber != 0 : "There is no tasks in the list";
        if (taskNumber > travelActivities.size() || (taskNumber <= 0)) {
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
     */
    public void removeTag(int taskNumber) throws OmniException {
        assert taskNumber != 0 : "There is no task in the list";
        if (taskNumber > travelActivities.size() || (taskNumber <= 0)) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTask = taskNumber - 1;
        TravelActivity taggedTask = travelActivities.get(indexOfTask);
        taggedTask.removeTag();
        System.out.println("Tag removed from the task:");
        System.out.println(taggedTask);
    }

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
        String oldTag = updatedTravelActivity.getTag();
        String oldTravelActivity = (oldTag.isBlank())? updatedTravelActivity.toString():
                                            (updatedTravelActivity.toString()
                                            + " (" + updatedTravelActivity.getTag() + ")");
        updatedTravelActivity.setDate(date);
        updatedTravelActivity.setDuration(duration);
        updatedTravelActivity.setTag(tag);
        String newTag = updatedTravelActivity.getTag();
        if(newTag.isBlank()){
            System.out.println("I have updated this task\nfrom: " + oldTravelActivity +
                    "\nto: " + updatedTravelActivity);
        } else{
            System.out.println("I have updated this task\nfrom: " + oldTravelActivity +
                    "\nto: " + updatedTravelActivity + " (" + updatedTravelActivity.getTag() + ")");
        }

    }

    public ArrayList<TravelActivity> getTravelActivities () {
        return travelActivities;
    }

    /**
     * Find all the tasks with a particular tag and prints them out
     *
     * @param tag The tag of tasks that the user wants to find
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
     * Find all the tasks of a particular type and prints them out
     *
     * @param type The type of tasks that the user wants to find
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
            System.out.println("Sorry I could not find what you are looking for.");
        }
    }

    /**
     * Adds expense to travel activity
     *
     * @param taskNumber The travel activity number on the list
     * @param expense  The expense of travel activity
     */
    public void expenseActivity(int taskNumber, String expense) throws OmniException {
        assert taskNumber != 0 : "There is no tasks in the list";
        if (taskNumber > travelActivities.size() || (taskNumber <= 0)) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTask = taskNumber - 1;
        TravelActivity task = travelActivities.get(indexOfTask);
        if(!(expense.startsWith("$") && CheckParameters.isValidExpense(expense.substring(1)))){
            throw new OmniException("Please follow format for expense: $50");
        }
        task.setExpense(expense);
        System.out.println("I have added expense for this task:");
        System.out.println(task + " (" + expense + ")");
    }

    /**
     * Removes the expense on a travel activity
     *
     * @param taskNumber The travel activity number on the list
     */
    public void removeExpense(int taskNumber) throws OmniException {
        assert taskNumber != 0 : "There is no task in the list";
        if (taskNumber > travelActivities.size() || (taskNumber <= 0)) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTask = taskNumber - 1;
        TravelActivity task = travelActivities.get(indexOfTask);
        task.removeExpense();
        System.out.println("Expense removed from the task:");
        System.out.println(task);
    }

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
        System.out.println("The total expense for " + type + " travel activities is: $" + tot);
    }

    /**
     * Lists out all the tags currently in the travel activity list
     */
    public void listTags(){
        for (TravelActivity travelActivity: travelActivities){
            if(travelActivity == null){
                break;
            }
            String tag = travelActivity.getTag();
            if(!travelActivityTags.contains(tag) && !tag.isBlank() ){
                travelActivityTags.add(tag);
            }
        }
        Collections.sort(travelActivityTags);
        int tagCount = 1;
        for (String tag: travelActivityTags){
            if(tag == null){
                break;
            }
            System.out.println(tagCount + ". " + tag);
            tagCount++;
        }
    }

    /**
     * Adds a location to travel activity
     * @param taskNumber The travel activity number on the list
     * @param location The location of travel activity
     */
    public void locationActivity(int taskNumber, String location) throws OmniException {
        assert taskNumber != 0 : "There is no tasks in the list";
        if (taskNumber > travelActivities.size() || (taskNumber <= 0)) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTask = taskNumber - 1;
        TravelActivity locationTask = travelActivities.get(indexOfTask);
        locationTask.setLocation(location);
        System.out.println("I have added a location to this task:");
        System.out.println(locationTask + " (" + location + ")");
    }

    /**
     * Removes the location on a travel activity
     * @param taskNumber The travel activity number on the list
     */
    public void removeLocation(int taskNumber) throws OmniException {
        assert taskNumber != 0 : "There is no task in the list";
        if (taskNumber > travelActivities.size() || (taskNumber <= 0)) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTask = taskNumber - 1;
        TravelActivity locationTask = travelActivities.get(indexOfTask);
        locationTask.removeLocation();
        System.out.println("Location removed from the task:");
        System.out.println(locationTask);
    }

    /**
     * Find all the tasks with a particular location and prints them out
     *
     * @param location The tag of tasks that the user wants to find
     */
    public void findLocation(String location){
        int foundCounter = 0;
        for (TravelActivity travelActivity : travelActivities) {
            assert !(foundCounter > travelActivities.size()) : "Error: There is more activities found than possible";
            if (travelActivity.getLocation().contains(location) &&
                    !travelActivity.getLocation().isEmpty()) {
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

}
