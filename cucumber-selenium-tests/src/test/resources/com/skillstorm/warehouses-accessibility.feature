@warehouses-accessibility
Feature: Warehouses Accessibility

  Scenario: Dropdowns are tab-accessible
    Given I am on the Warehouses page
    When I tab to the Warehouses Sort dropdown
    Then the warehouses sort dropdown will be focused

  Scenario: Dropdown items are navigable via the down arrow key
    Given I am on the Warehouses page
    And I am on the Warehouses Sort dropdown
    When I press the arrow key down one time on the Warehouses Sort dropdown
    Then I should see the second item in the Warehouses Sort dropdown highlighted
    And I can see the warehouse list ordered by "Name"