/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TCP_multithread_assignment_2;

/**
 *
 * @author DELL
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 1234);
            DataInputStream Network_in = new DataInputStream(s.getInputStream());
            DataOutputStream Network_out = new DataOutputStream(s.getOutputStream());
            Scanner keyboard = new Scanner(System.in);

            System.out.println("Please input three integers:");
            int data1 = keyboard.nextInt();
            int data2 = keyboard.nextInt();
            int data3 = keyboard.nextInt();

            Network_out.writeInt(data1);
            Network_out.writeInt(data2);
            Network_out.writeInt(data3);

            int LCM = Network_in.readInt();
            System.out.println("LCM of three integers is: " + LCM);

            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

