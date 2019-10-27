package hello.model;

public class SystemData {
    private int dryPeriods = 0;
    private int rainyPeriods = 0;
    private int rainPeakDay = 0;
    private int optimalPeriods = 0;

    public SystemData(int dryPeriods, int rainyPeriods, int rainPeakDay, int optimalPeriods) {
        this.dryPeriods = dryPeriods;
        this.rainyPeriods = rainyPeriods;
        this.rainPeakDay = rainPeakDay;
        this.optimalPeriods = optimalPeriods;
    }

    public int getDryPeriods() {
        return dryPeriods;
    }

    public int getRainyPeriods() {
        return rainyPeriods;
    }

    public int getRainPeakDay() {
        return rainPeakDay;
    }

    public int getOptimalPeriods() {
        return optimalPeriods;
    }
}
