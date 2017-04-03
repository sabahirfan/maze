package uk.gov.dwp.maze

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by sabahirfan on 02/04/2017.
 */
class MazeFactorySpec extends Specification {

    @Unroll
    void "test creation for buildMazeMap with invalid source as #file"() {

        given:
        MazeFactory mazeFactory = new MazeFactory()

        when:
        mazeFactory.buildMazeMap(file)

        then:
        def error = thrown(expectedException)
        error.message == expectedMessage

        where:

        file                                                      | expectedException        | expectedMessage
        null                                                      | IllegalArgumentException | 'Invalid input file - file input cannot be null'
        new File('')                                              | IllegalArgumentException | 'Invalid input file - file does not exist'
        new File('INVALID_PATH')                                  | IllegalArgumentException | 'Invalid input file - file does not exist'
        new File('src/test/resources/emptyMaze.txt')              | IllegalArgumentException | 'Invalid input file - empty lines'
        new File('src/test/resources/multipleStartsInMaze.txt')   | IllegalArgumentException | 'Invalid map data - should have one and only one Start point \'S\' and one and only one exit \'F\''
        new File('src/test/resources/multipleFinishesinMaze.txt') | IllegalArgumentException | 'Invalid map data - should have one and only one Start point \'S\' and one and only one exit \'F\''

    }

    void "test creation for Maze Map with valid data"() {

        given:
        given:
        MazeFactory mazeFactory = new MazeFactory()

        when:
        def map = mazeFactory.buildMazeMap(new File('src/test/resources/Maze1.txt'))

        then:
        map
        map.length == 15
        map[0].length == 15

    }

}
