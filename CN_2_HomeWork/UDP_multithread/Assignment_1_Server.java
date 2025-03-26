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
 * Chương trình UDP Server đa luồng để nhận và xử lý các gói dữ liệu từ client.
 * Tác giả: HUY.TD
 */
public class Assignment_1_Server {

    public static void main(String[] s) throws IOException {
        // Tạo socket server để lắng nghe các gói dữ liệu từ client trên cổng 1234
        DatagramSocket sv = new DatagramSocket(1234);

        // Tạo hàng đợi chia sẻ (thread-safe) để lưu trữ các gói dữ liệu nhận được
        LinkedBlockingQueue<DatagramPacket> Shared_List = new LinkedBlockingQueue<>();

        // Tạo luồng đẩy (Pushing Thread) để nhận dữ liệu từ client và thêm vào hàng đợi
        Assignment_1_Pushing_thread push = new Assignment_1_Pushing_thread(sv, Shared_List);

        // Tạo luồng lấy (Poping Thread) để xử lý dữ liệu trong hàng đợi và gửi phản hồi tới client
        Assignment_1_Poping_thread pop = new Assignment_1_Poping_thread(sv, Shared_List);

        // Khởi chạy cả hai luồng
        push.start(); // Luồng nhận dữ liệu từ client
        pop.start();  // Luồng xử lý và gửi phản hồi
    }
}
