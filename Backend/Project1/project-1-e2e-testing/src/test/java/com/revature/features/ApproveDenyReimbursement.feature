Feature: Add Reimbursement

  Scenario: Approve reimbursement
    Given I am at the manager page with a reimbursement to approve/deny
    When I click approve for a reimbursement
    Then the reimbursement is approved

  Scenario: Deny reimbursement
    Given I am at the manager page with a reimbursement to approve/deny
    When I click deny for a reimbursement
    Then the reimbursement is denied
