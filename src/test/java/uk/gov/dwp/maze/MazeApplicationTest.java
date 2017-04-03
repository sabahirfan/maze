package uk.gov.dwp.maze;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by sabahirfan on 31/03/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MazeApplicationTest {

    @Autowired
    ApplicationContext ctx;

    @Test
    @Ignore
    public void testContextLoads() throws Exception {
        assertThat(this.ctx).isNotNull();
    }
}
