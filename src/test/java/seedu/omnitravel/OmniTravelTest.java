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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

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
    public void deleteTestWhenInputIsGreaterThanNumberOfActivityInList() throws OmniException {
        TravelActivityList travelActivityList = new TravelActivityList();
        travelActivityList.addTravelActivity(new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", ""));
        travelActivityList.addTravelActivity(new TravelActivity("visit home",
                LocalDate.parse("2019-12-14"), "5hours", "Sightseeing", "$50"));
        assertThrows(OmniException.class, () -> travelActivityList.removeTravelActivity(3));
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
        travelActivityList.removeTravelActivity(1);
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
        travelActivityList.removeTravelActivity(1);
        assertEquals("cant be found", travelActivityList.getDescription("visit museum"));
        //check number of activities
        assertEquals(2, travelActivityList.getNoOfTravelActivities());
    }

    @Test
    public void checkWhenTravelActivityIsAlreadyCheckedTest() throws OmniException {
        TravelActivityList travelActivityList = new TravelActivityList();
        TravelActivity travelActivity1 = new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "$50");
        travelActivityList.addTravelActivity(travelActivity1);
        travelActivityList.checkTravelActivity(1);
        travelActivityList.checkTravelActivity(1);
        String result = "I have checked this activity:" + System.lineSeparator() +
                "[X] 1. General: visit museum :12 May 2019 :2hours (Sightseeing) ($50)" + System.lineSeparator() +
                "This activity is already done!" + System.lineSeparator();
        assertEquals(capturedOutputStream.toString(), result);
    }

    @Test
    public void checkTest() throws OmniException {
        //add the first plan
        TravelActivityList travelActivityList = new TravelActivityList();
        assertThrows(OmniException.class, () -> travelActivityList.checkTravelActivity(1));
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
        assertThrows(OmniException.class, () -> travelActivityList.uncheckTravelActivity(1));
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
    public void findWithoutExclusionWhenListIsEmptyTest () {
        TravelActivityList travelActivityListNew = new TravelActivityList();
        String result = "Sorry I could not find what you are looking for." + System.lineSeparator();
        travelActivityListNew.searchKeyword("anything");
        assertEquals(capturedOutputStream.toString(), result);
    }

    @Test
    public void findWithExclusionWhenListIsEmptyTest () {
        TravelActivityList travelActivityListNew = new TravelActivityList();
        String result = "Sorry I could not find what you are looking for." + System.lineSeparator();
        travelActivityListNew.searchKeyword("anything", "nothing");
        assertEquals(capturedOutputStream.toString(), result);
    }

    @Test
    //basic test for searchKeyword function
    public void findWithoutExclusionTest () {
        TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
        String findExpectedOutput = "Here are what you are looking for:" + System.lineSeparator() +
                "[ ] 1. Food: utown mala :19 Jun 2028 :2 hours (spicy)" + System.lineSeparator() +
                "[ ] 2. Food: pgpr mala :7 Jul 2026 :1 hours (spicy)"  + System.lineSeparator();
        travelActivityListNew.searchKeyword("mala");
        assertEquals(capturedOutputStream.toString(), findExpectedOutput);
    }

    @Test
    public void findWithExclusionTest () {
        TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
        String findExpectedOutput = "Here are what you are looking for:" + System.lineSeparator() +
                "[ ] 1. Food: pgpr mala :7 Jul 2026 :1 hours (spicy)"  + System.lineSeparator();
        travelActivityListNew.searchKeyword("mala", "utown");
        assertEquals(capturedOutputStream.toString(), findExpectedOutput);
    }

    @Test
    public void findTagWithoutExclusionAndTagIsNotInListTest () {
        TravelActivityList travelActivityListNew = new TravelActivityList();
        String result = "Sorry I could not find what you are looking for." + System.lineSeparator();
        travelActivityListNew.findTag("sightseeing");
        assertEquals(capturedOutputStream.toString(), result);
    }

    @Test
    public void findTagWithExclusionAndTagIsNotInListTest () {
        TravelActivityList travelActivityListNew = new TravelActivityList();
        String result = "Sorry I could not find what you are looking for." + System.lineSeparator();
        travelActivityListNew.findTag("sightseeing", "holiday");
        assertEquals(capturedOutputStream.toString(), result);
    }

    @Test
    //basic test for searchKeyword function
    public void findTagWithoutExclusionTest () {
        TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
        String findExpectedOutput2 = "Here are what you are looking for:" + System.lineSeparator() +
                "[ ] 1. General: merlion :7 Apr 2026 :2 hours (sightseeing)" + System.lineSeparator() +
                "[ ] 2. General: chinatown :21 Feb 2025 :5 hours (sightseeing)"  + System.lineSeparator();
        travelActivityListNew.findTag("sightseeing");
        assertEquals(capturedOutputStream.toString(), findExpectedOutput2);
    }
    @Test
    //basic test for searchKeyword function
    public void findTagWithExclusionTest () {
        TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
        String findExpectedOutput2 = "Here are what you are looking for:" + System.lineSeparator() +
                "[ ] 1. General: chinatown :21 Feb 2025 :5 hours (sightseeing)"  + System.lineSeparator();
        travelActivityListNew.findTag("sightseeing", "merlion");
        assertEquals(capturedOutputStream.toString(), findExpectedOutput2);
    }

    @Test
    public void findTypeWithoutExclusionTestAndActivityIsNotFound () {
        TravelActivityList travelActivityListNew = new TravelActivityList();
        String result = "Sorry, I could not find what you are looking for." + System.lineSeparator();
        travelActivityListNew.findType("accommodation");
        assertEquals(capturedOutputStream.toString(), result);
    }

    @Test
    public void findTypeWithExclusionTestAndActivityIsNotFound () {
        TravelActivityList travelActivityListNew = new TravelActivityList();
        String result = "Sorry, I could not find what you are looking for." + System.lineSeparator();
        travelActivityListNew.findType("accommodation", "waffle");
        assertEquals(capturedOutputStream.toString(), result);
    }

    @Test
    public void findTypeWithExclusionTest () {
        try {
            TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
            String findExpectedOutput3 = "Here are what you are looking for:" + System.lineSeparator() +
                    "[ ] 1. Accommodation: nus rvrc :12 Dec 2025 :5 years (campus stay)" + System.lineSeparator() +
                    "[ ] 2. Accommodation: nus pgpr :12 Oct 2025 :5 years (campus stay)" + System.lineSeparator() +
                    "[ ] 3. Accommodation: nus utr :12 Sep 2025 :5 years (campus stay)" + System.lineSeparator();
            Parser.findTypeCommand("findtype accommodation", travelActivityListNew);
            assertEquals(capturedOutputStream.toString(), findExpectedOutput3);
        } catch (OmniException exception) {
            Ui.printException(exception);
        }
    }

    @Test
    public void findTypeWithoutExclusionTest () {
        try {
            TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
            String findExpectedOutput3 = "Here are what you are looking for:" + System.lineSeparator() +
                    "[ ] 1. Accommodation: nus rvrc :12 Dec 2025 :5 years (campus stay)" + System.lineSeparator() +
                    "[ ] 2. Accommodation: nus pgpr :12 Oct 2025 :5 years (campus stay)" + System.lineSeparator();
            Parser.findTypeCommand("findtype accommodation /exclude utr", travelActivityListNew);
            assertEquals(capturedOutputStream.toString(), findExpectedOutput3);
        } catch (OmniException exception) {
            Ui.printException(exception);
        }
    }

    @Test
    public void testTagActivityWhenTaskNumberIsGreaterThanListSize() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        assertThrows(OmniException.class, () -> list.tagActivity(1, "activity 1"));
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
        assertThrows(OmniException.class, () -> list.removeTag(3));
    }

    @Test
    public void testUpdateActivityWhenActivityIndexIsGreaterThanListSize() throws OmniException{
        TravelActivityList travelActivityList = new TravelActivityList();
        assertThrows(OmniException.class, () -> travelActivityList.updateTravelActivity(1,
                LocalDate.parse("2020-12-10"), "3hours", "misc"));
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
    public void testExpenseActivityAndTaskNumberIsGreaterThanListSize() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        assertThrows(OmniException.class, () -> list.expenseActivity(1, "$10"));
    }

    @Test
    public void testExpenseActivityAndExpenseDontStartWithDollarSign() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "$30"));
        assertThrows(OmniException.class, () -> list.expenseActivity(1, "10"));
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
    public void testRemoveExpenseAndTaskNumberIsGreaterThanListSize() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        assertThrows(OmniException.class, () -> list.removeExpense(1));
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
    public void testTotalExpenseAndTypeIsInvalid() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "$20"));
        assertThrows(OmniException.class, () -> list.totalExpense("NA"));
    }

    @Test
    public void testTotalExpenseAll() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new TravelActivity("visit museum",
                LocalDate.parse("2019-05-12"),"2hours", "Sightseeing", "$20"));
        list.totalExpense("all");

        String findExpectedOutput = "The total expense for all travel activities is: $20.0";
        assertEquals(capturedOutputStream.toString().trim(), findExpectedOutput);
    }

    @Test
    public void testTotalExpenseAccommodation() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new Accommodation("RVRC", LocalDate.parse("2022-07-12"), "3hours",
                "hostel", "$70"));
        String findExpectedOutput1 = "The total expense for Accommodation travel activities is: $70.0";
        list.totalExpense("Accommodation");
        assertEquals(capturedOutputStream.toString().trim(), findExpectedOutput1);
    }

    @Test
    public void testTotalExpenseFood() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new Food("UTOWN Flavours", LocalDate.parse("2028-05-12"), "1 hours",
                "lunch", "$10"));
        String findExpectedOutput2 = "The total expense for Food travel activities is: $10.0";
        list.totalExpense("Food");
        assertEquals(capturedOutputStream.toString().trim(), findExpectedOutput2);
    }

    @Test
    public void testTotalExpenseLandmark() throws OmniException{
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
    public void testGetListWhenActivityIsNull() throws OmniException {
        TravelActivityList travelActivityListNew = new TravelActivityList();
        travelActivityListNew.addTravelActivity(null);
        travelActivityListNew.listTravelActivities(false, false, LocalDate.now());
        assertEquals(capturedOutputStream.toString().trim(), "There are no activities to list");
    }

    @Test
    public void testGetListWhenEmpty() throws OmniException {
        TravelActivityList travelActivityListNew = new TravelActivityList();
        travelActivityListNew.listTravelActivities(false, false, LocalDate.now());
        assertEquals(capturedOutputStream.toString().trim(), "There are no activities to list");
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
        travelActivityListNew.listTravelActivities(false, false, LocalDate.now());
        assertEquals(capturedOutputStream.toString().trim(), expectedOutput1);
    }

    @Test
    public void testActivityCommandAccommodation() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        String expectedOutput4 = "_______________________________________________________________________________" +
                System.lineSeparator() +
                "I added a new accommodation" + System.lineSeparator() +
                "Accommodation: description :4 Oct 2024 :2 days (test)" + System.lineSeparator() +
                "_______________________________________________________________________________";
        Parser.activityCommand("accommodation description /date 2024-10-04 /duration 2 days /tag test", list);
        assertEquals(capturedOutputStream.toString().trim(), expectedOutput4);
    }

    @Test
    public void testActivityCommandFood() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        String expectedOutput4 = "_______________________________________________________________________________" +
                System.lineSeparator() +
                "I added a new food activity" + System.lineSeparator() +
                "Food: description :4 Oct 2024 :2 days (test)" + System.lineSeparator() +
                "_______________________________________________________________________________";
        Parser.activityCommand("food description /date 2024-10-04 /duration 2 days /tag test", list);
        assertEquals(capturedOutputStream.toString().trim(), expectedOutput4);
    }

    @Test
    public void testActivityCommandLandmark() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        String expectedOutput4 = "_______________________________________________________________________________" +
                System.lineSeparator() +
                "I added a new landmark" + System.lineSeparator() +
                "Landmark: description :4 Oct 2024 :2 days (test)" + System.lineSeparator() +
                "_______________________________________________________________________________";
        Parser.activityCommand("landmark description /date 2024-10-04 /duration 2 days /tag test", list);
        assertEquals(capturedOutputStream.toString().trim(), expectedOutput4);
    }

    @Test
    public void testActivityCommandError() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        String expectedOutput4 = "____________________________________________________________" +
                System.lineSeparator() +
                "Unknown activity type" +
                "____________________________________________________________";
        assertThrows(OmniException.class, () -> Parser.activityCommand(
                "Test description /date 2024-10-04 /duration 2 days /tag test", list));
    }

    @Test
    public void testActivityCommandButDateIsBeforeNow() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        assertThrows(OmniException.class, () -> Parser.activityCommand(
                "accommodation description /date 2000-10-04 /duration 2 days /tag test", list));
    }

    @Test
    public void testTagCommand() throws OmniException {
        TravelActivityList list = initialiseTestTravelActivityList();
        assertThrows(OmniException.class, () -> Parser.tagCommand("tag 1", list));
        assertThrows(OmniException.class, () -> Parser.tagCommand("tag", list));
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
        String[] input1 = {"untag", "1", "5"};
        String expectedOutput6 = "I have tagged this task:" + System.lineSeparator() +
                "Accommodation: nus rvrc :12 Dec 2025 :5 years (test)" + System.lineSeparator() +
                "Tag removed from the task:" +
                System.lineSeparator() + "Accommodation: nus rvrc :12 Dec 2025 :5 years";
        assertThrows(OmniException.class, () -> Parser.removeTagCommand(input1, list));
        Parser.removeTagCommand(input, list);
        assertEquals(capturedOutputStream.toString().trim(), expectedOutput6);
    }

    @Test
    public void testUpdateCommand() throws OmniException {
        TravelActivityList list = initialiseTestTravelActivityList();
        list.addTravelActivity(accommodationNew1);
        assertThrows(OmniException.class, () -> Parser.updateCommand(
                "update 1 /date 2000-04-04 /duration 2 days /tag test", list));
        Parser.updateCommand("update 1 /date 2025-04-04 /duration 2 days /tag test", list);
        String expectedOutput7 = "I have updated this task\n" +
                "from: Accommodation: nus rvrc :12 Dec 2025 :5 years (campus stay)\n" +
                "to: Accommodation: nus rvrc :4 Apr 2025 :2 days (test)"  + System.lineSeparator();
        assertEquals(capturedOutputStream.toString(), expectedOutput7);
    }

    @Test
    public void testFindTagCommandWithoutExclusion() throws OmniException {
        try {
            TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
            assertThrows(OmniException.class, () -> Parser.findTagCommand(
                    "findtype", travelActivityListNew));
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
    public void testFindTagCommandWithExclusion() throws OmniException {
        try {
            TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
            String findExpectedOutput2 = "Here are what you are looking for:" + System.lineSeparator() +
                    "[ ] 1. General: chinatown :21 Feb 2025 :5 hours (sightseeing)" + System.lineSeparator();
            Parser.findTagCommand("findtag sightseeing /exclude merlion", travelActivityListNew);
            assertEquals(capturedOutputStream.toString(), findExpectedOutput2);
        } catch (OmniException exception) {
            Ui.printException(exception);
        }
    }

    @Test
    public void testFindTypeCommandWithoutExclusion() throws OmniException {
        TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
        assertThrows(OmniException.class, () -> Parser.findTypeCommand(
                "findtype", travelActivityListNew));
        String expectedOutput3 = "Here are what you are looking for:" + System.lineSeparator() +
                "[ ] 1. General: esplanade :19 Mar 2026 :3 hours (concert)" + System.lineSeparator() +
                "[ ] 2. General: merlion :7 Apr 2026 :2 hours (sightseeing)" + System.lineSeparator() +
                "[ ] 3. General: chinatown :21 Feb 2025 :5 hours (sightseeing)";
        Parser.findTypeCommand("findtype TravelActivity", travelActivityListNew);
        assertEquals(capturedOutputStream.toString().trim(), expectedOutput3);
    }

    @Test
    public void testFindTypeCommandWithExclusion() throws OmniException {
        TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
        String expectedOutput3 = "Here are what you are looking for:" + System.lineSeparator() +
                "[ ] 1. General: esplanade :19 Mar 2026 :3 hours (concert)" + System.lineSeparator() +
                "[ ] 2. General: chinatown :21 Feb 2025 :5 hours (sightseeing)" + System.lineSeparator();
        Parser.findTypeCommand("findtype TravelActivity /exclude merlion", travelActivityListNew);
        assertEquals(capturedOutputStream.toString(), expectedOutput3);
    }
    @Test
    public void testExpenseCommand() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(accommodationNew1);
        assertThrows(OmniException.class, () -> Parser.expenseCommand(
                "expense 1", list));
        assertThrows(OmniException.class, () -> Parser.expenseCommand(
                "expense", list));
        String expectedOutput4 = "I have added expense for this task:" + System.lineSeparator() +
                "Accommodation: nus rvrc :12 Dec 2025 :5 years (campus stay) ($50)" + System.lineSeparator();
        Parser.expenseCommand("expense 1 $50", list);
        assertEquals(capturedOutputStream.toString(), expectedOutput4);
    }

    @Test
    public void testRemoveExpenseCommand() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(accommodationNew1);
        String[] command1 = {"removeexpense","2", "5"};
        assertThrows(OmniException.class, () -> Parser.removeExpenseCommand(
                command1, list));
        Parser.expenseCommand("expense 1 $50", list);
        String[] input = {"removeExpense", "1"};
        String expectedOutput5 = "I have added expense for this task:" + System.lineSeparator() +
                "Accommodation: nus rvrc :12 Dec 2025 :5 years (campus stay) ($50)" + System.lineSeparator() +
                "Expense removed from the task:" + System.lineSeparator() +
                "Accommodation: nus rvrc :12 Dec 2025 :5 years (campus stay)" + System.lineSeparator();
        Parser.removeExpenseCommand(input, list);
        assertEquals(capturedOutputStream.toString(), expectedOutput5);
    }

    @Test
    public void testFindCommandWithoutExclusion() throws OmniException {
        try {
            TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
            assertThrows(OmniException.class, () -> Parser.findCommand(
                    "find", travelActivityListNew));
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
    public void testFindCommandWithExclusion() throws OmniException {
        try {
            TravelActivityList travelActivityListNew = initialiseTestTravelActivityList();
            String findExpectedOutput = "Here are what you are looking for:" + System.lineSeparator() +
                    "[ ] 1. Food: pgpr mala :7 Jul 2026 :1 hours (spicy)"  + System.lineSeparator();
            Parser.findCommand("find mala /exclude utown", travelActivityListNew);
            assertEquals(capturedOutputStream.toString(), findExpectedOutput);
        } catch (OmniException exception) {
            Ui.printException(exception);
        }
    }
    @Test
    public void testTotalExpenseCommand() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        assertThrows(OmniException.class, () -> Parser.totalExpenseCommand(
                "", list));
        assertThrows(OmniException.class, () -> Parser.totalExpenseCommand(
                "expenditure", list));
        String result = "The total expense for all travel activities is: $0.0" + System.lineSeparator();
        Parser.totalExpenseCommand("totalexpense", list);
        assertEquals(capturedOutputStream.toString(), result);
        TravelActivityList newList = initialiseTestTravelActivityList();
        Parser.totalExpenseCommand("totalexpense /type general", list);
        String result1 = "The total expense for all travel activities is: $0.0" + System.lineSeparator() +
                "The total expense for General travel activities is: $0.0" + System.lineSeparator();
        assertEquals(capturedOutputStream.toString(), result1);
        String result2 = "The total expense for all travel activities is: $0.0" + System.lineSeparator() +
                "The total expense for General travel activities is: $0.0" + System.lineSeparator() +
                "The total expense for accommodation travel activities is: $0.0" + System.lineSeparator();
        Parser.totalExpenseCommand("totalexpense /type accommodation", list);
        assertEquals(capturedOutputStream.toString(), result2);
    }

    @Test
    public void testCurrencyExchangeCommand() throws OmniException {
        Parser.currencyExchangeCommand("change 100 /from USD /to EUR");
    }



    @Test
    public void testAddExceptions() throws OmniException {
        CheckParameters.addExceptions(new String[]{"description", "/date", "2024-04-08", "/duration", "2 days"},
                "add", "add description /date 2024-04-08 /duration 2 days");
        assertThrows(OmniException.class, () -> CheckParameters.addExceptions(new String[]{
            "   ", "2024-04-08", "2 days"}, "add",
            "add description /date 2024-04-08 /duration 2 days"));
        assertThrows(OmniException.class, () -> CheckParameters.addExceptions(new String[]{
            "description", " ", "2 days"}, "add",
            "add description /date 2024-04-08 /duration 2 days"));
        assertThrows(OmniException.class, () -> CheckParameters.addExceptions(new String[]{
            "description", "2024-04-08"," "}, "add",
            "add description /date 2024-04-08 /duration 2 days"));
        assertThrows(OmniException.class, () -> CheckParameters.addExceptions(new String[]{
            "description", "2024-04-08", "2 days", " "}, "add",
            "add description /date 2024-04-08 /duration 2 days"));
        assertThrows(OmniException.class, () -> CheckParameters.addExceptions(new String[]{
            "description"},
            "add","add description /date 2024-04-08 /duration 2 days"));
    }

    @Test
    public void testContainsWords() throws OmniException {
        CheckParameters.containsWords("2 days");
        assertThrows(OmniException.class, () -> {
            CheckParameters.containsWords("£$%");
        });
    }

    @Test
    public void testIsValidExpense() throws OmniException {
        assertTrue(CheckParameters.isValidExpense("50"));
        assertThrows(OmniException.class, () -> {
            CheckParameters.isValidExpense("-0");
        });
        assertTrue(!CheckParameters.isValidExpense("number"));
    }

    @Test
    public void testCheckCurrencyParameters() throws OmniException {
        CheckParameters.checkCurrencyParameters(new String[]{"change", "100", "/from", "USD", "/to", "EUR"},
            "change 100 /from USD /to EUR");
        assertThrows(OmniException.class, () -> CheckParameters.checkCurrencyParameters(new String[]{
            "change", "hundred", "USD", "EUR"}, "change 100 /from USD /to EUR"));
        assertThrows(OmniException.class, () -> CheckParameters.checkCurrencyParameters(new String[]{
            "change", "hundred", "usd", "usd"}, "change 100 /from USD /to EUR"));
        assertThrows(OmniException.class, () -> CheckParameters.checkCurrencyParameters(new String[]{
            "change", "hundred", "USD"}, "change 100 /from USD /to EUR"));
    }

    @Test
    public void testHandleException() {
        OmniException omniException = new OmniException("Test OmniException");
        NoSuchElementException noSuchElementException = new NoSuchElementException("Test NoSuchElementException");
        NumberFormatException numberFormatException = new NumberFormatException("Test NumberFormatException");
        DateTimeException dateTimeException = new DateTimeException("Test DateTimeException");
        IOException ioException = new IOException("Test IOException");
        InterruptedException interruptedException = new InterruptedException("Test InterruptedException");

        assertDoesNotThrow(() -> {
            CheckParameters.handleException(omniException);
        });
        assertDoesNotThrow(() -> {
            CheckParameters.handleException(noSuchElementException);
        });
        assertDoesNotThrow(() -> {
            CheckParameters.handleException(numberFormatException);
        });
        assertDoesNotThrow(() -> {
            CheckParameters.handleException(dateTimeException);
        });
        assertDoesNotThrow(() -> {
            CheckParameters.handleException(ioException);
        });
        assertDoesNotThrow(() -> {
            CheckParameters.handleException(interruptedException);
        });
    }

    @Test
    public void testAsciiCheck() throws OmniException {
        CheckParameters.asciiCheck("Valid input");
        assertThrows(OmniException.class, () -> {
            CheckParameters.asciiCheck(" ©, ®, €, £, µ, ¥,");
        });
    }

    @Test
    public void testGetListMethod() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(accommodationNew1);
        Parser.getList("list", list);
    }

    @Test
    public void testGetListMethodButDateIsInWrongFormat() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        assertThrows(DateTimeParseException.class, () -> list.addTravelActivity(new Accommodation("Airbnb",
                LocalDate.parse("20-1200-12"), "2hours", "", "")));
        assertThrows(OmniException.class, () -> Parser.getList("list /date 20-1200-12", list));
    }

    @Test
    public void testGetListMethodButDateIsBeforeNow() throws OmniException {
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(new Accommodation("Airbnb",
                        LocalDate.parse("2020-12-12"), "2hours", "", ""));
        assertThrows(OmniException.class, () -> Parser.getList("list /date 1920-12-12", list));
    }



    @Test
    public void testGetListMethodWithSorting() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(accommodationNew1);
        list.addTravelActivity(foodNew2);
        Parser.getList("list /sort", list);
    }

    @Test
    public void testGetListMethodWithDate() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(accommodationNew1);
        list.addTravelActivity(foodNew2);
        Parser.getList("list /date 2025-12-12 ", list);
    }

    @Test
    public void testAddCommandMethod() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        assertThrows(OmniException.class, () -> Parser.addCommand(
                "add home /date 2000-12-12 /duration 2 days", list));
        // Test case without tags
        Parser.addCommand("add home /date 2026-12-12 /duration 2 days", list);
        // Test case with tags
        Parser.addCommand("add home /date 2026-12-12 /duration 2 days /tag first", list);
    }

    @Test
    public void testDeleteCommandMethod() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        String[] command1 = {"delete", "1"};
        String[] command2 = {"delete", "pgpr mala"};
        String[] command3 = {"delete"};
        assertThrows(OmniException.class, () -> Parser.deleteCommand(command1, list, "delete 1"));
        list.addTravelActivity(accommodationNew1);
        list.addTravelActivity(foodNew2);
        assertThrows(OmniException.class, () -> Parser.deleteCommand(command3, list, "delete 1"));
        Parser.deleteCommand(command1, list, "delete 1");
        Parser.deleteCommand(command2, list, "delete pgpr mala");
    }

    @Test
    public void testCheckCommandMethod() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        String[] command1 = {"check"};
        assertThrows(OmniException.class, () -> Parser.checkCommand(command1, list, "check 1"));
        list.addTravelActivity(accommodationNew1);
        list.addTravelActivity(foodNew2);
        String[] command = {"check", "1"};
        Parser.checkCommand(command, list, "check 1");
    }

    @Test
    public void testUncheckCommandMethod() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        String[] command1 = {"check"};
        assertThrows(OmniException.class, () -> Parser.uncheckCommand(command1, list, "uncheck 1"));
        list.addTravelActivity(accommodationNew1);
        list.addTravelActivity(foodNew2);
        String[] command = {"uncheck", "1"};
        Parser.uncheckCommand(command, list, "uncheck 1");
    }

    @Test
    public void testListTagsCommandMethod() throws OmniException{
        TravelActivityList list = new TravelActivityList();
        list.addTravelActivity(accommodationNew1);
        list.addTravelActivity(foodNew2);
        String[] command = {"listtags"};
        String[] command1 = {"listtags", "list", "tag"};
        assertThrows(OmniException.class, () -> Parser.listTagsCommand(command1, list));
        Parser.listTagsCommand(command, list);
    }

    @Test
    public void testPrintDateTimeExceptionError() {
        String result = "Invalid date, please input the date in the following order: YYYY-MM-DD"
                + System.lineSeparator();
        Ui.printDateTimeExceptionError();
        assertEquals(result, capturedOutputStream.toString());
    }

    @Test
    public void testHelpCommand() {
        String result = "_______________________________________________________________________________" +
                System.lineSeparator() +
                "These are the available commands!" + System.lineSeparator() + System.lineSeparator() +
                "1. list <date> <sort>: List out the current list for given date sorted\n" +
                "2. help: Get all commands for the chatbot\n" +
                "3. bye: Exit the chatbot\n" +
                "4. add <travel activity> <date> <duration> <tag>\n" +
                "5. accommodation <travel activity> <date> <duration> <tag>\n" +
                "6. food <travel activity> <date> <duration> <tag>\n" +
                "7. landmark <travel activity> <date> <duration> <tag>\n" +
                "8. delete <activity number>\n" +
                "9. find <keyword> <exclusion>\n" +
                "10. check <activity number>\n" +
                "11. uncheck <activity number>\n" +
                "12. tag <activity number> <tag name>\n" +
                "13. untag <activity number>\n" +
                "14. update <update> <date> <duration> <tag>\n" +
                "15. findtag <tag name> <exclusion>\n" +
                "16. findtype <type> <exclusion>\n" +
                "17. listtags \n" +
                "18. expense <activity number> <expense amount>\n" +
                "19. removeexpense <activity number>\n" +
                "20. totalexpense <type>\n" +
                "21. change <amount> /from <current currency> /to <changed currency>\n" + System.lineSeparator() +
                "_______________________________________________________________________________";
        Ui.helpCommand();
        assertEquals(capturedOutputStream.toString().trim(), result);
    }

    @Test
    public void testPrintBye() {
        String result = "_______________________________________________________________________________" +
                System.lineSeparator() +
                "Thank you for using Omnitravel" + System.lineSeparator() +
                "We hope to see you again! Goodbye!" + System.lineSeparator() +
                "_______________________________________________________________________________";
        Ui.printBye();
        assertEquals(capturedOutputStream.toString().trim(), result);
    }

    @Test
    public void testPrintGreeting() {
        String result = "_______________________________________________________________________________" +
                System.lineSeparator() +
                " ____  _      _      _  _____  ____  ____  _     _____ _\n" +
                "/  _ \\/ \\__/|/ \\  /|/ \\/__ __\\/  __\\/  _ \\/ \\ |\\/  __// \\\n" +
                "| / \\|| |\\/||| |\\ ||| |  / \\  |  \\/|| / \\|| | //|  \\  | |\n" +
                "| \\_/|| |  ||| | \\||| |  | |  |    /| |-||| \\// |  /_ | |_/\\\n" +
                "\\____/\\_/  \\|\\_/  \\|\\_/  \\_/  \\_/\\_\\\\_/ \\|\\__/  \\____\\\\____/)" +
                System.lineSeparator() + "Hello" + System.lineSeparator() + "How may I assist you?" +
                System.lineSeparator() +
                "_______________________________________________________________________________";
        Ui.printGreeting();
        assertEquals(capturedOutputStream.toString().trim(), result);
    }

    @Test
    public void testUpdateException () {
        assertThrows(OmniException.class, () -> CheckParameters.updateExceptions(new String[]{
            "   ", "2024-05-20", "2 days", "test"}, "update 1 /date 2024-05-20 /duration 2 days"));
        assertThrows(OmniException.class, () -> CheckParameters.updateExceptions(new String[]{
            "1", " ", "2 days", "test"}, "update 1 /date 2024-05-20 /duration 2 days"));
        assertThrows(OmniException.class, () -> CheckParameters.updateExceptions(new String[]{
            "description", "2024-05-20"," ", "test"}, "update 1 /date 2024-05-20 /duration 2 days"));
        assertThrows(OmniException.class, () -> CheckParameters.updateExceptions(new String[]{
            "description", "2024-05-20", "2 days", " "}, "update 1 /date 2024-05-20 /duration 2 days"));
        assertThrows(OmniException.class, () -> CheckParameters.updateExceptions(new String[]{
            "1"}, "update 1 /date 2024-05-20 /duration 2 days"));
    }

    @Test
    public void testListException () {
        assertThrows(OmniException.class, () -> CheckParameters.listExceptions(new String[]{
            "test", "/date",}, new String[]{"test", "2024-05-20", "2 days", "test"},
                "testing"));
    }

}
