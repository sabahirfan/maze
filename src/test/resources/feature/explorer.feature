Feature: Maze Explorer


  Scenario: As a world famous explorer of Mazes I would like to exist in a maze and be able to navigate it
  So that I can explore it
    Given Given a maze the explorer should be able to drop in to the Start point
    When A maze has been created the number of walls and empty spaces should be available to me
    Then An explorer on a maze must be able to move forward
    Then An explorer on a maze must be able to turn left and right (changing direction the explorer is facing)
    Then An explorer on a maze must be able to declare what is in front of them
    Then An explorer on a maze must be able to declare all movement options from their given location
    Then An explorer on a maze must be able to report a record of where they have been in an understandable fashion
