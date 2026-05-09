public class School extends ServiceProvider{
    public School(int row, int col,int radius){
        super(row,col,radius);
    }

    @Override
    public String getType(){
        return "S";
    }

    @Override
    public String toString(){
        return "S";
    }

    @Override
    public  void applyService(Zone zone){
        zone.setHasEducation(true);
    }

}
