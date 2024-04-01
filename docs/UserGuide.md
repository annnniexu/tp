# User Guide

## Introduction

OmniTravel is a software that **allows travellers to store their travel plans and expenses in a
list via a Command Line Interface**. 

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 
> 📒 Notes about the command format :
> * The words that are in `UPPER_CASE` represents the parameters that the users are required to input
> e.g. `delete INDEX`, `INDEX` is a parameter which can be used as `delete 1`.
> * Commands such as `help`, `list` and `bye` do not require additional parameters. Hence, any extra parameters will be ignored. 
> e.g. `help 123` will just be intepreted as `help`.
> * Words that are in square brackets such as `[/tag TAG]` indicates that it is
> optional to include in the command.
 

{Give detailed description of each feature}

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

### Deleting a travel activity : `delete`
Deletes a travel activity from the travel activity list

Format: `delete INDEX`
* `INDEX` has to be a number that is shown in the list of travel activities

Examples of usage: `delete 1`

### Listing all the travel activities : `list`
Shows all the travel activities including their tags and expenses

Format: `list`

Examples of usage: `list`

### Updating a travel activity : `update`
Updates the date, duration and tag of a travel activity

Format: `update INDEX /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* `INDEX` has to be a number that is shown in the list of travel activities

Examples of usage:
* `update 1 /date 2019-12-14 /duration 2 hours`
* `update 2 /date 2018-12-12 /duration 3 hours /tag Important`

### Adding a tag: `tag`
Adds a new tag to an existing travel activity.

Format: `tag n/ACTIVITY_NUMBER d/TAG_NAME`

* The `ACTIVITY_NUMBER` must be a valid activity number.

Example of usage: 

`tag n/1 d/activity 1`

### Removing a tag: `untag`
Removes a tag from an existing travel activity.

Format: `untag n/ACTIVITY_NUMBER`

* The `ACTIVITY_NUMBER` must be a valid activity number.

Example of usage:

`untag n/1 `

### Adding an expense amount: `expense`
Adds an expense amount to an existing travel activity.

Format: `expense n/ACTIVITY_NUMBER d/EXPENSE_AMOUNT`

* The `ACTIVITY_NUMBER` must be a valid activity number
* The `EXPENSE_AMOUNT` can include the currency of the expense.

Example of usage:

`expense n/1 d/$50`

### Removing an expense amount: `removeexpense`
Removes an expense amount to an existing travel activity.

Format: `removeexpense n/ACTIVITY_NUMBER`

* The `ACTIVITY_NUMBER` must be a valid activity number.

Example of usage:

`removeexpense n/2`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Get commands `help`
* Add general travel activity `add DESCRIPTION /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Delete travel activity `delete INDEX`
* List travel activities `list`
* Update travel activity `update INDEX /date YYYY-MM-DD /duration DURATION [/tag TAG]`
* Add tag `tag n/ACTIVITY_NUMBER d/TAG_NAME`
* Remove tag `untag n/ACTIVITY_NUMBER`
* Add expense `expense n/ACTIVITY_NUMBER d/EXPENSE_AMOUNT`
* Remove expense `removeexpense n/ACTIVITY_NUMBER`
