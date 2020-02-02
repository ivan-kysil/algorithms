package search.bigestzoneingrid.impl;

import search.bigestzoneingrid.Cell;
import search.bigestzoneingrid.Color;
import search.bigestzoneingrid.Finder;
import search.bigestzoneingrid.Grid;
import search.bigestzoneingrid.Path;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Kysil Ivan on 2/2/2020.
 */
public class MaxSequenceIterativeFinder implements Finder {
    private Cell.CellFactory factory = new Cell.CellFactory();
    private Set<Cell> visited = new HashSet<>();
    private Path longestPath = new Path();

    @Override
    public Path find(Grid grid) {
        for (int x = 0; x < grid.getGrid().length; x++) {
            Color[] column = grid.getGrid()[x];
            for (int y = 0; y < column.length; y++) {
                Cell cell = factory.createCell(column[y], x, y);
                Path path = depthFirstSearch(cell, grid, visited);
                if (path.getSize() > longestPath.getSize()) {
                    longestPath = path;
                }
            }
        }

        return longestPath;
    }

    private Path depthFirstSearch(Cell cell, Grid grid, Set<Cell> visited) {
        Path result = new Path();
        LinkedList<Cell> heap = new LinkedList<>();
        heap.add(cell);
        do {
            Cell c = heap.pollLast();
            if (!visited.contains(c) && cell.getColor() == c.getColor()) {
                result.add(c);
                visited.add(c);
                heap.addAll(grid.getNeighborsOf(c, factory));
            }
        } while(!heap.isEmpty());

        return result;
    }

}
