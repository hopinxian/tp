---
layout: page
title: User Guide
---
* Table of Contents
{:toc}
---

**VirusTracker** is a **desktop app for generating statistics for Covid-19, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
It is mainly targeted towards healthcare officials who are handling large amounts of data due to the pandemic.
VirusTracker aims to provide a faster and quicker alternative to common statistical programs.

--------------------------------------------------------------------------------------------------------------------
## Preface

Welcome to the user guide on VirusTracker.

This guide will guide you through the different features that VirusTracker has to offer.
VirusTracker works with three main entities:
* People
* Locations
* Visits

A person refers to any person whom may be at risk from Covid-19. VirusTracker stores data about the id, name, address, phone number and email of each person. 
A location refers to any location which are open for visiting. VirusTracker stores data about the id, name and address of each location.
A visit refers to when a Person visits a Location on a given date. VirusTracker stores the data of the Person and the Location involved in the Visit.

The recommended way for you to use VirusTracker is to first store the information of all the people and locations you wish to track.
Then, whenever a person visits a location, add the corresponding `visit`.

VirusTracker would be able to generate useful information based off the data that is input into the system.

--------------------------------------------------------------------------------------------------------------------

## Notations

Here are a few notations which may be used in this User Guide. Each notation represents a different meaning to help you understand the guide better.

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

Presents information which are helpful to take note about. 

</div>

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:**

Good to learn, but not necessary to know.

</div>

<div markdown="block" class="alert alert-danger">

:warning: **Warning:**

Focuses on information that you should be careful about. Being cautious is recommended.

</div>

--------------------------------------------------------------------------------------------------------------------
## Glossary

The following table presents a list of key terms that will be used in this user guide. 

| Term       | Meaning                                                                                |
|------------|----------------------------------------------------------------------------------------|
| Entity     | Refers to people, locations or visits                                                  |
| Command    | Refers to user input that instructs VirusTracker on what to do                         |
| Identifier | Refers to ids or indexes. These are used to uniquely identify a location or person     |
| Prefix     | Refers to prefixes used in commands. These precede fields that are typed in user input |

