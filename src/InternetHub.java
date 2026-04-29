public class InternetHub extends UtilityProvider{

    public InternetHub(int row, int col,String utilityType){
        super(row,col,"Internet");
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public String toString() {
        return "T";
    }
}
