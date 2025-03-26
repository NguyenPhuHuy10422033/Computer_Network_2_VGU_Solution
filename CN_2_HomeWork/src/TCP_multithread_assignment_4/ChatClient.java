/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TCP_multithread_assignment_4;

/**
 *
 * @author DELL
 */
import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT)) {
            System.out.println("Connected to Group Chat. Type messages and press Enter to send.");

            // Send "hello" to join the chat
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("hello");

            // Start sender and receiver threads
            new Thread(new MessageSender(out)).start();
            new Thread(new MessageReceiver(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Thread for sending messages to the server
class MessageSender implements Runnable {
    private PrintWriter out;
    private BufferedReader keyboardInput;

    public MessageSender(PrintWriter out) {
        this.out = out;
        this.keyboardInput = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = keyboardInput.readLine()) != null) {
                out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Connection closed.");
        }
    }
}

// Thread for receiving messages from the server
class MessageReceiver implements Runnable {
    private BufferedReader in;

    public MessageReceiver(Socket socket) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Connection closed.");
        }
    }
}

