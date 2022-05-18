package game;

import java.awt.*;

public class AI extends Player {

    private String name;
    private final int team;
    private final int difficulty;

    public AI(int team, Color playerColor, int difficulty) {
        super(team, playerColor);
        this.team = team;
        this.difficulty = difficulty;
        difficultyName();
    }

    private void difficultyName() {
        if (difficulty == 1) {
            this.name = "EASY BOT";
        } else if (difficulty == 2) {
            this.name = "NORMAL BOT";
        }
    }

    public String getName() {
        return name;
    }

    public int makeMove(Piece[][] circles, int rows, int columns) {
        AIBrain brain = new AIBrain(circles, rows, columns, team, difficulty);
        return brain.findAIMove();
    }

    private record AIBrain(Piece[][] circles, int rows, int columns, int ai, int difficulty) {
        public int findAIMove() {
            if (findWinningMoveVertical() != 999) {
                return findWinningMoveVertical();
            } else if (findWinningMoveHorizontalA() != 999) {
                return findWinningMoveHorizontalA();
            } else if (findWinningMoveHorizontalB() != 999) {
                return findWinningMoveHorizontalB();
            } else if (findWinningMoveHorizontalC() != 999) {
                return findWinningMoveHorizontalC();
            } else if (findWinningMoveHorizontalD() != 999) {
                return findWinningMoveHorizontalD();
            } else if (findWinningMoveDiagonalUpA() != 999) {
                return findWinningMoveDiagonalUpA();
            } else if (findWinningMoveDiagonalUpB() != 999) {
                return findWinningMoveDiagonalUpB();
            } else if (findWinningMoveDiagonalUpC() != 999) {
                return findWinningMoveDiagonalUpC();
            } else if (findWinningMoveDiagonalUpD() != 999) {
                return findWinningMoveDiagonalUpD();
            } else if (findWinningMoveDiagonalDownA() != 999) {
                return findWinningMoveDiagonalDownA();
            } else if (findWinningMoveDiagonalDownB() != 999) {
                return findWinningMoveDiagonalDownB();
            } else if (findWinningMoveDiagonalDownC() != 999) {
                return findWinningMoveDiagonalDownC();
            } else if (findWinningMoveDiagonalDownD() != 999) {
                return findWinningMoveDiagonalDownD();
            } else if (blockPlayerVertical() != 999) {
                return blockPlayerVertical();
            } else if (blockPlayerHorizontalA() != 999) {
                return blockPlayerHorizontalA();
            } else if (blockPlayerHorizontalB() != 999) {
                return blockPlayerHorizontalB();
            } else if (blockPlayerHorizontalC() != 999) {
                return blockPlayerHorizontalC();
            } else if (blockPlayerHorizontalD() != 999) {
                return blockPlayerHorizontalD();
            } else if (blockPlayerDiagonalUpA() != 999) {
                return blockPlayerDiagonalUpA();
            } else if (blockPlayerDiagonalUpB() != 999) {
                return blockPlayerDiagonalUpB();
            } else if (blockPlayerDiagonalUpC() != 999) {
                return blockPlayerDiagonalUpC();
            } else if (blockPlayerDiagonalUpD() != 999) {
                return blockPlayerDiagonalUpD();
            } else if (blockPlayerDiagonalDownA() != 999) {
                return blockPlayerDiagonalDownA();
            } else if (blockPlayerDiagonalDownB() != 999) {
                return blockPlayerDiagonalDownB();
            } else if (blockPlayerDiagonalDownC() != 999) {
                return blockPlayerDiagonalDownC();
            } else if (blockPlayerDiagonalDownD() != 999) {
                return blockPlayerDiagonalDownD();
            } else if (blockPlayerHorizontalBuildUpA() != 999 && difficulty > 1) {
                return blockPlayerHorizontalBuildUpA();
            } else if (blockPlayerHorizontalBuildUpB() != 999 && difficulty > 1) {
                return blockPlayerHorizontalBuildUpB();
            } else if (blockPlayerHorizontalBuildUpC() != 999 && difficulty > 1) {
                return blockPlayerHorizontalBuildUpC();
            } else if (blockPlayerDiagonalBuildUpA() != 999 && difficulty > 1) {
                return blockPlayerDiagonalBuildUpA();
            } else if (blockPlayerDiagonalBuildUpB() != 999 && difficulty > 1) {
                return blockPlayerDiagonalBuildUpB();
            } else if (dontEnablePlayerHorizontalA() != 999 && difficulty > 1) {
                return findBasicMove(); //TODO add method that excludes the returned value (putAnywhereButHere)
            } else if (dontEnablePlayerHorizontalB() != 999 && difficulty > 1) {
                return findBasicMove();
            } else if (dontEnablePlayerHorizontalC() != 999 && difficulty > 1) {
                return findBasicMove();
            } else if (dontEnablePlayerHorizontalD() != 999 && difficulty > 1) {
                return findBasicMove();
            } else if (buildUpHorizontalA() != 999 && difficulty > 1) {
                return buildUpHorizontalA();
            } else if (buildUpHorizontalB() != 999 && difficulty > 1) {
                return buildUpHorizontalB();
            } else if (buildUpHorizontalC() != 999 && difficulty > 1) {
                return buildUpHorizontalC();
            } else if (buildUpHorizontalD() != 999 && difficulty > 1) {
                return buildUpHorizontalD();
            } else if (buildUpVertical() != 999 && difficulty > 1) {
                return buildUpVertical();
            } else if (firstBuildUpHorizontalA() != 999 && difficulty > 1) {
                return firstBuildUpHorizontalA();
            } else if (firstBuildUpHorizontalB() != 999 && difficulty > 1) {
                return firstBuildUpHorizontalB();
            } else if (firstBuildUpHorizontalC() != 999 && difficulty > 1) {
                return firstBuildUpHorizontalC();
            } else if (firstBuildUpVertical() != 999 && difficulty > 1) {
                return firstBuildUpVertical();
            } else {
                return findBasicMove();
            }
        }

