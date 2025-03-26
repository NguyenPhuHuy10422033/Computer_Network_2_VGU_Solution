/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luồng xử lý dữ liệu UDP từ danh sách chia sẻ và gửi phản hồi tới client.
 * Tác giả: HUY.TD
 */
public class Assignment_1_Poping_thread extends Thread {

    // Phương thức gửi dữ liệu tới client
    static void gui(String data, InetAddress add, int port, DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm dữ liệu
        buff = data.getBytes(); // Chuyển chuỗi dữ liệu thành mảng byte
        DatagramPacket p = new DatagramPacket(buff, buff.length, add, port); // Tạo gói dữ liệu
        s.send(p); // Gửi gói dữ liệu qua socket
    }

    Queue<DatagramPacket> Shared_List; // Danh sách chia sẻ (dùng chung giữa các luồng)
    ArrayList<DatagramPacket> Local_List; // Danh sách nội bộ của luồng
    DatagramSocket sv; // Socket Datagram của server

    // Constructor khởi tạo danh sách và socket
    public Assignment_1_Poping_thread(DatagramSocket socket, Queue<DatagramPacket> list) {
        sv = socket;
        Shared_List = list;
        Local_List = new ArrayList<>(); // Danh sách nội bộ được khởi tạo
    }

    // Phương thức run thực thi logic xử lý gói dữ liệu
    public void run() {
        DatagramPacket p = null;

        while (true) {
            // Kiểm tra nếu danh sách chia sẻ không rỗng
            if (!Shared_List.isEmpty()) {
                // Lấy gói dữ liệu từ danh sách chia sẻ
                p = Shared_List.remove();

                InetAddress addcl = p.getAddress(); // Lấy địa chỉ IP của client
                int portcl = p.getPort(); // Lấy cổng của client
                String data = new String(p.getData()).trim(); // Chuyển dữ liệu thành chuỗi và loại bỏ khoảng trắng
                data = data.toUpperCase(); // Chuyển dữ liệu thành chữ hoa

                byte buff[] = data.getBytes(); // Chuyển dữ liệu chữ hoa thành mảng byte
                DatagramPacket q = new DatagramPacket(buff, buff.length, addcl, portcl); // Tạo gói dữ liệu phản hồi
                try {
                    sv.send(q); // Gửi gói dữ liệu phản hồi tới client
                } catch (IOException ex) {
                    Logger.getLogger(Assignment_1_Poping_thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
