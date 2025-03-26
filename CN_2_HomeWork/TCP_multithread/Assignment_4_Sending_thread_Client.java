/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Luồng gửi dữ liệu từ client tới server trong chương trình TCP Chat Room đa client.
 * Tác giả: HUY.TD
 */
public class Assignment_4_Sending_thread_Client extends Thread {
    private Socket s; // Biến lưu socket kết nối tới server

    // Hàm khởi tạo, nhận socket từ client
    Assignment_4_Sending_thread_Client(Socket Cl) {
        s = Cl;
    }

    // Phương thức run thực thi logic gửi dữ liệu tới server
    public void run() {
        Scanner bp = new Scanner(System.in); // Đối tượng Scanner để đọc dữ liệu từ bàn phím
        DataOutputStream gui = null; // Luồng gửi dữ liệu
        try {
            gui = new DataOutputStream(s.getOutputStream()); // Lấy luồng đầu ra từ socket
            while (true) {
                // Nhập dữ liệu từ bàn phím và gửi tới server
                String tam2 = bp.nextLine(); 
                gui.writeUTF(tam2); // Gửi dữ liệu tới server dưới dạng UTF
                if (tam2.equals("bye")) { // Nếu người dùng nhập "bye", đóng luồng và kết nối
                    gui.close();
                    s.close();
                    break; // Thoát khỏi vòng lặp
                }
            }
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu có lỗi trong quá trình gửi dữ liệu
        }
    }
}
