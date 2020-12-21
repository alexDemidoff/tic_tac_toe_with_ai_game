package tictactoe.tools;

import tictactoe.TicTacToe;

public class Point {

    public int x;
    public int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point createXY(int x, int y) {
        return new Point(x, y);
    }

    public static Point convertToLocal(Point point) {
        return Point.createXY(TicTacToe.board.SIZE - point.y, point.x - 1);
    }
}