Scenario Outline: Showing message "no rents" in empty list of rents
Given: No data
When: I get into empty rents list
Then: I should get displayed message: "No rents"

Scenario Outline: Adding rent without filling "Customer name" field
Given: clicked "Rent this copy" button, customer name: none
When: I click "Add copy" button
Then: I should get displayed message: ""customerName" field shouldn't be empty..."

Scenario Outline: Adding rent without filling "Customer name" field
Given: clicked "Rent this copy" button, customer name: none
When: I click "Add copy" button
Then: I should get displayed message: ""customerName" field shouldn't be empty..."

Scenario Outline: Adding rent
Given: clicked "Rent this copy" button, customer name: "Steve"
When: I click "Add copy" button
Then: new rent should be added correctly

Scenario Outline: Deleting rent
Given: one rent in the rents list
When: I click "Remove" button
Then: I should get displayed message: "No rents"

Scenario Outline: Adding two rents to the list of rents
Given: clicked "Rent this copy" button, customer name: "Steve" (first rent), customer name: "Mike" (second rent)
When: I click "Add copy" button after filling customer name field (twice)
Then: I should add two rents correctly

Scenario Outline: Deleting one of two existing rents
Given: two rents in the rents list
When: I click "Remove" button
Then: One rent should be deleted, second should stay

Scenario Outline: Editing rent: change rent date, expiration date, and customer name
Given: customer name: "Mike", automatically set rent date, automatically set expiration date
When: I click "Edit" button and change customer name to "Barbara", rent date to "2022-07-12" and expiration date to "2021-08-15"
Then: I should successfully edit rent and set new correctly entered data

