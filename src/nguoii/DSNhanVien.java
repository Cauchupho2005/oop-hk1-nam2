package nguoii;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import nguoii.NhanVien;

public class DSNhanVien {
    int n;
    static Scanner reader = new Scanner(System.in);
    static NhanVien dsnv[];

    public DSNhanVien() {
        this.n = 0;
        dsnv = new NhanVien[0];
    }

    public DSNhanVien(NhanVien[] nhanvien, int n) {
        this.n = n;
        dsnv = new NhanVien[n];
        for (int i = 0; i < n; i++) {
            dsnv[i] = nhanvien[i];
        }
    }

    public void input() {
        try{
            File myfile = new File("data\\inputNhanVien.input");
            Scanner f = new Scanner(myfile);
            while (f.hasNextLine()){
                // file có dạng: Nguyen Van A, Nam, Q5, Phuc vu, 30000, ABC
                String s = f.nextLine();
                String[] mangs = s.split(", ");
                dsnv = Arrays.copyOf(dsnv, n + 1); // Tăng kích thước mảng
                dsnv[n] = new NhanVien(mangs[0], mangs[1], mangs[2], mangs[3], Double.parseDouble(mangs[4]), mangs[5]); 
                this.n++;
            }
            f.close();
        } catch (Exception e){
            System.out.println("Lỗi không chạy được!");
        }
    }

    public void them() {
        System.out.print("Nhập mã: ");
        String ma = reader.nextLine();
        System.out.print("Nhập tên: ");
        String ten = reader.nextLine();
        System.out.print("Nhập giới tính: ");
        String gioitinh = reader.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String diachi = reader.nextLine();
        System.out.print("Nhập công việc: ");
        String congviec = reader.nextLine();
        System.out.print("Nhập tiền công: ");
        double tienCong = reader.nextDouble();
        reader.nextLine();
        NhanVien nv = new NhanVien(ten, gioitinh, diachi, congviec, tienCong, ma);
        dsnv = Arrays.copyOf(dsnv, n + 1);
        dsnv[n++] = nv;

    }
    //ma, ten, gioi tinh, dia chi, cong viec, tien cong
    public void xuat() {
        // Kiểm tra mảng dsnv và số lượng nhân viên
        if (dsnv == null || n == 0) {
            System.out.println("Danh sách nhân viên rỗng");
            return;
        }
        System.out.println("Danh sách nhân viên");

        System.out.printf("%-15s %-30s %-15s %-30s %-20s %-20s%n",
                "Mã", "Tên", "Giới Tính", "Địa chỉ", "Công Việc", "Tiền Công");
        System.out.println("===================================================================================================================");
       
        for (int i = 0; i < n; i++) {
            System.out.printf("%-15s %-30s %-15s %-30s %-20s %-20.2f%n",
                    dsnv[i].ma, dsnv[i].ten, dsnv[i].gioitinh, dsnv[i].diachi, dsnv[i].congviec, dsnv[i].tienCong);
        }
    }
    

