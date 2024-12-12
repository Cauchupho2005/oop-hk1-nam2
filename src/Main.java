import service.QuanLyHoaDon;
import service.QuanLyNuoc;
import nguoii.MenuNhanVien;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuNhanVien nv = new MenuNhanVien();
        QuanLyNuoc quanLyNuoc = new QuanLyNuoc();
        quanLyNuoc.importData("src/data/nuoc.txt");
        QuanLyHoaDon quanLyHoaDon = new QuanLyHoaDon();
        quanLyHoaDon.importData("src/data/hoaDon.txt");


        while (true) {
            System.out.println("Vui lòng chọn chức năng: ");
            System.out.println("1. Quản lý nướcc");
            System.out.println("2. Quản lý hóa đơn");
            System.out.println("3. Quản lý nhân viên");
            System.out.println("4. Thoát");
            int chon = 0;
            try {
                System.out.print(" Chọn: ");
                chon = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số");
                return;
            }

            switch (chon) {
                case 1:
                    quanLyNuoc.getMenu();
                    break;
                case 2:
                    quanLyHoaDon.getMenu();
                    break;
                case 3:
                    nv.Menu();
                    break;
                case 4:
                    System.out.println("Lưu dữ liệu");
                    quanLyNuoc.exportData("src/data/nuoc.txt");
                    quanLyHoaDon.exportData("src/data/hoaDon.txt");
                    System.out.println("Kết thúc chương trình");
                    return;
                default:
                    System.out.println("Vui lòng cọn chức năng khác");
            }
        }
    }
}