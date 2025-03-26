/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Luồng xử lý giới hạn thời gian của trò chơi dự đoán số, thông báo "Time out" khi hết thời gian.
 * Tác giả: HUY.TD
 */
public class Assignment_5_time_out_thread extends Thread {

    // Phương thức gửi dữ liệu tới client
    static void gui(String data, InetAddress add, int port, DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm dữ liệu
        buff = data.getBytes(); // Chuyển dữ liệu từ chuỗi thành mảng byte
        DatagramPacket p = new DatagramPacket(buff, buff.length, add, port); // Tạo gói dữ liệu
        s.send(p); // Gửi gói dữ liệu qua socket
    }

    DatagramSocket sv; // Socket của server

    // Constructor nhận socket server
    public Assignment_5_time_out_thread(DatagramSocket socket) {
        sv = socket; // Gán socket
    }

    // Phương thức run xử lý logic thời gian trò chơi
    public void run() {
        try {
            // Dừng luồng trong 30 giây để mô phỏng giới hạn thời gian
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            // Xử lý ngoại lệ nếu luồng bị gián đoạn
        }

        // Nếu cờ trạng thái `flag` trong Poping_thread chưa được kích hoạt (không có ai thắng)
        if (Assignment_5_Poping_thread.flag == false) {
            for (int i = 0; i < Assignment_5_Poping_thread.Clients.size(); i++) {
                String tempt_cl = Assignment_5_Poping_thread.Clients.get(i); // Lấy thông tin client
                String[] s = tempt_cl.split("_"); // Tách địa chỉ và cổng từ thông tin client
                InetAddress add_client;
                try {
                    add_client = InetAddress.getByName(s[0]); // Địa chỉ client
                    int port_client = Integer.parseInt(s[1]); // Cổng client
                    gui("Time out", add_client, port_client, sv); // Gửi thông báo "Time out" tới client
                } catch (Exception ex) {
                    // Xử lý ngoại lệ trong quá trình gửi dữ liệu
                }
            }
        }
    }
}
