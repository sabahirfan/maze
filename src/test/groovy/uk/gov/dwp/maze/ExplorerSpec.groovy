package uk.gov.dwp.maze

import spock.lang.Specification
import spock.lang.Unroll
import uk.gov.dwp.maze.domain.Block
import uk.gov.dwp.maze.domain.BlockState
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
        Block startBlock = maze.getStartBlock()

        when:
        explorer.exploreMaze()
        def finalPathString = explorer.printExplorersPath()
        Block[][] outputPath = MazeFactory.buildMazeMapFromString(finalPathString)
        Block outputBlock = MazeUtil.findBlockByState(outputPath, BlockState.START)

        then:
        startBlock.getRow() == outputBlock.getRow()
        startBlock.getColumn() == outputBlock.getColumn()
        startBlock == outputBlock
    }

    @Unroll
    void "test explorer can move forward at #x, #y"() {
        when:

        MazeUtil.setExitBlock(maze.getBlock(), maze.getExitBlock(), x, y, state)
        explorer.exploreMaze()

        then:
        maze.getPath().getPaths()
        pathSize == maze.getPath().getPaths().size()
        path1 == maze.getPath().getPaths().pop().toString()
        path2 == maze.getPath().getPaths().pop().toString()

        where:

        pathSize | path1          | path2           | x | y | state
        2        | '(3, 4, FINISH)' | '(3, 3, START)' | 3 | 4 | BlockState.WALLED
        3        | '(3, 5, FINISH)' | '(3, 4, OPEN)'  | 3 | 5 | BlockState.OPEN
    }


    void "test explored/happy path, Exit exists in the top of the path stack"() {

        when:
        explorer.exploreMaze()

        then:
        maze?.getPath()?.getPaths()
        maze?.getPath()?.getPaths()?.size() > 0
        new Block(14, 1, BlockState.FINISH) == maze.getPath().getPaths().peek()
    }

    void "test put Wall between Start and End has no solution"() {

        when:
        MazeUtil.setBlock(maze.getBlock(), 3, 4, BlockState.WALLED)
        MazeUtil.setBlock(maze.getBlock(), 3, 5, BlockState.FINISH)
        MazeUtil.setBlock(maze.getBlock(), 14, 1, BlockState.OPEN)

        explorer.exploreMaze()

        then:
        maze?.getPath()?.getPaths()?.size() == 0
    }

    void "test final path doesn't contain the blocks lead to the dead end location (6, 12)"() {

        when:
        explorer.exploreMaze()

        then:
        !maze.getPath().getPaths().contains(new Block(6, 12, BlockState.OPEN))

    }


}
