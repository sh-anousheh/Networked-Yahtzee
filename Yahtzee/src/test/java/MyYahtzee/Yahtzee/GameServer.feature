@tag
Feature: Tests for GameServer class

  Background: 
    Given Define the default val

  @tag1
  Scenario: Test for joining the game1
    Given The firstGame is started
    When "Anousheh" would enter the firstGame
    Then Check if they could enter successfully to FirstGame

  @tag2
  Scenario: Test for joining the game2
    Given The SecondGame is started
    When "Anousheh,Ryan,Julie" would enter the SecondGame
    Then Check if they could enter successfully to SecondGame

  @tag3
  Scenario: Test for joining the game1
    Given The ThirdGame is started
    When "Anousheh,Ryan,Julie,Brayana" would enter the ThirdGame
    Then Check if they could not enter successfully to ThirdGame

  @tag4
  Scenario: Testcases for a player ending the game
    Given The game is started with the socket 51735
    And Anousheh, Ryan, and Brayana join the game
    When It is the end of the game
    And Check if isFinish is <"true">
    Then Validate the result

  @tag5
  Scenario: Testcases for a player ending the game
    #any changes resulted in error in the game, would make isFinish false
    Given The game is started with the socket 51739
    And Anousheh, Ryan, and Brayana join the game
    When It is the end of the game
    And Check if isFinish is <"false">
    Then Validate the result
