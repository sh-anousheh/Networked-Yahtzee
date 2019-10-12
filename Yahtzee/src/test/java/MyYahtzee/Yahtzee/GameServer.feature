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

  @tag1
  Scenario: Test for joining the game1
    Given The ThirdGame is started
    When "Anousheh,Ryan,Julie,Brayana" would enter the ThirdGame
    Then Check if they could not enter successfully to ThirdGame
