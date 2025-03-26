/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_multithread;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Luồng xử lý dữ liệu UDP từ danh sách chia sẻ, tính toán BSCNN (Bội số chung nhỏ nhất), và gửi phản hồi tới client.
 * Tác giả: HUY.TD
 */
public class Assignment_2_Poping_thread extends Thread {

    // Phương thức tính toán BSCNN của ba số nguyên
    static int BSCNN(int a, int b, int c) {
        int i = a;
        while (!(i % a == 0 && i % b == 0 && i % c == 0)) {
            i++;
        }
        return i;
    }

    // Phương thức gửi dữ liệu tới client
    static void gui(String data, InetAddress add, int port, DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm dữ liệu
        buff = data.getBytes(); // Chuyển chuỗi dữ liệu thành byte
        DatagramPacket p = new DatagramPacket(buff, buff.length, add, port); // Tạo gói dữ liệu
        s.send(p); // Gửi gói dữ liệu qua socket
    }

    Queue<DatagramPacket> Shared_List; // Danh sách chia sẻ (hàng đợi các gói dữ liệu)
    ArrayList<DatagramPacket> Local_List; // Danh sách lưu trữ cục bộ các gói dữ liệu
    DatagramSocket sv; // Socket Datagram của server

    // Constructor khởi tạo danh sách và socket
    public Assignment_2_Poping_thread(DatagramSocket socket, Queue<DatagramPacket> list) {
        sv = socket; // Gán socket server
        Shared_List = list; // Gán danh sách chia sẻ
        Local_List = new ArrayList<>(); // Khởi tạo danh sách cục bộ
    }

    // Phương thức run thực thi logic xử lý gói dữ liệu
    public void run() {
        DatagramPacket p = null;

        while (true) {
            // Kiểm tra nếu danh sách chia sẻ không rỗng
            if (!Shared_List.isEmpty()) {
                p = Shared_List.remove(); // Lấy gói dữ liệu ra khỏi hàng đợi
                int dem = 0; // Đếm số gói dữ liệu liên quan tới client
                int position1 = 0, number1 = 0, position2 = 0, number2 = 0;

                // Kiểm tra gói dữ liệu trong danh sách cục bộ
                for (int i = 0; i < Local_List.size(); i++) {
                    DatagramPacket q = (DatagramPacket) Local_List.get(i);

                    // So sánh địa chỉ và cổng để tìm gói dữ liệu của client
                    if (q.getAddress().equals(p.getAddress()) && q.getPort() == p.getPort()) {
                        dem++;
                        if (dem == 1) {
                            position1 = i;
                            String a = new String(q.getData()).trim();
                            number1 = Integer.parseInt(a); // Lấy số thứ nhất
                        }
                        if (dem == 2) {
                            position2 = i;
                            String b = new String(q.getData()).trim();
                            number2 = Integer.parseInt(b); // Lấy số thứ hai
                        }
                    }
                }

                if (dem == 0 || dem == 1) {
                    Local_List.add(p); // Thêm gói dữ liệu vào danh sách cục bộ nếu chưa đủ ba số
                }

                if (dem == 2) {
                    String tempt = new String(p.getData()).trim();
                    int number3 = Integer.parseInt(tempt); // Lấy số thứ ba
                    int BSC = BSCNN(number1, number2, number3); // Tính BSCNN của ba số
                    try {
                        gui(String.valueOf(BSC), p.getAddress(), p.getPort(), sv); // Gửi kết quả tới client
                    } catch (IOException ex) {
                    }
                    Local_List.remove(position1); // Xóa các gói dữ liệu đã xử lý
                    Local_List.remove(position2 - 1);
                }
            }
        }
    }
}
