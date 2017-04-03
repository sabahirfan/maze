package uk.gov.dwp.maze.domain

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by sabahirfan on 31/03/2017.
 */
class SquareStateSpec extends Specification {


    @Unroll
    void "test getSquareState for #sign"() {
        when:
        def abc = SquareState.getSquareState(sign)

        then:
        abc == result

        where:

        sign        | result
        ' ' as char | SquareState.OPEN
        'S' as char | SquareState.START
        'F' as char | SquareState.EXIT
        'V' as char | SquareState.VISITED
        'X' as char | SquareState.WALLED
        'a' as char | SquareState.UNKNOWN
        'z' as char | SquareState.UNKNOWN
    }

}
