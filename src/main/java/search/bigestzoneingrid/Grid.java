package search.bigestzoneingrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kysil Ivan on 2/2/2020.
 */
public class Grid {
    Color[][] grid;

    public Grid(Color[][] grid) {
        this.grid = grid;
    }

    public List<Cell> getNeighborsOf(Cell cell, Cell.CellFactory factory) {
        if (cell.x >= grid.length || cell.y >= grid[0].length) {
            throw new IllegalArgumentException("cell is out of bound " + cell.x + "," + cell.y);
        }

        List<Cell> result = new ArrayList(4);

        // top
        if(cell.y > 0) {
            result.add(factory.createCell(grid[cell.x][cell.y-1], cell.x, cell.y - 1));
        }

        // bottom
        if (cell.y+1 < grid[0].length) {
            result.add(factory.createCell(grid[cell.x][cell.y+1], cell.x, cell.y + 1));
        }

        // left
        if (cell.x > 0) {
            result.add(factory.createCell(grid[cell.x-1][cell.y], cell.x - 1, cell.y));
        }

        // right
        if (cell.x+1 < grid.length) {
            result.add(factory.createCell(grid[cell.x+1][cell.y], cell.x + 1, cell.y));
        }

        return result;
    }

    public Color[][] getGrid() {
        return grid;
    }
}
