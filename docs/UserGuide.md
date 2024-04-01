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
 

{Give detailed description of each feature}

### `help` - Display the commands
Displays the features of OmniTravel

Example of usage:

`help`

Expected outcome:

```
____________________________________________________________
These are the available commands!
1. list
2. help
3. bye
4. add <travel activity> <date> <duration> <tag>
5. accommodation <travel activity> <date> <duration> <tag>
6. food <travel activity> <date> <duration> <tag>
7. landmark <travel activity> <date> <duration> <tag>
8. delete <activity number>
9. find <keyword>
10. check <activity number>
11. uncheck <activity number>
12. tag <activity number> <tag name>
13. untag <activity number>
14. update <update> <date> <duration> <tag>
15. findtag <tag name>
16. findtype <type>
17. expense <activity number> <expense amount>
18. removeexpense <activity number>
____________________________________________________________
```
### `list` - Lists out the current activity list

After you type `list`, the chatbot will output the current activity list

Example of usage:

`list`

Expected outcome:

```
____________________________________________________________
Here are the travel activities in your list:
[ ] 1. paris :12 Dec 2023 :1 (hello) ($50)
[ ] 2. japan :12 Dec 2023 :now ($100)
____________________________________________________________
```

### `add` - add a general activity into the list

After you type `add DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`, the chatbot will input your general
activity into the activity list

Example of usage:

Without tags

`add visit paris /date 2024-12-12 /duration 2 weeks`

With tags

`add visit paris /date 2024-12-12 /duration 2 weeks /tag first country`

Expected outcome:

```
____________________________________________________________
I added a new travel activity
visit paris :12 Dec 2024 :2 weeks
____________________________________________________________
```

### `accommodation` - add an accommodation activity type into the list

After you type `accommodation`, adds an accommodation activity into the list

Example of usage:

Without tags

`accommodation four seasons hotel /date 2024-12-12 /duration 2 weeks`

With tags

`add four seasons hotel /date 2024-12-12 /duration 2 weeks /tag first hotel`

Expected outcome:

```
____________________________________________________________
I added a new accommodation
Accommodation: four seasons hotel :12 Dec 2024 :2 weeks
____________________________________________________________

```

### `food` - add a food activity type into the list

After you type `food`, adds a food activity into the list

Example of usage:

Without tags

`food Hells Kitchen /date 2024-12-12 /duration 2 hours`

With tags

`add Hells Kitchen /date 2024-12-12 /duration 2 hours /tag Meet Gordon Ramsay`

Expected outcome:

```
____________________________________________________________
I added a new restaurant
Food: Hells Kitchen :12 Dec 2024 :2 hours
____________________________________________________________

```
### `landmark` - add a landmark activity type into the list

After you type `landmark`, adds a landmark activity into the list

Example of usage:

Without tags

`landmark Eiffel Tower /date 2024-12-12 /duration 2 hours`

With tags

`landmark Eiffel Tower /date 2024-12-12 /duration 2 hours /tag go up tower`

Expected outcome:

```
____________________________________________________________
I added a new landmark
Landmark: Eiffel Tower :12 Dec 2024 :2 hours
____________________________________________________________

```

### `find` - find an activity in the list using a keyword


### `delete` - delete an existing activity from the list

### `check` - check an activity from the list to mark as done

### `uncheck` - uncheck an activity from the list to mark as not done

### `update` - update an existing activity in the list

### `tag` - add a tag to an existing activity in the list

### `untag` - remove a tag from an existing activity in the list

### `findtag` - find an activity through its tag

### `findtype` - find all activities of the same type

### `expense` - add an expense to an activity in the list

### `removeexpense` - remove an expense from an activity in the list

### `bye` - exits the chatbot

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Get activity list `list`
* Get commands `help`
* Exit chatbot `bye`
* Add general travel activity `add DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add accommodation type `accommodation DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add Food type `food DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add Landmark type `landmark DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Find keyword in list `find KEYWORD`
* Delete travel activity `delete INDEX`
* Check activity as done `check INDEX`
* Uncheck activity as not done `uncheck INDEX`
* Update travel activity `update INDEX /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add tag `tag INDEX TAGNAME`
* Remove tag `untag INDEX`
* Find tag `findtag TAGNAME`
* Find type `findtype TYPENAME`
* Add expense `expense n/ACTIVITY_NUMBER d/EXPENSE_AMOUNT`
* Remove expense `removeexpense n/ACTIVITY_NUMBER`
