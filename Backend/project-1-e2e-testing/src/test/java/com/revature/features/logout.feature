Feature: Logout

Scenario Outline: Logout from employee (positive)
Given I am at the login page
When I type in an employee username of <username>
And I type in the employee password  <password>
And I click the Login button
And I am sent to the employee page
And I click the logout button
Then I return to the login page

Examples:
| username | password |
| "JohnDoe"| "password" |
| "MikeDingeldein" | "password" |

Scenario Outline: Logout from Manager (positive test)
Given I am at the login page
When I type in an manager username of <username>
And I type in the manager password  <password>
And I click the Login button
And I am be sent to the manager page
And I click the manager page logout button
Then I return to the login page

Examples:
| username | password |
| "JaneDoe"| "password" |
| "MikesWallet" | "password" |