package feature;

/**
 * Created by sabahirfan on 03/04/2017.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"},
        features = {"src/test/resources/feature"},
        glue = {"src/test/java/feature"}
)
public class RunMazeTest {
//TODO: Complete missing steps for feature.

}
