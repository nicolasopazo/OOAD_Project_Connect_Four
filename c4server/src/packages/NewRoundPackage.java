package packages;

import java.io.Serializable;

public record NewRoundPackage(int roundCount) implements Serializable {

    public int getRoundCount() {
        return roundCount;
    }
}
