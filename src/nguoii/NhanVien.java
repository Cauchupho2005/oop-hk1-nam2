package nguoii;

import java.util.Scanner;

public class NhanVien extends Nguoi {
   static Scanner reader;
   String congviec;
   String ma;
   double tienCong;

   public NhanVien(String var1, String var2, String var3, String var4, double var5, String var7) {
      super(var1, var2, var3);
      this.congviec = var4;
      this.tienCong = var5;
      this.ma = var7;
   }

   public String getcongviec() {
      return this.congviec;
   }

   public void setcongviec(String var1) {
      this.congviec = var1;
   }

   public double gettienCong() {
      return this.tienCong;
   }

   public void settienCong(double var1) {
      this.tienCong = var1;
   }

   public String getma() {
      return this.ma;
   }

   public void setma(String var1) {
      this.ma = var1;
   }

   public void noiChuyen() {
      System.out.println("P.H.A.M xin chao");
   }
   @Override
   public void xuat() {
      System.out.printf("%-15s %-30s %-15s %-30s %-20s %-20s%n",
                "Mã", "Tên", "Giới Tính", "Địa Chỉ", "Công Việc", "Tiềng Công");
      System.out.println("===================================================================================================================");
      System.out.printf("%-15s %-30s %-15s %-30s %-20s %-20.2f%n",ma,ten,gioitinh,diachi,congviec,tienCong);
   }

   static {
      reader = new Scanner(System.in);
   }
}
