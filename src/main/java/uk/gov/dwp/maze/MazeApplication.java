package uk.gov.dwp.maze;

/**
 * Created by sabahirfan on 31/03/2017.
 */

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

/**
 * Created by sabahirfan on 02/04/2017.
 */
@SpringBootApplication
public class MazeApplication implements CommandLineRunner {

    @Override
    public void run(String... args) {
        // Start of application
        MazeBuilder builder = new MazeBuilderFile("src/main/resources/Maze1.txt");
        Optional<Maze> mazeOptional = builder.build();
        if (mazeOptional.isPresent()) {
            Maze maze = mazeOptional.get();
            System.out.println(maze);
            Explorer explorer = new Explorer(maze);
            explorer.exploreMaze();

            System.out.print(maze.getPath().toString());

        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MazeApplication.class, args);
    }

}