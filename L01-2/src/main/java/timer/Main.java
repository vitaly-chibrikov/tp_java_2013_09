package timer;

import org.eclipse.jetty.server.Server;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new JettyServer());

        server.start();
        server.join();
    }
}

