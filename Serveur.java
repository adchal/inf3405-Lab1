import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Serveur {
    private static ServerSocket Listener;

    public static void main(String[] args) throws Exception {
        int clientNumber = 0; // Compteur de clients connectés
        String serverAddress = "127.0.0.1";
        int serverPort = 5000;

        Listener = new ServerSocket();
        Listener.setReuseAddress(true);
        InetAddress serverIP = InetAddress.getByName(serverAddress);
        Listener.bind(new InetSocketAddress(serverIP, serverPort));

        System.out.format("Le serveur fonctionne sur %s:%d%n", serverAddress, serverPort);

        try {
            while (true) {
                // Lorsqu'un client se connecte, un thread `ClientHandler` est lancé
                new ClientHandler(Listener.accept(), clientNumber++).start();
            }
        } finally {
            Listener.close(); // Fermeture du serveur proprement
        }
    }
}
