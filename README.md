# Networked Turned Based Yahtzee Game
This project is first implemented using TDD approach associating Junit tests. Then, Cucumber is used for the final user defined, and senario based tests.

###  Objective
This project is an individual assignment done for COMP 5104 at Carleton University in the fall semester of 2019.


The purpose of the project was to implement a networked (client/server) version of the Yahtzee game, with a text-based interface that allows each player to see the score sheet of ALL players. This project is programmed to play a single game (i.e. 13 rounds) for exactly 3 players. A first player will initiate the game, then others will join.

Code is implemented using a TDD approach associating Junit-like tests SOLELY with the methods that pertain to game logic (viz., playing via the interface AND scoring).
TDD requires that you write (and commit) one or more tests and then the code to make these tests pass (in a separate commit). 
Finally, user defined senario based tests have been done using Cucumber.

Yahtzee is a simple game whose rules can be found at http://www.yahtzee.org.uk/rules.html


### Technologies
* Java [JDK 8+]
* Eclipse
* Maven dependencies
* JUnit
* Cucumber

### Running The Project
* To run the game, first click right on GameServer class and run it as Java Application. Then, run App class as Java Application and enter the name of the first player. Again run App class for the second time and enter the second player’s name. Repeat this process for the third player. Now you have a console page for the server and three console page for the players.
* Different senarios and acceptance tests and their respective files is defined in the AccTest.rtf file.
