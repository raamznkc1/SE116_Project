public class Simulation {
    private Cell[][] grid;
    private int currentTick;

    //Pools
    private int totalPopulationPool = 0;
    private int totalGoodsPool = 0;
    private int totalLifestylePool = 0;

    public Simulation(Cell[][] grid){
        this.grid = grid;
        this.currentTick = 0;
    }

    public void runTick(){
        System.out.println("Tick " + currentTick);

        provideServices();
        distributeUtilities();

        //Resource Distribution
        if (currentTick > 0) {
            distributeResources();
        }

        updateZones();
        collectProduction();
        currentTick++;
    }

    private void collectProduction() {
    }

    private void updateZones() {
    }

    private void distributeResources() {
    }

    private void distributeUtilities() {
    }

    private void provideServices() {
    }
}
