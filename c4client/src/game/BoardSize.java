package game;

public record BoardSize(int rows, int columns) {

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

}
