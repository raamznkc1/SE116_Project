public class Simulation {
    private Grid grid;
    private int currentTick;

    //Pools
    private int totalPopulationPool = 0;
    private int totalGoodsPool = 0;
    private int totalLifestylePool = 0;

    public Simulation(Grid grid){
        this.grid = grid;
        this.currentTick = 0;
    }
    public void run(int totalTicks) {
        for (int i = 0; i < totalTicks; i++) {
            runTick();
        }
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
    private void provideServices() {
    }

    private void collectProduction() {
    }

    private void updateZones() {
    }

    private void distributeResources() {
    }

    private void distributeUtilities() {
    }
}
