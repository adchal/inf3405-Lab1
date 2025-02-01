package Server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private int clientNumber;

    public ClientHandler(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        System.out.println("New connection with client#" + clientNumber + " at " + socket);
    }

    @Override
    public void run() {
        try {
            // Create output stream to send data to the client
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Hello from server - You are client #" + clientNumber); // Send a message to the client
        } catch (IOException e) {
            System.out.println("Error handling client #" + clientNumber + ": " + e.getMessage());
        } finally {
            try {
                socket.close(); // Close the client socket
            } catch (IOException e) {
                System.out.println("Failed to close socket for client #" + clientNumber);
            }
            System.out.println("Connection with client #" + clientNumber + " closed");
        }
    }
}
