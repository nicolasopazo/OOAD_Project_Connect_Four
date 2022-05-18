package packages;

public class PackageHandler {

    private PackageListener listener;

    public void unpack(Object o) {
        if (o instanceof TeamPackage teamPackage) {
            listener.eventOccurred(1, teamPackage);
        } else if (o instanceof PlayerPackage playerPackage) {
            listener.eventOccurred(2, playerPackage);
        } else if (o instanceof ClientMessage chatMessage) {
            listener.eventOccurred(3, chatMessage);
        } else if (o instanceof MovePackage move) {
            listener.eventOccurred(4, move);
        } else if (o instanceof StartPackage start) {
            listener.eventOccurred(5, start);
        } else if (o instanceof NewRoundPackage newRound) {
            listener.eventOccurred(6, newRound);
        }
    }

    public void setListener(PackageListener listener) {
        this.listener = listener;
    }

}