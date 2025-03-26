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
 * Luồng xử lý dữ liệu từ danh sách chia sẻ và gửi phản hồi tới các client khác.
 * Tác giả: HUY.TD
 */
public class Assignment_4_Poping_thread extends Thread {

    // Phương thức gửi dữ liệu tới client
    static void gui(String data, InetAddress add, int port, DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm dữ liệu
        buff = data.getBytes(); // Chuyển dữ liệu từ chuỗi thành mảng byte
        DatagramPacket p = new DatagramPacket(buff, buff.length, add, port); // Tạo gói dữ liệu
        s.send(p); // Gửi gói dữ liệu qua socket
    }

    Queue<DatagramPacket> Shared_List; // Danh sách chia sẻ giữa các luồng
    ArrayList<String> Client; // Danh sách lưu thông tin các client
    DatagramSocket sv; // Socket Datagram server

    // Constructor nhận socket và danh sách chia sẻ
    public Assignment_4_Poping_thread(DatagramSocket socket, Queue<DatagramPacket> list) {
        sv = socket; // Gán socket server
        Shared_List = list; // Gán danh sách chia sẻ
        Client = new ArrayList<>(); // Khởi tạo danh sách client
    }

    // Phương thức run thực thi logic xử lý gói dữ liệu
    public void run() {
        DatagramPacket p = null;

        while (true) {
            // Kiểm tra nếu danh sách chia sẻ không rỗng
            if (!Shared_List.isEmpty()) {
                p = Shared_List.remove(); // Lấy gói dữ liệu từ danh sách chia sẻ
                String addcl = p.getAddress().toString(); // Lấy địa chỉ client gửi dữ liệu
                int portcl = p.getPort(); // Lấy cổng client gửi dữ liệu
                String client_id = addcl.substring(1) + "_" + portcl; // Tạo định danh duy nhất cho client
                String data = new String(p.getData()).trim(); // Lấy dữ liệu từ gói và loại bỏ khoảng trắng

                int position = -1, i = 0;

                // Lặp qua danh sách client để xác định client khác
                for (; i < Client.size(); i++) {
                    String tempt_cl = Client.get(i);

                    if (!tempt_cl.equalsIgnoreCase(client_id)) {
                        // Gửi dữ liệu tới các client khác
                        String[] s = tempt_cl.split("_");
                        InetAddress add_client;
                        try {
                            add_client = InetAddress.getByName(s[0]); // Lấy địa chỉ client từ danh sách
                            int port_client = Integer.parseInt(s[1]); // Lấy cổng client
                            gui(data, add_client, port_client, sv); // Gửi dữ liệu
                        } catch (Exception ex) {
                            Logger.getLogger(Assignment_4_Poping_thread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        position = i; // Đánh dấu vị trí client hiện tại
                    }
                }

                // Nếu client chưa có trong danh sách, thêm vào
                if (position == -1) {
                    Client.add(client_id);
                } else {
                    // Nếu nhận "bye" từ client, xóa khỏi danh sách
                    if (data.equalsIgnoreCase("bye")) {
                        Client.remove(position);
                    }
                }
            }
        }
    }
}
