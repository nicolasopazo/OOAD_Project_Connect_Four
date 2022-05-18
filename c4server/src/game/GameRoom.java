package game;

import packages.*;
import server.ServerConnection;

public class GameRoom {

    private final ServerConnection connection1;
    private final ServerConnection connection2;

    public GameRoom(ServerConnection connection1, ServerConnection connection2) {
        this.connection1 = connection1;
        this.connection2 = connection2;
        connection1.setOpponent(connection2);
        connection2.setOpponent(connection1);

        connection1.sendPackage(new TeamPackage(1));
        connection2.sendPackage(new TeamPackage(2));
        startGame();
    }

    private void startGame() {
        System.out.println("Start Game");
        while (true) {
            if (connection1.getPlayer() != null && connection2.getPlayer() != null) {
                break;
            }
        }
        System.out.println("Player 1 name:" + connection1.getPlayer().getName() + " team: " + connection1.getPlayer().getTeam());
        System.out.println("Player 2 name:" + connection2.getPlayer().getName() + " team: " + connection2.getPlayer().getTeam());
    }

}
