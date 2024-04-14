# Chin Yan Xu - Project Portfolio Page

## Overview
Omnitravel is a CLI (Command-Line Interface) app that stores users travel plans for easy reference while travelling.
Users are able to store, edit and delete various types of travel plans such as Accommodation, Food and Landmark. 
Users can also associate small bits of information about their trip to their travel plans such as the purpose of the 
visit through the expense and tagging functions. Various lookup functions such as the findtag functions enable the user 
to quickly group and retrieve information.

### Summary of Contributions
Given below are my contributions to the project.

* New Feature: Added the find command [#25](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/25)
  * What it does: Enables the user to display all the travel activities stored in Omnitravel containing a certain 
keyword in their description
  * Justification: User might only want to see a list of certain activities and this feature will enable them to do so.
  * Highlights: User can use the `/exclude KEYWORD` optional inputs to further filter out what they want to see it their 
search
* New Feature: Added the findtag command [#89](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/89)
  * What it does: Enables the user to display all the travel activities stored in Omnitravel containing a certain tag
  * Justification: User might have tagged each activity to categorise them and might want to see which activity belongs
to a tag. This feature enables them to do so.
  * Highlights: User can use the `/exclude KEYWORD` optional inputs to further filter out what they want to see it their
search
* New Feature: Added the findtype command [#91](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/91)
  * What it does: Enables the user to display all the travel activities stored in Omnitravel of a certain type
  * Justification: User might have sorted each activity into their respective types and might want to see which 
activity is under a type. This feature enables them to do so.
  * Highlights: User can use the `/exclude KEYWORD` optional inputs to further filter out what they want to see it their
search

#### Code contribution:
[RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=chinyanxu&breakdown=true)

* Enhancements implemented to existing features
  * Added a way to delete travel activities from the list using keywords instead of just the index of the travel 
activity [#211](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/211)
* Documentation
  * User Guide:
    * Added documentation for feature `find` [#127](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/127)
    * Added documentation for feature `findtag` [#127](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/127)
    * Added documentation for feature `findtype` [#127](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/127)
    * Added documentation for delete by keyword enhancement [#211](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/211)
  * Developer Guide:
    * Added OmniTravel value proposition. [#92](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/92)
  * Community:
    * Suggested fixes for other teams in the forum [#38](https://github.com/nus-cs2113-AY2324S2/forum/issues/38)
* Contributions to team-based-tasks
  * Contributed large portions of the JUnit tests [#236](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/236)
  * Implemented a way to test text outputted to console [#92](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/92)
  * Implemented a way to test if an exception is triggered [#236](https://github.com/AY2324S2-CS2113-T12-4/tp/pull/236)