--------------------------------------------------------------------------------------------------------------------
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `virustracker.jar` from [here](https://github.com/AY2021S1-CS2103T-T13-1/tp/releases). <br>
   _Note: VirusTracker is still a work-in-progress and official releases are not available yet._
   
3. Copy the file to the folder you want to use as the _home folder_ for your VirusTracker.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list l/people`** : Lists all people.

   * **`addLocation`**`idl/L123A n/NTU a/50 Nanyang Ave, 639798` : Adds a location named `NTU` to the VirusTracker.

   * **`deletePerson`**`3` : Deletes the 3rd person shown in the current list.

   * **`clear`** : Deletes all entries from VirusTracker.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## User Interface

You may refer to the following table and figure on the components of the User Interface.
 ![annotatedUI](images/annotatedUI.png)

The User Interface is made up of multiple components whose purpose are explained in the table below.

| Component | Description |
| --------------- | ---------------------------------------- | 
| Toolbar         | Displays the toolbar for VirusTracker. Clicking on `File` and `Help` will show you the exit and help buttons respectively.  | 
| Command Box     | You may type in your inputs into this textbox.    | 
| Command Result  | VirusTracker displays responses inside this box. You may read success messages or warnings from this box.  | 
| Persons List    | Displays a list of Persons. Panels contain detailed information about each person. |
| Locations List  | Displays a list of Locations. Panels contain detailed information about each location. | 
| Visits List     | Displays a list of visits. Panels contain information about the person, location and date of the visit. | 

--------------------------------------------------------------------------------------------------------------------

## Features

This section introduces you to important notations and details that apply to the commands in VirusTracker.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addPerson n/NAME`, `NAME` is a parameter which can be used as `addPerson n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* In the case where you give multiple responses to the same parameter, VirusTracker takes the last response.<br>
  e.g. Given the command format `addPerson n/NAME p/PHONE_NUMBER` and your command `addPerson n/John p/12345678 p/87654321`,
  VirusTracker takes the phone number to be `87654321` and ignores `12345678`.

* **Data** refers collectively to people, locations and visits unless stated otherwise.

* VirusTracker will actively stop the user from creating duplicates of entities. VirusTracker will alert you if it detects you are adding a duplicate entity.
  Entities are duplicates if they meet any of the following conditions.
  1. They have the same id.
  2. For visits, they are duplicates if they have the same date and involve the same person and location.
  3. For persons, they are duplicates if either
       - their name and phone are the same or
       - their name and email are the same.
  4. For locations, they are duplicates if they have the same address. 
</div>

### Valid Formats for Command Parameters

From the following table, you may see a list of command parameters that found in this user guide.
Each row shows the parameter, the corresponding prefix and conditions for the parameter to be valid. 

| Parameter | Prefix | Valid Format |
| ----------| ------ | ------------ | 
| Date      |  d/    | Date format should follow `yyyy-MM-dd`.<br> e.g. 23 January 2020 is "2020-01-23". | 
| List type |  l/    | List types can only be `people`, `quarantined`, `infected`, `locations`, `visits`, `high-risk-locations`, `stats`. | 
| Name      |  n/    | Names may only contain alphanumeric characters and spaces, and it should not be blank. |
| Phone     |  p/    | Phone numbers may only contain numbers, and it should be at least 3 digits long. |
| Address   |  a/    | Addresses can take any values, and it should not be blank.|
| Email     |  e/    | Please refer to [Email Format](#Email Format) below for more details.|
| Quarantine Status | q/| Quarantine status should either be true or false, and it should not be blank.|
| Infected Status | i/ | Infection status should either be true or false, and it should not be blank. |
| Tag       | t/     | Tags should be alphanumeric. |
| Person Id | idp/   | Person Ids can take any values, and it should be at least 5 characters long.|
| Location Id | idl/ | Location Ids can take any values, and it should be at least 5 characters long.|

#### Email Format
Emails should be of the format `local-part@domain` and adhere to the following constraints:
 1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (!#$%&'*+/=?`{|}~^.-).
 2. This is followed by a '@' and then a domain name. 
 3. The domain name must:
     - be at least 2 characters long
     - start and end with alphanumeric characters
     - consist of alphanumeric characters, a period or a hyphen for the characters in between, if any.

### Index and Ids

There are many situations where you may want to refer to a specific location or person when giving a command.
For example, you may want to delete a location or add a visit involving a specific location and person.
In such cases, VirusTracker provides two methods for you to use when referring to a specific person or location, either index or id.
This guide collectively refers to indexes and ids as identifiers.
In the figure below showing a person panel, there is a number beside the person's name and there is a person id.<br>
 ![personPanel](images/personPanel.PNG)

You may use either the index number (which corresponds to the position of the person within the persons list) or the id to refer to a person. 
The same applies for locations.
In the example shown above, you may use either `deletePerson 1` or `deletePerson idp/S123A` to remove the person named `Alex Yeoh` from VirusTracker.
You may prefer to use ids when they do not wish to scroll the list to find the relevant index.
You may also prefer to use indexes which tend to be shorter than the ids of people and locations.


<div markdown="block" class="alert alert-info">

**:information_source: Notes about using indexes and ids in commands:**<br>
* The field `IDENTIFIER` means that the user needs to input either an id or index. You are not allowed to use both at the same time. 
  `LOCATION_IDENTIFIER` and `PERSON_IDENTIFIER` refers to location and person identifiers respectively.
* You may input an index by just using the number alone. However, ids must have a prefix in front of them.
  `idp` is the prefix for person while `idl` is the prefix for locations. <br>
  e.g.  `deleteLocation 3` and `deleteLocation idl/L123A`. The first command uses an index of 3 while the second command uses the id `L123A`.
* When using indexes, it should before any other fields which need prefixes. <br>
  e.g. `addVisit 1 1 d/2020-02-02` is allowed but `addVisit 1 d/2020-02-02 1` is not allowed.

