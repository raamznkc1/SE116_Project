public abstract class  UtilityProvider extends Cell {
    private int capacity;
    private String utilityType;

    public UtilityProvider(int row, int col,String utilityType){
        super(row, col);
        this.capacity=100;
        this.utilityType=utilityType;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getUtilityType() {
        return utilityType;
    }

    public abstract void applyUtility(Zone zone, int amount);
}
