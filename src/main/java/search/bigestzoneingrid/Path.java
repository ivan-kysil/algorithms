package search.bigestzoneingrid;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Kysil Ivan on 2/2/2020.
 */
public class Path {
    List<Cell> path = new LinkedList<>();

    public Optional<Color> getColor() {
        return path.isEmpty()
                ? Optional.<Color>empty()
                : Optional.of(path.get(0).color);
    }

    public void add(Path otherPath) {
        path.addAll(otherPath.path);
    }

    public void add(Cell cell) {
        path.add(cell);
    }

    public int getSize() {
        return path.size();
    }

    public List<Cell> getPath() {
        return path;
    }
}
