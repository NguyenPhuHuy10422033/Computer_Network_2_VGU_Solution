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
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket sv = new DatagramSocket(1234);
            LinkedBlockingQueue<DatagramPacket> Shared_List = new LinkedBlockingQueue<>();

            Assignment_2_Pushing_thread push = new Assignment_2_Pushing_thread(sv, Shared_List);
            Assignment_2_Poping_thread pop = new Assignment_2_Poping_thread(sv, Shared_List);

            push.start();
            pop.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

