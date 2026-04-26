public abstract class Zone extends Cell {
    protected int level;
    protected int currentOutput;
    protected int receivedElectricity;
    protected int receivedWater;
    protected int receivedInternet;
    protected boolean hasSecurity;
    protected boolean hasHealth;
    protected boolean hasEducation;
    protected int receivedPopulation;
    protected int receivedGoods;
    protected int receivedLifestyle;
    protected int utilityDemand;

    public Zone(int row, int col) {
        super(row, col);
        this.level = 0;
        this.currentOutput = 0;
        this.receivedElectricity = 0;
        this.receivedWater = 0;
        this.receivedInternet = 0;
        this.hasSecurity = false;
        this.hasHealth = false;
        this.hasEducation = false;
        this.receivedPopulation = 0;
        this.receivedGoods = 0;
        this.receivedLifestyle = 0;
        this.utilityDemand = 1;
    }
    public int getLevel() {
        return level;
    }

    public int getCurrentOutput() {
        return currentOutput;
    }

    public int getUtilityDemand() {
        return utilityDemand;
    }

    public int getReceivedElectricity() {
        return receivedElectricity;
    }

    public int getReceivedWater() {
        return receivedWater;
    }

    public int getReceivedInternet() {
        return receivedInternet;
    }

    public int getReceivedPopulation() {
        return receivedPopulation;
    }

    public int getReceivedGoods() {
        return receivedGoods;
    }

    public int getReceivedLifestyle() {
        return receivedLifestyle;
    }

    public boolean getHasSecurity() {
        return hasSecurity;
    }

    public boolean getHasHealth() {
        return hasHealth;
    }

    public boolean getHasEducation() {
        return hasEducation;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public void setCurrentOutput(int currentOutput) {
        this.currentOutput = currentOutput;
    }

    public void setUtilityDemand(int utilityDemand) {
        this.utilityDemand = utilityDemand;
    }

    public void setReceivedElectricity(int amount) {
        this.receivedElectricity = amount;
    }

    public void setReceivedWater(int amount) {
        this.receivedWater = amount;
    }

    public void setReceivedInternet(int amount) {
        this.receivedInternet = amount;
    }

    public void setReceivedPopulation(int amount) {
        this.receivedPopulation = amount;
    }

    public void setReceivedGoods(int amount) {
        this.receivedGoods = amount;
    }

    public void setReceivedLifestyle(int amount) {
        this.receivedLifestyle = amount;
    }

    public void setHasSecurity(boolean hasSecurity) {
        this.hasSecurity = hasSecurity;
    }

    public void setHasHealth(boolean hasHealth) {
        this.hasHealth = hasHealth;
    }

    public void setHasEducation(boolean hasEducation) {
        this.hasEducation = hasEducation;
    }

    public abstract void computeNewLevel();

    public abstract void computeOutput();

    @Override
    public boolean isConnectable() {
        return true;
    }















}
