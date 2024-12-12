package service;

import model.ChiTietHoaDon;
import model.HoaDon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class QuanLyHoaDon implements QuanLy<HoaDon>{
    private List<HoaDon> danhSachHoaDon;

    public QuanLyHoaDon() {
        this.danhSachHoaDon = new ArrayList<>();
    }

    @Override
    public HoaDon timKiem(String ma) {
        Optional<HoaDon> hoaDon = danhSachHoaDon.stream().filter(hd -> hd.getMaHoaDon().equals(ma)).findFirst();
        return hoaDon.orElse(null);
    }

    @Override
    public void them(HoaDon hd) {
        danhSachHoaDon.add(hd);
    }

    @Override
    public void sua(String ma, HoaDon object) {
    }

    @Override
    public void xoa(String ma) {
    }

    @Override
    public void getMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quản lý hóa đơn");
        while (true) {
            Utils.clearConsole();
            System.out.println("1. Thêm hóa đơn");
            System.out.println("2. Hiển thị danh sách hóa đơn");
            System.out.println("3. Hiển thị chi tiết hóa đơn");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng: ");
            int chon = 0;
            try {
                chon = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số");
                return;
            }
            scanner.nextLine();

            switch (chon) {
                case 1:
                    HoaDon hoaDon = nhapHoaDon();
                    them(hoaDon);
                    break;
                case 2:
                    hienThi();
                    break;
                case 3:
                    System.out.print("Nhập mã hóa đơn cần hiển thị: ");
                    String maHD = scanner.nextLine();
                    hiemThi1HoaDon(maHD);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Chọn chức năng không hợp lệ");
            }

            System.out.println("Nhấn Enter để tiếp tục...");
            scanner.nextLine();

        }
    }

    @Override
    public void hienThi() {
        System.out.println("Danh sách hóa đơn");

        System.out.println("==================================================================");
        System.out.printf("%-15s %-15s %-20s%n", "Mã hóa đơn", "Ngày bán", "Tổng tiền");
        System.out.println("------------------------------------------------------------------");

        for (HoaDon hoaDon : danhSachHoaDon) {
            System.out.printf("%-15s %-15s %,-20.2f%n",
                    hoaDon.getMaHoaDon(), hoaDon.getNgayBan(), hoaDon.getTongTien());
        }
        System.out.println("==================================================================");
    }

    public void hiemThi1HoaDon(String maHD) {
        HoaDon hoaDon = timKiem(maHD);
        if (hoaDon == null) {
            System.out.println("Không tìm thấy hóa đơn");
            return;
        }

        System.out.println("==================================================================");
        System.out.printf("Mã hóa đơn: %-10s Ngày bán: %-15s Tổng tiền: %,.2f%n",
                hoaDon.getMaHoaDon(), hoaDon.getNgayBan(), hoaDon.getTongTien());
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-15s %-10s %-15s %-15s%n", "Mã vạch", "Số lượng", "Đơn giá", "Thành tiền");

        for (ChiTietHoaDon chiTietHoaDon : hoaDon.getChiTietHoaDonList()) {
            double donGia = chiTietHoaDon.getThanhTien() / chiTietHoaDon.getSoLuong();
            System.out.printf("%-15s %-10d %,-15.2f %,-15.2f%n",
                    chiTietHoaDon.getMaVach(), chiTietHoaDon.getSoLuong(), donGia, chiTietHoaDon.getThanhTien());
        }
        System.out.println("==================================================================");
    }


    private HoaDon nhapHoaDon() {
        Scanner scanner = new Scanner(System.in);

        // Random mã hóa đơn
        String maHoaDon = "HD" + (danhSachHoaDon.size() + 1);
        LocalDate ngayBan = LocalDate.now();

        System.out.println("Thêm chi tiết hóa đơn");
        List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
        double tongTien = 0;

        while (true) {
            System.out.println("1. Thêm chi tiết hóa đơn");
            System.out.println("2. Thoát");
            System.out.print("Chọn chức năng: ");
            int chon;
            try {
                chon = scanner.nextInt();
                scanner.nextLine(); // Xử lý dòng trống sau khi nhập số
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
                scanner.nextLine(); // Bỏ qua dòng không hợp lệ
                continue;
            }

            switch (chon) {
                case 1:
                    System.out.print("Nhập mã vạch: ");
                    String maVach = scanner.nextLine();

                    if (!QuanLyNuoc.checkMaVach(maVach)) { // Kiểm tra mã vạch tồn tại
                        System.out.println("Mã vạch không tồn tại");
                        break;
                    }

                    System.out.print("Nhập số lượng: ");
                    int soLuong;
                    try {
                        soLuong = scanner.nextInt();
                        scanner.nextLine(); // Xử lý dòng trống
                    } catch (Exception e) {
                        System.out.println("Số lượng phải là số nguyên!");
                        scanner.nextLine(); // Bỏ qua dòng không hợp lệ
                        break;
                    }

                    if (!QuanLyNuoc.checkSLT(maVach, soLuong)) { // Kiểm tra số lượng tồn
                        System.out.println("Số lượng không đủ");
                        break;
                    }
                    QuanLyNuoc.updateSLT(maVach, soLuong);

                    double giaBan = QuanLyNuoc.getGiaBan(maVach);
                    double thanhTien = giaBan * soLuong;
                    tongTien += thanhTien;

                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maHoaDon, maVach, soLuong, thanhTien);
                    chiTietHoaDonList.add(chiTietHoaDon);

                    System.out.println("Thêm chi tiết hóa đơn thành công!");
                    break;
                case 2:
                    System.out.println("Kết thúc thêm chi tiết hóa đơn.");
                    return new HoaDon(maHoaDon, ngayBan, tongTien, chiTietHoaDonList);
                default:
                    System.out.println("Chọn chức năng không hợp lệ.");
            }
        }
    }

    // Import dữ liệu từ file
    public void importData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String[] info = parts[0].split(",");
                String maHoaDon = info[0];
                LocalDate ngayBan = LocalDate.parse(info[1]);
                double tongTien = Double.parseDouble(info[2]);

                List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
                if (parts.length > 1) {
                    String[] chiTietParts = parts[1].split(";");
                    for (String chiTiet : chiTietParts) {
                        String[] chiTietInfo = chiTiet.split(":");
                        String maVach = chiTietInfo[0];
                        int soLuong = Integer.parseInt(chiTietInfo[1]);
                        double thanhTien = Double.parseDouble(chiTietInfo[3]);

                        ChiTietHoaDon cthd = new ChiTietHoaDon(maHoaDon, maVach, soLuong, thanhTien);
                        chiTietHoaDonList.add(cthd);
                    }
                }

                HoaDon hoaDon = new HoaDon(maHoaDon, ngayBan, tongTien, chiTietHoaDonList);
                danhSachHoaDon.add(hoaDon);
            }
            System.out.println("Dữ liệu hóa đơn đã được nhập thành công!");
        } catch (IOException e) {
            System.out.println("Không thể đọc file: " + e.getMessage());
        }
    }

    // Export dữ liệu ra file
    public void exportData(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (HoaDon hoaDon : danhSachHoaDon) {
                StringBuilder line = new StringBuilder();
                line.append(hoaDon.getMaHoaDon()).append(",")
                        .append(hoaDon.getNgayBan()).append(",")
                        .append(hoaDon.getTongTien()).append("|");

                List<String> chiTietStrings = new ArrayList<>();
                for (ChiTietHoaDon chiTiet : hoaDon.getChiTietHoaDonList()) {
                    String chiTietString = String.format("%s:%d:%.2f:%.2f",
                            chiTiet.getMaVach(),
                            chiTiet.getSoLuong(),
                            chiTiet.getThanhTien() / chiTiet.getSoLuong(),
                            chiTiet.getThanhTien());
                    chiTietStrings.add(chiTietString);
                }
                line.append(String.join(";", chiTietStrings));

                writer.write(line.toString());
                writer.newLine();
            }
            System.out.println("Dữ liệu hóa đơn đã được lưu thành công!");
        } catch (IOException e) {
            System.out.println("Không thể ghi file: " + e.getMessage());
        }
    }
}
