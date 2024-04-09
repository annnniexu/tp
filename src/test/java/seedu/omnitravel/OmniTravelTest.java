package seedu.omnitravel;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seedu.omnitravel.errorhandlers.CheckParameters;
import seedu.omnitravel.errorhandlers.OmniException;
import seedu.omnitravel.parser.Parser;
import seedu.omnitravel.travelactivitytypes.Food;
import seedu.omnitravel.travelactivitytypes.TravelActivity;
import seedu.omnitravel.travelactivitytypes.TravelActivityList;
import seedu.omnitravel.travelactivitytypes.Accommodation;
import seedu.omnitravel.travelactivitytypes.Landmark;
import seedu.omnitravel.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

class OmniTravelTest {


    Accommodation accommodationNew1 = new Accommodation("nus rvrc", LocalDate.parse("2025-12-12"),
            "5 years", "campus stay", "");
    Accommodation accommodationNew2 = new Accommodation("nus pgpr", LocalDate.parse("2025-10-12"),
            "5 years", "campus stay", "");
    Accommodation accommodationNew3 = new Accommodation("nus utr", LocalDate.parse("2025-09-12"),
            "5 years", "campus stay", "");
    Landmark landmarkNew1 = new Landmark("berlin wall", LocalDate.parse("2027-12-15"), "5 hours",
            "historic site", "");
    Landmark landmarkNew2 = new Landmark("utown", LocalDate.parse("2028-08-14"), "10 hours",
            "recreational centre", "");
    Landmark landmarkNew3 = new Landmark("supper stretch", LocalDate.parse("2027-08-18"), "2 hours",
            "tourist hotspot", "");
    Food foodNew1 = new Food("utown mala", LocalDate.parse("2028-06-19"), "2 hours",
            "spicy", "");
    Food foodNew2 = new Food("pgpr mala", LocalDate.parse("2026-07-07"), "1 hours",
            "spicy", "");
    Food foodNew3 = new Food("pgpr waffle", LocalDate.parse("2026-03-09"), "0.5 hours",
            "non-spicy", "");
    TravelActivity travelActivityNew1 = new TravelActivity("esplanade", LocalDate.parse("2026-03-19"),
            "3 hours", "concert", "");
    TravelActivity travelActivityNew2 = new TravelActivity("merlion", LocalDate.parse("2026-04-07"),
            "2 hours", "sightseeing", "");
    TravelActivity travelActivityNew3 = new TravelActivity("chinatown", LocalDate.parse("2025-02-21"),
            "5 hours", "sightseeing", "");

    private final PrintStream printedText = System.out;
    private final ByteArrayOutputStream capturedOutputStream = new ByteArrayOutputStream();


    public TravelActivityList initialiseTestTravelActivityList() {
        TravelActivityList travelActivityListNew = new TravelActivityList();
        travelActivityListNew.addTravelActivity(accommodationNew1);
        travelActivityListNew.addTravelActivity(accommodationNew2);
        travelActivityListNew.addTravelActivity(accommodationNew3);
        travelActivityListNew.addTravelActivity(landmarkNew1);
        travelActivityListNew.addTravelActivity(landmarkNew2);
        travelActivityListNew.addTravelActivity(landmarkNew3);
        travelActivityListNew.addTravelActivity(foodNew1);
        travelActivityListNew.addTravelActivity(foodNew2);
        travelActivityListNew.addTravelActivity(foodNew3);
        travelActivityListNew.addTravelActivity(travelActivityNew1);
        travelActivityListNew.addTravelActivity(travelActivityNew2);
        travelActivityListNew.addTravelActivity(travelActivityNew3);
        return travelActivityListNew;
    }


    @BeforeEach
    public void setUpPrintLnTest() {
        System.setOut(new PrintStream(capturedOutputStream));
    }

    @AfterEach
    public void restorePrintLn() {
        System.setOut(printedText);
    }