        private int findWinningMoveVertical() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 0; j < columns; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i + 1][j].getTeam() == ai &&
                            circles[i + 2][j].getTeam() == ai && circles[i + 3][j].getTeam() == ai) {
                        return j;
                    }
                }
            }
            return 999;
        }

        private int findWinningMoveHorizontalA() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i][j + 1].getTeam() == ai &&
                            circles[i][j + 2].getTeam() == ai && circles[i][j + 3].getTeam() == ai) {
                        if (i != rows - 1 && circles[i + 1][j].getTeam() != 0) {
                            return j;
                        } else if (i == rows - 1) {
                            return j;
                        }
                    }
                }
            }
            return 999;
        }

        private int findWinningMoveHorizontalB() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i][j + 1].getTeam() == 0 &&
                            circles[i][j + 2].getTeam() == ai && circles[i][j + 3].getTeam() == ai) {
                        if (i != rows - 1 && circles[i + 1][j + 1].getTeam() != 0) {
                            return j + 1;
                        } else if (i == rows - 1) {
                            return j + 1;
                        }
                    }
                }
            }
            return 999;
        }

        private int findWinningMoveHorizontalC() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i][j + 1].getTeam() == ai &&
                            circles[i][j + 2].getTeam() == 0 && circles[i][j + 3].getTeam() == ai) {
                        if (i != rows - 1 && circles[i + 1][j + 2].getTeam() != 0) {
                            return j + 2;
                        } else if (i == rows - 1) {
                            return j + 2;
                        }
                    }
                }
            }
            return 999;
        }

        private int findWinningMoveHorizontalD() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i][j + 1].getTeam() == ai &&
                            circles[i][j + 2].getTeam() == ai && circles[i][j + 3].getTeam() == 0) {
                        if (i != rows - 1 && circles[i + 1][j + 3].getTeam() != 0) {
                            return j + 3;
                        } else if (i == rows - 1) {
                            return j + 3;
                        }
                    }
                }
            }
            return 999;
        }

        private int findWinningMoveDiagonalUpA() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i + 1][j + 1].getTeam() == ai &&
                            circles[i + 2][j + 2].getTeam() == ai && circles[i + 3][j + 3].getTeam() == ai) {
                        if (circles[i + 1][j].getTeam() != 0) {
                            return j;
                        }
                    }
                }
            }
            return 999;
        }

        private int findWinningMoveDiagonalUpB() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i + 1][j + 1].getTeam() == 0 &&
                            circles[i + 2][j + 2].getTeam() == ai && circles[i + 3][j + 3].getTeam() == ai) {
                        if (circles[i + 2][j + 1].getTeam() != 0) {
                            return j + 1;
                        }
                    }
                }
            }
            return 999;
        }

        private int findWinningMoveDiagonalUpC() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i + 1][j + 1].getTeam() == ai &&
                            circles[i + 2][j + 2].getTeam() == 0 && circles[i + 3][j + 3].getTeam() == ai) {
                        if (circles[i + 3][j + 2].getTeam() != 0) {
                            return j + 2;
                        }
                    }
                }
            }
            return 999;
        }

        private int findWinningMoveDiagonalUpD() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i + 1][j + 1].getTeam() == ai &&
                            circles[i + 2][j + 2].getTeam() == ai && circles[i + 3][j + 3].getTeam() == 0) {
                        if (i + 3 == rows - 1) {
                            return j + 3;
                        } else if (circles[i + 4][j + 3].getTeam() != 0) {
                            return j + 3;
                        }
                    }
                }
            }
            return 999;
        }

        private int findWinningMoveDiagonalDownA() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 3; j < columns; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i + 1][j - 1].getTeam() == ai &&
                            circles[i + 2][j - 2].getTeam() == ai && circles[i + 3][j - 3].getTeam() == ai) {
                        if (circles[i + 1][j].getTeam() != 0) {
                            return j;
                        }
                    }
                }
            }
            return 999;
        }

        private int findWinningMoveDiagonalDownB() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 3; j < columns; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i + 1][j - 1].getTeam() == 0 &&
                            circles[i + 2][j - 2].getTeam() == ai && circles[i + 3][j - 3].getTeam() == ai) {
                        if (circles[i + 2][j - 1].getTeam() != 0) {
                            return j - 1;
                        }
                    }
                }
            }
            return 999;
        }

        private int findWinningMoveDiagonalDownC() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 3; j < columns; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i + 1][j - 1].getTeam() == ai &&
                            circles[i + 2][j - 2].getTeam() == 0 && circles[i + 3][j - 3].getTeam() == ai) {
                        if (circles[i + 3][j - 2].getTeam() != 0) {
                            return j - 2;
                        }
                    }
                }
            }
            return 999;
        }

        private int findWinningMoveDiagonalDownD() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 3; j < columns; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i + 1][j - 1].getTeam() == ai &&
                            circles[i + 2][j - 2].getTeam() == ai && circles[i + 3][j - 3].getTeam() == 0) {
                        if (i + 3 == rows - 1) {
                            return j - 3;
                        } else if (circles[i + 4][j - 3].getTeam() != 0) {
                            return j - 3;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerVertical() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 0; j < columns; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i + 1][j].getTeam() == 1 &&
                            circles[i + 2][j].getTeam() == 1 && circles[i + 3][j].getTeam() == 1) {
                        return j;
                    }
                }
            }
            return 999;
        }

        private int blockPlayerHorizontalA() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i][j + 1].getTeam() == 1 &&
                            circles[i][j + 2].getTeam() == 1 && circles[i][j + 3].getTeam() == 1) {
                        if (i != rows - 1 && circles[i + 1][j].getTeam() != 0) {
                            return j;
                        } else if (i == rows - 1) {
                            return j;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerHorizontalB() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i][j + 1].getTeam() == 0 &&
                            circles[i][j + 2].getTeam() == 1 && circles[i][j + 3].getTeam() == 1) {
                        if (i != rows - 1 && circles[i + 1][j + 1].getTeam() != 0) {
                            return j + 1;
                        } else if (i == rows - 1) {
                            return j + 1;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerHorizontalC() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i][j + 1].getTeam() == 1 &&
                            circles[i][j + 2].getTeam() == 0 && circles[i][j + 3].getTeam() == 1) {
                        if (i != rows - 1 && circles[i + 1][j + 2].getTeam() != 0) {
                            return j + 2;
                        } else if (i == rows - 1) {
                            return j + 2;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerHorizontalD() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i][j + 1].getTeam() == 1 &&
                            circles[i][j + 2].getTeam() == 1 && circles[i][j + 3].getTeam() == 0) {
                        if (i != rows - 1 && circles[i + 1][j + 3].getTeam() != 0) {
                            return j + 3;
                        } else if (i == rows - 1) {
                            return j + 3;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerDiagonalUpA() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i + 1][j + 1].getTeam() == 1 &&
                            circles[i + 2][j + 2].getTeam() == 1 && circles[i + 3][j + 3].getTeam() == 1) {
                        if (circles[i + 1][j].getTeam() != 0) {
                            return j;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerDiagonalUpB() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i + 1][j + 1].getTeam() == 0 &&
                            circles[i + 2][j + 2].getTeam() == 1 && circles[i + 3][j + 3].getTeam() == 1) {
                        if (circles[i + 2][j + 1].getTeam() != 0) {
                            return j + 1;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerDiagonalUpC() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i + 1][j + 1].getTeam() == 1 &&
                            circles[i + 2][j + 2].getTeam() == 0 && circles[i + 3][j + 3].getTeam() == 1) {
                        if (circles[i + 3][j + 2].getTeam() != 0) {
                            return j + 2;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerDiagonalUpD() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i + 1][j + 1].getTeam() == 1 &&
                            circles[i + 2][j + 2].getTeam() == 1 && circles[i + 3][j + 3].getTeam() == 0) {
                        if (i + 3 == rows - 1) {
                            return j + 3;
                        } else if (i + 3 < rows - 1 && circles[i + 4][j + 3].getTeam() != 0) {
                            return j + 3;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerDiagonalDownA() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 3; j < columns; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i + 1][j - 1].getTeam() == 1 &&
                            circles[i + 2][j - 2].getTeam() == 1 && circles[i + 3][j - 3].getTeam() == 1) {
                        if (circles[i + 1][j].getTeam() != 0) {
                            return j;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerDiagonalDownB() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 3; j < columns; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i + 1][j - 1].getTeam() == 0 &&
                            circles[i + 2][j - 2].getTeam() == 1 && circles[i + 3][j - 3].getTeam() == 1) {
                        if (circles[i + 2][j - 1].getTeam() != 0) {
                            return j - 1;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerDiagonalDownC() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 3; j < columns; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i + 1][j - 1].getTeam() == 1 &&
                            circles[i + 2][j - 2].getTeam() == 0 && circles[i + 3][j - 3].getTeam() == 1) {
                        if (circles[i + 3][j - 2].getTeam() != 0) {
                            return j - 2;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerDiagonalDownD() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 3; j < columns; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i + 1][j - 1].getTeam() == 1 &&
                            circles[i + 2][j - 2].getTeam() == 1 && circles[i + 3][j - 3].getTeam() == 0) {
                        if (i + 3 == rows - 1) {
                            return j - 3;
                        } else if (i + 3 < rows - 1 && circles[i + 4][j - 3].getTeam() != 0) {
                            return j - 3;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerHorizontalBuildUpA() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i][j + 1].getTeam() == 1 &&
                            circles[i][j + 2].getTeam() == 1 && circles[i][j + 3].getTeam() == 0) {
                        if (i != rows - 1 && circles[i + 1][j + 3].getTeam() != 0) {
                            return j + 3;
                        } else if (i == rows - 1) {
                            return j + 3;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerHorizontalBuildUpB() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i][j + 1].getTeam() == 0 &&
                            circles[i][j + 2].getTeam() == 1 && circles[i][j + 3].getTeam() == 0) {
                        if (i != rows - 1 && circles[i + 1][j + 1].getTeam() != 0) {
                            return j + 1;
                        } else if (i == rows - 1) {
                            return j + 1;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerHorizontalBuildUpC() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i][j + 1].getTeam() == 1 &&
                            circles[i][j + 2].getTeam() == 0 && circles[i][j + 3].getTeam() == 0) {
                        if (i != rows - 1 && circles[i + 1][j + 2].getTeam() != 0) {
                            return j + 2;
                        } else if (i == rows - 1) {
                            return j + 2;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerDiagonalBuildUpA() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 3; j < columns; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i + 1][j - 1].getTeam() == 1 &&
                            circles[i + 2][j - 2].getTeam() == 1 && circles[i + 3][j - 3].getTeam() == 0) {
                        if (i + 3 == rows - 1) {
                            return j - 3;
                        } else if (circles[i + 4][j - 3].getTeam() != 0) {
                            return j - 3;
                        }
                    }
                }
            }
            return 999;
        }

        private int blockPlayerDiagonalBuildUpB() {
            for (int i = 0; i < rows - 3; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i + 1][j + 1].getTeam() == 1 &&
                            circles[i + 2][j + 2].getTeam() == 1 && circles[i + 3][j + 3].getTeam() == 0) {
                        if (i + 3 == rows - 1) {
                            return j + 3;
                        } else if (circles[i + 4][j + 3].getTeam() != 0) {
                            return j + 3;
                        }
                    }
                }
            }
            return 999;
        }

        private int dontEnablePlayerHorizontalA() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i][j + 1].getTeam() == 1 &&
                            circles[i][j + 2].getTeam() == 1 && circles[i][j + 3].getTeam() == 0) {
                        if (i < rows - 2 && circles[i + 2][j + 3].getTeam() != 0 && circles[i + 1][j + 3].getTeam() == 0) {
                            return j + 3;
                        } else if (i == rows - 2 && circles[i + 1][j + 3].getTeam() == 0) {
                            return j + 3;
                        }
                    }
                }
            }
            return 999;
        }

        private int dontEnablePlayerHorizontalB() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i][j + 1].getTeam() == 1 &&
                            circles[i][j + 2].getTeam() == 1 && circles[i][j + 3].getTeam() == 1) {
                        if (i < rows - 2 && circles[i + 2][j].getTeam() != 0 && circles[i + 1][j].getTeam() == 0) {
                            return j;
                        } else if (i == rows - 2 && circles[i + 1][j].getTeam() == 0) {
                            return j;
                        }
                    }
                }
            }
            return 999;
        }

        private int dontEnablePlayerHorizontalC() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 2; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i][j + 1].getTeam() == 1 &&
                            circles[i][j + 2].getTeam() == 1) {
                        if (i < rows - 2 && circles[i + 1][j].getTeam() == 0 && circles[i + 2][j].getTeam() != 0) {
                            return j;
                        } else if (i == rows - 2 && circles[i + 1][j].getTeam() == 0) {
                            return j;
                        }
                    }
                }
            }
            return 999;
        }

        private int dontEnablePlayerHorizontalD() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 2; j++) {
                    if (circles[i][j].getTeam() == 1 && circles[i][j + 1].getTeam() == 0 &&
                            circles[i][j + 2].getTeam() == 1) {
                        if (i < rows - 2 && circles[i + 2][j + 1].getTeam() != 0 && circles[i + 1][j + 1].getTeam() == 0) {
                            return j + 1;
                        } else if (i == rows - 2 && circles[i + 1][j + 1].getTeam() == 0) {
                            return j + 1;
                        }
                    }
                }
            }
            return 999;
        }

        private int buildUpHorizontalA() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i][j + 1].getTeam() == 1 &&
                            circles[i][j + 2].getTeam() == 1 && circles[i][j + 3].getTeam() == 0) {
                        if (i != rows - 1 && circles[i + 1][j + 2].getTeam() != 0) {
                            return j;
                        } else if (i == rows - 1) {
                            return j;
                        }
                    }
                }
            }
            return 999;
        }

        private int buildUpHorizontalB() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i][j + 1].getTeam() == 0 &&
                            circles[i][j + 2].getTeam() == 0 && circles[i][j + 3].getTeam() == ai) {
                        if (i != rows - 1 && circles[i + 1][j + 1].getTeam() != 0) {
                            return j + 1;
                        } else if (i == rows - 1) {
                            return j + 1;
                        }
                    }
                }
            }
            return 999;
        }

        private int buildUpHorizontalC() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i][j + 1].getTeam() == ai &&
                            circles[i][j + 2].getTeam() == 0 && circles[i][j + 3].getTeam() == 0) {
                        if (i != rows - 1 && circles[i + 1][j + 2].getTeam() != 0) {
                            return j + 2;
                        } else if (i == rows - 1) {
                            return j + 2;
                        }
                    }
                }
            }
            return 999;
        }

        private int buildUpHorizontalD() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 3; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i][j + 1].getTeam() == 0 &&
                            circles[i][j + 2].getTeam() == ai && circles[i][j + 3].getTeam() == ai) {
                        if (i != rows - 1 && circles[i + 1][j + 1].getTeam() != 0) {
                            return j + 1;
                        } else if (i == rows - 1) {
                            return j + 1;
                        }
                    }
                }
            }
            return 999;
        }

        private int firstBuildUpHorizontalA() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 2; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i][j + 1].getTeam() == 0 &&
                            circles[i][j + 2].getTeam() == ai) {
                        if (i != rows - 1 && circles[i + 1][j + 1].getTeam() != 0) {
                            return j + 1;
                        } else if (i == rows - 1) {
                            return j + 1;
                        }
                    }
                }
            }
            return 999;
        }

        private int firstBuildUpHorizontalB() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 2; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i][j + 1].getTeam() == 0 &&
                            circles[i][j + 2].getTeam() == 0) {
                        if (i != rows - 1 && circles[i + 1][j + 1].getTeam() != 0) {
                            return j + 1;
                        } else if (i == rows - 1) {
                            return j + 1;
                        }
                    }
                }
            }
            return 999;
        }

        private int firstBuildUpHorizontalC() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns - 2; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i][j + 1].getTeam() == ai &&
                            circles[i][j + 2].getTeam() == 0) {
                        if (i != rows - 1 && circles[i + 1][j].getTeam() != 0) {
                            return j;
                        } else if (i == rows - 1) {
                            return j;
                        }
                    }
                }
            }
            return 999;
        }

        private int buildUpVertical() {
            for (int i = 0; i < rows - 2; i++) {
                for (int j = 0; j < columns; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i + 1][j].getTeam() == ai && circles[i + 2][j].getTeam() == ai) {
                        return j;
                    }
                }
            }
            return 999;
        }

        private int firstBuildUpVertical() {
            for (int i = 0; i < rows - 1; i++) {
                for (int j = 0; j < columns; j++) {
                    if (circles[i][j].getTeam() == 0 && circles[i + 1][j].getTeam() == ai) {
                        return j;
                    }
                }
            }
            return 999;
        }

        private int findBasicMove() {
            for (int i = 0; i < rows - 1; i++) {
                for (int j = 0; j < columns - 1; j++) {
                    if (circles[i][j].getTeam() == ai && circles[i + 1][j].getTeam() == ai) {
                        return j;
                    } else if (circles[i][j].getTeam() == ai && circles[i][j + 1].getTeam() == 0) {
                        return j + 1;
                    } else if (circles[i][j].getTeam() == 0 && circles[i][j + 1].getTeam() == ai) {
                        return j;
                    } else if (circles[i][j].getTeam() == 0 && circles[i + 1][j].getTeam() == ai) {
                        return j;
                    }
                }
            }
            return columns / 2;
        }
    }

}
