package uk.gov.dwp.maze;

import uk.gov.dwp.maze.domain.Square;

import java.io.File;
import java.util.Optional;

/**
 * Created by sabahirfan on 31/03/2017.
 */
public class MazeBuilderFile implements MazeBuilder {

    private File file;

    public MazeBuilderFile(String filePath) {

        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null.");
        }

        if (filePath.trim().equals("")) {
            throw new IllegalArgumentException("File path cannot be empty string.");
        }

        this.file = new File(filePath);
    }

    @Override
    public Optional<Maze> build() {
        try {
            Square[][] map = MazeFactory.buildMazeMap(file);
            return Optional.ofNullable(new Maze(map));
        } catch (IllegalArgumentException ia) {
            return Optional.empty();
        }
    }
}

