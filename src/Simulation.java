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

    private void resetAllZoneValues() {
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    zone.resetReceivedValues();
                }
            }
        }
    }
    private void provideServices() {
         resetAllZoneValues();

        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);

                if (cell != null) {
                    String type = cell.getType();

                    if (type.equals("F")) {
                        bfsSpreadService(r, c, type, 5);
                    } else if (type.equals("D")) {
                        bfsSpreadService(r, c, type, 3);
                    } else if (type.equals("S")) {
                        bfsSpreadService(r, c, type, 4);
                    }
                }
            }
        }
    }

    private void bfsSpreadService(int startRow, int startCol, String type, int maxRange) {
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
