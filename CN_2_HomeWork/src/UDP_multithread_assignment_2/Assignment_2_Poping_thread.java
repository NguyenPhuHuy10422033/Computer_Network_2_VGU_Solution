/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UDP_multithread_assignment_2;

/**
 *
 * @author DELL
 */
import java.net.*;
import java.util.*;

public class Assignment_2_Poping_thread extends Thread {
    Queue<DatagramPacket> Shared_List;
    ArrayList<DatagramPacket> Local_List;
    DatagramSocket sv;

    public Assignment_2_Poping_thread(DatagramSocket socket, Queue<DatagramPacket> list) {
        sv = socket;
        Shared_List = list;
        Local_List = new ArrayList<>();
    }

    public void run() {
        while (true) {
            try {
                DatagramPacket packet;
                synchronized (Shared_List) {
                    while (Shared_List.isEmpty()) {
                        Shared_List.wait();
                    }
                    packet = Shared_List.poll();
                }

                String receivedData = new String(packet.getData(), 0, packet.getLength());
                String clientKey = packet.getAddress().toString() + ":" + packet.getPort();

                synchronized (Local_List) {
                    Local_List.add(packet);

                    if (countPackets(clientKey) == 3) {
                        int[] nums = extractNumbers(clientKey);
                        int lcmResult = calculateLCM(nums[0], nums[1], nums[2]);

                        sendData(String.valueOf(lcmResult), packet.getAddress(), packet.getPort());
                        removePackets(clientKey);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int countPackets(String clientKey) {
        int count = 0;
        for (DatagramPacket p : Local_List) {
            if ((p.getAddress().toString() + ":" + p.getPort()).equals(clientKey)) {
                count++;
            }
        }
        return count;
    }

    private int[] extractNumbers(String clientKey) {
        int[] nums = new int[3];
        int index = 0;
        for (DatagramPacket p : Local_List) {
            if ((p.getAddress().toString() + ":" + p.getPort()).equals(clientKey) && index < 3) {
                nums[index++] = Integer.parseInt(new String(p.getData(), 0, p.getLength()));
            }
        }
        return nums;
    }

    private void removePackets(String clientKey) {
        Local_List.removeIf(p -> (p.getAddress().toString() + ":" + p.getPort()).equals(clientKey));
    }

    private void sendData(String message, InetAddress address, int port) throws Exception {
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        sv.send(sendPacket);
    }

    private int calculateLCM(int a, int b, int c) {
        return lcm(lcm(a, b), c);
    }

    private int lcm(int x, int y) {
        return (x * y) / gcd(x, y);
    }

    private int gcd(int x, int y) {
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }
}

