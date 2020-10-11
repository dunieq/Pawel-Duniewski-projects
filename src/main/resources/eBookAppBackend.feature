Scenario Outline: Successful registration attempt
Given: setting login: newGuy and password: rubberduck
When: sending POST request to /user/register
Then: I should get user ID: 421, and confirmation that the user is new - "true"

Scenario Outline: Unsuccessful registration attempt
Given: setting login: newGuy and password: rubberduck (both used before)
When: sending POST request to /user/register
Then: I should get back user ID: 421, and denied that the user is new - "false"

Scenario Outline: Trying to log in when user is registered
Given: login: newGuy and password: rubberduck
When: sending POST request to /user/login
Then: I should get user ID: 421

Scenario Outline: Trying to log in when user is registered, with improper login and proper password
Given: login: oldGuy, password: rubberduck
When: sending POST request to /user/login
Then: I should get "-1"

Scenario Outline: Trying to log in when user is registered, with proper login and improper password
Given: login: newGuy, password: woodenduck
When: sending POST request to /user/login
Then: I should get "-1"

Scenario Outline: Trying to log in when user is registered, with both improper login and password
Given: login: oldGuy, password: woodenduck
When: sending POST request to /user/login
Then: I should get "-1"

Scenario Outline: Successful title creation
Given: user ID: 421, setting author: Aristotle, title: Politics, year: 2020
When: sending POST request to /titles
Then: I should get title ID: 422

Scenario Outline: Unsuccessful title creating when user ID is improper
Given: user ID: 4210, setting author: Aristotle, title: Politics, year: 2020
When: sending POST request to /titles
Then: I should get message "User 4210 doesn't exist."

Scenario Outline: Successful title displaying
Given: user ID 421
When: sending GET request to titles/?userId=421
Then: I should get title ID: 422, author: Aristotle, title: Politics, year: 2020

Scenario Outline: Successful title update
Given: user ID 421, title ID: 422, author: Arystoteles, title: Polityka, year: 2019
When: sending PUT request to titles/?userId=421
Then: changes take effect, I should get title ID: 422, author: Arystoteles, title: Politityka, year: 2019

Scenario Outline: Unsuccessful title update with improper user ID and proper title ID
Given: user ID 4210, title ID: 422, author: Arystoteles, title: Politityka, year: 2019
When: sending PUT request to titles/?userId=421
Then: I should get message "User 4210 doesn't exist."

Scenario Outline: Unsuccessful title update with proper user ID and improper title ID
Given: user ID 421, title ID: 4220, author: Arystoteles, title: Politityka, year: 2019
When: sending PUT request to titles/?userId=421
Then: I should get message "There is no book with id=4220 created by user with id=421"

Scenario Outline: Unsuccessful title update with both improper user ID and title ID
Given: user ID 4210, title ID: 4220, author: Arystoteles, title: Politityka, year: 2019
When: sending PUT request to titles/?userId=421
Then: I should get message "User 4210 doesn't exist."

Scenario Outline: Unsuccessful deleting title with improper user ID and proper title ID
Given: user ID: 4210, title ID: 422
When: sending DELETE request to titles/?userId=421
Then: I should get "false"

Scenario Outline: Unsuccessful deleting title with proper user ID and improper title ID
Given: user ID: 421, title ID: 4220
When: sending DELETE request to titles/?userId=421
Then: I should get "false"

Scenario Outline: Unsuccessful deleting title with both improper user ID and title ID
Given: user ID: 4210, title ID: 4220
When: sending DELETE request to titles/?userId=421
Then: I should get "false"

Scenario Outline: Successful deleting title
Given: user ID 421, title ID: 422
When: sending DELETE request to titles/?userId=421
Then: I should get "true"

Scenario Outline: Successful item creation
Given: user ID 421, title ID: 422, setting setting date of purchase: 2020-06-23
When: sending POST request to items/?userId=421&titleId=422
Then: I should get item ID: 427, purchase date: 2020-06-23, status: AVAILABLE

Scenario Outline: Successful item display
Given: user ID 421, title ID: 422
When: sending GET request to items/?userId=421&titleId=422
Then: I should get item ID: 427, purchase date": 2020-06-23, status: AVAILABLE

Scenario Outline: Unsuccessful item creation with improper user ID and proper title ID
Given: user ID 4210, title ID: 422, setting date of purchase: 2020-06-23
When: sending POST request to items/?userId=421&titleId=422
Then: I should get message "User 4210 doesn't exist."

Scenario Outline: Unsuccessful item creation with proper user ID and improper title ID
Given: user ID 421, title ID: 4220, setting date of purchase: 2020-06-23
When: sending POST request to items/?userId=421&titleId=422
Then: I should get message "There is no book with id=4220 created by user with id=421"

Scenario Outline: Unsuccessful item creation with both improper user ID and title ID
Given: user ID 4210, title ID: 4220, , setting date of purchase: 2020-06-23
When: sending POST request to items/?userId=421&titleId=422
Then: I should get message "User 4210 doesn't exist."

Scenario Outline: Successful item update
Given: user ID 421, item ID: 427, setting new purchase date: 2020-07-11
When: sending PUT request to items/?userId=421&id=427
Then: changes take effect, i should get item ID: 427, purchase date: 2020-07-11

Scenario Outline: Unsuccessful item update with improper user ID and proper item ID
Given: user ID 4210, item ID: 427, purchaseDate: 2020-07-11
When: sending PUT request to items/?userId=421&id=427
Then: I should get message "User 4210 doesn't exist."

