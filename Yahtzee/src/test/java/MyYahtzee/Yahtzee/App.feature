@tag
Feature: Tests for App class

  Background: 
    Given Define the default return value
    Then Validate the outcomes

  @tag1
  Scenario Outline: Test for re-roll the Dice
    Given The Dice  <d1>, <d2>, <d3>, <d4>, <d5>
    And The dice positions to hold are "<holds>"
    When Re-rolle the dice
    And Check if the new Dice contains "<choosen dice>"

    Examples: 
      | d1 | d2 | d3 | d4 | d5 | holds     | choosen dice |
      |  5 |  3 |  6 |  1 |  1 |           |              |
      |  6 |  1 |  5 |  2 |  4 | 3 4 5     | 5 2 4        |
      |  3 |  1 |  1 |  2 |  4 | 1 2 3 4 5 | 3 1 1 2 4    |
