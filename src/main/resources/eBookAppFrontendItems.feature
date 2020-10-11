Scenario Outline: Adding one item (copy) to the list of items
Given: year: "2020", month: "July", day: "11"
When: I click "Add copy" button
Then: I should add new copy and it's status should be "Available"

Scenario Outline: Editing item (copy) from list of items
Given: year: "2024", month: "March", day: "13"
When: I click "Edit copy" button
Then: Edited copy adding date would be changed to "2024-03-13"

Scenario Outline: Deleting item (copy) from list of items
Given: one item (copy) in the list of items
When: I click "Remove" button
Then: I should get message "No copies..."

Scenario Outline: Adding two items (copies) to the list of items
Given: year: "2023", month: "July", day: "11", year: "2021", month: "December", day: "9"
When: I click "Add copy" button after setting date of adding (twice)
Then: I should add two copies and it's status should be "Available"

Scenario Outline: Deleting two items (copies) from list of items
Given: two items (copies) in the list of items
When: I click "Remove" button in both cases
Then: I should get message "No copies..."

Scenario Outline: Adding one item (copy) to the list of items and then getting back to titles list
Given: year: "2021", month: "December", day: "11"
When: I click "Add copy" button, and after that "Return" button
Then: I should get back to titles list and it would not be empty