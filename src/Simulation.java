public class Simulation {
    private Grid grid;
    private int currentTick;

    //Pools
    private int totalPopulationPool = 0;
    private int totalGoodsPool = 0;
    private int totalLifestylePool = 0;

    public Simulation(Grid grid) {
        this.grid = grid;
        this.currentTick = 1;
    }

    public void run(int totalTicks) {
        for (int i = 0; i < totalTicks; i++) {
            runTick();
        }
    }


    public void runTick() {
        System.out.println("Tick " + currentTick);

        provideServices();
        distributeUtilities();

        if (currentTick > 1) {
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

                        String zoneName = (zone instanceof HousingZone) ? "House" :
                                (zone instanceof CommercialZone) ? "Commercial" : "Industrial";

                        if (type.equals("F")) {
                            if (!zone.getHasSecurity()) {
                                zone.setHasSecurity(true);
                                System.out.println(zoneName + " at (" + r + "," + c + ") received security service");
                            }

                        } else if (type.equals("D")) {
                            if (!zone.getHasHealth()) {
                                zone.setHasHealth(true);
                                System.out.println(zoneName + " at (" + r + "," + c + ") received health service");
                            }

                        } else if (type.equals("S")) {
                            if (!zone.getHasEducation()) {
                                zone.setHasEducation(true);
                                System.out.println(zoneName + " at (" + r + "," + c + ") received education service");
                            }
                        }
                    }
                }
            }
        }
    }

    private void distributeUtilities() {
        UtilityDistributor distributor = new UtilityDistributor();

        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);
                if (cell instanceof UtilityProvider) {
                    distributor.distribute(grid, (UtilityProvider) cell);
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

                    if(output > 0){
                        if (zone instanceof HousingZone) {
                            totalPopulationPool += output;
                            System.out.println("House at (" + r + "," + c + ") generated " + output + " population");
                        } else if (zone instanceof IndustrialZone) {
                            totalGoodsPool += output;
                            System.out.println("Industrial at (" + r + "," + c + ") generated " + output + " goods");
                        } else if (zone instanceof CommercialZone) {
                            totalLifestylePool += output;
                            System.out.println("Commercial at (" + r + "," + c + ") generated " + output + " lifestyle");
                        }
                    }
                }
            }
        }
    }

    private void updateZones() {

        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    int oldLevel = zone.getLevel();
                    zone.computeNewLevel();

                    int newLevel = zone.getLevel();
                    if(newLevel > oldLevel){
                        String zoneName = (zone instanceof  HousingZone) ? "House":
                                (zone instanceof CommercialZone) ? "Commercial" : "Industrial";
                        System.out.println(zoneName + " at (" + r + "," + c + ") levels up from " + oldLevel + " to " + newLevel);

                    } else if (newLevel < oldLevel) {
                        String zoneName = (zone instanceof HousingZone) ? "House" :
                                (zone instanceof CommercialZone) ? "Commercial" : "Industrial";
                        System.out.println(zoneName + " at (" + r + "," + c + ") levels down from " + oldLevel + " to " + newLevel);
                    }
                }
            }
        }
    }

    private void distributeResources() {

        int housingCount = 0;
        int commercialCount = 0;
        int industrialCount = 0;


        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);
                if (cell instanceof HousingZone) housingCount++;
                if (cell instanceof CommercialZone) commercialCount++;
                if (cell instanceof IndustrialZone) industrialCount++;
            }
        }

        int populationTargets = industrialCount + commercialCount;
        int popPerZone = (populationTargets > 0) ? (totalPopulationPool / populationTargets) : 0;

        int goodsTargets = commercialCount;
        int goodsPerZone = (goodsTargets > 0) ? (totalGoodsPool / goodsTargets) : 0;

        int lifestylePerZone = (housingCount > 0) ? (totalLifestylePool / housingCount) : 0;

        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Cell cell = grid.getCell(r, c);

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;

                    if (zone instanceof HousingZone) {
                        zone.setReceivedLifestyle(lifestylePerZone);
                        if (lifestylePerZone > 0) {
                            System.out.println("House at (" + r + "," + c + ") received " + lifestylePerZone + " lifestyle");
                        }

                    } else if (zone instanceof IndustrialZone) {
                        zone.setReceivedPopulation(popPerZone);
                        if (popPerZone > 0) {
                            System.out.println("Industrial at (" + r + "," + c + ") received " + popPerZone + " population");
                        }

                    } else if (zone instanceof CommercialZone) {
                        zone.setReceivedPopulation(popPerZone);
                        zone.setReceivedGoods(goodsPerZone);
                        if (popPerZone > 0) {
                            System.out.println("Commercial at (" + r + "," + c + ") received " + popPerZone + " population");
                        }
                        if (goodsPerZone > 0) {
                            System.out.println("Commercial at (" + r + "," + c + ") received " + goodsPerZone + " goods");
                        }
                    }
                }
            }
        }
    }
}
