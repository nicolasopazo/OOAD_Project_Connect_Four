package packages;

public class PackageHandler {

    private PackageListener setUpListener;
    private PackageListener gameEventListener;

    public void unpack(Object o) {
        if (o instanceof TeamPackage teamPackage) {
            setUpListener.eventOccurred(1, teamPackage);
        } else if (o instanceof PlayerPackage playerPackage) {
            setUpListener.eventOccurred(2, playerPackage);
        } else if (o instanceof ClientMessage chatMessage) {
            gameEventListener.eventOccurred(3, chatMessage);
        } else if (o instanceof MovePackage move) {
            gameEventListener.eventOccurred(4, move);
        } else if (o instanceof StartPackage start) {
            gameEventListener.eventOccurred(5, start);
        } else if (o instanceof NewRoundPackage newRound) {
            gameEventListener.eventOccurred(6, newRound);
        }
    }

    public void setSetUpListener(PackageListener setUpListener) {
        this.setUpListener = setUpListener;
    }

    public void setGameEventListener(PackageListener gameEventListener) {
        this.gameEventListener = gameEventListener;
    }

}