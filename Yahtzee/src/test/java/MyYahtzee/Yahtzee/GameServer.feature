@tag
Feature: Tests for GameServer class

  @tag1
  Scenario Outline: Test for joining the game
    Given The game is started
    When "<List of players(comma separated)>" would enter the game
    And Check if "<List of players(comma separated)>"could enter successfully
    Then validate if the result is "<res>"

    Examples: 
      | List of players(comma separated) | res   |
      | Anousheh                         | true  |
      | Anousheh,Ryan,Julie              | true  |
      | Anousheh,Ryan,Julie,Brayana      | false |
