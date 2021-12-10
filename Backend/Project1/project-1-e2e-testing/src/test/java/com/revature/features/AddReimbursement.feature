Feature: Add Reimbursement

  Scenario: Add Reimbursement (positive)
    Given I am at the employee reimbursement page
    And I type reimbursement amount of "25.35"
    And I type in a reimbursement type of "FOOD"
    And I type in a reimbursement description of "E2E test"
    And I choose a reimbursement receipt file
    And I click the Add Reimbursement Button
    Then I should see the added reimbursement

  Scenario Outline: Add Reimbursement with wrong type (Negative)
    Given I am at the employee reimbursement page
    And I type reimbursement amount of <amount>
    And I type in a reimbursement type of <type>
    And I type in a reimbursement description of "E2E test"
    And I choose a reimbursement receipt file
    And I click the Add Reimbursement Button
    Then I should be see an error message of <message>

    Examples: 
      | amount   | type      | message                                          |
      | "25.98"  | "food"    | "You have entered an invalid reimbursement type" |
      | "25.98"  | "vjhv"    | "You have entered an invalid reimbursement type" |
      | "-25.98" | "FOOD"    | "Reimbursement amount cannot be less than 0"     |
      | "-5.00"  | "LODGING" | "Reimbursement amount cannot be less than 0"     |
