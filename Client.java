import java.io.DataInputStream;
import java.net.Socket;

public class Client {
    private static Socket socket;

    public static void main(String[] args) throws Exception {
        String serverAddress = "127.0.0.1";
        int port = 5000;

        // Connexion au serveur
        socket = new Socket(serverAddress, port);
        System.out.format("Connecté au serveur [%s:%d]\n", serverAddress, port);

        // Canal de réception des messages du serveur
        DataInputStream in = new DataInputStream(socket.getInputStream());
        String messageDuServeur = in.readUTF();
        System.out.println("Message du serveur: " + messageDuServeur);

        // Fermeture de la connexion
        socket.close();
    }
}
