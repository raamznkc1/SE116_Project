public abstract class ServiceProvider extends Cell {
    private int radius;

    public ServiceProvider(int row, int col, int radius) {
        super(row, col);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public boolean isConnectable(){
        return true;
    }

    public abstract void applyService(Zone zone);

    public void distribute(Grid grid) {
    for(int i=0;i<grid.getRows();i++){
        for(int j=0;j<grid.getCols();j++){

            int rowDistance =row-i;
            int colDistance =col-j;

            if(rowDistance <0) rowDistance =-rowDistance;
            if(colDistance <0) colDistance =-colDistance;
            int manhattanDistance= rowDistance + colDistance;

            if(radius>=manhattanDistance){
                Cell cell=grid.getCell(i,j);
                if(cell instanceof Zone){
                    applyService((Zone) cell);
                }
            }
        }
    }
    }
}
