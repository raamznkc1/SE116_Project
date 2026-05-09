public class Hospital extends ServiceProvider{
    public Hospital(int row, int col,int radius){
        super(row,col,radius);
    }

    @Override
    public String getType(){
        return "D";
    }

    @Override
    public String toString(){
        return "D";
    }

    @Override
    public  void applyService(Zone zone){
        zone.setHasHealth(true);
    }

}