</div>

<div markdown="block" class="alert alert-danger">

**:warning: Warnings about indexes and ids**<br>
* Take note that the index of a person/location may change depending on the index of the person/location as viewed from the most recently displayed person/location list.
  Observe that the index of ION Orchard changes from 1 to 2 after adding a new location in the figure below. <br>

   ![indexes](images/indexes.png)
* Indexes **must be positive integers**: 1, 2, 3, …​ and within the range of its shown list, otherwise warnings will be triggered.
* Ids used must belong to a person/location within VirusTracker.

</div>


### Adding data

To add data to VirusTracker, there are `add` commands for each entity.

#### Adding a person

Adds a person to VirusTracker.

Format: `addPerson idp/ID n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS q/QUARANTINE_STATUS i/INFECTED_STATUS [t/TAG]…​` 

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0).
</div>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* `ID` of person must be unique. No other person in the VirusTracker may have the same ID.
* `QUARANTINE_STATUS` and `INFECTED_STATUS` can only be true or false.

</div>

Examples:
* `addPerson idp/S123 n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 q/true i/false`
* `addPerson idp/S234 n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 q/false i/true t/criminal`

#### Adding a location

Adds a location to VirusTracker.

Format: `addLocation idl/ID n/NAME a/ADDRESS`

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* `ID` of location must be unique. No other location in the VirusTracker may have the same ID.

</div>

Examples:
* `addLocation idl/L123 n/Vivocity a/John street, block 123, #01-01`
* `addLocation idl/L234 n/Parliament House a/1 Parliament Place, Singapore 178880`

#### Adding a visit

Adds a visit by the person, location of visit and date of visit

Format: `addVisit PERSON_IDENTIFIER LOCATION_IDENTIFIER d/DATE`

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* VirusTracker gives a warning if visits involve an infected/quarantined people visiting locations they should not.
* Visits may be added by either using all indexes or all ids only. A mix of both is not allowed and will trigger a warning. 
* Date format should follow "yyyy-MM-dd", otherwise exceptions would be thrown.
* Dates of the visit must not be after the current date.<br>
  e.g. If today is 31 October 2020, you may set the date as 31 October 2020 or earlier, but not 1 November 2020. 

</div>

Examples:
* `addVisit 1 1 d/2020-09-12`
* `addVisit idp/S123A idl/L123A d/2020-02-20`

### Listing data:

There are a variety of `list` commands that list different types of data.

#### Listing all people 
Format: `list l/people`

* Updates the persons list to display all people currently stored in VirusTracker.

#### Listing all infected people 
Format: `list l/infected`

* Filters the persons list to display all people that are currently infected.

#### Listing all quarantined people 
Format: `list l/quarantined`

* Filters the persons list to display all people currently in quarantine.

#### Listing all locations
Format: `list l/locations`

* Updates the locations list to displays all locations currently stored in VirusTracker.

#### Listing all visits
Format: `list l/visits`

* Updates the visits list to displays all visits currently stored in VirusTracker.

#### Listing high risk locations

Lists the locations with high risk of Covid infection.

Format: `list l/high-risk-locations`

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* A location is considered as infected if an infected person visited that location.
* If number of infected locations are more than 60% of number of total locations, number of high risk locations equals 
to 40% of number of total locations. Else, number of high risk locations equals to number of infected locations.
* Let number of high risk locations be `n`. The first `n` number of most infected locations are shown.
* For example, number of total locations is `10`, number of infected locations is `7`, so the number of high risk 
locations is `40% * 10 = 4`. The first `4` infected locations from the list of infected locations sorted from highest to 
lowest risk are displayed.
* If there are less than ten locations that are infected, all locations will
  be shown.

</div>
  
#### Listing summary of data

Shows the general summary of the data in the form of statistics.

Format: `list l/stats`

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* Data used to generate statistics are the people, locations and visits added into VirusTracker.
* Currently, the statistics generated include:
    1. Total number of people, locations and visits
    2. Total number of people infected/quarantined
    3. Percentage of people infected/quarantined
