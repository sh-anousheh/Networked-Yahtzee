@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario Outline: Testcases for tackling a  3-player game from beginning to end
    Given Start the Game
    And Three players with the names: Anousheh, Ryan, and Brayana join the game
    When They finish the game
    And Check if the <Num>th player was "<PlayerName>" in the <Round> and chose the "<Category>"
    Then Check the outcomes

    Examples: 
      | Num | PlayerName | Round | Category |
      |  01 | Anousheh   |     1 | Yahtzee  |
#   |     | Ryan       |     1 |          |
#   |     | Brayana    |     1 |          |
#   |     | Anousheh   |     2 |          |
#   |     | Ryan       |     2 |          |
#   |     | Brayana    |     2 |          |
#   |     | Anousheh   |     3 |          |
#   |     | Ryan       |     3 |          |
#   |     | Brayana    |     3 |          |
#   |     | Anousheh   |     4 |          |
#   |     | Ryan       |     4 |          |
#   |     | Brayana    |     4 |          |
#   |     | Anousheh   |     5 |          |
#   |     | Ryan       |     5 |          |
#   |     | Brayana    |     5 |          |
#   |     | Anousheh   |     6 |          |
#   |     | Ryan       |     6 |          |
#   |     | Brayana    |     6 |          |
#   |     | Anousheh   |     7 |          |
#   |     | Ryan       |     7 |          |
#   |     | Brayana    |     7 |          |
#   |     | Anousheh   |     8 |          |
#   |     | Ryan       |     8 |          |
#   |     | Brayana    |     8 |          |
#   |     | Anousheh   |     9 |          |
#   |     | Ryan       |     9 |          |
#   |     | Brayana    |     9 |          |
#   |     | Anousheh   |    10 |          |
#   |     | Ryan       |    10 |          |
#   |     | Brayana    |    10 |          |
#   |     | Anousheh   |    11 |          |
#   |     | Ryan       |    11 |          |
#   |     | Brayana    |    11 |          |
#   |     | Anousheh   |    12 |          |
#   |     | Ryan       |    12 |          |
#   |     | Brayana    |    12 |          |
#   |     | Anousheh   |    13 |          |
#   |     | Ryan       |    13 |          |
#   |     | Brayana    |    13 |          |
#
