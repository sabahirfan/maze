package uk.gov.dwp.maze.domain

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by sabahirfan on 31/03/2017.
 */
class BlockStateSpec extends Specification {


    @Unroll
    void "test getBlockState for #sign"() {
        when:
        def abc = BlockState.getBlockState(sign)

        then:
        abc == result

        where:

        sign        | result
        ' ' as char | BlockState.OPEN
        'S' as char | BlockState.START
        'F' as char | BlockState.FINISH
        'V' as char | BlockState.VISITED
        'X' as char | BlockState.WALLED
        'a' as char | BlockState.UNKNOWN
        'z' as char | BlockState.UNKNOWN
    }

}
