package Client;
import java.io.DataInputStream;
import java.net.Socket;

public class Client {
    private static Socket socket;

    public static void main(String[] args) throws Exception {
        String serverAddress = "127.0.0.1"; // The address of the server (localhost)
        int port = 5000; // The port to connect to

        // Connect to the server
        socket = new Socket(serverAddress, port);
        System.out.format("Connected to server [%s:%d]\n", serverAddress, port);

        // Create an input stream to receive data from the server
        DataInputStream in = new DataInputStream(socket.getInputStream());
        String serverMessage = in.readUTF(); // Read the message from the server
        System.out.println("Message from server: " + serverMessage);

        // Close the connection after receiving the message
        socket.close();
    }
}
