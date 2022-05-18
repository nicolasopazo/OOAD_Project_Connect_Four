package game;

import game.enums.GameMode;
import game.enums.PlayerTurn;
import game.listeners.GuiUpdateListener;
import game.network.ClientConnection;
import packages.MovePackage;
import packages.NewRoundPackage;

import java.util.Random;

public class GameController {

    private final int rows;
    private final int columns;
    private final GameMode gameMode;
    private int roundCounter;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameLogic logic;
    private ClientConnection connection;
    private GuiUpdateListener guiListener;
    private PlayerTurn playerTurn = PlayerTurn.NOT_YOUR_TURN;

    public GameController(BoardSize boardSize, Player player1, Player player2, GameMode gameMode) {
        this.roundCounter = 0;
        this.rows = boardSize.getRows();
        this.columns = boardSize.getColumns();
        this.gameMode = gameMode;
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
    }

    public void newGame() {
        playerTurn = PlayerTurn.NOT_YOUR_TURN;
        roundCounter++;
        logic.addCircles();
        if (roundCounter % 2 != 0) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
        if (gameMode == GameMode.ONE_PLAYER && currentPlayer == player2) {
            aiTurn();
        }
        if (gameMode == GameMode.ONE_PLAYER && currentPlayer == player1) {
            playerTurn = PlayerTurn.YOUR_TURN;
        }
    }

    private void changePlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    private void aiTurn() {
        while (true) {
            Random random = new Random();
            int aiRandomMove = random.nextInt(columns);
            AI ai = (AI) player2;
            int aiMove = ai.makeMove(logic.getCircles(), rows, columns);
            if (logic.checkColumn(aiMove)) {
                logic.putPiece(aiMove, currentPlayer.getTeam());
                break;
            } else if (logic.checkColumn(aiRandomMove)) {
                logic.putPiece(aiRandomMove, currentPlayer.getTeam());
                break;
            }
        }
        if (checkWinOrFull()) {
            newGame();
        } else {
            changePlayer();
            playerTurn = PlayerTurn.YOUR_TURN;
        }
    }

    public void setGameHandler() {
        connection.setGameEventListener((event, o) -> {
            switch (event) {
                case 4 -> {
                    playerTurn = PlayerTurn.NOT_YOUR_TURN;
                    MovePackage movePackage = (MovePackage) o;
                    int networkMove = (int) movePackage.getMove();
                    logic.putPiece(networkMove, currentPlayer.getTeam());
                    if (checkWinOrFull()) {
                        newGame();
                    } else {
                        changePlayer();
                        playerTurn = PlayerTurn.YOUR_TURN;
                    }
                    guiListener.updateOccurred(1);
                }
                case 5, 6 -> {
                    playerTurn = PlayerTurn.YOUR_TURN;
                    guiListener.updateOccurred(1);
                }
            }
        });
    }

    private boolean checkWinOrFull() {
        if (logic.checkWin()) {
            currentPlayer.setScore(1);
            guiListener.updateOccurred(2);
            return true;
        } else if (logic.checkBoardFull()) {
            guiListener.updateOccurred(3);
            return true;
        }
        return false;
    }

    public void makeMove(int chosenColumn) {
        if (logic.checkColumn(chosenColumn)) {
            if (gameMode == GameMode.NETWORK && playerTurn == PlayerTurn.YOUR_TURN) {
                playerTurn = PlayerTurn.NOT_YOUR_TURN;
                logic.putPiece(chosenColumn, currentPlayer.getTeam());
                connection.sendPackage(new MovePackage(chosenColumn));
                if (checkWinOrFull()) {
                    connection.sendPackage(new NewRoundPackage(roundCounter+1));
                    newGame();
                } else {
                    changePlayer();
                }
            } else if (gameMode == GameMode.TWO_PLAYERS) {
                logic.putPiece(chosenColumn, currentPlayer.getTeam());
                if (checkWinOrFull()) {
                    newGame();
                } else {
                    changePlayer();
                }
            } else if (gameMode == GameMode.ONE_PLAYER && playerTurn == PlayerTurn.YOUR_TURN) {
                playerTurn = PlayerTurn.NOT_YOUR_TURN;
                logic.putPiece(chosenColumn, currentPlayer.getTeam());
                if (checkWinOrFull()) {
                    newGame();
                } else {
                    changePlayer();
                    aiTurn();
                }
            }
        }
    }

    public void setLogic(GameLogic logic) {
        this.logic = logic;
    }

    public void setConnection(ClientConnection connection) {
        this.connection = connection;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getCurrentTeam() {
        return currentPlayer.getTeam();
    }

    public Piece[][] getCircles() {
        return logic.getCircles();
    }

    public void setGuiUpdateListener(GuiUpdateListener guiListener) {
        this.guiListener = guiListener;
    }

    public PlayerTurn getPlayerTurn() {
        return playerTurn;
    }

    public boolean isEmpty() {
        return logic.isEmpty();
    }

}
