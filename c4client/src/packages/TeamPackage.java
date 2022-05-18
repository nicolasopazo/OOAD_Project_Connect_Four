package packages;

import java.io.Serializable;

public record TeamPackage(int team) implements Serializable {

    public int getTeam() {
        return team;
    }
}
