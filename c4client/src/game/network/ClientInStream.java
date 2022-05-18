package game.network;

import packages.PackageHandler;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientInStream implements Runnable {

    private final ObjectInputStream in;
    private boolean running = true;
    private final PackageHandler handler;

    public ClientInStream(ObjectInputStream in, PackageHandler handler) {
        this.in = in;
        this.handler = handler;
    }

    @Override
    public void run() {
        while (running) {
            try {
                handler.unpack(in.readObject());
            } catch (IOException | ClassNotFoundException e) {
                close();
            }
        }
    }

    private void close() {
        try {
            in.close();
            running = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
