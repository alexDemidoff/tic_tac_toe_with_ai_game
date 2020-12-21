package tictactoe.gameboard;

import tictactoe.tools.Point;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    public final int SIZE;
    public final int LEFT_BOUND;
    public final int RIGHT_BOUND;
    public final int TOP_BOUND;
    public final int BOTTOM_BOUND;

    private final Seed[][] cells;

    private Board(int size) {

        this.SIZE = size;
        this.cells = new Seed[size][size];
        this.LEFT_BOUND = 1;
        this.RIGHT_BOUND = size;
        this.TOP_BOUND = size;
        this.BOTTOM_BOUND = 1;

        reset();
    }

    // Initializes the field (Singleton pattern)
    public static Board create(int size) {
        return new Board(size);
    }

    // Gets empty cells
    public ArrayList<Point> getEmptyCells() {

        ArrayList<Point> result = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isEmpty(Point.createXY(i, j))) {
                    result.add(Point.createXY(i, j));
                }
            }
        }

        return result;
    }

    // Draws the board
    public void draw() {

        for (int i = 0; i < SIZE * SIZE; i++) {
            System.out.print("-");
        }

        System.out.println();

        for (Seed[] row : cells) {
            System.out.print("| ");
            for (Seed s : row) {
                System.out.print(getChar(s) + " ");
            }
            System.out.println("|");
        }

        for (int i = 0; i < SIZE * SIZE; i++) {
            System.out.print("-");
        }

        System.out.println();
    }

    private char getChar(Seed seed) {
        if (seed == Seed.CROSS) {
            return 'X';
        } else if (seed == Seed.NOUGHT) {
            return 'O';
        } else  {
            return ' ';
        }
    }

    // Checks if the cell is free
    public boolean isEmpty(Point point) {

        return cells[point.x][point.y] == Seed.EMPTY;
    }

    // Gets amount of Xs or Os
    public int getAmountOf(Seed seed) {

        int count = 0;
        for (Seed[] row : cells) {
            for (Seed s : row) {
                if (s == seed) {
                    count++;
                }
            }
        }

        return count;
    }

    // Makes move
    public void setMove(Point point) {

        cells[point.x][point.y] = getAmountOf(Seed.CROSS) <= getAmountOf(Seed.NOUGHT) ? Seed.CROSS : Seed.NOUGHT;
    }

    // Checks the state of the game
    public GameState getStateOfGame() {

        Seed winner = findWinner();
        if (winner != null) {
            return winner == Seed.CROSS ? GameState.XWINS : GameState.OWINS;
        }

        if (getEmptyCells().size() == 0) {
            return GameState.DRAW;
        }

        return GameState.PLAYING;
    }

    public Seed findWinner() {

        // Checking rows
        for (int i = 0; i < SIZE; i++) {
            if (cells[i][0] != Seed.EMPTY) {
                if (cells[i][0] == cells[i][1] && cells[i][0] == cells[i][2]) {
                    return cells[i][0];
                }
            }
        }

        // Checking columns
        for (int i = 0; i < SIZE; i++) {
            if (cells[0][i] != Seed.EMPTY) {
                if (cells[0][i] == cells[1][i] && cells[0][i] == cells[2][i]) {
                    return cells[0][i];
                }
            }
        }

        // Checking main diagonal
        if (cells[0][0] != Seed.EMPTY) {
            if (cells[0][0] == cells[1][1] && cells[0][0] == cells[2][2]) {
                return cells[0][0];
            }
        }

        // Checking minor diagonal
        if (cells[2][0] != Seed.EMPTY) {
            if (cells[2][0] == cells[1][1] && cells[2][0] == cells[0][2]) {
                return cells[2][0];
            }
        }

        return null;
    }

    // Fills the field with spaces, when command 'start' is activated
    public void reset() {

        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(cells[i], Seed.EMPTY);
        }
    }

    public Point findEmptyFor(Seed seed) {

        Point cellAfter;

        // Checking rows
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cellAfter = Point.createXY(i, (j + 2) % SIZE);

                if (isEmpty(cellAfter)) {
                    if (cells[i][j] == cells[i][(j + 1) % SIZE] &&
                            cells[i][j] == seed) {
                        return cellAfter;
                    }
                }
            }
        }

        // Checking columns
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cellAfter = Point.createXY((j + 2) % SIZE, i);

                if (isEmpty(cellAfter)) {
                    if (cells[j][i] == cells[(j + 1) % SIZE][i] &&
                            cells[j][i] == seed) {
                        return cellAfter;
                    }
                }
            }
        }

        // Checking main diagonal
        for (int i = 0; i < SIZE; i++) {
            cellAfter = Point.createXY((i + 2) % SIZE, (i + 2) % SIZE);

            if (isEmpty(cellAfter)) {
                if (cells[i][i] == cells[(i + 1) % SIZE][(i + 1) % SIZE] &&
                        cells[i][i] == seed) {
                    return cellAfter;
                }
            }
        }

        // Checking minor diagonal
        for (int i = SIZE - 1; i >= 0; i--) {
            cellAfter = Point.createXY((i - 2 + SIZE) % SIZE, (SIZE - i + 1) % SIZE);

            if (isEmpty(cellAfter)) {
                if (cells[i][SIZE - 1 - i] == cells[(i - 1 + SIZE) % SIZE][(SIZE - i) % SIZE] &&
                        cells[i][SIZE - 1 - i] == seed) {
                    return cellAfter;
                }
            }
        }

        return null;
    }

    public void setCellToEmpty(Point point) {
        cells[point.x][point.y] = Seed.EMPTY;
    }

    public void printStateOfGame() {
        switch (getStateOfGame()) {
            case XWINS:
                System.out.println(getChar(Seed.CROSS) + " wins");
                break;
            case OWINS:
                System.out.println(getChar(Seed.NOUGHT) + " wins");
                break;
            case DRAW:
                System.out.println("Draw");
                break;
            default:
                System.out.println("Playing");
        }
    }

    public Seed getCurrentPlayer() {
        return getAmountOf(Seed.CROSS) <= getAmountOf(Seed.NOUGHT) ? Seed.CROSS : Seed.NOUGHT;
    }
}