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
      | Num | PlayerName | Round | Category      |
      |   1 | Anousheh   |     1 | Yahtzee       |
      |   2 | Ryan       |     1 | LargeStraight |
      |   3 | Brayana    |     1 | FullHouse     |
      |   4 | Anousheh   |     2 | Sixes         |
      |   5 | Ryan       |     2 | Fours         |
      |   6 | Brayana    |     2 | Yahtzee       |
      |   7 | Anousheh   |     3 | SmallStraight |
      |   8 | Ryan       |     3 | Threes        |
      |   9 | Brayana    |     3 | Fives         |
      |  10 | Anousheh   |     4 | Threes        |
      |  11 | Ryan       |     4 | Chance        |
      |  12 | Brayana    |     4 | SmallStraight |
      |  13 | Anousheh   |     5 | FullHouse     |
      |  14 | Ryan       |     5 | Yahtzee       |
      |  15 | Brayana    |     5 | Twos          |
      |  16 | Anousheh   |     6 | Aces          |
      |  17 | Ryan       |     6 | SmallStraight |
      |  18 | Brayana    |     6 | Fours         |
      |  19 | Anousheh   |     7 | ThreeOfAKind  |
      |  20 | Ryan       |     7 | FullHouse     |
      |  21 | Brayana    |     7 | Threes        |
      |  22 | Anousheh   |     8 | FourOfAKind   |
      |  23 | Ryan       |     8 | Aces          |
      |  24 | Brayana    |     8 | Aces          |
      |  25 | Anousheh   |     9 | Fives         |
      |  26 | Ryan       |     9 | ThreeOfAKind  |
      |  27 | Brayana    |     9 | ThreeOfAKind  |
      |  28 | Anousheh   |    10 | Fours         |
      |  29 | Ryan       |    10 | Fives         |
      |  30 | Brayana    |    10 | LargeStraight |
      |  31 | Anousheh   |    11 | LargeStraight |
      |  32 | Ryan       |    11 | FourOfAKind   |
      |  33 | Brayana    |    11 | Chance        |
      |  34 | Anousheh   |    12 | Chance        |
      |  35 | Ryan       |    12 | Twos          |
      |  36 | Brayana    |    12 | FourOfAKind   |
      |  37 | Anousheh   |    13 | Twos          |
      |  38 | Ryan       |    13 | Sixes         |
      |  39 | Brayana    |    13 | Sixes         |
