public class RoadCell extends Cell {

    public RoadCell(int row, int col) {
        super(row, col);
    }

    @Override
    public String getType() {
        return "R";
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public String toString() {
        return "R";
    }
}


// for  github control