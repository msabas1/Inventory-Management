@items-page
Feature: Items Page

    Scenario: Navigate to Add Item Page
        Given I am on the Items page
        When I click on the add item button
        Then I can see the add item form modal

    Scenario: See all expected displays on the Items Page when values exist
        Given I am on the Items page
        Then I can see the Add Item button
        And I can see the Items Sort By dropdown
        And I can see the Items table

    Scenario Outline: Sort By item dropdown filters by selection
        Given I am on the Items page
        When I select the Items Sort by dropdown option with text "<sortOption>"
        Then I can see the item list ordered by "<sortOption>"

        Examples:
            | sortOption |
            | Name       |
            | Price      |
            | Quantity   |