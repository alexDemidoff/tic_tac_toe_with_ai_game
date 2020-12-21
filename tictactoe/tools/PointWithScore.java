package tictactoe.tools;

public class PointWithScore {
    private int score;
    private Point point;

    public PointWithScore() {
    }

    public PointWithScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}