# User Guide

## Introduction

OmniTravel is a software that **allows travellers to store their travel plans and expenses in a
list via a Command Line Interface**. 

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest omni.jar from here.

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
> * Commands such as `help`, `list` and `bye` do not require additional parameters. Hence, any extra parameters will be ignored. 
> e.g. `help 123` will just be intepreted as `help`.
> * Words that are in square brackets such as `[/tag TAG]` indicates that it is
> optional to include in the command.
> * Users should not use any `/` into their input descriptions unless using for input command format


### Adding a general travel activity : `add`
Adds a general travel activity into the travel activity list
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
Adds an accommodation type travel activity into the travel activity list

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
Find an activity based on their description. All activities with the given description will be listed out.

Format: `find DESCRIPTION`
* `DESCRIPTION` has to match the activity description exactly to find the activity

Examples of usage (assuming saizeriya is in list): 
* `find saizeriya`

Expected output:
```
____________________________________________________________
Here are what you are looking for:
[ ] 1. Food: saizeriya :14 Mar 2025 :2 hours
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

Expected output:
```
____________________________________________________________
I have removed this activity:
Accommodation: Four Seasons Hotel :14 Mar 2025 :2 days
____________________________________________________________
```
```
____________________________________________________________
I have removed this activity:
1. Landmark: Eiffel Tower :14 Mar 2025 :2 hours (go up tower)
____________________________________________________________
```

### Listing all the travel activities : `list`
Shows all the travel activities including their tags and expenses

Format: `list`

Examples of usage: 
* `list`

Expected outcome:
```
____________________________________________________________
Here are the travel activities in your list:
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

Format: `tag INDEX TAGNAME`

* The `INDEX` must be a valid activity index.

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

Format: `findtag TAG`
* `TAG` has to match the activity tag exactly to find the activity

Examples of usage: 
* `findtag spicy`

Expected outcome:
```
____________________________________________________________
Here are what you are looking for:
[ ] 1. Food: Mala Hotpot :14 Mar 2025 :2 hours (very spicy)
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

Format: `findtype TYPE`
* `TYPE` has to match the activity type exactly to find the activity
* The different types are `general`, `accommodation`, `food`, `landmark`

Examples of usage: 
* `findtype general`

Expected outcome:
```
____________________________________________________________
Here are what you are looking for:
[ ] 1. General: Go to Japan  :14 Dec 2026 :2 hours (activity 2)
[ ] 2. General: Go to Hong Kong  :25 Aug 2025 :6 hours (with family)
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

### Adding an expense amount: `totalexpense`
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

Converts the currency from one currency to another

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

### Adding a location: `location`
Adds a location to an existing travel activity.

Format: `location INDEX LOCATION`

* The `INDEX` must be a valid activity index
*
Example of usage:

`location 3 Singapore`

Expected outcome:
```
____________________________________________________________
I have added a location to this task:
Go to Japan  :14 Dec 2026 :2 hours (Japan)
____________________________________________________________
```

### Removing a location amount: `removelocation`
Removes a location to an existing travel activity.

Format: `removelocation INDEX`

* The `INDEX` must be a valid activity index.

Example of usage:

`removelocation 3`

Expected outcome:
```
____________________________________________________________
Location removed from the task:
Go to Japan  :14 Dec 2026 :2 hours
____________________________________________________________
```

### Find activity from the list using activity location: `findlocation`

Find an activity based on their location. All activities with the given location will be listed out.

Format: `findlocation LOCATION`
* `LOCATION` has to match the activity location exactly to find the activity

Examples of usage: `findlocation Japan`

Expected outcome:
```
____________________________________________________________
Here are what you are looking for:
[ ] 1. General: Go to Japan  :14 Dec 2026 :2 hours (Japan)
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

* Get activity list `list`
* Get commands `help`
* Exit chatbot `bye`
* Add general travel activity `add DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add accommodation type `accommodation DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add Food type `food DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add Landmark type `landmark DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Find activity using activity description `find DESCRIPTION`
* Delete travel activity `delete INDEX`
* Check activity as done `check INDEX`
* Uncheck activity as not done `uncheck INDEX`
* Update travel activity `update INDEX /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add tag `tag INDEX TAGNAME`
* Remove tag `untag INDEX`
* Find activity using activity tag `findtag TAG`
* Find activity using activity type `findtype TYPE`
* Add expense `expense INDEX EXPENSE`
* Remove expense `removeexpense INDEX`
* Total expense `totalexpense [/type TYPE]`
* Check currency exchange `change AMOUNT /from CODE /to CODE`
* Add location `location INDEX LOCATION`
* Remove location `removelocation INDEX`
* Find location `findlocation LOCATION`
