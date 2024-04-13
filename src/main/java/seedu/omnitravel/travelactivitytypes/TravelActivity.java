package seedu.omnitravel.travelactivitytypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The TravelActivity class represents a single travel activity and has attributes
 * description, date, duration, tag, expense, and whether or not the activity is completed.
 *
 */

public class TravelActivity {
    /** Travel activity description */
    private String travelActivity;
    /** Travel activity date */
    private LocalDate date;
    /** Travel activity duration */
    private String duration;
    /** Travel activity status */
    private boolean activityIsDone = false;
    /** Travel activity tag */
    private String tag;
    /** Travel activity expense */
    private String expense;



    public TravelActivity(String description, LocalDate date, String duration, String tag, String expense){
        travelActivity = description;
        this.date = date;
        this.duration = duration;
        this.tag = tag;
        this.expense = expense;
    }

    @Override
    public String toString(){
        if(tag.isBlank()){
            return travelActivity + " :" + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " :" + duration;
        }
        return travelActivity + " :" + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " :" + duration
                + " (" + tag +")";

    }

    /**
     * Sets the status of the activity to complete or incomplete
     * @param activityIsDone activity status
     */
    public void setActivityStatus(boolean activityIsDone){
        this.activityIsDone = activityIsDone;
    }

    /**
     * Gets the description of the travel activity
     * @return The description of the travel activity
     */
    public String getPlan(){
        return travelActivity;
    }

    /**
     * Gets the status of the travel activity
     * @return boolean representing if activity is done or not
     */
    public boolean getActivityStatus() {
        return activityIsDone;
    }

    /**
     * Sets the date of the travel activity
     * @param date the date to be set
     */
    public void setDate(LocalDate date){
        this.date = date;
    }

    /**
     * Gets the date of the travel activity.
     * @return the date of the travel activity
     */
    public LocalDate getDate(){
        return date;
    }

    /**
     * Sets the duration of the travel activity.
     * @param duration the duration to be set
     */
    public void setDuration(String duration){
        this.duration = duration;
    }

    /**
     * Gets the duration of the travel activity
     * @return the duration of the travel activity
     */
    public String getDuration(){
        return duration;
    }

    /**
     * Get the tag of the travel activity
     * @return The tag of the travel activity
     */
    public String getTag() {
        return tag;
    }

    /**
     * Set the tag of the travel activity
     * @param tag The tag to be set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Remove the tag of the travel activity
     */
    public void removeTag() {
        this.tag = "";
    }

    /**
     * Get the expense of the travel activity
     * @return The expense of the travel activity
     */
    public String getExpense() {
        return expense;
    }

    /**
     * Set the expense of the travel activity
     * 
     * @param expense The expense of the travel activity
     */
    public void setExpense(String expense) {
        this.expense = expense;
    }

    /**
     * Remove the expense of the travel activity
     */
    public void removeExpense() {
        this.expense = "";
    }


}

