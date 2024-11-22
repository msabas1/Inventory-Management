@warehouses-accessibility
Feature: Warehouses Accessibility

  Scenario: Warehouse Sort Dropdowns are tab-accessible
    Given I am on the Warehouses page
    When I tab to the Warehouses Sort dropdown
    Then the warehouses sort dropdown will be focused

  Scenario: Warehouse Sort Dropdown items are navigable via the up and down arrow keys
    Given I am on the Warehouses page
    And I am on the Warehouses Sort dropdown
    When I press the arrow key down two times and up one time on the Warehouses Sort dropdown
    Then I should see the second item in the Warehouses Sort dropdown highlighted
    And I can see the warehouse list ordered by "Name"

  Scenario: Navigate by keyboard to Add Warehouse
    Given I am on the Warehouses page
    When I tab to click the Add Warehouses button
    Then I can see the add warehouse form modal

  Scenario: Navigate by keyboard to Get Warehouse
    Given I am on the Warehouses page
    When I tab to Navigate to a warehouse
    Then I can see the Update Warehouse button