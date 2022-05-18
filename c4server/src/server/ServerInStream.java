package server;

import packages.PackageHandler;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ServerInStream implements Runnable {

    private final ObjectInputStream in;
    private final PackageHandler handler;
    private boolean running = true;

    public ServerInStream(ObjectInputStream in, PackageHandler handler) {
        this.handler = handler;
        this.in = in;
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
