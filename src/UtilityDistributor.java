import java.util.LinkedList;
import java.util.Queue;
import java.util.Locale;

    public class UtilityDistributor {

        public void distribute(Grid grid, UtilityProvider provider) {

            // Keeps track of visited cells to avoid checking the same cell again.
            boolean[][] visited = new boolean[grid.getRows()][grid.getCols()];
            Queue<int[]> queue = new LinkedList<>();

            int providerRow = provider.getRow();
            int providerCol = provider.getCol();
            int remainingCapacity = provider.getCapacity();
            String utilityType = provider.getUtilityType();

            // Starts BFS from the utility provider's position.
            int[] providerPosition = {providerRow, providerCol};
            queue.add(providerPosition);
            visited[providerRow][providerCol] = true;

            // There are four movement directions possible: up, down, left, and right.
            int[][] directions = {
                    {-1, 0},
                    {1, 0},
                    {0, -1},
                    {0, 1}
            };

            // Uses BFS to distribute utility through connected cells while capacity is available.
            while (!queue.isEmpty() && remainingCapacity > 0) {
                int[] current = queue.remove();

                int currentRow = current[0];
                int currentCol = current[1];

                for (int[] direction : directions) {
                    int nextRow = currentRow + direction[0];
                    int nextCol = currentCol + direction[1];

                    if (grid.isValidPosition(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                        Cell cell = grid.getCell(nextRow, nextCol);

                        if (cell != null && cell.isConnectable()) {
                            visited[nextRow][nextCol] = true;

                            if (cell instanceof Zone) {
                                Zone zone = (Zone) cell;
                                int demand = zone.getUtilityDemand();

                                int currentReceived = 0;

                                if (utilityType.equals("Electricity")) {
                                    currentReceived = zone.getReceivedElectricity();
                                }
                                else if (utilityType.equals("Water")) {
                                    currentReceived = zone.getReceivedWater();
                                }
                                else {
                                    currentReceived = zone.getReceivedInternet();
                                }

                                int needed = demand - currentReceived;

                                if (needed > 0 && remainingCapacity > 0) {
                                    int delivered = Math.min(needed, remainingCapacity);

                                    provider.applyUtility(zone, delivered);

                                    remainingCapacity -= delivered;

                                    String zoneName;
                                    if (zone instanceof HousingZone) {
                                        zoneName = "House";
                                    }
                                    else if (zone instanceof CommercialZone) {
                                        zoneName = "Commercial";
                                    }
                                    else {
                                        zoneName = "Industrial";
                                    }
                                    System.out.println(zoneName + " at (" + nextRow + "," + nextCol + ") received " + delivered + " " + utilityType.toLowerCase(Locale.ENGLISH));
                                }
                            }
                            int[] nextPosition = {nextRow, nextCol};
                            queue.add(nextPosition);
                        }
                    }
                }
            }
        }
    }

