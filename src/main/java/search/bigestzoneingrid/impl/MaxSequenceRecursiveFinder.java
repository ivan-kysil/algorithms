package search.bigestzoneingrid.impl;

import search.bigestzoneingrid.Cell;
import search.bigestzoneingrid.Color;
import search.bigestzoneingrid.Finder;
import search.bigestzoneingrid.Grid;
import search.bigestzoneingrid.Path;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kysil Ivan on 2/2/2020.
 */
public class MaxSequenceRecursiveFinder implements Finder {
    private Cell.CellFactory factory = new Cell.CellFactory();
    private Set<Cell> visited = new HashSet<>();
    private Path longestPath = new Path();

    @Override
    public Path find(Grid grid) {
        for (int x = 0; x < grid.getGrid().length; x++) {
            Color[] column = grid.getGrid()[x];
            for (int y = 0; y < column.length; y++) {
                Cell cell = factory.createCell(column[y], x, y);
                if (!visited.contains(cell)) {
                    Path path = visit(cell, grid, visited);
                    if (path.getSize() > longestPath.getSize()) {
                        longestPath = path;
                    }
                }
            }
        }

        return longestPath;
    }

    private Path visit(Cell cell, Grid grid, Set<Cell> visited) {
        Path result = new Path();
        result.add(cell);
        visited.add(cell);
        for (Cell c: grid.getNeighborsOf(cell, factory)) {
            if (!visited.contains(c) && c.getColor() == cell.getColor()) {
                result.add(visit(c, grid, visited));
            }
        }
        return result;
    }
}