* The above provides a brief summary of the pandemic and is subject to extension.

</div>


#### Adding data from CSV files

As you may have pre-existing data stored in the Excel file format, VirusTracker provides a way to import data directly from
files in the CSV format. Excel provides an option to save existing _.xlsx_ extension files as _.csv_ files.

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
If you are importing data from a pre-existing Excel file, you may have to first format it to a format that is readable by VirusTracker.
</div>

You may read more about it [here](#format-for-csv-files).

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
As visits rely on people and locations, it is recommended that person and location data csv files be added before visits so
as to ensure that the referenced people and locations in the visits data csv file exist.
</div>


Format: `addFromCsv FILE_PATH l/LIST_TYPE`

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* `FILE_PATH` refers to the absolute file path where the CSV file resides.
  * For example, if you wish to import data from `personList.csv` located in your desktop, the absolute file path could look
  something like this: `C:/Users/user/Desktop/personList.csv` _(for Windows)_, `/Users/admin/Documents/personList.csv` _(for MacOS)_,
  `/home/user/docs/personList.csv` _(for Linux)_
  * You may **find the absolute file path** as follows
    1. Right-click your file `E.g. personList.csv`
    2. Select 'Properties'
    3. Take note of the path specified in the 'Location' field. `E.g. C:/Users/user/Desktop`
    4. The absolute file path is the path found in Step 3 along with your file name. `C:/Users/user/Desktop/personList.csv`
* `LIST_TYPE` refers to 'people', 'locations' or 'visits'.
  * The prefix `l/` is also used for [listing data](#listing-data-list)
* The CSV file should have its data in [VirusTracker readable format](#format-for-csv-files).
  * For visits data, the format used references the id of the people and locations. The format using
  list indexing is not supported.
* If you do not specify an absolute path, VirusTracker **may not be able to find your file!**

</div>


##### _Error Handling_
Sometimes, the format of the CSV file may be wrong when executing the command. VirusTracker has different behaviour for different
types of errors.
1. Duplicate entities detected within the CSV file 
    * Command operation does not terminate
    * VirusTracker adds all entities which are not duplicates
    * At the end of the operation, the lines in the CSV files with duplicates are displayed so that you could make adjustments to the file. 
2. Erroneous entry _(Not enough parameters)_
    * Occurs when one or more rows do not have enough compulsory parameters
    * Command operation terminates immediately and nothing is added
    * The line number of the erroneous row lacking parameters will be displayed
    * Only the first line with such an error will be displayed, so it is possible that other lines may not have enough paramters as well
3. Erroneous entry _(Wrong format for field)_
    * Occurs when an input field is of the wrong format
    * Command operation terminates immediately and nothing is added
    * The line number of the erroneous row will be displayed alongside the correct format for the field
    * Only the first line with such an error will be displayed, so it is possible that other rows after it have an error as well
    * Within the row, only the first erroneous field will be displayed. It is possible that other fields after it may have the wrong format as well
    
Examples:
* `addFromCsv C:/Users/alice/Desktop/peopleToAdd.csv l/people`
* `addFromCsv D:/visits on Dec 20.csv l/visits`

#### Exporting data to CSV files

Often, you may not only work on a single device. 

VirusTracker enables you to export the current data stored into a CSV file 
which could then be read by the VirusTracker application on another device.

Format: `exportToCsv FILE_PATH l/LIST_TYPE`

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* `FILE_PATH` refers to the absolute file path where the CSV file should reside.
  * Refer to the [Adding data from CSV files](#adding-data-from-csv-files) section to find out the absolute path of a file.
  * If the CSV file does not exist at the specified location, VirusTracker will create it for you.
* `LIST_TYPE` refers to 'people', 'locations' or 'visits'.
  * The prefix `l/` is also used for [listing data](#listing-data-list)
* The CSV file will have its data in [VirusTracker readable format](#format-for-csv-files).
* If you do not specify an absolute path, the file may be **created at an unexpected place!**

</div>

Examples:
* `exportToCsv C:/Users/alice/Desktop/peopleToAdd.csv l/people` creates a people data CSV file named `peopleToAdd.csv`
* `exportToCsv D:/visits on Dec 20.csv l/visits` creates a visit data CSV file named `visits on Dec 20.csv`

### Format for CSV files

As data can be formatting differently from file to file, VirusTracker specifies a certain format for CSV files to be imported.

* Depending on the entity, the format for each row will follow its relevant `add` command.
  * For example, for a CSV file adding locations, each row will correspond to an individual [addLocation](#adding-a-location) command format.
  ![Example Location CSV](images/ExampleCSVLocations.png)
  * As you can see from the figure above, each row is a valid `addLocation` command, with the command word omitted. 
  * Each column corresponds to a field in `addLocation` format.
  * As with the commands themselves, the order of the arguments do not matter.
  * You **MAY NOT** have data of different formats in the same CSV file. (i.e. adding people from rows 1 to 4, then locations from 5 to 8, etc.)

The conversion of pre-existing data to the required CSV format may require a bit of effort. Below are some tips to guide you along.
* It is recommended to create a new CSV file for importing instead of using the pre-existing data file to prevent data loss.
* Copy the rows of relevant data (name, addresses, dates, etc) into the new file.
* Using Excel functions, you can prepend the required prefixes to each data field.
* CSV files exported by VirusTracker already have this format and do not need to be reformatted.

#### Using Excel to add prefixes

The data present may be in a different format than what VirusTracker requires. Hence, below is a step by step guide to convert the common 
types of data fields to their required format.

The diagram below shows possible data columns pre-formatting. Column A represents a `PERSON_ID` and Column B represents `DATE` in this case.

![Format CSV 1](images/FormatCSV/FormatCsv1.png)

Find an empty column, and type the function in the diagram shown below.

![Format CSV 2](images/FormatCSV/FormatCsv2.png)

Upon pressing enter, you should see that the prefix has been prepended to the first item in column A as shown below.

![Format CSV 3](images/FormatCSV/FormatCsv3.png)

You may then click the bottom right corner of the formatted cell, C1 in this case, and drag downwards to fill the remaining cells.
Alternatively, you could also choose `Fill` -> `Down` from the menu bar.
 
 You should see a result similar to below.

![Format CSV 4](images/FormatCSV/FormatCsv4.png)

For date fields, the format of the function is slightly different. The date has to be formatted to the correct date format in addition
to being prepended with the date prefix.

The below function in the diagram **only works if the field is a date.**

![Format CSV 5](images/FormatCSV/FormatCsv5.png)

You may then similarly fill the cells as shown in the two diagrams below.

![Format CSV 6](images/FormatCSV/FormatCsv6.png)

![Format CSV 7](images/FormatCSV/FormatCsv7.png)

#### Replacing the data

After creating the formatted data, you may be tempted to directly copy the new data into the column containing the preformatted data.

However, this would result in an error as the formatted data is currently referencing the old data. To fix this, we should paste the values using
`Paste Special`.

For example, if you wished to copy formatted data from column C to column A, right click on A as shown below.

![Format CSV 8](images/FormatCSV/FormatCsv8.png)

Select `Paste Special` -> `Values` as shown below.

![Format CSV 9](images/FormatCSV/FormatCsv9.png)

Column A now has the formatted data and column C can be deleted.

![Format CSV 10](images/FormatCSV/FormatCsv10.png)

### Deleting data: 
To delete data from VirusTracker, there are various `delete` commands that can be used.

#### Deleting a person

Deletes the specified person from the persons list.

Format: `deletePerson PERSON_IDENTIFIER` <br>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**
* All visits made by the specified person would also be deleted.

</div>

<div markdown="block" class="alert alert-danger">
:warning: **Warning:**
Be careful that deleting a person is irreversible!
</div>

Examples:
* `list l/infected` followed by `delete 2` deletes the 2nd infected person in the displayed people list.
* `findPerson Betsy` followed by `delete 1` deletes the 1st person in the results of the `findPerson` command.
* `deletePerson idp/S123` deletes the person with the id S123.

#### Deleting a location

Deletes the specified location from the location list.

Format: `deleteLocation LOCATION_IDENTIFIER` <br>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* All visits that contains the specified locations would also be deleted.

</div>

<div markdown="block" class="alert alert-danger">
:warning: **Warning:**
Be careful that deleting a location is irreversible!
</div>

Examples:
* `list l/locations` followed by `deleteLocation 2` deletes the 2nd location in the displayed location list.
* `deleteLocation idl/L123` deletes the location with the ID L123.

#### Deleting visits using date

Deletes all visits before and including the date.

Format: `deleteVisits d/DATE`

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* A `DATE` is valid if **at least one** visit occurs before or on the specified date.
* All the visits before and including the date will be removed from the visits list.
* Date format should follow "yyyy-MM-dd", otherwise exceptions would be thrown.

</div>

Examples:
* `deleteVisits d/2020-09-12`

### Editing data: 
To edit data in VirusTracker, there are various `edit` commands that could be used.

#### Editing a person

Edits an existing person in VirusTracker.

Format: `editPerson PERSON_IDENTIFIER [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [q/QUARANTINE_STATUS] [i/INFECTION_STATUS] [t/TAG]…​` <br>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* A person's id cannot be edited.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.

</div>

Examples:
*  `editPerson 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `editPerson idp/S123A n/Betsy Crower t/` Edits the name of the person with ID S123 to be `Betsy Crower` and clears all existing tags.

#### Editing a location

Edits an existing location in VirusTracker.

Format: `editLocation LOCATION_IDENTIFIER [n/NAME] [a/ADDRESS]`

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* A location's ID cannot be edited.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

</div>

Examples:
*  `editLocation 1 n/NTU a/Bugis street` Edits the name and address of the 1st location to be `NTU` and `Bugis Street` respectively.
*  `editLocation idl/L123A n/NUS` Edits the name of the location with ID L123A to be `NUS`.

### Finding persons by name:

Finds persons whose names contain any of the given keywords.

Format: `findPerson KEYWORD [MORE_KEYWORDS]`

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

</div>

Examples:
* `findPerson John` returns `john` and `John Doe`
* `findPerson alex david` returns `Alex Yeoh`, `David Li`<br>

### Generating all locations visited by a person:

Shows a list of locations visited by an infected person in the past 2 weeks. 

Format: `generateLocations PERSON_IDENTIFIER`

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* Locations listed were visited by the infected person of the index given.
* The result given is a filtered list of locations that the person visited in the past 2 weeks.
* This function can be used to identify locations needing to be disinfected after being visited by an infected person.

</div>

Examples:
* `generateLocation 4`
* `generateLocation idp/S456D`

### Generating all people in contact with an infected person:

Shows a list of people who were in contact with an infected person in the past 2 weeks. 

Format `generatePeople PERSON_IDENTIFIER`

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* People listed were in contact with the infected person of the index given.
* The result given is a filtered list of people who visited the same locations as that the infected person in the past 2 weeks.
* This function can be used to identify people who need to be quarantined or issued Stay Home Notices.

</div>

Examples:
* `generatePeople 4`
* `generatePeople idp/S456D`

### Clearing all entries : 

Clears all entries from VirusTracker.

Format: `clear`

### Viewing help :

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Exiting the program :

Exits the program.

Format: `exit`


### Saving the data

VirusTracker data saves in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer. Create a folder named `data` in the same location as the app and copy over `personbook.json, locationbook.json and visitbook.json` into that folder. <br>
       Another solution is to use the import and export commands that VirusTracker provides. You may export all entities to 3 csv files and import them within the new VirusTracker.
       Please refer to [adding data for CSV files](#adding-data-from-csv-files) and [exporting data to CSV files](#exporting-data-to-csv-files).
--------------------------------------------------------------------------------------------------------------------
