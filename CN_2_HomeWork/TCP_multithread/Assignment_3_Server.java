/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Chương trình TCP Server sử dụng đa luồng để xử lý việc gửi và nhận dữ liệu đồng thời với client.
 * Tác giả: HUY.TD
 */
public class Assignment_3_Server {
    
    public static void main(String s[]) throws IOException {
        // Tạo đối tượng ServerSocket lắng nghe kết nối từ client tại cổng 1234
        ServerSocket ss = new ServerSocket(1234);
        
        // Chấp nhận kết nối từ client
        Socket sv = ss.accept();
        
        // Tạo các luồng đọc/ghi để giao tiếp với client
        DataInputStream nhan = new DataInputStream(sv.getInputStream()); // Nhận dữ liệu từ client
        DataOutputStream gui = new DataOutputStream(sv.getOutputStream()); // Gửi dữ liệu tới client

        // Tạo và khởi chạy hai luồng riêng biệt: một để gửi dữ liệu và một để nhận dữ liệu
        Assignment_3_Sending_thread sending = new Assignment_3_Sending_thread(sv); // Luồng gửi
        Assignment_3_Receiving_thread receiving = new Assignment_3_Receiving_thread(sv); // Luồng nhận
        
        sending.start(); // Khởi chạy luồng gửi
        receiving.start(); // Khởi chạy luồng nhận
    }
}
