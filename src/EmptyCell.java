public class EmptyCell extends Cell {

    public EmptyCell(int row, int col) {
        super(row, col);
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public boolean isConnectable() {
        return false;
    }

    @Override
    public String toString() {
        return "E";
    }
}
