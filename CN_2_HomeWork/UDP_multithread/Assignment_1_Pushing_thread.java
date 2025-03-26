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
 * Luồng xử lý nhận dữ liệu từ client qua UDP và thêm vào danh sách chia sẻ.
 * Tác giả: HUY.TD
 */
public class Assignment_1_Pushing_thread extends Thread {

    // Phương thức nhận dữ liệu từ client
    static DatagramPacket nhan(DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm dữ liệu
        DatagramPacket p = new DatagramPacket(buff, buff.length); // Tạo gói dữ liệu để nhận
        s.receive(p); // Nhận dữ liệu qua socket
        return p; // Trả về gói dữ liệu
    }

    Queue<DatagramPacket> Shared_List; // Danh sách chia sẻ (dùng chung giữa các luồng)
    DatagramSocket sv; // Socket Datagram của server

    // Constructor nhận socket và danh sách chia sẻ
    public Assignment_1_Pushing_thread(DatagramSocket socket, Queue<DatagramPacket> list) {
        sv = socket; // Gán socket cho biến sv
        Shared_List = list; // Gán danh sách chia sẻ cho biến Shared_List
    }

    // Phương thức run thực thi logic nhận gói dữ liệu
    public void run() {
        DatagramPacket p = null; // Khởi tạo gói dữ liệu
        while (true) {
            try {
                p = nhan(sv); // Nhận gói dữ liệu từ client
            } catch (IOException ex) {
                // Xử lý ngoại lệ nếu xảy ra lỗi
            }
            Shared_List.add(p); // Thêm gói dữ liệu vào danh sách chia sẻ
        }
    }
}
