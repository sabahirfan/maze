package uk.gov.dwp.maze

import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by sabahirfan on 02/04/2017.
 */
class MazeSpec extends Specification {


    def @Shared
            optionalMaze

    def setupSpec() {
        def builder = new MazeBuilderFile("src/test/resources/Maze1.txt")
        optionalMaze = builder.build()
    }


    void "verify maze object from valid file contents"() {

        when:
        def maze
        if (optionalMaze.isPresent()) {
            maze = optionalMaze.get()
        }

        then:

        maze
        maze.getSquare()
        maze.getHeight() == 15
        maze.getWidth() == 15
        maze.getStartCount() == 1
        maze.getExitCount() == 1
    }

    void "verify walled co ordinates"() {
        when:
        def maze
        if (optionalMaze.isPresent()) {
            maze = optionalMaze.get()
        }

        then:
        maze
        maze.getSquare(x, y)
        maze.getSquare(x, y).isWalled()
        !maze.getSquare(x, y).isOpen()
        !maze.getSquare(x, y).isExit()
        !maze.getSquare(x, y).isStart()

        where:
        x << [0, 14, 0, 0, 1]
        y << [0, 14, 1, 2, 14]
    }

    void "verify Start co ordinates"() {
        when:
        def maze
        if (optionalMaze.isPresent()) {
            maze = optionalMaze.get()
        }

        then:
        maze
        maze.getSquare(3, 3)
        maze.getSquare(3, 3).isStart()
        maze.getSquare(3, 3).isOpen()
        !maze.getSquare(3, 3).isExit()
        !maze.getSquare(3, 3).isWalled()

    }

    void "verify Finish co ordinates"() {
        when:
        def maze
        if (optionalMaze.isPresent()) {
            maze = optionalMaze.get()
        }

        then:
        maze
        maze.getSquare(x, y)
        maze.getSquare(x, y).isOpen()
        !maze.getSquare(x, y).isStart()
        !maze.getSquare(x, y).isExit()
        !maze.getSquare(x, y).isWalled()

        where:
        x << [2]
        y << [1]
    }

    void "verify open co ordinates"() {
        when:
        def maze
        if (optionalMaze.isPresent()) {
            maze = optionalMaze.get()
        }

        then:

        maze
        maze.getSquare(x, y)
        maze.getSquare(x, y).isOpen()
        !maze.getSquare(x, y).isStart()
        !maze.getSquare(x, y).isExit()
        !maze.getSquare(x, y).isWalled()

        where:
        x << [2,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
        y << [1,1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12, 13]

    }

    void "verify out of range coordinates"() {
        when:
        def maze
        if (optionalMaze.isPresent()) {
            maze = optionalMaze.get()
        }

        then:

        maze
        !maze.getSquare(x, y)
        !maze.getSquare(x, y)
        !maze.getSquare(x, y)

        where:
        x << [-1, -2, 19]
        y << [22, 1, 0]
    }
}
