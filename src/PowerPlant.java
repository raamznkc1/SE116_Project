public class PowerPlant extends UtilityProvider{

    public PowerPlant(int row, int col,String utilityType){
        super(row,col,"Electricity");
    }

    @Override
    public String getType() {
        return "P";
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public String toString() {
        return "P";
    }
}
