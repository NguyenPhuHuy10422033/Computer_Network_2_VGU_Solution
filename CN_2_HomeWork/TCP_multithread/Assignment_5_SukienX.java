/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_multithread;

import java.util.Vector;

/**
 * Lớp đại diện cho sự kiện X trong chương trình. Lớp này có nhiệm vụ quản lý các đối tượng lắng nghe và kích hoạt sự kiện.
 * Tác giả: dinhhuy
 */
public class Assignment_5_SukienX extends Thread {
    Vector a; // Danh sách lưu trữ các đối tượng lắng nghe sự kiện X

    // Constructor khởi tạo danh sách
    public Assignment_5_SukienX() {
        a = new Vector();
    }

    // Phương thức gửi thông tin sự kiện tới các đối tượng lắng nghe
    void send_information(boolean information) {
        // Duyệt qua danh sách các đối tượng lắng nghe và gọi hàm xử lý sự kiện
        for (int i = 0; i < a.size(); i++) {
            giaodien_sukien_X t = (giaodien_sukien_X) a.elementAt(i);
            t.ham_sukien_X(); // Gọi phương thức xử lý sự kiện từ đối tượng
        }
    }

    // Phương thức thêm một đối tượng lắng nghe vào danh sách
    void add_Listener_object(giaodien_sukien_X t) {
        a.add(t); // Thêm đối tượng lắng nghe vào danh sách
    }

    // Phương thức run thực thi logic sự kiện X
    public void run() {
        boolean flag = false;
        try {
            // Tạm dừng luồng trong 300.000ms (5 phút)
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            // Xử lý ngoại lệ nếu luồng bị gián đoạn
        }
        flag = true;
        send_information(flag); // Gửi thông tin sự kiện đến các đối tượng lắng nghe
    }
}
