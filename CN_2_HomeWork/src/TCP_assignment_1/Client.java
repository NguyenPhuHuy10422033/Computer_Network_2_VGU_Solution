/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TCP_assignment_1;

/**
 *
 * @author DELL
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Step 1: Connect to Server
            Socket socket = new Socket("localhost", 1234);

            // Step 2: Create Data Streams
            BufferedReader networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter networkOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner keyboard = new Scanner(System.in);

            // Step 3: Implement the Functions of the Client
            System.out.println("Please input a string:");
            String data = keyboard.nextLine();
            networkOut.write(data + "\r\n");
            networkOut.flush();

            String result = networkIn.readLine();
            System.out.println("Data from Server: " + result);

            // Step 4: Close the Socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

/*
Please input a string:
abc
Data from Server: ABC
*/