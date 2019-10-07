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
#-------------------------------------------
