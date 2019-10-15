@tag
Feature: Tests for App class

  Background: 
    Given Define the default return value

  @tag1
  Scenario Outline: Test for Hold the Dice
    Given The Dice  <d1>, <d2>, <d3>, <d4>, <d5>
    And The dice positions to hold are "<holds>"
    When Re-rolle the dice
    And Check if the new Dice contains "<choosen dice>"
    Then Validate the outcomes

    Examples: 
      | d1 | d2 | d3 | d4 | d5 | holds     | choosen dice |
      |  5 |  3 |  6 |  1 |  1 |           |              |
      |  6 |  1 |  5 |  2 |  4 | 3 4 5     | 5 2 4        |
      |  3 |  1 |  1 |  2 |  4 | 1 2 3 4 5 | 3 1 1 2 4    |

  #_____________________________________________________________________
  @tag2
  Scenario Outline: Tests for scoring after the Re-rolls in the Chance category
    Given A player start the game
    And Choose the "<actions>" to perform respectively
    When The dice positions to hold for the fist and second holds are "<hold1>" and "<hold2>" respectively
    And Check if the the number of holds that have been make is correct
    And Scoring in the chance catetory is have been made or not
    Then Check if the "<acceptence>" of the test
    And Check the Correctness

    Examples: 
      | actions   | hold1 | hold2 | acceptence  |
      |         3 |       |       | Accepted    |
      |       1,3 | 3 5   |       | Accepted    |
      |       2,3 |       |       | Accepted    |
      |     2,1,3 | 1 3 4 |       | Accepted    |
      |     2,2,3 |       |       | Accepted    |
      |     1,1,3 | 2 4 5 | 1 3   | Accepted    |
      | 2,2,1,1,3 |     1 | 1 3   | Notaccepted |
