package UDP_multithread_assignment_4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] buffer;

    public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public void sendThenReceive() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                // Reading input from the user
                System.out.print("Enter a message to send: ");
                String messageToSend = scanner.nextLine();

                // Converting the message to bytes
                buffer = messageToSend.getBytes();

                // Sending the packet to the server
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);

                // Receiving the response from the server
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(responsePacket);

                // Extracting the server's response
                String messageFromServer = new String(responsePacket.getData(), 0, responsePacket.getLength());
                System.out.println("The server says: " + messageFromServer);

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) throws IOException {
        // Creating a DatagramSocket
        DatagramSocket datagramSocket = new DatagramSocket();

        // Resolving the server's address
        InetAddress inetAddress = InetAddress.getByName("localhost");

        // Instantiating the client
        Client client = new Client(datagramSocket, inetAddress);

        System.out.println("Send UDP packets to a server.");
        client.sendThenReceive();
    }
}
