package search.bigestzoneingrid.impl;

import search.bigestzoneingrid.Cell;
import search.bigestzoneingrid.Color;
import search.bigestzoneingrid.Grid;
import search.bigestzoneingrid.Path;
import org.junit.Test;

import java.util.stream.Collectors;

import static search.bigestzoneingrid.Color.BLUE;
import static search.bigestzoneingrid.Color.GREEN;
import static search.bigestzoneingrid.Color.RED;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kysil Ivan on 2/2/2020.
 */
public class MaxSequenceFinder {
    Grid grid1 = new Grid(
            new Color[][] {
                    new Color[] { GREEN, BLUE, GREEN }, // column 0, x == 0
                    new Color[] { BLUE, RED, GREEN },
                    new Color[] { GREEN, BLUE, BLUE },
                    new Color[] { BLUE, RED, BLUE },
                    new Color[] { RED, BLUE, BLUE }
            }
    );
    MaxSequenceRecursiveFinder rFinder = new MaxSequenceRecursiveFinder();
    MaxSequenceIterativeFinder iFinder = new MaxSequenceIterativeFinder();

    @Test
    public void recursiveFinderTest1() {
        assertPath1(rFinder.find(grid1));
    }

    @Test
    public void iterativeFinderTest1() {
        assertPath1(iFinder.find(grid1));
    }

    private void assertPath1(Path path) {
        Cell.CellFactory cellFactory = new Cell.CellFactory();
        assertTrue(path.getSize() == 5);
        assertTrue(path.getColor().isPresent());
        assertTrue(path.getColor().get() == BLUE);
        assertTrue(path.getPath().contains(cellFactory.createCell(BLUE, 2, 1)));
        assertTrue(path.getPath().contains(cellFactory.createCell(BLUE, 2, 2)));
        assertTrue(path.getPath().contains(cellFactory.createCell(BLUE, 3, 2)));
        assertTrue(path.getPath().contains(cellFactory.createCell(BLUE, 4, 1)));
        assertTrue(path.getPath().contains(cellFactory.createCell(BLUE, 4, 2)));
    }

    Grid grid2 = new Grid(
            new Color[][] {
                    new Color[] { GREEN, GREEN, BLUE, GREEN, GREEN}, // column 0, x == 0
                    new Color[] { GREEN, RED, BLUE, BLUE, BLUE },
                    new Color[] { GREEN, BLUE, BLUE, BLUE, BLUE },
                    new Color[] { GREEN, RED, BLUE, BLUE, BLUE },
                    new Color[] { GREEN, GREEN, GREEN, GREEN, GREEN }
            }
    );

    @Test
    public void recursiveFinderTest2() {
        assertPath2(rFinder.find(grid2));
    }

    @Test
    public void iterativeFinderTest2() {
        assertPath2(iFinder.find(grid2));
    }

    private void assertPath2(Path path) {
        assertTrue(path.getSize() == 11);
        assertTrue(path.getColor().get() == BLUE);
        System.out.println("color = " + path.getColor());
        System.out.println("size = " + path.getSize());
        System.out.println("path = " + path.getPath().stream().map(Cell::toString).collect(Collectors.joining("\n")));
    }

}
