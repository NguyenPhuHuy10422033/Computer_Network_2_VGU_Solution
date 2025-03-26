package UDP_multithread_assignment_4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    private DatagramSocket datagramSocket;
    private byte[] buffer = new byte[256];

    public Server(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public void receiveThenSend() {
        while (true) {
            try {
                // Receiving a packet
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);

                // Extracting data
                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

                System.out.println("Message from client: " + messageFromClient);

                // Sending a response
                String responseMessage = "Echo: " + messageFromClient;
                DatagramPacket responsePacket = new DatagramPacket(responseMessage.getBytes(), responseMessage.length(), inetAddress, port);
                datagramSocket.send(responsePacket);

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(1234); // Port number
        Server server = new Server(socket);
        server.receiveThenSend();
    }
}
