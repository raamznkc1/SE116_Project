public class InternetHub extends UtilityProvider{

    public InternetHub(int row, int col){
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

    @Override
    public void applyUtility(Zone zone, int amount) {
        zone.setReceivedInternet(zone.getReceivedInternet()+amount);

    }
}
