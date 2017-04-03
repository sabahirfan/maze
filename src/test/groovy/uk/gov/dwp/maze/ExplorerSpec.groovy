package uk.gov.dwp.maze

import spock.lang.Specification
import spock.lang.Unroll
import uk.gov.dwp.maze.domain.Square
import uk.gov.dwp.maze.domain.SquareState
import uk.gov.dwp.maze.util.MazeUtil

/**
 * Created by sabahirfan on 02/04/2017.
 */
class ExplorerSpec extends Specification {

    private Maze maze
    private Explorer explorer

    def setup() {
        MazeBuilder builder = new MazeBuilderFile('src/test/resources/Maze1.txt')
        def optionalMaze = builder.build()
        if (optionalMaze.isPresent()) {
            maze = optionalMaze.get()
            explorer = new Explorer(maze)
        }
    }

    void "test if maze is NULL"() {

        when:
        new Explorer(null)

        then:
        def error = thrown(IllegalArgumentException)
        error.message == 'Null maze in explorer.'
    }


    void "test explorer can be dropped at Start Point"() {

        given:
        Square startSquare = maze.getStartSquare()

        when:
        explorer.exploreMaze()
        def finalPathString = explorer.printExplorersPath()
        Square[][] outputPath = MazeFactory.buildMazeMapFromString(finalPathString)
        Square outputSquare = MazeUtil.findSquareByState(outputPath, SquareState.START)

        then:
        startSquare.getRow() == outputSquare.getRow()
        startSquare.getColumn() == outputSquare.getColumn()
        startSquare == outputSquare
    }

    @Unroll
    void "test explorer can move forward at #x, #y"() {
        when:

        MazeUtil.setExitSquare(maze.getSquare(), maze.getExitSquare(), x, y, state)
        explorer.exploreMaze()

        then:
        maze.getPath().getPaths()
        pathSize == maze.getPath().getPaths().size()
        path1 == maze.getPath().getPaths().pop().toString()
        path2 == maze.getPath().getPaths().pop().toString()

        where:

        pathSize | path1          | path2           | x | y | state
        2        | '(3, 4, EXIT)' | '(3, 3, START)' | 3 | 4 | SquareState.WALLED
        3        | '(3, 5, EXIT)' | '(3, 4, OPEN)'  | 3 | 5 | SquareState.OPEN
    }


    void "test explored/happy path, Exit exists in the top of the path stack"() {

        when:
        explorer.exploreMaze()

        then:
        maze?.getPath()?.getPaths()
        maze?.getPath()?.getPaths()?.size() > 0
        new Square(14, 1, SquareState.EXIT) ==  maze.getPath().getPaths().peek()
    }

    void "test put Wall between Start and End has no solution" () {

        when:
        MazeUtil.setSquare(maze.getSquare(), 3, 4, SquareState.WALLED)
        MazeUtil.setSquare(maze.getSquare(), 3, 5, SquareState.EXIT)
        MazeUtil.setSquare(maze.getSquare(), 14, 1, SquareState.OPEN)

        explorer.exploreMaze()

        then:
        maze?.getPath()?.getPaths()?.size() == 0
    }

    void "test final path doesn't contain the squares lead to the dead end location (6, 12)" () {

        when:
        explorer.exploreMaze()

        then:
        !maze.getPath().getPaths().contains(new Square(6, 12, SquareState.OPEN))

    }


}
