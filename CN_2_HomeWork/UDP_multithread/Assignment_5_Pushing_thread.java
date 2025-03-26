/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Queue;

/**
 * Luồng nhận dữ liệu từ client và thêm vào danh sách chia sẻ (Shared_List) trong chương trình UDP đa luồng.
 * Tác giả: HUY.TD
 */
public class Assignment_5_Pushing_thread extends Thread {

    // Phương thức nhận dữ liệu từ client qua socket Datagram
    static DatagramPacket nhan(DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm lưu dữ liệu nhận
        DatagramPacket p = new DatagramPacket(buff, buff.length); // Tạo gói dữ liệu để nhận
        s.receive(p); // Nhận dữ liệu qua socket
        return p; // Trả về gói dữ liệu nhận được
    }

    Queue<DatagramPacket> Shared_List; // Danh sách chia sẻ chứa các gói dữ liệu nhận được
    DatagramSocket sv; // Socket server

    // Constructor khởi tạo luồng với socket và danh sách chia sẻ
    public Assignment_5_Pushing_thread(DatagramSocket socket, Queue<DatagramPacket> list) {
        sv = socket; // Gán socket server
        Shared_List = list; // Gán danh sách chia sẻ
    }

    // Phương thức run thực thi logic nhận dữ liệu
    public void run() {
        DatagramPacket p = null; // Biến lưu trữ gói dữ liệu nhận
        while (true) {
            try {
                // Nhận gói dữ liệu từ client
                p = nhan(sv); 
            } catch (IOException ex) {
                // Xử lý ngoại lệ nếu xảy ra lỗi trong quá trình nhận
            }
            Shared_List.add(p); // Thêm gói dữ liệu vào danh sách chia sẻ
        }
    }
}