Scenario Outline: Unsuccessful item update with proper user ID and improper item ID
Given: user ID 421, item ID: 4270, purchaseDate: 2020-07-11
When: sending PUT request to titles/?userId=421
Then: I should get message "There is no item id=4270 for user id=421"

Scenario Outline: Unsuccessful item update with both improper user ID and item ID
Given: user ID 4210, item ID: 4270, purchaseDate: 2020-07-11
When: sending PUT request to items/?userId=421&id=427
Then: I should get message "User 4210 doesn't exist."

Scenario Outline: Unsuccessful deleting item with improper user ID and proper item ID
Given: user ID: 4210, item ID: 427
When: sending DELETE request to items/?userId=421&id=427
Then: I should get message "User 4210 doesn't exist."

Scenario Outline: Unsuccessful deleting item with proper user ID and improper item ID
Given: user ID: 421, item ID: 4270
When: sending DELETE request to items/?userId=421&id=427
Then: I should message "There is no item id=4270 for user id=421"

Scenario Outline: Unsuccessful deleting item with both improper user ID and item ID
Given: user ID: 4210, item ID: 4270
When: sending DELETE request to items/?userId=421&id=427
Then: I should get message "User 4210 doesn't exist."

Scenario Outline: Successful deleting item
Given: user ID 421, item ID: 427
When: sending DELETE request to items/?userId=421&id=427
Then: I should get "true"

Scenario Outline: Successful rent creation
Given: userId: 421, itemId: 427, setting customerName: Zbyszko z Bogdańca, rentDate: 2020-08-26, expirationDate: 2020-09-02
When: sending POST request to rents/?userId=421&itemId=427
Then: I should get rent ID: 428, customerName: Zbyszko z Bogdańca, rentDate: 2020-08-26, expirationDate: 2020-09-02

Scenario Outline: Unsuccessful rent creation with improper user ID and proper item ID
Given: user ID 4210, item ID: 427, 427, setting customerName: Zbyszko z Bogdańca, rentDate: 2020-08-26, expirationDate: 2020-09-02
When: sending POST request to rents/?userId=421&itemId=427
Then: I should get message "User 4210 doesn't exist."

Scenario Outline: Unsuccessful rent creation with proper user ID and improper item ID
Given: user ID 421, item ID: 4270, 427, setting customerName: Zbyszko z Bogdańca, rentDate: 2020-08-26, expirationDate: 2020-09-02
When: sending POST request to rents/?userId=421&itemId=427
Then: I should get message  "There is no item id=4270 for user id=421"

Scenario Outline: Unsuccessful rent creation with both improper user ID and item ID
Given: user ID 4210, item ID: 4270, setting customerName: Zbyszko z Bogdańca, rentDate: 2020-08-26, expirationDate: 2020-09-02
When: sending POST request to rents/?userId=421&itemId=427
Then: I should get message  "User 4210 doesn't exist."

Scenario Outline: Successful rent display
Given: user ID 421, item ID: 427
When: sending GET request to rents/?userId=421&itemId=427
Then: I should get rent ID: 428, customerName: Zbyszko z Bogdańca, rentDate: 2020-08-26, expirationDate: 2020-09-02

Scenario Outline: Successful rent update
Given: userId: 421, rentId: 428, setting customerName: Zbyszko z Bogdańca, rentDate: 2020-08-26, expirationDate: 2020-09-12
When: sending PUT request to rents/?userId=421&itemId=427
Then: I should get rent ID: 428, customerName: Zbyszko z Bogdańca, rentDate: 2020-08-26, expirationDate: 2020-09-12

Scenario Outline: Unsuccessful rent update with improper user ID and proper rent ID
Given: userId: 4210, rentId: 428, setting customerName: Zbyszko z Bogdańca, rentDate: 2020-08-26, expirationDate: 2020-09-12
When: sending PUT request to rents/?userId=421&itemId=427
Then: I should get message  "User 4210 doesn't exist."

Scenario Outline: Unsuccessful rent update with proper user ID and improper rent ID
Given: userId: 421, rentId: 4280, setting customerName: Zbyszko z Bogdańca, rentDate: 2020-08-26, expirationDate: 2020-09-12
When: sending PUT request to rents/?userId=421&itemId=427
Then: I should get message "Rent id=4280 for user id=421 doesn't exist."

Scenario Outline: Unsuccessful rent update with both improper user ID rent ID
Given: userId: 4210, rentId: 4280, setting customerName: Zbyszko z Bogdańca, rentDate: 2020-08-26, expirationDate: 2020-09-12
When: sending PUT request to rents/?userId=421&itemId=427
Then: I should get message  "User 4210 doesn't exist."

Scenario Outline: Unsuccessful deleting rent with proper user ID and improper rent ID
Given: user ID: 421, item ID: 4280
When: sending DELETE request to rents/?userId=421&id=428
Then: I should get “false”

Scenario Outline: Unsuccessful deleting rent with improper user ID and proper rent ID
Given: user ID: 4210, rent ID: 428
When: sending DELETE request to rents/?userId=421&id=428
Then: I should get message "User 4210 doesn't exist."

Scenario Outline: Unsuccessful deleting rent with both improper user ID and rent ID
Given: user ID: 4210, rent ID: 428
When: sending DELETE request to rents/?userId=421&id=428
Then: I should get message "User 4210 doesn't exist."

Scenario Outline: Successful deleting rent
Given: user ID 421, rent ID: 428
When: sending DELETE request to rents/?userId=421&id=428
Then: I should get "true"