Scenario Outline: Showing message "no titles" in empty list of titles
Given: No data
When: I get into empty title list
Then: I should get displayed message: "No titles"

Scenario Outline: Unsuccessful adding title with empty title field, only with author and year fields filled
Given: author: "George Orwell", year: "1949"
When: I click "Add new" button and fill author and year fields
Then: I should get message: ""title" field shouldn't be empty..."

Scenario Outline: Unsuccessful adding title with empty author field, only with title and year fields filled
Given: title: "Nineteen Eighty-Four", year: "1949"
When: I click "Add new" button and fill title and year fields
Then: I should get message: ""author" field shouldn't be empty..."

Scenario Outline: Unsuccessful adding title with empty year field, only with title and author fields filled
Given: title: "Nineteen Eighty-Four", author: "George Orwell"
When: I click "Add new" button and fill title and author fields
Then: I should get message: ""year" field shouldn't be empty..."

Scenario Outline: Adding one title, and deleting it after
Given: title: "Nineteen Eighty-Four", author: "George Orwell", year: "1949"
When: I click "Add new" button and fill all fields and after that I click "Remove"
Then: The title should be added correctly, and after that it should be deleted

Scenario Outline: Adding two titles, and deleting first of them after
Given: 1. title: "Nineteen Eighty-Four", author: "George Orwell", year: "1949" 2. title: "The Lord of the Rings", author: "J. R. R. Tolkien", year: "1949"
When: I click "Add new" button and fill all fields, repeat it, and after that I click "Remove"
Then: The titles should be added correctly, and after that first title should be deleted

Scenario Outline: Editing title, and changing "year" field
  Given: title: "The Lord of the Rings", author: "J. R. R. Tolkien", year: "1949"
  When: I click "Edit" button and change year to "1965"
  Then: year field should be changed from "1949" to "1965"

Scenario Outline: Editing title, and changing "title" field
Given: title: "Two towers", author: "J. R. R. Tolkien", year: "1949"
When: I click "Edit" button and change title to "Two towers"
Then: title field should be changed from "The Lord of the Rings" to "Two towers"

Scenario Outline: Editing title, and changing "author" field
Given: title: "Two towers", author: "J. R. R. Tolkien", year: "1949"
When: I click "Edit" button and change author to "unknown"
Then: title field should be changed from "J. R. R. Tolkien" to "unknown"
