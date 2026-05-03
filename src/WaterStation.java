public class WaterStation extends UtilityProvider{

    public WaterStation(int row, int col){
        super(row,col,"Water");
    }

    @Override
    public String getType() {
        return "W";
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public String toString() {
        return "W";
    }
}

