import java.util.ArrayList;
import java.util.List;

public class EightQueenApp {

    private static final int BOARD_SIZE = 8;
    private static int successCount = 0;
    private static long triesCount = 0;
    private static boolean PRINT_SOLUTIONS = false;
    private static boolean PRINT_FIRST_SOLUTION = true;

    public static void main(String[] args) throws CloneNotSupportedException {
        Board board = new Board();
        long start = System.currentTimeMillis();
        search(board);
        System.out.println("Size: " + BOARD_SIZE);
        System.out.println("Time:" + (System.currentTimeMillis() - start));
        System.out.println("Success count: " + successCount);
        System.out.println("Tries count: " + triesCount);
    }

    private static void search(Board origBoard) throws CloneNotSupportedException {
        triesCount++;
        int level = origBoard.level;
        if (level >= BOARD_SIZE || origBoard.usableLeft == 0) {
            if (level >= BOARD_SIZE) {
                successCount ++;
                if (PRINT_SOLUTIONS || (PRINT_FIRST_SOLUTION && successCount == 1)) {
                    origBoard.print();
                }
            }
            return;
        };

        for (int i = 0; i < BOARD_SIZE; i++) {
            final Pos pos = Pos.of(i, level);
            if (origBoard.getState(pos) == State.EMPTY){
                Board newBoard = (Board)origBoard.clone();
                newBoard.mark(pos, State.QUEEN);
                newBoard.markNonUsableFromQueenPos(pos);
                newBoard.level++;
                search(newBoard);
            }

        }
    }


    public enum State {
        EMPTY, QUEEN, NON_USABLE;
    }

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        static Pos of(final int x, final int y) {
            return new Pos(x, y); 
        }

        boolean isValid (){
            return x < BOARD_SIZE && y < BOARD_SIZE && x >= 0 && y >= 0;
        }
    }

    public static class Board implements Cloneable {
        private List<State> states = new ArrayList<>(BOARD_SIZE * BOARD_SIZE);
        private int usableLeft = BOARD_SIZE * BOARD_SIZE;
        private int level = 0;

        {
            for (int i = 0; i < (BOARD_SIZE * BOARD_SIZE); i ++) {
                states.add(State.EMPTY);
            }
        }

        void print() {
            for (int i = 0; i < states.size(); i++) {
                if (i%BOARD_SIZE == 0) {
                    System.out.println();
                }

                final State s = states.get(i);
                switch (s) {
                    case EMPTY:
                        System.out.print("' ");
                        break;
                    case QUEEN:
                        System.out.print("* ");
                        break;
                    case NON_USABLE:
                        System.out.print("- ");
                        break;

                }
            }
            System.out.println();
        }

        void mark(Pos p, State s) {
            if(usableLeft == 0 || !p.isValid()) return;

            final int index = p.x * BOARD_SIZE + p.y;
            if (states.get(index) == State.EMPTY) {
                usableLeft--;
                states.set(index, s);
            }
        }
        
        void markNonUsableFromQueenPos(Pos queenPos) {
            // mark horizontal
            for (int y = queenPos.y + 1; y < BOARD_SIZE; y++) {
                Pos pos = Pos.of(queenPos.x, y);
                mark(pos, State.NON_USABLE);
            }
            
            // mark vertical
            for (int x = 0; x < BOARD_SIZE; x++) {
                Pos pos = Pos.of(x, queenPos.y);
                mark(pos, State.NON_USABLE);
            }
            
            // mark diagonal
            for (int i = 1; i < BOARD_SIZE - queenPos.y; i++) {
                Pos pos1 = Pos.of(queenPos.x + i, queenPos.y + i);
                Pos pos2 = Pos.of(queenPos.x - i, queenPos.y + i);
                mark(pos1, State.NON_USABLE);
                mark(pos2, State.NON_USABLE);
            }
        }

        State getState(final Pos p) {
            return states.get(p.x * BOARD_SIZE + p.y);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            try {
                Board b = (Board) super.clone();
                b.states = new ArrayList<>(this.states);
                return b;
            } catch (CloneNotSupportedException e) {
                throw new InternalError(e);
            }
        }
    }
}
