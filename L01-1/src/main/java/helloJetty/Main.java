package helloJetty;

import org.eclipse.jetty.server.Server;

public class Main {

    public static void main(String[] args) throws Exception {
        simpleServer();
    }

    private static void simpleServer() throws Exception {
        Server server = new Server(8080);
        server.setHandler(new JettyServer());

        server.start();
        server.join();
    }
}

