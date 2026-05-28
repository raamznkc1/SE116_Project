import java.util.LinkedList;
import java.util.Queue;

    public class UtilityDistributor {

        public void distribute(Grid grid, UtilityProvider provider) {

            boolean[][] visited = new boolean[grid.getRows()][grid.getCols()];
            Queue<int[]> queue = new LinkedList<>();

            int providerRow = provider.getRow();
            int providerCol = provider.getCol();
            int remainingCapacity = provider.getCapacity();
            String utilityType = provider.getUtilityType();


            queue.add(new int[]{providerRow, providerCol});
            visited[providerRow][providerCol] = true;

            int[][] directions = {
                    {-1, 0},
                    {1, 0},
                    {0, -1},
                    {0, 1}
            };

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

                                int currentReceived = utilityType.equals("Electricity") ? zone.getReceivedElectricity() :
                                        utilityType.equals("Water") ? zone.getReceivedWater() : zone.getReceivedInternet();

                                int needed = demand - currentReceived;

                                if (needed > 0 && remainingCapacity > 0) {
                                    int delivered = Math.min(needed, remainingCapacity);


                                    provider.applyUtility(zone, delivered);

                                    remainingCapacity -= delivered;

                                    String zoneName = (zone instanceof HousingZone) ? "House" :
                                            (zone instanceof CommercialZone) ? "Commercial" : "Industrial";
                                    System.out.println(zoneName + " at (" + nextRow + "," + nextCol + ") received " + delivered + " " + utilityType.toLowerCase(java.util.Locale.ENGLISH));
                                }
                            }
                            queue.add(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }
        }
    }

