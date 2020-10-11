Scenario Outline: Successful redirection to the registration page
Given: main page
When: I click "Sign Up"
Then: I should be redirected to the registration page

Scenario Outline: Unsuccessful registration attempt when using existing user's data
Given: login: "newUser", password: "newPassword"
When: I click "Sign Up"
Then: I should get message: "This user already exist!"

Scenario Outline: Unsuccessful registration attempt with empty login field, only with password and password confirmation fields completed
Given: password: "newPassword", password confirmation: "newPassword"
When: I click "Sign Up"
Then: I should get message: "You can't leave fields empty"

Scenario Outline: Unsuccessful registration attempt with empty password field, only with login and password confirmation fields completed
Given: login: "newUser", password confirmation: "newPassword"
When: I click "Sign Up"
Then: I should get message: "You can't leave fields empty"

Scenario Outline: Unsuccessful registration attempt with empty password confirmation field, only with login and password fields completed
Given: login: "newUser", password: "newPassword"
When: I click "Sign Up"
Then: I should get message: "You can't leave fields empty"

Scenario Outline: Unsuccessful registration attempt with all empty fields
Given: none
When: I click "Sign Up"
Then: I should get message: "You can't leave fields empty"

Scenario Outline: Successful registration of new user
Given: login: "newUser", password: "newPassword",
When: I click "Sign Up"
Then: I should get message: "You have been successfully registered!"