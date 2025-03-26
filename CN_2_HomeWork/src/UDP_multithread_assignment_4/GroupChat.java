package UDP_multithread_assignment_4;

import java.net.*;
import java.io.*;
import java.util.*;

public class GroupChat {
    private static final String TERMINATE = "Exit";
    static String name;
    static volatile boolean finished = false;

    public static void main(String[] args) {
        String multicastHost = "230.0.0.0"; // Địa chỉ multicast mặc định
        int port = 4446; // Số cổng mặc định

        if (args.length == 2) {
            multicastHost = args[0];
            port = Integer.parseInt(args[1]);
        } else {
            System.out.println("Using default multicast host and port: " + multicastHost + " " + port);
        }

        try {
            InetAddress group = InetAddress.getByName(multicastHost);
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your name: ");
            name = sc.nextLine();
            MulticastSocket socket = new MulticastSocket(port);

            // Since we are deploying
            socket.setTimeToLive(0);
            // this on localhost only (For a subnet set it as 1)

            socket.joinGroup(group);
            Thread t = new Thread(new ReadThread(socket, group, port));

            // Spawn a thread for reading messages
            t.start();

            // sent to the current group
            System.out.println("Start typing messages...\n");
            while (true) {
                String message;
                message = sc.nextLine();
                if (message.equalsIgnoreCase(GroupChat.TERMINATE)) {
                    finished = true;
                    socket.leaveGroup(group);
                    socket.close();
                    break;
                }
                message = name + ": " + message;
                byte[] buffer = message.getBytes();
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, port);
                socket.send(datagram);
            }
        } catch (SocketException se) {
            System.out.println("Error creating socket");
            se.printStackTrace();
        } catch (IOException ie) {
            System.out.println("Error reading/writing from/to socket");
            ie.printStackTrace();
        }
    }
}

class ReadThread implements Runnable {
    private MulticastSocket socket;
    private InetAddress group;
    private int port;
    private static final int MAX_LEN = 1000;

    ReadThread(MulticastSocket socket, InetAddress group, int port) {
        this.socket = socket;
        this.group = group;
        this.port = port;
    }

    @Override
    public void run() {
        while (!GroupChat.finished) {
            byte[] buffer = new byte[MAX_LEN];
            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, port);
            String message;
            try {
                socket.receive(datagram);
                message = new String(buffer, 0, datagram.getLength(), "UTF-8");
                if (!message.startsWith(GroupChat.name))
                    System.out.println(message);
            } catch (IOException e) {
                System.out.println("Socket closed!");
            }
        }
    }
}