package Server;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Serveur {
    private static ServerSocket listener; 

    public static void main(String[] args) throws Exception {
        int clientNumber = 0;
        String serverAddress = "127.0.0.1"; 
        int serverPort = 5000;


        listener = new ServerSocket();
        listener.setReuseAddress(true);
        InetAddress serverIP = InetAddress.getByName(serverAddress);
        listener.bind(new InetSocketAddress(serverIP, serverPort));

        System.out.format("Server is running on %s:%d%n", serverAddress, serverPort);

        try {
            while (true) {
                new ClientHandler(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close(); 
        }
    }
}
