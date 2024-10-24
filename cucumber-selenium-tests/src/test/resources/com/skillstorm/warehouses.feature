@warehouses-page
Feature: Warehouses Page

  Scenario: Navigate to Add Warehouse Page
    Given I am on the Warehouses page
    When I click on the add warehouse button
    Then I can see the add warehouse form modal

  Scenario: See all expected displays on the Warehouses Page when values exist
    Given I am on the Warehouses page
    Then I can see the Add Warehouse button
    And I can see the Sort By dropdown
    And I can see the Warehouses table

  Scenario Outline: Sort By dropdown filters by selection
    Given I am on the Warehouses page
    When I select the Sort by dropdown option with text "<sortOption>"
    Then I can see the warehouse list ordered by "<sortOption>"

    Examples:
      | sortOption |
      | ID         |
      | Name       |
      | Capacity   |