    @Test
    public void addTest() {
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity1 = new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "");
        TravelActivity travelActivity2 = new TravelActivity("visit home",
                LocalDate.parse("2019-12-14"), "5hours", "Sightseeing", "$50");
        travelActivityList.addTravelActivity(travelActivity1);
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        travelActivityList.addTravelActivity(travelActivity2);
        assertEquals("visit home", travelActivityList.getDescription("visit home"));
    }

    @Test
    public void deleteTest() throws OmniException {
        //add the plan
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity = new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "$50");
        travelActivityList.addTravelActivity(travelActivity);
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //delete the plan
        travelActivityList.removeTravelActivity("1");
        assertEquals("cant be found", travelActivityList.getDescription("visit museum"));
        //testing the keyword delete enhancement
        TravelActivity travelActivity2 = new TravelActivity("visit home",
                LocalDate.parse("2019-12-14"), "5hours", "Sightseeing", "$50");
        travelActivityList.removeTravelActivity("home");
        assertEquals("cant be found", travelActivityList.getDescription("visit home"));

    }

    @Test
    public void getNoActivitiesTest() throws OmniException {
        //add the first plan
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity1 = new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "30");
        TravelActivity travelActivity2 = new TravelActivity("go to beach",
                LocalDate.parse("2018-10-12"),"3hours", "Sightseeing", "30");
        TravelActivity travelActivity3 = new TravelActivity("shopping",
                LocalDate.parse("2020-12-05"),"5hours", "Shopping", "$100");
        travelActivityList.addTravelActivity(travelActivity1);
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //add the second and third plan
        travelActivityList.addTravelActivity(travelActivity2);
        assertEquals("go to beach", travelActivityList.getDescription("go to beach"));
        travelActivityList.addTravelActivity(travelActivity3);
        assertEquals("shopping", travelActivityList.getDescription("shopping"));
        //check number of activities
        assertEquals(3, travelActivityList.getNoOfTravelActivities());
        //delete the first plan
        travelActivityList.removeTravelActivity("1");
        assertEquals("cant be found", travelActivityList.getDescription("visit museum"));
        //check number of activities
        assertEquals(2, travelActivityList.getNoOfTravelActivities());
    }

    @Test
    public void checkTest() throws OmniException {
        //add the first plan
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity1 = new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "$50");
        travelActivityList.addTravelActivity(travelActivity1);
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //check the plan
        travelActivityList.checkTravelActivity(1);
        TravelActivity travelActivity2 = travelActivityList.getTravelActivities().get(0);
        assertTrue(travelActivity2.getActivityStatus());
    }

    @Test
    public void uncheckTest() throws OmniException {
        //add the first plan
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity1 = new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "$50");
        travelActivityList.addTravelActivity(travelActivity1);
        assertEquals("visit museum", travelActivityList.getDescription("visit museum"));
        //check the plan
        travelActivityList.checkTravelActivity(1);
        TravelActivity travelActivity2 = travelActivityList.getTravelActivities().get(0);
        assertTrue(travelActivity2.getActivityStatus());
        //uncheck the plan
        travelActivityList.uncheckTravelActivity(1);
        assertFalse(travelActivity2.getActivityStatus());
    }

    @Test
    public void trueTest(){
        assertTrue(true);
    }

    @Test
    //basic test for searchKeyword function
    public void findTest () {
        try {
            TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
            String[] command1 = new String[]{"find", "mala"};
            String findExpectedOutput = "Here are what you are looking for:" + System.lineSeparator() +
                    "[ ] 1. Food: utown mala :19 Jun 2028 :2 hours (spicy)" + System.lineSeparator() +
                    "[ ] 2. Food: pgpr mala :7 Jul 2026 :1 hours (spicy)"  + System.lineSeparator();
            Parser.findCommand("find mala", travelActivityListNew);
            assertEquals(capturedOutputStream.toString(), findExpectedOutput);
        } catch (OmniException exception) {
            Ui.printException(exception);
        }
    }

    @Test
    //basic test for searchKeyword function
    public void findTagTest () {
        try {
            TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
            String findExpectedOutput2 = "Here are what you are looking for:" + System.lineSeparator() +
                    "[ ] 1. General: merlion :7 Apr 2026 :2 hours (sightseeing)" + System.lineSeparator() +
                    "[ ] 2. General: chinatown :21 Feb 2025 :5 hours (sightseeing)" + System.lineSeparator();
            Parser.findTagCommand("findtag sightseeing", travelActivityListNew);
            assertEquals(capturedOutputStream.toString(), findExpectedOutput2);

        } catch (OmniException exception) {
            Ui.printException(exception);
        }
    }

    @Test
    //basic test for searchKeyword function
    public void findTypeTest () {
        try {
            TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
            String findExpectedOutput3 = "Here are what you are looking for:" + System.lineSeparator() +
                    "[ ] 1. Accommodation: nus rvrc :12 Dec 2025 :5 years (campus stay)" + System.lineSeparator() +
                    "[ ] 2. Accommodation: nus pgpr :12 Oct 2025 :5 years (campus stay)" + System.lineSeparator() +
                    "[ ] 3. Accommodation: nus utr :12 Sep 2025 :5 years (campus stay)" + System.lineSeparator();
            Parser.findTypeCommand("findtype Accommodation", travelActivityListNew);
            assertEquals(capturedOutputStream.toString(), findExpectedOutput3);
            Parser.findTypeCommand("findtype Accommodation", travelActivityListNew);

        } catch (OmniException exception) {
            Ui.printException(exception);
        }
    }

    @Test
    public void testTagActivity() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "$50"));
        assertEquals("visit museum", list.getDescription("visit museum"));
        // Tagging an existing task
        list.tagActivity(1, "activity 1");
        TravelActivity travelActivity = list.getTravelActivities().get(0);
        assertEquals("activity 1", travelActivity.getTag());
    }

    @Test
    public void testRemoveTagFromActivity() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "$100"));
        assertEquals("visit museum", list.getDescription("visit museum"));
        // Tagging an existing task
        list.tagActivity(1, "activity 1");
        TravelActivity travelActivity = list.getTravelActivities().get(0);
        assertEquals("activity 1", travelActivity.getTag());
        // Remove an existing tag
        list.removeTag(1);
        assertEquals("visit museum", list.getDescription("visit museum"));
    }

    @Test
    public void testUpdateActivity() throws OmniException{
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity1 = new TravelActivity("Go Paris",
                LocalDate.parse("2019-02-10"),"2hours", "Sightseeing", "$40");
        travelActivityList.addTravelActivity(travelActivity1);
        assertEquals("10 Feb 2019",
                travelActivity1.getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        assertEquals("2hours", travelActivity1.getDuration());
        travelActivityList.updateTravelActivity(1,
                LocalDate.parse("2020-12-10"), "3hours", "misc");
        assertEquals("10 Dec 2020",
                travelActivity1.getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        assertEquals("3hours", travelActivity1.getDuration());
    }

    @Test
    public void testExpenseActivity() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "$30"));
        assertEquals("visit museum", list.getDescription("visit museum"));
        // adding expense to existing task
        list.expenseActivity(1, "$50");
        TravelActivity travelActivity = list.getTravelActivities().get(0);
        assertEquals("$50", travelActivity.getExpense());
    }

    @Test
    public void testRemoveExpense() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "$20"));
        assertEquals("visit museum", list.getDescription("visit museum"));
        // adding expense to existing task
        list.expenseActivity(1, "$50");
        TravelActivity travelActivity = list.getTravelActivities().get(0);
        assertEquals("$50", travelActivity.getExpense());
        // Remove an existing expense
        list.removeExpense(1);
        assertEquals("visit museum", list.getDescription("visit museum"));
    }

    @Test
    public void testTotalExpanseAll() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "$20"));
        list.totalExpense("all");

        String findExpectedOutput = "The total expense for all travel activities is: $20.0";
        assertEquals(capturedOutputStream.toString().trim(), findExpectedOutput);
    }

    @Test
    public void testTotalExpanseAccommodation() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new Accommodation("RVRC", LocalDate.parse("2022-07-12"), "3hours",
                "hostel", "$70"));
        String findExpectedOutput1 = "The total expense for Accommodation travel activities is: $70.0";
        list.totalExpense("Accommodation");
        assertEquals(capturedOutputStream.toString().trim(), findExpectedOutput1);
    }

    @Test
    public void testTotalExpanseFood() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new Food("UTOWN Flavours", LocalDate.parse("2028-05-12"), "1 hours",
                "lunch", "$10"));
        String findExpectedOutput2 = "The total expense for Food travel activities is: $10.0";
        list.totalExpense("Food");
        assertEquals(capturedOutputStream.toString().trim(), findExpectedOutput2);
    }

    @Test
    public void testTotalExpanseLandmark() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new Landmark("Berlin Wall", LocalDate.parse("2027-08-14"), "1 hours",
                "sightseeig", "$5"));
        String findExpectedOutput2 = "The total expense for Landmark travel activities is: $5.0";
        list.totalExpense("Landmark");
        assertEquals(capturedOutputStream.toString().trim(), findExpectedOutput2);
    }

    @Test

    public void testListTags() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        // Testcases with tags
        list.addTravelActivity(accommodationNew1);
        list.addTravelActivity(foodNew1);
        list.addTravelActivity(landmarkNew1);
        list.addTravelActivity(travelActivityNew1);

        // Testcases without tags
        list.addTravelActivity(new Accommodation("Airbnb",
                LocalDate.parse("2012-12-12"), "2hours", "", ""));
        list.addTravelActivity(new Food("Takoyaki",
                LocalDate.parse("2012-12-12"), "2hours", "", ""));
        list.addTravelActivity(new Landmark("Pyramid",
                LocalDate.parse("2012-12-12"), "2hours", "", ""));
        list.addTravelActivity(new TravelActivity("Go home",
                LocalDate.parse("2012-12-12"), "2hours", "", ""));

        list.listTags();
        String expectedOutput = "1. campus stay" + System.lineSeparator()
                + "2. concert" + System.lineSeparator()
                + "3. historic site" + System.lineSeparator()
                + "4. spicy" + System.lineSeparator();
        assertEquals(capturedOutputStream.toString(), expectedOutput);
    }


    @Test
    public void testIsNumeric() {
        assertTrue(CheckParameters.isNumeric("123"));
        assertFalse(CheckParameters.isNumeric("abc"));
    }

    @Test
    public void testGetList() throws OmniException {
        TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
        String expectedOutput1 = "[ ] 1. Accommodation: nus rvrc :12 Dec 2025 :5 years (campus stay)" +
                System.lineSeparator() +
                "[ ] 2. Accommodation: nus pgpr :12 Oct 2025 :5 years (campus stay)" + System.lineSeparator() +
                "[ ] 3. Accommodation: nus utr :12 Sep 2025 :5 years (campus stay)" + System.lineSeparator() +
                "[ ] 4. Landmark: berlin wall :15 Dec 2027 :5 hours (historic site)" + System.lineSeparator() +
                "[ ] 5. Landmark: utown :14 Aug 2028 :10 hours (recreational centre)" + System.lineSeparator() +
                "[ ] 6. Landmark: supper stretch :18 Aug 2027 :2 hours (tourist hotspot)" + System.lineSeparator() +
                "[ ] 7. Food: utown mala :19 Jun 2028 :2 hours (spicy)" + System.lineSeparator() +
                "[ ] 8. Food: pgpr mala :7 Jul 2026 :1 hours (spicy)" + System.lineSeparator() +
                "[ ] 9. Food: pgpr waffle :9 Mar 2026 :0.5 hours (non-spicy)" + System.lineSeparator() +
                "[ ] 10. General: esplanade :19 Mar 2026 :3 hours (concert)" + System.lineSeparator() +
                "[ ] 11. General: merlion :7 Apr 2026 :2 hours (sightseeing)" + System.lineSeparator() +
                "[ ] 12. General: chinatown :21 Feb 2025 :5 hours (sightseeing)";
        travelActivityListNew.listTravelActivities();
        assertEquals(capturedOutputStream.toString().trim(), expectedOutput1);
    }

    @Test
    public void testActivityCommand() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        String expectedOutput4 = "____________________________________________________________" +
                System.lineSeparator() +
                "I added a new accommodation" + System.lineSeparator() +
                "Accommodation: description :4 Oct 2024 :2 days (test)" + System.lineSeparator() +
                "____________________________________________________________";
        Parser.activityCommand("accommodation description /date 2024-10-04 /duration 2 days /tag test", list);
        assertEquals(capturedOutputStream.toString().trim(), expectedOutput4);
    }


    @Test
    public void testTagCommand() throws OmniException {
        TravelActivityList list = initialiseTestTravelActivityList();
        String expectedOutput5 = "I have tagged this task:" + System.lineSeparator() +
                "Accommodation: nus rvrc :12 Dec 2025 :5 years (test)";
        Parser.tagCommand("tag 1 test", list);
        assertEquals(capturedOutputStream.toString().trim(), expectedOutput5);
    }

    @Test
    public void testRemoveTagCommand() throws OmniException {
        TravelActivityList list = initialiseTestTravelActivityList();
        Parser.tagCommand("tag 1 test", list);
        String[] input = {"untag", "1"};
        String expectedOutput6 = "I have tagged this task:" + System.lineSeparator() +
                "Accommodation: nus rvrc :12 Dec 2025 :5 years (test)" + System.lineSeparator() +
                "Tag removed from the task:" +
                System.lineSeparator() + "Accommodation: nus rvrc :12 Dec 2025 :5 years";
        Parser.removeTagCommand(input, list);
        assertEquals(capturedOutputStream.toString().trim(), expectedOutput6);
    }

    @Test
    public void testUpdateCommand() throws OmniException {
        TravelActivityList list = initialiseTestTravelActivityList();
        list.addTravelActivity(accommodationNew1);
        // Test with valid input

        Parser.updateCommand("update 1 /date 2025-04-04 /duration 2 days /tag test", list);
        String expectedOutput7 = "I have updated this task" + System.lineSeparator() +
                "from: Accommodation: nus rvrc :12 Dec 2025 :5 years (campus stay) (campus stay)" +
                System.lineSeparator() +
                "to: Accommodation: nus rvrc :4 Apr 2025 :2 days (test) (test)";
    }

    @Test
    public void testFindTagCommand() throws OmniException {
        TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
        String expectedOutput2 = "Here are what you are looking for:" + System.lineSeparator() +
                "[ ] 1. Landmark: supper stretch :18 Aug 2027 :2 hours (tourist hotspot)";
        Parser.findTagCommand("findtag tourist", travelActivityListNew);
        assertEquals(capturedOutputStream.toString().trim(), expectedOutput2);
    }

    @Test
    public void testFindTypeCommand() throws OmniException {
        TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
        String expectedOutput3 = "Here are what you are looking for:" + System.lineSeparator() +
                "[ ] 1. General: esplanade :19 Mar 2026 :3 hours (concert)" + System.lineSeparator() +
                "[ ] 2. General: merlion :7 Apr 2026 :2 hours (sightseeing)" + System.lineSeparator() +
                "[ ] 3. General: chinatown :21 Feb 2025 :5 hours (sightseeing)";
        Parser.findTypeCommand("findtype General", travelActivityListNew);
        assertEquals(capturedOutputStream.toString().trim(), expectedOutput3);
    }
    @Test
    public void testExpenseCommand() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(accommodationNew1);
        String input = "expense 1 $50";
        String ex;
        Parser.expenseCommand(input, list);
    }

    @Test
    public void testRemoveExpenseCommand() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(accommodationNew1);
        Parser.expenseCommand("expense 1 $50", list);
        String[] input = {"removeExpense", "1"};
        Parser.removeExpenseCommand(input, list);
    }
    @Test
    public void testFindCommand() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        // Test with valid input
        Parser.findCommand(Arrays.toString(new String[]{"find", "test"}), list);
    }
    @Test
    public void testTotalExpenseCommand() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        // Test with valid input
        Parser.totalExpenseCommand("totalexpense", list);
    }




    @Test
    public void testCurrencyExchangeCommand() throws OmniException {
        Parser.currencyExchangeCommand("change 100 /from USD /to EUR");
    }



    @Test
    public void testAddExceptions() throws OmniException {
        CheckParameters.addExceptions(new String[]{"description", "/date", "2024-04-08", "/duration", "2 days"},
                "add", "add description /date 2024-04-08 /duration 2 days");
    }

    @Test
    public void testContainsWords() throws OmniException {
        CheckParameters.containsWords("2 days");
    }

    @Test
    public void testIsValidExpense() throws OmniException {
        assertTrue(CheckParameters.isValidExpense("50"));
    }

    @Test
    public void testCheckCurrencyParameters() throws OmniException {
        CheckParameters.checkCurrencyParameters(new String[]{"change", "100", "/from", "USD", "/to", "EUR"},
                "change 100 /from USD /to EUR");
    }

    @Test
    public void testHandleException() {
        CheckParameters.handleException(new OmniException("Test OmniException"));
    }

    @Test
    public void testAsciiCheck() throws OmniException {
        CheckParameters.asciiCheck("Valid input");
    }
}
