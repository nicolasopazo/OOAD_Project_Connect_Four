package packages;

import game.Player;

import java.io.Serializable;

public record PlayerPackage(Player player) implements Serializable {

    public Player getPlayer() {
        return player;
    }

}
