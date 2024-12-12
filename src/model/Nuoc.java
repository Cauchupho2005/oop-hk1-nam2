package model;

public abstract class Nuoc {
    private String maVach;
    private String ten;
    private double giaBan;
    private int soLuongTon;

    public Nuoc() {
    }

    public Nuoc(String maVach, String ten, double giaBan, int soLuongTon) {
        this.maVach = maVach;
        this.ten = ten;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
    }

    public String getMaVach() {
        return maVach;
    }

    public void setMaVach(String maVach) {
        this.maVach = maVach;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
}
