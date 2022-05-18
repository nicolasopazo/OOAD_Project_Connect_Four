package game;

import game.enums.GameMode;
import game.network.ClientConnection;
import gui.GuiBoard;
import gui.GuiColors;

import java.awt.*;

public class GameBuilder {

    private ClientConnection connection;
    private final BoardSize boardSize = new BoardSize(6,7);

    public GuiBoard onePlayerModeEasy(String namePlayer1, Color selectedColor) {
        Player player1 = new Player(1,selectedColor);
        AI ai = new AI(2,GuiColors.AI_PIECE_EASY,1);
        player1.setName(namePlayer1);
        GameController gameController = new GameController(boardSize, player1, ai, GameMode.ONE_PLAYER);
        GameLogic logic = new GameLogic(gameController);
        gameController.setLogic(logic);
        return new GuiBoard(gameController);
    }

    public GuiBoard onePlayerModeNormal(String namePlayer1, Color selectedColor) {
        Player player1 = new Player(1,selectedColor);
        AI ai = new AI(2,GuiColors.AI_PIECE_NORMAL,2);
        player1.setName(namePlayer1);
        GameController gameController = new GameController(boardSize, player1, ai, GameMode.ONE_PLAYER);
        GameLogic logic = new GameLogic(gameController);
        gameController.setLogic(logic);
        return new GuiBoard(gameController);
    }

    public GuiBoard twoPlayerMode(String namePlayer1, String namePlayer2, Color selectedColorOne, Color selectedColorTwo) {
        Player player1 = new Player(1,selectedColorOne);
        Player player2 = new Player(2,selectedColorTwo);
        player1.setName(namePlayer1);
        player2.setName(namePlayer2);
        GameController gameController = new GameController(boardSize, player1, player2, GameMode.TWO_PLAYERS);
        GameLogic logic = new GameLogic(gameController);
        gameController.setLogic(logic);
        return new GuiBoard(gameController);
    }

    public void networkMode(String namePlayer1, Color selectedColor) {
        connection = new ClientConnection(boardSize, namePlayer1,selectedColor,5555);
        connection.startInStream();
    }

    public ClientConnection getConnection() {
        return connection;
    }

    public GuiBoard getNetworkBoard() {
        return new GuiBoard(connection.getGameBoard());
    }

}
