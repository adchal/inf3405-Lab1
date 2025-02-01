import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private int clientNumber;

    public ClientHandler(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        System.out.println("Nouvelle connexion avec le client#" + clientNumber + " à " + socket);
    }

    public void run() {
        try {
            // Création du canal de sortie pour envoyer un message au client
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Bonjour depuis le serveur - Vous êtes le client #" + clientNumber);
        } catch (IOException e) {
            System.out.println("Erreur avec le client #" + clientNumber + ": " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Impossible de fermer la connexion du client #" + clientNumber);
            }
            System.out.println("Connexion avec le client #" + clientNumber + " fermée");
        }
    }
}
