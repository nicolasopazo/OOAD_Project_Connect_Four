import server.Server;

public class RunServer {

    public static void main(String[] args) {
        Server server = new Server(5555);
        server.start();
    }

}
