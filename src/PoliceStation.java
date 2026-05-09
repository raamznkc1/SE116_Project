public class PoliceStation extends ServiceProvider{

    public PoliceStation(int row, int col,int radius){
        super(row,col,radius);
    }

    @Override
    public String getType(){
    return "F";
    }

    @Override
    public String toString(){
        return "F";
    }

    @Override
    public  void applyService(Zone zone){
        zone.setHasSecurity(true);
    }

}
