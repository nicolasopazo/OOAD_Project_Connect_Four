package packages;

import java.io.Serializable;

public record MovePackage(int chosenColumn) implements Serializable {

    public Object getMove() {
        return chosenColumn;
    }
}
