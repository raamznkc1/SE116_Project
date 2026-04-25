public class Grid {

    private Cell[][] grid;
    private int rows;
    private int cols;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
    }

    public void setCell(int row, int col, Cell cell) {
        grid[row][col] = cell;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return rows;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != null) {
                    sb.append(grid[i][j].toString());
                } else {
                    sb.append("E");
                }
                if (j < cols - 1) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }
}