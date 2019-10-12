@tag
Feature: Tests for GameServer class

  @tag1
  Scenario Outline: Test for joining the game
    Given The game is started
    When "<List of players(comma separated)>" would enter the game
    And Check if they could enter successfully
    Then validate if the result is "<res>"

    Examples: 
      | List of players(comma separated) | res   |
      | Anousheh                         | true  |
      | Anousheh,Ryan,Julie,Brayana      | false |
      | Anousheh,Ryan,Julie              | true  |