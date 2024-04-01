package seedu.duke;

import java.time.LocalDate;

public class Food extends TravelActivity {
    public Food(String line, LocalDate date, String duration, String tag, String expense){
        super(line, date, duration, tag, expense);
    }

    @Override
    public String toString(){
        return "Food: " + super.toString();
    }
}
