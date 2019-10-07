@tag
Feature: Tests for Rules class

  Background: 
    Given For All tests

  #__________________________________________________________________________
  @tag1
  Scenario: Test for rolling function
    Given Define default answer
    When Roll a Die
    Then Check if the result is between 1 and 6

  #__________________________________________________________________________
  @tag2
  Scenario: Test for countDice function
    Given Roll the Dice
    When Calculate count of numbers
    And Check with the result with 0, 2, 1, 0, 0, 2
    Then I verify if the result is correct

  #__________________________________________________________________________
  @tag3
  Scenario: Test for ones function
    Given Have the Dice with numbers  1, 4, 6, 1, 1
    When Choose the category number 1 to score
    Then Check if the results is 3

  #__________________________________________________________________________
  @tag4
  Scenario: Test for twos function
    Given Have the Dice with numbers  1, 2, 6, 4, 1
    When Choose the category number 2 to score
    Then Check if the results is 2

  #__________________________________________________________________________
  @tag5
  Scenario: Test for threes function
    Given Have the Dice with numbers  3, 2, 3, 3, 3
    When Choose the category number 3 to score
    Then Check if the results is 12

  #__________________________________________________________________________
  @tag6
  Scenario: Test for fours function
    Given Have the Dice with numbers  4, 2, 4, 3, 3
    When Choose the category number 4 to score
    Then Check if the results is 8

  #__________________________________________________________________________
  @tag7
  Scenario: Test for fives function
    Given Have the Dice with numbers  4, 5, 5, 5, 3
    When Choose the category number 5 to score
    Then Check if the results is 15
