Feature: Logout

Scenario Outline: Logout from employee (positive)
Given I am at the employee page
When I click the logout button
Then I return to the login page

Examples:
| username | password |
| "JohnDoe"| "password" |
| "MikeDingeldein" | "password" |

Scenario Outline: Logout from Manager (positive test)
Given I am at the manager page
When I click the manager page logout button
Then I return to the login page

Examples:
| username | password |
| "JaneDoe"| "password" |
| "MikesWallet" | "password" |