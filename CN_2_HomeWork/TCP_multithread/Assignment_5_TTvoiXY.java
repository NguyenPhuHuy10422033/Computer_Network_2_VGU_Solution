/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Luồng xử lý giao tiếp giữa client và server trong chương trình TCP đa luồng với các sự kiện X và Y.
 * Tác giả: dinhhuy
 */
public class Assignment_5_TTvoiXY extends Thread implements giaodien_sukien_X, giaodien_sukien_Y {
    DataInputStream in; // Luồng đọc dữ liệu từ client
    DataOutputStream out; // Luồng ghi dữ liệu tới client
    int R; // Số ngẫu nhiên được tạo bởi server
    static volatile boolean flag = false; // Biến đồng bộ để kiểm soát trạng thái giữa các luồng
    boolean thang; // Trạng thái thắng của client hiện tại

    // Constructor nhận socket kết nối và số ngẫu nhiên
    public Assignment_5_TTvoiXY(Socket sv, int r) {
        R = r; // Lưu số ngẫu nhiên
        thang = false; // Ban đầu trạng thái thắng là false
        try {
            in = new DataInputStream(sv.getInputStream()); // Lấy luồng đầu vào
            out = new DataOutputStream(sv.getOutputStream()); // Lấy luồng đầu ra
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu xảy ra lỗi
        }
    }

    // Phương thức run thực thi logic chính
    public void run() {
        try {
            while (true) {
                // Nhận số từ client
                int x = in.readInt();

                // Kiểm tra và phản hồi tới client
                if (x > R) out.writeUTF("lon hon"); // Số nhập lớn hơn số ngẫu nhiên
                if (x < R) out.writeUTF("nho hon"); // Số nhập nhỏ hơn số ngẫu nhiên
                if (x == R && flag == false) {
                    // Nếu số nhập trùng khớp và chưa ai thắng
                    flag = true; // Đặt cờ flag thành true
                    thang = true; // Đặt trạng thái thắng cho client hiện tại
                    break; // Kết thúc vòng lặp
                }
            }
        } catch (IOException e) {
            // Xử lý ngoại lệ nếu xảy ra lỗi
        }
    }

    // Xử lý sự kiện X khi hết thời gian
    @Override
    public void ham_sukien_X() {
        try {
            out.writeUTF("het gio"); // Gửi thông báo hết thời gian tới client
            in.close(); // Đóng luồng đầu vào
            this.interrupt(); // Ngắt luồng hiện tại
        } catch (IOException ex) {
            // Xử lý ngoại lệ
        }
    }

    // Xử lý sự kiện Y để thông báo kết quả
    @Override
    public void ham_sukien_Y() {
        try {
            if (thang == false) out.writeUTF("you lose"); // Nếu không thắng thì thông báo thua
            else out.writeUTF("you win"); // Nếu thắng thì thông báo chiến thắng
            this.interrupt(); // Ngắt luồng hiện tại
        } catch (IOException ex) {
            // Xử lý ngoại lệ
        }
    }
}
