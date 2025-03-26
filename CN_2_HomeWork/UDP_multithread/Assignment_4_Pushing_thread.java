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
 * Luồng nhận dữ liệu từ client qua UDP và thêm vào danh sách chia sẻ (Shared_List).
 * Tác giả: HUY.TD
 */
public class Assignment_4_Pushing_thread extends Thread {

    // Phương thức nhận gói dữ liệu từ client qua socket
    static DatagramPacket nhan(DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm lưu dữ liệu
        DatagramPacket p = new DatagramPacket(buff, buff.length); // Tạo gói nhận dữ liệu
        s.receive(p); // Nhận dữ liệu qua socket
        return p; // Trả về gói dữ liệu đã nhận
    }

    Queue<DatagramPacket> Shared_List; // Danh sách chia sẻ chứa các gói dữ liệu
    DatagramSocket sv; // Socket Datagram của server

    // Constructor khởi tạo danh sách và socket
    public Assignment_4_Pushing_thread(DatagramSocket socket, Queue<DatagramPacket> list) {
        sv = socket; // Gán socket cho biến sv
        Shared_List = list; // Gán danh sách chia sẻ
    }

    // Phương thức run thực thi logic nhận dữ liệu
    public void run() {
        DatagramPacket p = null; // Khởi tạo biến lưu gói dữ liệu
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
