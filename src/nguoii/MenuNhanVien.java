package nguoii;
import nguoii.DSNhanVien;
import java.util.Scanner;
public class MenuNhanVien{
    static Scanner reader = new Scanner(System.in);
    String choose=" "; 
    DSNhanVien ds1 = new DSNhanVien();

    public void Menu(){
        ds1.input();
        while (!choose.equals("x")){
            System.out.print("\n\n\n");
            System.out.println("--Menu danh sách nhân viên--");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm nhân viên");
            System.out.println("3. Sửa nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("5. Tìm kiếm nhân viên");
            System.out.println("6. Trở lại trang trước");
            System.out.print("\tLựa chọn của bạn là: ");
            choose = reader.nextLine();
            switch (choose) {
                case "1":
                    ds1.xuat();
                    break;
                case "2":
                    ds1.them();
                    break;
                case "3":
                    ds1.sua();
                    break;
                case "4":
                    ds1.xoa();
                    break;
                case "5":
                    ds1.timkiem();
                    break;
                case "6":
                    // trở lại menu
                    ds1.luufile();
                    choose ="x";
                    break;
                case "x":
                    ds1.luufile();
                    break;
                default:
                    System.out.println("Không tải được menu");
                    break;
            }
        }
    }
}