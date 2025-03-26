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
 * Chương trình UDP Server sử dụng đa luồng để nhận và xử lý dữ liệu từ client.
 * Tác giả: HUY.TD
 */
public class Assignment_2_Server {

    public static void main(String[] s) throws IOException {
        // Tạo socket server trên cổng 1234 để lắng nghe gói dữ liệu từ client
        DatagramSocket sv = new DatagramSocket(1234);

        // Tạo danh sách chia sẻ (thread-safe) để lưu trữ các gói dữ liệu nhận được
        LinkedBlockingQueue<DatagramPacket> Shared_List = new LinkedBlockingQueue<>();

        // Tạo luồng nhận dữ liệu từ client (Pushing Thread)
        Assignment_2_Pushing_thread push = new Assignment_2_Pushing_thread(sv, Shared_List);

        // Tạo luồng xử lý dữ liệu từ danh sách chia sẻ và gửi phản hồi (Poping Thread)
        Assignment_2_Poping_thread pop = new Assignment_2_Poping_thread(sv, Shared_List);

        // Khởi chạy cả hai luồng
        push.start(); // Luồng nhận dữ liệu
        pop.start();  // Luồng xử lý dữ liệu
    }
}
