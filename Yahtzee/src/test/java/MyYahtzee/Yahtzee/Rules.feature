@tag
Feature: Tests for Rules class

  Background: 
    Given The default return value
    Then Verify if the result is correct

  #__________________________________________________________________________
  @tag1
  Scenario: Test for rolling function
    Given Roll a Die
    When Check if the result is between 1 and 6

  #__________________________________________________________________________
  @tag2
  Scenario: Test for countDice function
    Given Have the Dice with numbers  3, 2, 6, 6, 2
    When Calculate count of numbers
    And Check with the result with 0, 2, 1, 0, 0, 2

  #__________________________________________________________________________
  @tag3
  Scenario Outline: Test for functions to scoring the categories
    Given Have the Dice with numbers  <x1>, <x2>, <x3>, <x4>, <x5>
    When Choose the category number <category> to score
    And Check if the results is <score>

    Examples: 
      | x1 | x2 | x3 | x4 | x5 | category | score | #function               |
      |  1 |  4 |  6 |  1 |  1 |        1 |     3 | #ones function          |
      |  2 |  5 |  5 |  2 |  3 |        1 |     0 | #ones function          |
      |  1 |  2 |  6 |  4 |  1 |        2 |     2 | #twos function          |
      |  1 |  4 |  6 |  4 |  1 |        2 |     0 | #twos function          |
      |  3 |  2 |  3 |  3 |  3 |        3 |    12 | #threes function        |
      |  5 |  2 |  1 |  1 |  4 |        3 |     0 | #threes function        |
      |  4 |  2 |  4 |  4 |  1 |        4 |    12 | #fours function         |
      |  6 |  2 |  5 |  2 |  1 |        4 |     0 | #fours function         |
      |  4 |  5 |  5 |  5 |  3 |        5 |    15 | #fives function         |
      |  4 |  1 |  1 |  2 |  3 |        5 |     0 | #fives function         |
      |  6 |  5 |  5 |  6 |  3 |        6 |    12 | #sixes function         |
      |  1 |  5 |  5 |  2 |  3 |        6 |     0 | #sixes function         |
      |  4 |  2 |  1 |  5 |  3 |        7 |    40 | #largeStraight          |
      |  6 |  5 |  4 |  2 |  3 |        7 |    40 | #largeStraight          |
      |  6 |  5 |  3 |  2 |  3 |        7 |     0 | #largeStraight          |
      |  4 |  2 |  5 |  4 |  3 |        8 |    30 | #smallStraight function |
      |  2 |  2 |  5 |  2 |  3 |        8 |     0 | #smallStraight function |
      |  5 |  2 |  5 |  2 |  5 |        9 |    25 | #fullHouse function     |
      |  3 |  3 |  2 |  4 |  4 |        9 |     0 | #fullHouse function     |
      |  6 |  2 |  3 |  2 |  2 |       10 |    15 | #threeOfAKind function  |
      |  2 |  6 |  4 |  3 |  5 |       10 |     0 | #threeOfAKind function  |
      |  5 |  5 |  5 |  2 |  5 |       11 |    22 | #fourOfAKind function   |
      |  4 |  3 |  1 |  3 |  2 |       11 |     0 | #fourOfAKind function   |
      |  3 |  6 |  3 |  2 |  5 |       12 |    19 | #chance function        |
      |  4 |  4 |  4 |  4 |  4 |       13 |    50 | #yahtzee function       |
      |  6 |  2 |  5 |  4 |  3 |       13 |     0 | #yahtzee function       |

  #__________________________________________________________________________
  @tag4
  Scenario Outline: Test for calculateBonus function
    Given Get the scores of the upper section <Aces>, <Twos>, <Threes>, <Fours>, <Fives>, <Sixes>
    When Check if there is a <bonus>

    Examples: 
      | Aces | Twos | Threes | Fours | Fives | Sixes | bonus |
      |    3 |    8 |      3 |    16 |    15 |    30 |    35 |
      |    3 |    2 |      6 |    12 |    10 |     6 |     0 |
