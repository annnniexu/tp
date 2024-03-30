# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Display Commands : `help`
Displays the features of OmniTravel

Format: `help`

Example of usage:

`help`


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
* Add tag `tag n/ACTIVITY_NUMBER d/TAG_NAME`
* Remove tag `untag n/ACTIVITY_NUMBER`
* Add expense `expense n/ACTIVITY_NUMBER d/EXPENSE_AMOUNT`
* Remove expense `removeexpense n/ACTIVITY_NUMBER`
