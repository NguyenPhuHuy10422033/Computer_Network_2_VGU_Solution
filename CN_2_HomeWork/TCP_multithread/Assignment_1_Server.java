/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Chương trình TCP Server đa luồng thực hiện xử lý nhiều client đồng thời.
 * Tác giả: HUY.TD
 */
public class Assignment_1_Server {

    private static final int NTHREADS = 100; // Số lượng luồng tối đa cho chương trình
    private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS); // Tạo một ThreadPool giới hạn số luồng là 100

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(1234); // Tạo ServerSocket để lắng nghe kết nối từ client
        while (true) { 
            final Socket connection = socket.accept(); // Chấp nhận kết nối từ client
            Assignment_1_Handle_Request each_client = new Assignment_1_Handle_Request(connection); // Tạo đối tượng xử lý từng client
            exec.execute(each_client); // Thực thi client trong một luồng riêng biệt
        }
    }
}
