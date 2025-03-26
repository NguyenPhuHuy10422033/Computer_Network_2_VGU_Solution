/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_multithread;

import java.util.Vector;

/**
 * Lớp đại diện cho sự kiện Y trong chương trình. Lớp này quản lý các đối tượng lắng nghe và kích hoạt sự kiện Y.
 * Tác giả: dinhhuy
 */
public class Assignment_5_SukienY extends Thread {

    Vector a; // Danh sách lưu trữ các đối tượng lắng nghe sự kiện Y

    // Constructor khởi tạo danh sách
    public Assignment_5_SukienY() {
        a = new Vector();
    }

    // Phương thức gửi thông tin sự kiện tới các đối tượng lắng nghe
    void send_information(boolean information) {
        // Duyệt qua danh sách các đối tượng lắng nghe và gọi hàm xử lý sự kiện
        for (int i = 0; i < a.size(); i++) {
            giaodien_sukien_Y t = (giaodien_sukien_Y) a.elementAt(i);
            t.ham_sukien_Y(); // Gọi phương thức xử lý sự kiện từ đối tượng
        }
    }

    // Phương thức thêm một đối tượng lắng nghe vào danh sách
    void add_Listener_object(giaodien_sukien_Y t) {
        a.add(t); // Thêm đối tượng lắng nghe vào danh sách
    }

    // Phương thức run thực thi logic sự kiện Y
    public void run() {
        // Đợi đến khi cờ flag từ Assignment_5_TTvoiXY được đặt thành true
        while (Assignment_5_TTvoiXY.flag == false);

        // Kích hoạt sự kiện và gửi thông tin tới các đối tượng lắng nghe
        send_information(true); // Thông báo sự kiện đã xảy ra
    }
}
