public class Income extends Tax {
    private int percentage;
    public Income(String name, int amount, int percentage) {
        super(name, amount);
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }
}
