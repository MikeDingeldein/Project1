Feature: Login

Scenario Outline: Valid password, valid employee username (positive test)
Given I am at the login page
When I type in an employee username of <username>
And I type in the employee password  <password>
And I click the Login button
Then I should be sent to the employee page

Examples:
| username | password |
| "JohnDoe"| "password" |
| "MikeDingeldein" | "password" |

Scenario: Valid password, valid manager username (positive test)
Given I am at the login page
When I type in an manager username of <username>
And I type in the manager password  <password>
And I click the Login button
Then I should be sent to the manager page

Examples:
| username | password |
| "JaneDoe"| "password" |
| "MikesWallet" | "password" |


Scenario: Valid password, invalid employee username (negative test)
Given I am at the login page
When I type in an employee username of "vhyibv";
And I type in the employee password  "password"
And I click the Login button
Then I should be see a message of "Incorrect username and/or password"

Scenario: Invalid password, invalid employee username (negative test)
Given I am at the login page
When I type in an employee username of "vhyibv";
And I type in the employee password  "bkjbh"
And I click the Login button
Then I should be see a message of "Incorrect username and/or password"

Scenario: Invalid password, valid employee username (negative test)
Given I am at the login page
When I type in an employee username of "MikeDingeldein";
And I type in the employee password  "bkjbh"
And I click the Login button
Then I should be see a message of "Incorrect username and/or password"

Scenario: Valid password, invalid manager username (negative test)
Given I am at the login page
When I type in an manager username of "bvhjbg"
And I type in the manager password  "password"
And I click the Login button
Then I should be see a message of "Incorrect username and/or password"

Scenario: Invalid password, invalid manager username (negative test)
Given I am at the login page
When I type in an manager username of "bvhjbg"
And I type in the manager password  "vhjvh"
And I click the Login button
Then I should be see a message of "Incorrect username and/or password"

Scenario: Invalid password, valid manager username (negative test)
Given I am at the login page
When I type in an manager username of "MikesWallet"
And I type in the manager password  "vhjvh"
And I click the Login button
Then I should be see a message of "Incorrect username and/or password"
