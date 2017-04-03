package uk.gov.dwp.maze

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by sabahirfan on 31/03/2017.
 */
class MazeBuilderFileSpec extends Specification {

    @Unroll
    void "test creation for MazeBuilderFile with file path as #filePath"() {

        when:
        def builder = new MazeBuilderFile(filePath)

        then:
        def error = thrown(expectedException)
        error.message == expectedMessage

        where:

        filePath | expectedException        | expectedMessage
        null     | IllegalArgumentException | 'File path cannot be null.'
        ''       | IllegalArgumentException | 'File path cannot be empty string.'
        " "      | IllegalArgumentException | 'File path cannot be empty string.'

    }

    void "test creation for MazeBuilderFile with invalid path"() {

        given:
        def builder = new MazeBuilderFile("NON_EXIST")

        when:
        def maze = builder.build()

        then:
        maze.isPresent() == false
    }

    void "test creation for MazeBuilderFile with EMPTY FILE"() {

        given:
        def builder = new MazeBuilderFile("src/test/resources/emptyMaze.txt")

        when:
        def maze = builder.build()

        then:
        maze.isPresent() == false
    }


    void "test creation for MazeBuilderFile with valid path"() {

        given:
        def builder = new MazeBuilderFile("src/test/resources/Maze1.txt")

        when:
        def maze = builder.build()

        then:
        maze.isPresent()
        def mazeObject = maze.get()
        mazeObject.getBlock().length == 15
        mazeObject.getBlock()[0].length == 15

    }


}
