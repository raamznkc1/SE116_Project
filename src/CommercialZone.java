public class CommercialZone extends Zone {

    public CommercialZone(int row, int col) {
        super(row, col);
    }

    @Override
    public String getType() {
        return "C";
    }

    @Override
    public String toString() {
        return "C";
    }

    @Override
    public void computeNewLevel() {
        if (receivedElectricity == 0 && receivedWater == 0 && receivedInternet == 0) {
            dropToZero();
            return;
        }
        int targetLevel = 0;

        if (receivedPopulation > 0 && receivedGoods > 0 && receivedElectricity > 0 && receivedWater > 0 && receivedInternet > 0) {
            targetLevel = 1;

            if (hasSecurity) {
                targetLevel = 2;

                if (receivedPopulation > 0 && receivedGoods > 0) {
                    targetLevel = 3;
                }
            }
        }

        if (targetLevel > level) {
            levelUp();
        }
        else if (targetLevel < level) {
            levelDown();
        }
    }

    @Override
    public void computeOutput() {
        if (level == 0) {
            currentOutput = 0;
            updateDemand();
            return;
        }

        int m = Math.min(receivedElectricity, Math.min(receivedWater, receivedInternet));

        if (level == 1) {
            currentOutput = m;
        }
        else if (level == 2) {
            currentOutput = 2 * m;
        }
        else if (level == 3) {
            currentOutput = 2 * m + Math.min(receivedPopulation, receivedGoods);
        }

        updateDemand();
    }

    public int getProducedLifestyle() {
        return currentOutput;
    }
}










