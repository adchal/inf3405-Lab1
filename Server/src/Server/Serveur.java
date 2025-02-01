package Server;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Serveur {
    private static ServerSocket listener; // Server socket to listen for incoming connections

    public static void main(String[] args) throws Exception {
        int clientNumber = 0; // Counter for connected clients
        String serverAddress = "127.0.0.1"; // Server address (localhost)
        int serverPort = 5000; // Port the server listens on

        // Initialize and bind the server socket
        listener = new ServerSocket();
        listener.setReuseAddress(true);
        InetAddress serverIP = InetAddress.getByName(serverAddress);
        listener.bind(new InetSocketAddress(serverIP, serverPort));

        System.out.format("Server is running on %s:%d%n", serverAddress, serverPort);

        try {
            // Continuously accept client connections and spawn new threads to handle them
            while (true) {
                new ClientHandler(listener.accept(), clientNumber++).start(); // Start a new client handler thread
            }
        } finally {
            listener.close(); // Close the server socket when done
        }
    }
}
