/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Chương trình TCP Client sử dụng đa luồng để giao tiếp đồng thời với server.
 * Tác giả: dinhhuy
 */
public class Assignment_5_Client {
    public static void main(String arg[]) throws IOException {
        // Kết nối tới server thông qua địa chỉ "localhost" và cổng 1234
        Socket s = new Socket("localhost", 1234);

        // Tạo luồng đọc/ghi dữ liệu để giao tiếp với server
        DataInputStream in = new DataInputStream(s.getInputStream()); // Nhận dữ liệu từ server
        DataOutputStream out = new DataOutputStream(s.getOutputStream()); // Gửi dữ liệu tới server

        // Tạo hai luồng riêng biệt: một để gửi dữ liệu và một để nhận dữ liệu
        Assignment_5_Receiving_thread_Client t1 = new Assignment_5_Receiving_thread_Client(s); // Luồng nhận
        Assignment_5_Sending_thread_Client t2 = new Assignment_5_Sending_thread_Client(s); // Luồng gửi

        // Khởi chạy cả hai luồng
        t1.start(); // Khởi chạy luồng nhận dữ liệu từ server
        t2.start(); // Khởi chạy luồng gửi dữ liệu tới server
    }
}
