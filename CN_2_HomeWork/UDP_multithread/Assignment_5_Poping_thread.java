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
 * Luồng xử lý dự đoán số đúng từ client, xử lý thắng/thua và gửi phản hồi.
 * Tác giả: HUY.TD
 */
public class Assignment_5_Poping_thread extends Thread {

    // Phương thức gửi dữ liệu tới client
    static void gui(String data, InetAddress add, int port, DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm dữ liệu
        buff = data.getBytes(); // Chuyển chuỗi dữ liệu thành mảng byte
        DatagramPacket p = new DatagramPacket(buff, buff.length, add, port); // Tạo gói dữ liệu
        s.send(p); // Gửi gói dữ liệu qua socket
    }

    Queue<DatagramPacket> Shared_List; // Hàng đợi chứa các gói dữ liệu nhận từ client
    static ArrayList<String> Clients; // Danh sách lưu các client đã kết nối
    DatagramSocket sv; // Socket server
    int R; // Số bí mật mà client cần dự đoán
    static volatile boolean flag = false; // Cờ kiểm tra trạng thái dự đoán đúng

    // Constructor nhận socket server, số bí mật và hàng đợi chia sẻ
    public Assignment_5_Poping_thread(DatagramSocket socket, int r, Queue<DatagramPacket> list) {
        sv = socket; // Gán socket server
        R = r; // Gán số bí mật
        Shared_List = list; // Gán danh sách chia sẻ
        Clients = new ArrayList<>(); // Khởi tạo danh sách client
    }

    // Cập nhật cờ trạng thái khi có client dự đoán đúng
    public synchronized void update_flag() {
        flag = true;
    }

    // Phương thức run thực hiện logic xử lý gói dữ liệu
    public void run() {
        DatagramPacket p = null;

        // Chạy vòng lặp đến khi một client dự đoán đúng
        while (flag == false) {
            if (!Shared_List.isEmpty()) {
                p = Shared_List.remove(); // Lấy gói dữ liệu từ hàng đợi
                String addcl = p.getAddress().toString(); // Địa chỉ client
                int portcl = p.getPort(); // Cổng client
                String client_id = addcl.substring(1) + "_" + portcl; // Tạo định danh duy nhất cho client
                String data = new String(p.getData()).trim(); // Dữ liệu từ client
                int number = Integer.parseInt(data); // Chuyển dữ liệu dự đoán thành số nguyên

                // Kiểm tra dự đoán
                if (number == R) {
                    update_flag(); // Đặt cờ trạng thái thành true

                    // Gửi phản hồi "win/lose" tới tất cả các client
                    int position = -1, i = 0;
                    for (; i < Clients.size(); i++) {
                        String tempt_cl = Clients.get(i);
                        if (!tempt_cl.equalsIgnoreCase(client_id)) {
                            // Gửi thông báo "You lose" tới các client khác
                            String[] s = tempt_cl.split("_");
                            InetAddress add_client;
                            try {
                                add_client = InetAddress.getByName(s[0]);
                                int port_client = Integer.parseInt(s[1]);
                                gui("You lose", add_client, port_client, sv);
                            } catch (Exception ex) {
                                // Xử lý lỗi trong quá trình gửi dữ liệu
                            }
                        } else {
                            position = i;
                            try {
                                gui("You win", p.getAddress(), portcl, sv); // Gửi "You win" tới client đúng
                            } catch (IOException ex) {
                                Logger.getLogger(Assignment_5_Poping_thread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                    // Xử lý trường hợp client chưa trong danh sách
                    if (position == -1) {
                        try {
                            gui("You win", p.getAddress(), portcl, sv);
                        } catch (IOException ex) {
                            Logger.getLogger(Assignment_5_Poping_thread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    try {
                        // Gửi yêu cầu "dự đoán lại" nếu số chưa đúng
                        gui("Please predict again", p.getAddress(), portcl, sv);
                    } catch (IOException ex) {
                        Logger.getLogger(Assignment_5_Poping_thread.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Thêm client vào danh sách nếu chưa có
                    int position = -1, i = 0;
                    for (; i < Clients.size(); i++) {
                        String tempt_cl = Clients.get(i);
                        if (tempt_cl.equalsIgnoreCase(client_id)) {
                            position = i;
                        }
                    }
                    if (position == -1) {
                        Clients.add(client_id);
                    }
                }
            }
        }
    }
}
