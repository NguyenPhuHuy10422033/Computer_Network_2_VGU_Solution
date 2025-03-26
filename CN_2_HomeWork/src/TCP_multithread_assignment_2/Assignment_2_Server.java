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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Assignment_2_Server {
    private static final int NTHREADS = 100; // Maximum threads for this program
    private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(1234);
        System.out.println("Server started. Waiting for clients...");

        while (true) {
            final Socket connection = socket.accept();
            Assignment_2_Handle_Request each_client = new Assignment_2_Handle_Request(connection);
            exec.execute(each_client);
        }
    }
}

