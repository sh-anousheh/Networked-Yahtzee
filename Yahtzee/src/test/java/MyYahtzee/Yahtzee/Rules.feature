@tag
Feature: Tests for Rules class

  Background: 
    Given For All tests

  @tag1
  Scenario: Test for rolling function
    Given Define default answer
    When Roll a Die
    Then Check if the result is between 1 and 6
#-------------------------------------------
#    
#
# @tag2
# Scenario Outline: Title of your scenario outline
#   Given I want to write a step with <name>
#   When I check for the <value> in step
#   Then I verify the <status> in step
#
#   Examples:
#     | name  | value | status  |
#     | name1 |     5 | success |
#     | name2 |     7 | Fail    |
#
#-------------------------------------------
