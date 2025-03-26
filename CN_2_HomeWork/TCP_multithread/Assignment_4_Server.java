/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Chương trình TCP Server hỗ trợ đa client bằng cách sử dụng ExecutorService để quản lý luồng.
 * Tác giả: HUY.TD
 */
public class Assignment_4_Server {
    private static final int NTHREADS = 100; // Số luồng tối đa được chương trình hỗ trợ cùng lúc
    private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS); // Tạo ThreadPool với giới hạn số luồng là 100
        
    public static void main(String[] args) throws IOException {
        // Tạo ServerSocket lắng nghe kết nối từ client tại cổng 1234
        ServerSocket socket = new ServerSocket(1234);     
        while (true) { 
            // Chấp nhận kết nối từ client
            Socket connection = socket.accept();
            
            // Tạo đối tượng xử lý yêu cầu cho từng client và chạy trên luồng riêng
            Assignment_4_Handle_Request each_client = new Assignment_4_Handle_Request(connection);
            exec.execute(each_client); // Thực thi yêu cầu của client trong một luồng riêng biệt
        } 
    } 
}
