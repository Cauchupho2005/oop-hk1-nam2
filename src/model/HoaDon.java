package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HoaDon {
    private String maHoaDon;
    private LocalDate ngayBan;
    private double tongTien;
    private List<ChiTietHoaDon> chiTietHoaDonList;

    public HoaDon() {
        this.chiTietHoaDonList = new ArrayList<>();
    }

    public HoaDon(String maHoaDon, LocalDate ngayBan, double tongTien) {
        this.maHoaDon = maHoaDon;
        this.ngayBan = ngayBan;
        this.tongTien = tongTien;
        this.chiTietHoaDonList = new ArrayList<>();
    }

    public HoaDon(String maHoaDon, LocalDate ngayBan, double tongTien, List<ChiTietHoaDon> chiTietHoaDonList) {
        this.maHoaDon = maHoaDon;
        this.ngayBan = ngayBan;
        this.tongTien = tongTien;
        this.chiTietHoaDonList = chiTietHoaDonList;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public LocalDate getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(LocalDate ngayBan) {
        this.ngayBan = ngayBan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public List<ChiTietHoaDon> getChiTietHoaDonList() {
        return chiTietHoaDonList;
    }

    public void addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        this.chiTietHoaDonList.add(chiTietHoaDon);
    }

    public void removeChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        this.chiTietHoaDonList.remove(chiTietHoaDon);
    }
}
