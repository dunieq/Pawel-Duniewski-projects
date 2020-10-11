Scenario Outline: Unsuccessful attempt of logging in with empty login and password fields
Given: login: none, password: none
When: I click "Log in"
Then: I should get message: "You can't leave fields empty"

Scenario Outline: Unsuccessful attempt of logging in with empty login field and filled password field
Given: login: none, password: newPassword
When: I click "Log in"
Then: I should get message: "You can't leave fields empty"

Scenario Outline: Unsuccessful attempt of logging in with filled login field and empty password field
Given: login: "John", password: none
When: I click "Log in"
Then: I should get message: "You can't leave fields empty"

Scenario Outline: Unsuccessful attempt of logging in with incorrect login and correct password
Given: login: "Mary", password: "newPassword"
When: I click "Log in"
Then: I should get message: "Login failed"

Scenario Outline: Unsuccessful attempt of logging in with correct login and incorrect password
Given: login: "John", password: "oldPassword"
When: I click "Log in"
Then: I should get message: "Login failed"

Scenario Outline: Unsuccessful attempt of logging in with incorrect login and password
Given: login: "Mary", password: "oldPassword"
When: I click "Log in"
Then: I should get message: "Login failed"

Scenario Outline: Successful login of registered user
Given: login: "John", password: "newPassword",
When: I click "Log in"
Then: I should be transferred to titles page