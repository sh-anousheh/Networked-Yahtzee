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
      | x1 | x2 | x3 | x4 | x5 | category | score | 
      |  1 |  4 |  6 |  1 |  1 |        1 |     3 | 
      |  2 |  5 |  5 |  2 |  3 |        1 |     0 | 
      |  1 |  2 |  6 |  4 |  1 |        2 |     2 | 
      |  1 |  4 |  6 |  4 |  1 |        2 |     0 | 
      |  3 |  2 |  3 |  3 |  3 |        3 |    12 | 
      |  5 |  2 |  1 |  1 |  4 |        3 |     0 | 
      |  4 |  2 |  4 |  4 |  1 |        4 |    12 | 
      |  6 |  2 |  5 |  2 |  1 |        4 |     0 | 
      |  4 |  5 |  5 |  5 |  3 |        5 |    15 | 
      |  4 |  1 |  1 |  2 |  3 |        5 |     0 | 
      |  6 |  5 |  5 |  6 |  3 |        6 |    12 | 
      |  1 |  5 |  5 |  2 |  3 |        6 |     0 | 
      |  4 |  2 |  1 |  5 |  3 |        7 |    40 | 
      |  6 |  5 |  4 |  2 |  3 |        7 |    40 | 
      |  6 |  5 |  3 |  2 |  3 |        7 |     0 | 
      |  4 |  2 |  5 |  4 |  3 |        8 |    30 | 
      |  2 |  2 |  5 |  2 |  3 |        8 |     0 | 
      |  5 |  2 |  5 |  2 |  5 |        9 |    25 | 
      |  3 |  3 |  2 |  4 |  4 |        9 |     0 | 
      |  6 |  2 |  3 |  2 |  2 |       10 |    15 | 
      |  2 |  6 |  4 |  3 |  5 |       10 |     0 | 
      |  5 |  5 |  5 |  2 |  5 |       11 |    22 | 
      |  4 |  3 |  1 |  3 |  2 |       11 |     0 | 
      |  3 |  6 |  3 |  2 |  5 |       12 |    19 | 
      |  4 |  4 |  4 |  4 |  4 |       13 |    50 | 
      |  6 |  2 |  5 |  4 |  3 |       13 |     0 | 

  #__________________________________________________________________________
  @tag4
  Scenario Outline: Test for calculateBonus function
    Given Get the scores of the upper section <Aces>, <Twos>, <Threes>, <Fours>, <Fives>, <Sixes>
    When Check if there is a <bonus>

    Examples: 
      | Aces | Twos | Threes | Fours | Fives | Sixes | bonus |
      |    3 |    8 |      3 |    16 |    15 |    30 |    35 |
      |    3 |    2 |      6 |    12 |    10 |     6 |     0 |
