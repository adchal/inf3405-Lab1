package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.*;
import java.io.DataInputStream;
import java.net.Socket;

public class Client {
    private static Socket socket;
    private static JTextArea messageServeur;
    private static JTextField utilisateur;
    private static JTextField password;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Client");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            messageServeur = new JTextArea();
            messageServeur.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(messageServeur);
            frame.add(scrollPane, BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));  // Vertical layout

            JPanel utilisateurPanel = new JPanel();
            utilisateurPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel utilisateurLabel = new JLabel("Utilisateur: ");
            utilisateur = new JTextField(20);
            utilisateurPanel.add(utilisateurLabel);
            utilisateurPanel.add(utilisateur);

            JPanel passwordPanel = new JPanel();
            passwordPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel passwordLabel = new JLabel("Password: ");
            password = new JTextField(20);
            passwordPanel.add(passwordLabel);
            passwordPanel.add(password);
            
            
            JButton sendButton = new JButton("Send");
            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String user = utilisateur.getText();
                    String pass = password.getText();
                    sendCredentialsToServer(user, pass);
                }
            });
            

            bottomPanel.add(utilisateurPanel);
            bottomPanel.add(passwordPanel);
            bottomPanel.add(sendButton);
            
            frame.add(bottomPanel, BorderLayout.SOUTH);

            frame.setVisible(true);

            new Thread(() -> {
                try {
                    String serverAddress = "127.0.0.1";
                    int port = 5000;

                    socket = new Socket(serverAddress, port);
                    System.out.format("Connected to server [%s:%d]\n", serverAddress, port);

                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    String serverMessage = in.readUTF();

                    SwingUtilities.invokeLater(() -> messageServeur.append(serverMessage));

                    System.out.println(serverMessage);

                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }
    
    private static void sendCredentialsToServer(String user, String pass) {
    	new Thread(()->{
    		try {
    			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    			out.writeUTF(user);
                out.writeUTF(pass);
                out.flush();
                
                DataInputStream in= new DataInputStream(socket.getInputStream());
                String serverResponse = in.readUTF();
                SwingUtilities.invokeLater(() -> messageServeur.append("Server response: " + serverResponse + "\n"));
              
    		} catch (Exception e) {
                e.printStackTrace();
            }
    		}).start();  
    
    }
    
}
