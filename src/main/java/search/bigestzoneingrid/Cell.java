package search.bigestzoneingrid;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kysil Ivan on 2/2/2020.
 */

public class Cell {

    Cell(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    Color color;
    int x;
    int y;

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        if (y != cell.y) return false;
        return color == cell.color;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    public static class CellFactory {
        private Map<String, Cell> pool = new HashMap<>();
        public Cell createCell(Color color, int x, int y) {
            String key = String.format("%s;%s", x, y);
            return pool.computeIfAbsent(key, k -> new Cell(color, x, y));
        }
    }

    @Override
    public String toString() {
        return "Cell{" +
                "color=" + color +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