    public void sua() {
        boolean ktma = false;
        // Nhập mã nhân viên cần sửa
        String masua = "";
        while (!ktma) {
            System.out.print("Nhập mã nhân viên muốn sửa: ");
            masua = reader.nextLine();

            for (int i = 0; i < n; i++) {
                if (dsnv[i].getma().equals(masua)) {
                    ktma = true; // Mã tồn tại trong danh sách
                    break;
                }
            }

            if (!ktma) {
                System.out.println("Mã không có trong dánh sách! Nhập lại!");
            }
        }

        // Nhập số lượng thuộc tính muốn sửa
        System.out.print("Nhập số lần sửa thuộc tính: ");
        int soluongthuoctinh = reader.nextInt();
        reader.nextLine(); // Đọc bỏ dòng thừa

        String[] thuoctinh = { "Ma", "Ten", "Gioi Tinh", "Dia Chi", "Cong Viec", "Tien Cong" };
        while (soluongthuoctinh > 0) {
            boolean kt = false;
            String tenthuoctinh = "";

            // Nhập tên thuộc tính
            while (!kt) {
                System.out.print("Nhập tên thuộc tính muốn sửa (Viết hoa chử cái đầu, viết không dấu và có khoẳng trắng): ");
                tenthuoctinh = reader.nextLine();
                // Kiểm tra tên thuộc tính có hợp lệ không
                for (String t : thuoctinh) {
                    if (t.equals(tenthuoctinh)) {
                        kt = true; // Thuộc tính hợp lệ
                        break;
                    }
                }
                if (!kt) {
                    System.out.println("Tên thuộc tính không chính xác! Vui lòng nhập lại");
                }
            }
            // Thay đổi giá trị thuộc tính
            for (int i = 0; i < n; i++) {
                if (dsnv[i].getma().equals(masua)) {
                    System.out.print("Nhập giá trị muốn sửa: " + tenthuoctinh + "': ");
                    String giatriMoi = reader.nextLine();

                    switch (tenthuoctinh) {
                        case "Ma":
                            dsnv[i].setma(giatriMoi);
                            break;
                        case "Ten":
                            dsnv[i].setten(giatriMoi);
                            break;
                        case "Gioi Tinh":
                            dsnv[i].setgioitinh(giatriMoi);
                            break;
                        case "Dia Chi":
                            dsnv[i].setdiachi(giatriMoi);
                            break;
                        case "Cong Viec":
                            dsnv[i].setcongviec(giatriMoi);
                            break;
                        case "Tien Cong":
                            dsnv[i].settienCong(Double.parseDouble(giatriMoi));
                            break;
                        default:
                            System.out.println("Không thể sửa thuộc tính này");
                            break;
                    }
                    break;
                }
            }
            soluongthuoctinh--;
        }

        System.out.println("Sửa thông tin thành công");
    }
    public void xoa(){
        System.out.println("Nhập mã nhân viên muốn xóa: ");
        String maxoa = reader.nextLine();
        for(int i=0; i<n ; i++){
            if (dsnv[i].getma().equals(maxoa)){
                for (int j = i; j<n-1; j++){
                    dsnv[j] = dsnv[j + 1];
                }
                break;
            }
        }
        dsnv = Arrays.copyOf(dsnv, n-1);
    }
    public void timkiem(){
        boolean ktt=false;
        
        String[] thuoctinh = { "Ma", "Ten", "Gioi Tinh", "Dia Chi", "Cong Viec", "Tien Cong" };
        String tenthuoctinh="";
        while (!(ktt==true)) {
            System.out.print("Nhập tên thuộc tính muốn sửa (Viết hoa chử cái đầu, viết không dấu và có khoẳng trắng): ");
            tenthuoctinh = reader.nextLine();
            // Kiểm tra tên thuộc tính có hợp lệ không
            for (String t : thuoctinh) {
                if (t.equals(tenthuoctinh)) {
                    ktt = true; // Thuộc tính hợp lệ
                    break;
                }
            }
            if (!ktt) {
                System.out.println("Tên thuộc tính không hợp lệ! Vui lòng nhập lại");
            }
        }
        System.out.print("Nhập giá trị cần tìm kiếm " + tenthuoctinh + ": ");
        String giatriCanTim = reader.nextLine();

        int e =0 ;
        System.out.println("Sau đây là danh sách đã tìm kiếm được");
        for (int i = 0; i < n; i++) {
            boolean khop = false;
            // So sánh thuộc tính tương ứng
            switch (tenthuoctinh) {
                case "Ma":
                    if (dsnv[i].getma().equals(giatriCanTim)) khop = true;
                    break;
                case "Ten":
                    if (dsnv[i].getten().equalsIgnoreCase(giatriCanTim)) khop = true;
                    break;
                case "Gioi Tinh":
                    if (dsnv[i].getgioitinh().equalsIgnoreCase(giatriCanTim)) khop = true;
                    break;
                case "Dia Chi":
                    if (dsnv[i].getdiachi().contains(giatriCanTim)) khop = true;
                    break;
                case "Cong Viec":
                    if (dsnv[i].getcongviec().equalsIgnoreCase(giatriCanTim)) khop = true;
                    break;
                case "Tien Cong":
                    try {
                        double tienCongTim = Double.parseDouble(giatriCanTim);
                        if (dsnv[i].gettienCong() == tienCongTim) khop = true;
                    } catch (NumberFormatException ex) {
                        System.out.println("Giá trị cần cho 'Tiền Công' phải là số!");
                        return;
                    }
                    break;
                default:
                    return;
            }
            // Hiển thị nhân viên thỏa mãn
            if (khop) {
                e++;
                System.out.printf("%d. ",e);
                dsnv[i].xuat();
            }
        }
        if (e == 0) {
            System.out.println("Không tìm thấy nhân viên nào.");
        }
    
    }
    public void luufile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data\\inputNhanVien.input", false))) {
            for (int i = 0; i < n; i++) {
                writer.write(dsnv[i].getten() + ", " + dsnv[i].getgioitinh() + ", " + dsnv[i].getdiachi() + ", " 
                        + dsnv[i].getcongviec() + ", " + dsnv[i].gettienCong() + ", " + dsnv[i].getma());
                writer.newLine(); // Thêm dòng mới sau mỗi nhân viên
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }
    
    
}
