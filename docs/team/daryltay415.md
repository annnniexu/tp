# Daryl Tay Chin Kian - Project Portfolio Page

## Project: OmniTravel

OmniTravel is a command line interface application used for tracking and planning travel activities.
It is able to convert currencies as well as set expenses for each travel activities.

### Summary of Contributions
Given below are all the contributions I have made to this project.

* New Feature: Added a update command to update travel activities
  * What it does: Allows the user to update the existing travel activity with a new date, duration
    and tag.
  * Justification: Users might have changes in their plans and this feature will make it much easier
    for users to edit their plans.
  * Highlights: This enhancement does affect some existing commands.
* New Feature: Added a exchange currency command to change from one currency to another
  * What it does: Allows the user to convert an amount of a local currency to a foreign currency.
  * Justification: This feature allows the user to check how much local currency they require to convert
    to the foreign currency. It makes it more convenient for them to plan how much expenses they require
    for certain activities.
  * Highlights: It required some in-depth analysis to ensure the exceptions were handled well. 
    The implementation was also challenging as it was my first time using an API and the guide
    given by the third party does not explain much on using the API.
  * Credits: Current exchange API: [link](https://github.com/fawazahmed0/exchange-api/blob/main/LICENSE)
  
* Code contributed: [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=daryltay415&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* Project management:
  * Released issues in v1.0 on GitHub 
  * Managed releases v1.0 - v2.0 (2 releases) on GitHub
* Enhancements to existing features:
  * Added ability to add dates and duration to the `add` feature [#81](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/81) 
* Documentation: 
  * User Guide:
    * Added documentation for the features `add`,`update`,`list`, `listtags`, `change` [#110](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/110), [#195](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/195)
  * Developer Guide:
    * Added implementation details of the `update` feature [#94](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/94)
    * Added class diagram for overview of all classes [#228](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/228)
    * Added details about the main components of the system [#228](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/228)
    * Added manual testing details [#203](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/203)
* Community:
  * Reported bugs and suggestions for other teams in the class (examples [1](https://github.com/nus-cs2113-AY2324S2/tp/pull/45/files#r1550908455),
    [2](https://github.com/nus-cs2113-AY2324S2/tp/pull/85/files#r1550881947)) 
* Tools:
  * Integrated a third part API to the project [#195](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/195)
* Other contributions: Fixing, debugging and testing of code. [#193](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/193)