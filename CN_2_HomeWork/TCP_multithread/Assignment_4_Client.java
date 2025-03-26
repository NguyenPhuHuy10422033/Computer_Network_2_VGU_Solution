/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Chương trình TCP Client sử dụng đa luồng để gửi và nhận dữ liệu đồng thời với server.
 * Tác giả: HUY.TD
 */
public class Assignment_4_Client {

    public static void main(String s[]) throws IOException {
        // Kết nối tới server thông qua địa chỉ "localhost" và cổng 1234
        Socket cl = new Socket("localhost", 1234);

        // Tạo các luồng nhập/xuất để giao tiếp với server
        DataInputStream nhan = new DataInputStream(cl.getInputStream()); // Nhận dữ liệu từ server
        DataOutputStream gui = new DataOutputStream(cl.getOutputStream()); // Gửi dữ liệu tới server

        // Tạo hai luồng riêng biệt: một để gửi dữ liệu và một để nhận dữ liệu
        Assignment_4_Sending_thread_Client sending = new Assignment_4_Sending_thread_Client(cl); // Luồng gửi
        Assignment_4_Receiving_thread_Client receiving = new Assignment_4_Receiving_thread_Client(cl); // Luồng nhận

        // Bắt đầu cả hai luồng
        sending.start(); // Khởi chạy luồng gửi dữ liệu
        receiving.start(); // Khởi chạy luồng nhận dữ liệu
    }
}
