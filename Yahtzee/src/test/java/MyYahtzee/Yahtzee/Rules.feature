@tag
Feature: Tests for Rules class

  Background: 
    Given For All tests

  #__________________________________________________________________________
  @tag1
  Scenario: Test for rolling function
    Given Roll a Die
    When Check if the result is between 1 and 6
    Then Verify if the result for "rolling function" is correct

  #__________________________________________________________________________
  @tag2
  Scenario: Test for countDice function
    Given Have the Dice with numbers  3, 2, 6, 6, 2
    When Calculate count of numbers
    And Check with the result with 0, 2, 1, 0, 0, 2
    Then Verify if the result for "countDice function" is correct

  #__________________________________________________________________________
  @tag3
  Scenario: Test for ones function
    Given Have the Dice with numbers  1, 4, 6, 1, 1
    When Choose the category number 1 to score
    And Check if the results is 3
    Then Verify if the result for "ones function" is correct

  #__________________________________________________________________________
  @tag4
  Scenario: Test for twos function
    Given Have the Dice with numbers  1, 2, 6, 4, 1
    When Choose the category number 2 to score
    And Check if the results is 2
    Then Verify if the result for "twos function" is correct

  #__________________________________________________________________________
  @tag5
  Scenario: Test for threes function
    Given Have the Dice with numbers  3, 2, 3, 3, 3
    When Choose the category number 3 to score
    And Check if the results is 12
    Then Verify if the result for "threes function" is correct

  #__________________________________________________________________________
  @tag6
  Scenario: Test for fours function
    Given Have the Dice with numbers  4, 2, 4, 3, 3
    When Choose the category number 4 to score
    And Check if the results is 8
    Then Verify if the result for "fours function" is correct

  #__________________________________________________________________________
  @tag7
  Scenario: Test for fives function
    Given Have the Dice with numbers  4, 5, 5, 5, 3
    When Choose the category number 5 to score
    And Check if the results is 15
    Then Verify if the result for "fives function" is correct

  #__________________________________________________________________________
  @tag8
  Scenario: Test for sixes function
    Given Have the Dice with numbers  6, 5, 5, 6, 3
    When Choose the category number 6 to score
    And Check if the results is 12
    Then Verify if the result for "sixes function" is correct

  #__________________________________________________________________________
  @tag9
  Scenario: Test for threeOfAKind function
    Given Have the Dice with numbers  6, 2, 3, 2, 2
    When Choose the category number 10 to score
    And Check if the results is equal to sum
    Then Verify if the result for "threeOfAKind function" is correct

  #__________________________________________________________________________
  @tag10
  Scenario: Test for fourOfAKind function
    Given Have the Dice with numbers  5, 5, 5, 2, 5
    When Choose the category number 11 to score
    And Check if the results is equal to sum
    Then Verify if the result for "fourOfAKind function" is correct

  #__________________________________________________________________________
  @tag11
  Scenario: Test for fullHouse function
    Given Have the Dice with numbers  5, 2, 5, 2, 5
    When Choose the category number 9 to score
    And Check if the results is equal to 25
    Then Verify if the result for "fullHouse function" is correct

  #__________________________________________________________________________
  @tag12
  Scenario: Test for chance function
    Given Have the Dice with numbers  3, 6, 3, 2, 5
    When Choose the category number 12 to score
    And Check if the results is equal to sum
    Then Verify if the result for "chance function" is correct

  #__________________________________________________________________________
  # @tag13
  # Scenario: Test for yahtzee function
  #   Given Have the Dice with numbers  4, 4, 4, 4, 4
  #   When Choose the category number 13 to score
  #   And Check if the results is equal to 50
  #   Then Verify if the result for "yahtzee function" is correct
  #__________________________________________________________________________
  @tag14
  Scenario Outline: Test for smallStraight function
    Given Have the Dice with numbers  <x1>, <x2>, <x3>, <x4>, <x5>
    When Choose the category number <category> to score
    And Check if the results is equal to <score>
    Then Verify if the result for "<function>" is correct

    Examples: 
      | x1 | x2 | x3 | x4 | x5 | category | score | function               |
      |  6 |  2 |  5 |  4 |  3 |        8 |    30 | smallStraight function |
      |  4 |  2 |  5 |  4 |  3 |        8 |    30 | smallStraight function |
      |  4 |  6 |  5 |  4 |  3 |        8 |    30 | smallStraight function |
      |  2 |  2 |  5 |  2 |  3 |        8 |     0 | smallStraight function |
      |  4 |  4 |  4 |  4 |  4 |       13 |    50 | yahtzee function       |
      |  3 |  3 |  3 |  3 |  3 |       13 |    50 | yahtzee function       |
      |  6 |  2 |  5 |  4 |  3 |       13 |     0 | yahtzee function       |
