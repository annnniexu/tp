package seedu.omnitravel.travel_activity_types;

import java.time.LocalDate;

public class Landmark extends TravelActivity {
    public Landmark(String line, LocalDate date, String duration, String tag, String expense){
        super(line, date, duration, tag, expense);
    }
    @Override
    public String toString(){
        return "Landmark: " + super.toString();
    }
}
