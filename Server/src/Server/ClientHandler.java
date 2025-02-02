package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private int clientNumber;

    public ClientHandler(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        System.out.println("New connection with client# " + clientNumber + " at " + socket);
    }

    @Override
    public void run() {
        try {

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());


            String utilisateur = in.readUTF();
            String password = in.readUTF();

            System.out.println("Received from client# " + clientNumber + ":");
            System.out.println("Utilisateur: " + utilisateur);
            System.out.println("Password: " + password);


            String response = "Hello " + utilisateur + "! We got your password (" + password + ").";


            out.writeUTF(response);
            System.out.println("Sent to client# " + clientNumber + ": " + response);


            socket.close();
            System.out.println("Connection with client# " + clientNumber + " closed.");
        } catch (Exception e) {
            System.out.println("Error handling client# " + clientNumber + ": " + e);
        }
    }
}