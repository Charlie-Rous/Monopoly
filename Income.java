public class Income extends Tax {
    private double percent;

    public Income(String name, int amount, double percent) {
        super(name, amount);
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }
}
