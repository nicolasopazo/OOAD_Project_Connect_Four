package packages;

import java.io.Serializable;

public record ClientMessage(String message) implements Serializable {

    public String getMessage() {
        return message;
    }

}
