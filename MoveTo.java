public class MoveTo extends MoveCard {

    private int destination;

    public MoveTo(String text, int destination) {
        super(text, 0);
        this.destination = destination;
    }

    public int getDestination() {
        return destination;
    }

    public void execute(Player player, Board board) {
        int distance;
        if (destination > player.getPosition()) {
            distance = destination - player.getPosition();
        } else {
            distance = 40 - player.getPosition() + destination;
        }
        board.move(player, distance, true);
    }
}
