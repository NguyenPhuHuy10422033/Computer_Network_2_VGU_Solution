/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Chương trình UDP Server sử dụng đa luồng để nhận và chuyển tiếp dữ liệu giữa các client.
 * Tác giả: HUY.TD
 */
public class Assignment_4_Server {
    public static void main(String[] s) throws IOException {
        // Tạo socket server trên cổng 1234 để lắng nghe dữ liệu từ các client
        DatagramSocket sv = new DatagramSocket(1234);

        // Tạo danh sách chia sẻ (thread-safe) để lưu trữ các gói dữ liệu nhận được
        LinkedBlockingQueue<DatagramPacket> Shared_List = new LinkedBlockingQueue<>();

        // Tạo luồng nhận dữ liệu từ client (Pushing Thread)
        Assignment_4_Pushing_thread push = new Assignment_4_Pushing_thread(sv, Shared_List);

        // Tạo luồng xử lý và chuyển tiếp dữ liệu tới các client (Poping Thread)
        Assignment_4_Poping_thread pop = new Assignment_4_Poping_thread(sv, Shared_List);

        // Khởi chạy cả hai luồng
        push.start(); // Luồng nhận dữ liệu từ các client
        pop.start();  // Luồng xử lý và gửi dữ liệu tới client khác
    }
}
