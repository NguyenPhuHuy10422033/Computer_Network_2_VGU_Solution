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
import java.util.Queue;

public class Assignment_2_Pushing_thread extends Thread {
    Queue<DatagramPacket> Shared_List;
    DatagramSocket sv;

    public Assignment_2_Pushing_thread(DatagramSocket socket, Queue<DatagramPacket> list) {
        sv = socket;
        Shared_List = list;
    }

    public void run() {
        while (true) {
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket p = new DatagramPacket(buffer, buffer.length);
                sv.receive(p);

                synchronized (Shared_List) {
                    Shared_List.add(p);
                    Shared_List.notify();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

