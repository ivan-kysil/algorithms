package search.bigestzoneingrid;

import org.junit.Test;

import java.util.List;

import static search.bigestzoneingrid.Color.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kysil Ivan on 2/2/2020.
 */
public class GridTest {
    Grid grid = new Grid(
            new Color[][] {
                    new Color[] { GREEN, RED }, // column 0, x == 0
                    new Color[] { BLUE, GREEN } // column 1, x == 1
            }
    );

    @Test
    public void test() {
        Cell.CellFactory factory = new Cell.CellFactory();
        Cell cell_0_0 = factory.createCell(GREEN, 0, 0);
        List<Cell> neighbors_0_0 = grid.getNeighborsOf(cell_0_0, factory);
        assertEquals(neighbors_0_0.size(), 2);
        assertTrue(neighbors_0_0.contains(factory.createCell(RED, 0, 1)));
        assertTrue(neighbors_0_0.contains(factory.createCell(BLUE, 1, 0)));

        Cell cell_1_0 = factory.createCell(BLUE, 1, 0);
        List<Cell> neighbors_1_0 = grid.getNeighborsOf(cell_1_0, factory);
        assertEquals(neighbors_1_0.size(), 2);
        assertTrue(neighbors_1_0.contains(factory.createCell(GREEN, 0, 0)));
        assertTrue(neighbors_1_0.contains(factory.createCell(GREEN, 1, 1)));
    }
}
