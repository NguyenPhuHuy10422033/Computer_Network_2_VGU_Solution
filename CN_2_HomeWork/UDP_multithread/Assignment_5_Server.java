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
 * Chương trình UDP Server xử lý trò chơi dự đoán số bí mật qua đa luồng.
 * Tác giả: HUY.TD
 */
public class Assignment_5_Server {

    public static void main(String[] s) throws IOException {
        // Tạo socket server để lắng nghe trên cổng 1234
        DatagramSocket sv = new DatagramSocket(1234);

        // Tạo số bí mật ngẫu nhiên từ 0 đến 99
        int R = (int) (Math.random() * 100);
        System.out.println("R= " + R); // Hiển thị số bí mật trong console để kiểm tra

        // Tạo danh sách chia sẻ để lưu gói dữ liệu nhận được
        LinkedBlockingQueue<DatagramPacket> Shared_List = new LinkedBlockingQueue<>();

        // Tạo các luồng:
        // Luồng nhận dữ liệu từ client
        Assignment_5_Pushing_thread push = new Assignment_5_Pushing_thread(sv, Shared_List);

        // Luồng xử lý dữ liệu và gửi phản hồi
        Assignment_5_Poping_thread pop = new Assignment_5_Poping_thread(sv, R, Shared_List);

        // Luồng xử lý hết thời gian trò chơi
        Assignment_5_time_out_thread time_out = new Assignment_5_time_out_thread(sv);

        // Khởi chạy các luồng
        push.start(); // Luồng nhận dữ liệu
        pop.start();  // Luồng xử lý dữ liệu và phản hồi
        time_out.start(); // Luồng xử lý khi hết thời gian
    }
}
