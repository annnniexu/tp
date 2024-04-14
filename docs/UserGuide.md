# User Guide
* [Introduction](#introduction)
* [Quick Start](#quick-start)
* [Features](#features-)
    * [Adding a general travel activity](#adding-a-general-travel-activity--add)
    * [Add an accommodation activity type into the list](#add-an-accommodation-activity-type-into-the-list--accommodation)
    * [Add a food activity type into the list](#add-a-food-activity-type-into-the-list--food)
    * [Add a landmark activity type into the list](#add-a-landmark-activity-type-into-the-list--landmark)
    * [Find activity from the list using activity description](#find-activity-from-the-list-using-activity-description--find)
    * [Deleting a travel activity](#deleting-a-travel-activity--delete)
    * [Listing all the travel activities](#listing-all-the-travel-activities--list)
    * [Check a travel activity](#check-a-travel-activity--check)
    * [Uncheck a travel activity](#uncheck-a-travel-activity--uncheck)
    * [Updating a travel activity](#updating-a-travel-activity--update)
    * [Adding a tag](#adding-a-tag-tag)
    * [Removing a tag](#removing-a-tag-untag)
    * [Find an activity from the list using activity tag](#find-activity-from-the-list-using-activity-tag-findtag)
    * [List tags from the list](#list-tags-from-the-list--listtags)
    * [Find activity from the list using activity type](#find-activity-from-the-list-using-activity-type--findtype)
    * [Adding an expense amount](#adding-an-expense-amount-expense)
    * [Removing an expense amount](#removing-an-expense-amount-removeexpense)
    * [Calculating total expense](#calculating-total-expense-amount-totalexpense)
    * [Converting a currency](#converting-a-currency--change)
* [Command summary](#command-summary)
## Introduction

OmniTravel is a software that **allows travellers to store their travel plans and expenses in a
list via a Command Line Interface**. 

## Quick Start

1. Ensure you have Java 11 installed in your Computer.

2. Download the latest omni.jar from [here](https://github.com/AY2324S2-CS2113-T12-4/tp/releases/tag/tpv2.1).

3. Copy the file to the folder you want to use as the home folder for your chatbot.

4. Open a command terminal, cd into the folder you put the jar file in

5. Use the java -jar omni.jar command to run the application.

6. A welcome message similar to the one below should appear in a few seconds.
```
No existing database found! Creating a new save file for you!
____________________________________________________________
 ____  _      _      _  _____  ____  ____  _     _____ _
/  _ \/ \__/|/ \  /|/ \/__ __\/  __\/  _ \/ \ |\/  __// \
| / \|| |\/||| |\ ||| |  / \  |  \/|| / \|| | //|  \  | |
| \_/|| |  ||| | \||| |  | |  |    /| |-||| \// |  /_ | |_/\
\____/\_/  \|\_/  \|\_/  \_/  \_/\_\\_/ \|\__/  \____\\____/)
Hello
How may I assist you?
____________________________________________________________
```
7. Type the `help` command in the command box and press enter to open the help window.

## Features 
> 📒 Notes about the command format :
> * The words that are in `UPPER_CASE` represents the parameters that the users are required to input
> e.g. `delete INDEX`, `INDEX` is a parameter which can be used as `delete 1`.
> * Commands such as `help` and `bye` do not require additional parameters. Hence, any extra parameters will be ignored. 
> e.g. `help 123` will just be intepreted as `help`.
> * Words that are in square brackets such as `[/tag TAG]` indicates that it is
> optional to include in the command.
> * Users should not use any `/` into their input descriptions unless using for input command format.


### Adding a general travel activity : `add`
Adds a general travel activity into the travel activity list.
* This is for activities that do not fall under the other activity types. EG. visiting a country.

Format: `add DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`

Examples of usage:
* `add Go to Japan /date 2025-03-14 /duration 7 hours`
* `add Go to Hong Kong /date 2025-08-25 /duration 6 hours /tag with family`

Expected outcome:
Without tag
```
____________________________________________________________
I added a new travel activity
Go to Japan  :14 Mar 2025 :7 hour
____________________________________________________________
```
With tag
```
____________________________________________________________
I added a new travel activity
Go to Hong Kong  :25 Aug 2025 :6 hours (with family)
____________________________________________________________
```

### Add an accommodation activity type into the list : `accommodation`
Adds an accommodation type travel activity into the travel activity list.

Format: `accommodation DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`

Examples of usage:
* `accommodation Four Seasons Hotel /date 2025-03-14 /duration 2 weeks`
* `accommodation Four Seasons Hotel /date 2025-08-25 /duration 2 weeks /tag first hotel`

Expected outcome:
Without tag
```
____________________________________________________________
I added a new accommodation
Accommodation: Four Seasons Hotel :14 Mar 2025 :2 weeks
____________________________________________________________
```
With tag
```
____________________________________________________________
I added a new accommodation
Accommodation: Four Seasons Hotel :25 Aug 2025 :2 weeks (first hotel)
____________________________________________________________
```

### Add a food activity type into the list : `food`
Adds a food type travel activity into the travel activity list

Format: `food DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`

Examples of usage:
* `food Mala Hotpot /date 2025-03-14 /duration 2 hours`
* `food Mala Hotpot /date 2025-03-14 /duration 2 hours /tag very spicy`

Expected outcome:
Without tag
```
____________________________________________________________
I added a new food activity
Food: Mala Hotpot :14 Mar 2025 :2 hours
____________________________________________________________
```
With tag
```
____________________________________________________________
I added a new food activity
Food: Mala Hotpot :14 Mar 2025 :2 hours (very spicy)
____________________________________________________________

```

### Add a landmark activity type into the list : `landmark`
Adds a landmark type travel activity into the travel activity list

Format: `landmark DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`

Examples of usage:
* `landmark Eiffel Tower /date 2025-03-14 /duration 2 hours`
* `landmark Eiffel Tower /date 2025-03-14 /duration 2 hours /tag go up tower`

Expected outcome:
Without tag
```
____________________________________________________________
I added a new landmark
Landmark: Eiffel Tower :14 Mar 2025 :2 hours
____________________________________________________________
```
With tag
```
____________________________________________________________
I added a new landmark
Landmark: Eiffel Tower :14 Mar 2025 :2 hours (go up tower)
____________________________________________________________
```

### Find activity from the list using activity description : `find`
Find activities based on their description. All activities with the given description will be listed out.

Format: `find DESCRIPTION [/exclude KEYWORD]`

* `DESCRIPTION` has to be a word, a phrase or a segment of the activity description to find the activity
* `KEYWORD` has to be a word, a phrase or a segment of the activity description to exclude the activity
* `/exclude KEYWORD` will exclude any activity with `KEYWORD` found in the **description** of the activity

Examples of usage (assuming saizeriya is in list):
* `find saizeriya`
* `find saizeriya /exclude pizza`

Expected outcome:
Without `/exclude`

```
____________________________________________________________
Here are what you are looking for:
[ ] 1. Food: saizeriya pizza :14 Mar 2025 :2 hours
[ ] 2. Food: saizeriya pasta :14 Mar 2025 :2 hours
____________________________________________________________
```

With `/exclude`:

```
____________________________________________________________
Here are what you are looking for:
[ ] 1. Food: saizeriya pasta :14 Mar 2025 :2 hours
____________________________________________________________
```

### Deleting a travel activity : `delete`
Deletes a travel activity from the travel activity list

Format: `delete ACTIVITY`
* `ACTIVITY` can be an index of an activity or a keyword found in the description of an activity in the list of travel 
* activities. If the user writes a number only the activity in that particular index on the list gets deleted. 
* If the user writes a keyword then every activity with that keyword in its description gets deleted.

Examples of usage: 
* `delete 1`
* `delete Eiffel`

Expected outcome:
When ACTIVITY is a number
```
____________________________________________________________
I have removed this activity:
1. Accommodation: Four Seasons Hotel :14 Mar 2025 :2 days
____________________________________________________________
```

When ACTIVITY is a keyword
```
____________________________________________________________
I have removed this activity:
1. Landmark: Eiffel Tower :14 Mar 2025 :2 hours (go up tower)
2. Landmark: Eiffel Tower Food Stand :14 Mar 2025 :1 hours (dinner)
3. Accommodation: Hotel beside Eiffel Tower :14 Mar 2025 :2 days (rest)
____________________________________________________________
```

### Listing all the travel activities : `list`
Shows all the travel activities for including their tags and expenses. 
Shows all activities for given date if date is provided.
Sorts by date if sort is enabled.

Format: `list [/date DATE /sort]`
* Using `list` with `/date DATE` will show all the activities with date `DATE`.
* Using `list` with `/sort` will show the list sorted by date.

Examples of usage: 
* `list`

Expected outcome:
Without date and sort
```
____________________________________________________________
Here are the travel activities for all dates:
[ ] 1. General: Go to Japan  :14 Mar 2025 :7 hour
[ ] 2. General: Go to Hong Kong  :25 Aug 2025 :6 hours (with family)
[ ] 3. Accommodation: Four Seasons Hotel :14 Mar 2025 :2 weeks
[ ] 4. Accommodation: Four Seasons Hotel :25 Aug 2025 :2 weeks (first hotel)
[ ] 5. Food: Mala Hotpot :14 Mar 2025 :2 hours
[ ] 6. Food: Mala Hotpot :14 Mar 2025 :2 hours (very spicy)
[ ] 7. Landmark: Eiffel Tower :14 Mar 2025 :2 hours
[ ] 8. Landmark: Eiffel Tower :14 Mar 2025 :2 hours (go up tower)
[ ] 9. Food: saizeriya :14 Mar 2025 :2 hours
____________________________________________________________
```
* `list /date 2025-08-25`
Expected outcome: With date
```
____________________________________________________________
Here are the travel activities for 2025-08-05:
[ ] 1. General: Go to Hong Kong  :25 Aug 2025 :6 hours (with family)
[ ] 4. Accommodation: Four Seasons Hotel :25 Aug 2025 :2 weeks (first hotel)
____________________________________________________________
```
* `list /sort`
```
____________________________________________________________
Here are the travel activities for all dates:
[ ] 1. General: Go to Japan  :14 Mar 2025 :7 hour
[ ] 2. Accommodation: Four Seasons Hotel :14 Mar 2025 :2 weeks
[ ] 3. Food: Mala Hotpot :14 Mar 2025 :2 hours
[ ] 4. Food: Mala Hotpot :14 Mar 2025 :2 hours (very spicy)
[ ] 5. Landmark: Eiffel Tower :14 Mar 2025 :2 hours
[ ] 6. Landmark: Eiffel Tower :14 Mar 2025 :2 hours (go up tower)
[ ] 7. Food: saizeriya :14 Mar 2025 :2 hours
[ ] 8. General: Go to Hong Kong  :25 Aug 2025 :6 hours (with family)
[ ] 9. Accommodation: Four Seasons Hotel :25 Aug 2025 :2 weeks (first hotel)
____________________________________________________________
```
### Check a travel activity : `check`
Checks a travel activity as completed.

Format: `check INDEX`
* `INDEX` has to be a number that is shown in the list of travel activities

Examples of usage: 
* `check 1`

Expected outcome:
```
____________________________________________________________
I have checked this activity:
[X] 1. General: Go to Japan  :14 Mar 2025 :7 hour
____________________________________________________________
```

### Uncheck a travel activity : `uncheck`
Unchecks travel activity and sets it to uncompleted.

Format: `uncheck INDEX`
* `INDEX` has to be a number that is shown in the list of travel activities

Examples of usage: 
* `uncheck 1`

Expected outcome:
```
____________________________________________________________
I have unchecked this activity:
[ ] 1. General: Go to Japan  :14 Mar 2025 :7 hour
____________________________________________________________
```


### Updating a travel activity : `update`
Updates the date, duration and tag of a travel activity

Format: `update INDEX /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* `INDEX` has to be a number that is shown in the list of travel activities

Examples of usage:
* `update 1 /date 2026-12-14 /duration 2 hours`
* `update 2 /date 2026-12-12 /duration 3 hours /tag Important`

Expected outcome:
```
____________________________________________________________
I have updated this task
from: Go to Japan  :14 Mar 2025 :7 hour
to: Go to Japan  :14 Dec 2026 :2 hours
____________________________________________________________
```

### Adding a tag: `tag`
Adds a new tag to an existing travel activity. The tag can be a short description to help a user better organise 
their travel activities. 

> 📒 Notes about the tag command:
> * The tag that is included into the input does not remove any whitespaces that users may have accidentally included.
> This gives users the option to include whitespaces in their tags.

Format: `tag INDEX TAGNAME`

* The `INDEX` must be a valid activity index.
* There must not be any trailing spaces in `INDEX`.

Example of usage:
* `tag 1 activity 1`

Expected outcome:
```
____________________________________________________________
I have tagged this task:
Go to Japan  :14 Dec 2026 :2 hours (activity 1)
____________________________________________________________
```

### Removing a tag: `untag`
Removes a tag from an existing travel activity.

Format: `untag INDEX`

* The `INDEX` must be a valid activity index.
* * There must not be any trailing spaces in `INDEX`.

Example of usage:
* `untag 1 `

Expected outcome:
```
____________________________________________________________
Tag removed from the task:
Go to Japan  :14 Dec 2026 :2 hours
____________________________________________________________
```

### Find activity from the list using activity tag: `findtag`

Find an activity based on their tag. All activities with the given tag will be listed out.

Format: `findtag TAG [/exclude KEYWORD]`

* `TAG` has to be a word, a phrase or a segment of the activity description to find the activity
* `KEYWORD` has to be a word, a phrase or a segment of the activity description to exclude the activity
* `/exclude KEYWORD` will exclude any activity with `KEYWORD` found in the **description** of the activity

Examples of usage: 
* `findtag spicy`
* `findtag spicy /exclude mala`

Expected outcome:
Without `/exclude`
```
____________________________________________________________
Here are what you are looking for:
[ ] 1. Food: Mala Hotpot :14 Mar 2025 :2 hours (very spicy)
[ ] 2. Food: McSpicy :16 Mar 2025 :1 hour (very spicy)
____________________________________________________________
```

With `/exclude`
```
____________________________________________________________
Here are what you are looking for:
[ ] 1. Food: McSpicy :16 Mar 2025 :1 hour (very spicy)
____________________________________________________________
```

### List tags from the list : `listtags`

Lists out all the tags that the user has placed in the list

Format: `listtags`

Examples of usage: 
* `listtags`

Expected outcome:
```
____________________________________________________________
These are the tags in your list: 
1. activity 2
2. first hotel
3. go up tower
4. very spicy
5. with family
____________________________________________________________
```

### Find activity from the list using activity type : `findtype`

Find an activity based on their type. All activities with the given type will be listed out.

Format: `findtype TYPE [/exclude KEYWORD]`
* `TYPE` has to be a word, a phrase or a segment of the activity description to find the activity
* `KEYWORD` has to be a word, a phrase or a segment of the activity description to exclude the activity
* The different types are `general`, `accommodation`, `food`, `landmark`
* `/exclude KEYWORD` will exclude any activity with `KEYWORD` found in the **description** of the activity

Examples of usage: 
* `findtype general`
* `findtype general /exclude japan`

Expected outcome:

Without `/exclude`
```
____________________________________________________________
Here are what you are looking for:
[ ] 1. General: Go to Japan  :14 Dec 2026 :2 hours (activity 2)
[ ] 2. General: Go to Hong Kong  :25 Aug 2025 :6 hours (with family)
____________________________________________________________
```

With `/exclude`
```
____________________________________________________________
Here are what you are looking for:
[ ] 1. General: Go to Hong Kong  :25 Aug 2025 :6 hours (with family)
____________________________________________________________
```


### Adding an expense amount: `expense`

Adds an expense amount to an existing travel activity.

Format: `expense INDEX EXPENSE`

* The `INDEX` must be a valid activity index
* The `EXPENSE` can include the currency of the expense.

Example of usage:
* `expense 2 $50`

Expected outcome:
```
____________________________________________________________
I have added expense for this task:
Go to Hong Kong  :25 Aug 2025 :6 hours (with family) ($50)
____________________________________________________________
```

### Removing an expense amount: `removeexpense`
Removes an expense amount to an existing travel activity.

Format: `removeexpense INDEX`

* The `INDEX` must be a valid activity index.

Example of usage:
* `removeexpense 2`

Expected outcome:
```
____________________________________________________________
Expense removed from the task:
Go to Hong Kong  :25 Aug 2025 :6 hours (with family)
____________________________________________________________
```

### Calculating total expense amount: `totalexpense`
Calculates the total expense for all activities of the given type in the travel activity list.

Format: `totalexpense [/type TYPE]`

* The `TYPE` must be a valid type (Accomodation, Food, Landmark, TravelActivity)

Example of usage:
`totalexpense /type food`
`totalexpense`

Expected outcome:
```
____________________________________________________________
The total expense for all travel activities is: $0.0
____________________________________________________________
```

### Converting a currency : `change`

Converts the currency from one currency to another. (currency rates are adjusted in real time)

Format: `change AMOUNT /from CURRENCY1 /to CURRENCY2`
* `CURRENCY1` and `CURRENCY2` cannot be the same currency
* `AMOUNT` has to be a valid number
* `CURRENCY1` and `CURRENCY2` has to be in the form `sgd` for singapore dollars, `myr` for malaysia ringit.

Examples of usage:
* change 100 /from sgd /to myr
* change 1000 /from myr /to usd

Expected outcome:
```
____________________________________________________________
100.0sgd = 352.1923myr
____________________________________________________________
```

Common currency codes:
* USA: USD
* EURO: EUR
* ENGLAND: GBP
* JAPAN: JPY
* SINGAPORE: SGD
* HONG KONG: HKD
* AUSTRALIA: AUD
* NEW ZEALAND: NZD
* MALAYSIA: MYR
* CHINA: CNY
* KOREA: KRW

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: To transfer your data to another computer, copy the text file `omni.txt` under the text-ui-test file into the
same location of your other computer to transfer all the activities.

## Command Summary

* Get activity list `list [/date DATE /sort]`
* Get commands `help`
* Exit chatbot `bye`
* Add general travel activity `add DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add accommodation type `accommodation DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add Food type `food DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add Landmark type `landmark DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Find activity using activity description `find DESCRIPTION [/exclude KEYWORD]`
* Delete travel activity `delete INDEX/ACTIVITY`
* Check activity as done `check INDEX`
* Uncheck activity as not done `uncheck INDEX`
* Update travel activity `update INDEX /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add tag `tag INDEX TAGNAME`
* Remove tag `untag INDEX`
* Find activity using activity tag `findtag TAG [/exclude KEYWORD]`
* Find activity using activity type `findtype TYPE [/exclude KEYWORD]`
* Lists out all tags `listtags`
* Add expense `expense INDEX EXPENSE`
* Remove expense `removeexpense INDEX`
* Total expense `totalexpense [/type TYPE]`
* Check currency exchange `change AMOUNT /from CODE /to CODE`

