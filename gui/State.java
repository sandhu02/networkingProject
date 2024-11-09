package gui;

import networking.*;

public class State {
    public static boolean onServer;
    public static Client client;
    public static Server server;

    public static void setClient(Client Argclient) {
        client = Argclient;
        server = null;
        onServer = false;
    }
    public static void setServer (Server Argserver) {
        server = Argserver;
        client = null;

        onServer = true;
    }
}
