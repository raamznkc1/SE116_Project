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
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;

                    int distance = Math.abs(startRow - r) + Math.abs(startCol - c);

                    if (distance <= maxRange) {
                        if (type.equals("F")) {
                            zone.setHasSecurity(true);
                        } else if (type.equals("D")) {
                            zone.setHasHealth(true);
                        } else if (type.equals("S")) {
                            zone.setHasEducation(true);
                        }
                    }
                }
            }
        }
    }

    private void distributeUtilities() {
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);
                if (cell != null) {
                    if (cell.getType().equals("P")) {
                        bfsSpreadUtility(r, c, "Electricity", 100);
                    } else if (cell.getType().equals("W")) {
                        bfsSpreadUtility(r, c, "Water", 100);
                    }
                }
            }
        }
    }

    private void bfsSpreadUtility(int startRow, int startCol, String utilityType, int initialCapacity) {
        int rows = grid.getRows();
        int cols = grid.getCols();

        boolean[][] visited = new boolean[rows][cols];

        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        int remainingCapacity = initialCapacity;
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

        while (!queue.isEmpty() && remainingCapacity > 0) {
            int[] current = queue.poll();
            int currRow = current[0];
            int currCol = current[1];

            for (int[] dir : directions) {
                int nextRow = currRow + dir[0];
                int nextCol = currCol + dir[1];

                if (grid.isValidPosition(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                    Cell neighbor = grid.getCell(nextRow, nextCol);

                    if (neighbor != null) {
                        if (neighbor instanceof Zone) {
                            Zone zone = (Zone) neighbor;
                            int demand = zone.getUtilityDemand();

                            if (utilityType.equals("Electricity") && zone.getReceivedElectricity() < demand) {
                                zone.setReceivedElectricity(demand);
                                remainingCapacity -= demand;
                            }
                            else if (utilityType.equals("Water") && zone.getReceivedWater() < demand) {
                                zone.setReceivedWater(demand);
                                remainingCapacity -= demand;
                            }
                            visited[nextRow][nextCol] = true;
                        }

                        else if (neighbor.isConnectable()) {
                            visited[nextRow][nextCol] = true;
                            queue.add(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }
        }
    }

    private void collectProduction() {

        totalPopulationPool = 0;
        totalGoodsPool = 0;
        totalLifestylePool = 0;

        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    zone.computeOutput();
                    int output = zone.getCurrentOutput();

                    if (zone instanceof HousingZone) {
                        totalPopulationPool += output;
                    } else if (zone instanceof IndustrialZone) {
                        totalGoodsPool += output;
                    } else if (zone instanceof CommercialZone) {
                        totalLifestylePool += output;
                    }
                }
            }
        }
    }

    private void updateZones() {
    }

    private void distributeResources() {
    }
}
