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

6. A GUI similar to the below should appear in a few seconds.



## Features 
> 📒 Notes about the command format :
> * The words that are in `UPPER_CASE` represents the parameters that the users are required to input
> e.g. `delete INDEX`, `INDEX` is a parameter which can be used as `delete 1`.
> * Commands such as `help`, `list` and `bye` do not require additional parameters. Hence, any extra parameters will be ignored. 
> e.g. `help 123` will just be intepreted as `help`.
> * Words that are in square brackets such as `[/tag TAG]` indicates that it is
> optional to include in the command.
> * Users should not use any `/` into their input descriptions unless using for input command format
 


### Display Commands : `help`
Displays the features of OmniTravel

Format: `help`

Example of usage:

`help`

### Adding a general travel activity : `add`
Adds a general travel activity into the travel activity list

Format: `add DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`

Examples of usage:
* `add Go to Japan /date 2024-03-14 /duration 7 hours`
* `add Go to Hong Kong /date 2024-08-25 /duration 6 hours /tag with family`

### Add an accommodation activity type into the list : `accommodation`
Adds an accommodation type travel activity into the travel activity list

Format: `accommodation DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`

Examples of usage:
* `accommodation Four Seasons Hotel /date 2024-03-14 /duration 2 weeks`
* `accommodation Four Seasons Hotel /date 2024-08-25 /duration 2 weeks /tag first hotel`

### Add a food activity type into the list : `food`
Adds a food type travel activity into the travel activity list

Format: `food DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`

Examples of usage:
* `food Mala Hotpot /date 2024-03-14 /duration 2 hours`
* `food Mala Hotpot /date 2024-03-14 /duration 2 hours /tag very spicy`

### Add a landmark activity type into the list : `landmark`
Adds a landmark type travel activity into the travel activity list

Format: `landmark DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`

Examples of usage:
* `landmark Eiffel Tower /date 2024-03-14 /duration 2 hours`
* `landmark Eiffel Tower /date 2024-03-14 /duration 2 hours /tag go up tower`

### Find activity from the list using activity description : `find`
Find an activity based on their description. All activities with the given description will be listed out.

Format: `find DESCRIPTION`
* `DESCRIPTION` has to match the activity description exactly to find the activity

Examples of usage: `find saizeriya`

### Deleting a travel activity : `delete`
Deletes a travel activity from the travel activity list

Format: `delete INDEX`
* `INDEX` has to be a number that is shown in the list of travel activities

Examples of usage: `delete 1`

### Listing all the travel activities : `list`
Shows all the travel activities including their tags and expenses

Format: `list`

Examples of usage: `list`

### Check a travel activity : `check`
Checks a travel activity as completed.

Format: `check INDEX`
* `INDEX` has to be a number that is shown in the list of travel activities

Examples of usage: `check 1`


### Uncheck a travel activity : `uncheck`
Unchecks travel activity and sets it to uncompleted.

Format: `uncheck INDEX`
* `INDEX` has to be a number that is shown in the list of travel activities

Examples of usage: `uncheck 1`


### Updating a travel activity : `update`
Updates the date, duration and tag of a travel activity

Format: `update INDEX /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* `INDEX` has to be a number that is shown in the list of travel activities

Examples of usage:
* `update 1 /date 2019-12-14 /duration 2 hours`
* `update 2 /date 2018-12-12 /duration 3 hours /tag Important`

### Adding a tag: `tag`
Adds a new tag to an existing travel activity.

Format: `tag INDEX TAGNAME`

* The `INDEX` must be a valid activity index.

Example of usage:

`tag 1 activity 1`

### Removing a tag: `untag`
Removes a tag from an existing travel activity.

Format: `untag INDEX`

* The `INDEX` must be a valid activity index.

Example of usage:

`untag 1 `

### Find activity from the list using activity tag: `findtag`

Find an activity based on their tag. All activities with the given tag will be listed out.

Format: `findtag TAG`
* `TAG` has to match the activity tag exactly to find the activity

Examples of usage: `findtag sightseeing`

### List tags from the list : `listtags`

Lists out all the tags that the user has placed in the list

Format: `listtags`

Examples of usage: `listtags`
### Find activity from the list using activity type : `findtype`

Find an activity based on their type. All activities with the given type will be listed out.

Format: `findtype TYPE`
* `TYPE` has to match the activity type exactly to find the activity

Examples of usage: `findtype TravelActivity`

### Adding an expense amount: `expense`
Adds an expense amount to an existing travel activity.

Format: `expense INDEX EXPENSE`

* The `INDEX` must be a valid activity index
* The `EXPENSE` can include the currency of the expense.

Example of usage:

`expense 2 $50`

### Removing an expense amount: `removeexpense`
Removes an expense amount to an existing travel activity.

Format: `removeexpense INDEX`

* The `INDEX` must be a valid activity index.

Example of usage:

`removeexpense 2`

### Adding an expense amount: `totalexpense`
Calculates the total expense for all activities of the given type in the travel activity list.

Format: `totalexpense [/type TYPE]`

* The `TYPE` must be a valid type (Accomodation, Food, Landmark, TravelActivity)

Example of usage:

`totalexpense /type food`
`totalexpense`

### Converting a currency : `change`

Converts the currency from one currency to another

Format: `change AMOUNT /from CURRENCY1 /to CURRENCY2`
* `CURRENCY1` and `CURRENCY2` cannot be the same currency
* `AMOUNT` has to be a valid number
* `CURRENCY1` and `CURRENCY2` has to be in the form `sgd` for singapore dollars, `myr` for malaysia ringit.

Examples of usage:
* change 100 /from sgd /to myr
* change 1000 /from myr /to usd
* 
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
