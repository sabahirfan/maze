package uk.gov.dwp.maze;

/**
 * Created by sabahirfan on 31/03/2017.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

/**
 * Created by sabahirfan on 02/04/2017.
 */
@SpringBootApplication
public class MazeApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MazeApplication.class);

    @Override
    public void run(String... args) {
        // Start of application
        MazeBuilder builder = new MazeBuilderFile("src/main/resources/Maze1.txt");
        Optional<Maze> mazeOptional = builder.build();
        if (mazeOptional.isPresent()) {
            Maze maze = mazeOptional.get();
            log.info(maze.toString());
            Explorer explorer = new Explorer(maze);
            explorer.exploreMaze();

            log.info(maze.getPath().toString());

        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MazeApplication.class, args);
    }

}