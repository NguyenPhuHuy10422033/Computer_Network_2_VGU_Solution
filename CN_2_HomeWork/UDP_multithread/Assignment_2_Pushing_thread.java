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
public class Assignment_2_Pushing_thread extends Thread {

    // Phương thức nhận dữ liệu từ client
    static DatagramPacket nhan(DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm dữ liệu để nhận
        DatagramPacket p = new DatagramPacket(buff, buff.length); // Tạo gói dữ liệu để nhận
        s.receive(p); // Nhận dữ liệu qua socket
        return p; // Trả về gói dữ liệu đã nhận
    }

    Queue<DatagramPacket> Shared_List; // Danh sách chia sẻ giữa các luồng
    DatagramSocket sv; // Socket Datagram của server

    // Constructor nhận danh sách chia sẻ và socket
    public Assignment_2_Pushing_thread(DatagramSocket socket, Queue<DatagramPacket> list) {
        sv = socket; // Gán socket cho biến sv
        Shared_List = list; // Gán danh sách chia sẻ cho biến Shared_List
    }

    // Phương thức run thực thi logic nhận gói dữ liệu và thêm vào Shared_List
    public void run() {
        DatagramPacket p = null; // Khởi tạo gói dữ liệu
        while (true) {
            try {
                p = nhan(sv); // Nhận dữ liệu từ client
            } catch (IOException ex) {
                // Xử lý ngoại lệ trong trường hợp lỗi nhận dữ liệu
            }
            Shared_List.add(p); // Thêm gói dữ liệu vào danh sách chia sẻ
        }
    }
}
