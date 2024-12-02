@items-accessibility
Feature: Items Accessibility

    Scenario: Item Sort Dropdowns are tab-accessible
        Given I am on the Items page
        When I tab to the Items Sort dropdown
        Then the items sort dropdown will be focused

    Scenario: Item Sort Dropdown items are navigable via the up and down arrow keys
        Given I am on the Items page
        And I am on the Items Sort dropdown
        When I press the arrow key down two times and up one time on the Items Sort dropdown
        Then I should see the second item in the Items Sort dropdown highlighted
        And I can see the item list ordered by "Price"

    Scenario: Navigate by keyboard to Add Item
        Given I am on the Items page
        When I tab to click the Add Items button
        Then I can see the add item form modal

    Scenario: Navigate by keyboard to Get Item
        Given I am on the Items page
        When I tab to Navigate to a item
        Then I can see the Update Item button

    Scenario: Add Item by keyboard
        Given I am on the Items page
        When I tab to click the Add Items button
        And I fill out the Add Item form using only the keyboard
        And I submit the filled out Add Item form using only the keyboard
        Then I can see the newly added item in the items list