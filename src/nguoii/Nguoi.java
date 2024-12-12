package nguoii;

import java.util.Scanner;
public abstract class Nguoi{
    String ten;
    String diachi;
    String gioitinh;
    static Scanner reader = new Scanner(System.in);
    // hàm thiết lập constructor
    public Nguoi(){
        this.ten = "";
        this.diachi="";
        this.gioitinh="";
    }
    public Nguoi(String ten, String gioitinh, String diachi){
        this.ten = ten;
        this.diachi = diachi;
        this.gioitinh = gioitinh; 
    }
    public String getten() {
        return ten;
    }
    public void setten(String ten) {
        this.ten = ten;
    }
    public String getgioitinh() {
        return gioitinh;
    }
    public void setgioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    public String getdiachi() {
        return diachi;
    }
    public void setdiachi(String diachi) {
        this.diachi = diachi;
    }
    public void xuat(){
        System.out.println("Tên: "+ten);
        System.out.println("Địa chỉ: "+diachi);
        System.out.println("Giới tính: "+gioitinh);
    }
    //phương thức trừu tượng
    public abstract void noiChuyen();
}